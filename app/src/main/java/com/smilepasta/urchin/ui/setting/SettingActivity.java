package com.smilepasta.urchin.ui.setting;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.TextView;

import com.smilepasta.urchin.Constant;
import com.smilepasta.urchin.R;
import com.smilepasta.urchin.bean.req.VersionReqBean;
import com.smilepasta.urchin.bean.resp.VersionRespBean;
import com.smilepasta.urchin.http.exception.ExceptionCodeUtil;
import com.smilepasta.urchin.presenter.implPresenter.VersionInfoPresenterImpl;
import com.smilepasta.urchin.presenter.implView.IVersionInfoView;
import com.smilepasta.urchin.ui.common.WebViewActivity;
import com.smilepasta.urchin.ui.common.basic.TextBarActivity;
import com.smilepasta.urchin.utils.AppUtil;
import com.smilepasta.urchin.utils.CleanDataUtil;
import com.smilepasta.urchin.utils.DialogUtil;
import com.smilepasta.urchin.utils.PreUtil;
import com.smilepasta.urchin.utils.StringUtil;
import com.smilepasta.urchin.utils.ToastUtil;
import com.smilepasta.urchin.utils.UpgradeManager;
import com.smilepasta.urchin.utils.permission.Permission;

public class SettingActivity extends TextBarActivity implements IVersionInfoView {

    private TextView cacheSizeTextView;
    private VersionInfoPresenterImpl versionInfoPresenter;

    public final static int RESPONSE_CODE_SUCCESS = 200; //请求成功
    public final static int RESPONSE_CODE_NEW = 300;//当前已是最新的版本，不需要更新了
    private VersionRespBean.VersionDetailBean versionDetailBean;
    private boolean isFindNewVersion = false;

    @Override
    protected void menuIconAction(Bundle bundle) {
    }

    private void initView() {
        cacheSizeTextView = findViewById(R.id.tv_cache_size);
        findViewById(R.id.ll_clean_cache).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (StringUtil.isNotEmpty(cacheSizeTextView.getText().toString().trim())) {
                    cleanCache();
                }
            }
        });
        findViewById(R.id.tv_language_switch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SettingContainerActivity.start(SettingActivity.this, SettingContainerActivity.SETTING_KEY_VALUE_PROTOBUF);
            }
        });
        findViewById(R.id.tv_about_me).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebViewActivity.start(SettingActivity.this, Constant.ABOUT_ME_URL);
            }
        });
        findViewById(R.id.tv_version_upgrade).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doGetVersionInfo();
            }
        });
    }

    private void doGetVersionInfo() {
        if (versionInfoPresenter == null) {
            versionInfoPresenter = new VersionInfoPresenterImpl(this);
        }
        VersionReqBean versionReqBean = new VersionReqBean();
        versionReqBean.setLanguage(PreUtil.getPrefString(this, Constant.LANGUAGE_TYPE, Constant.LANGUAGE_ZH));
        versionReqBean.setVersionCode(AppUtil.getVersionCode(this));
        versionInfoPresenter.getVersionInfo(versionReqBean);
    }

    @Override
    public void getVersionInfo(VersionRespBean versionRespBean) {
        if (versionRespBean != null) {
            if (versionRespBean.getCode() == RESPONSE_CODE_SUCCESS) {
                versionDetailBean = versionRespBean.getData();
                showQueryUpgradeDialog();
            } else if (versionRespBean.getCode() == RESPONSE_CODE_NEW) {
                ToastUtil.show(this, getString(R.string.tips_7));
            }
        }

    }

    /**
     * 显示询问升级的对话框
     */
    private void showQueryUpgradeDialog() {
        //判断是否强制升级
        if (versionDetailBean.getIsFocreUpdate() == 1) {
            //强制,弹出没有取消的dialog
            DialogUtil.queryConfirm(this
                    , getString(R.string.tips_8)
                    , getString(R.string.tips_9)
                    , new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            DialogUtil.cancel();
                            checkUpgradePermission();
                        }
                    });
        } else {
            //非强制
            DialogUtil.query(this
                    , getString(R.string.tips_8)
                    , getString(R.string.tips_10)
                    , getString(R.string.tips_11)
                    , getString(R.string.tips_12)
                    , new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            DialogUtil.cancel();
                            checkUpgradePermission();
                        }
                    });
        }
    }

    /**
     * 检查升级的权限
     */
    private void checkUpgradePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(this, Permission.STORAGE, Permission.REQUEST_STORAGE);
        } else {
            downloadApk();
        }
    }

    private void downloadApk() {
        new UpgradeManager(this, versionDetailBean).downloadApk();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == Permission.REQUEST_STORAGE) {
            checkStoragePermission(grantResults);
        }
    }

    /**
     * 检查存储权限
     *
     * @param grantResults
     */
    private void checkStoragePermission(int[] grantResults) {
        if (grantResults.length == Permission.STORAGE.length) {
            if (grantResults[0] == 0) {
                //用户同意了权限
                downloadApk();
            } else {
                //用户没有同意读写存储空间，那就劝用户同意这个权限
                DialogUtil.query(this
                        , getString(R.string.hint)
                        , getString(R.string.tips_14)
                        , getString(R.string.cancel)
                        , getString(R.string.go_permission_setting)
                        , new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                DialogUtil.cancel();
                                isFindNewVersion = true;

                                //引导用户至设置页手动授权
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                Uri uri = Uri.fromParts("package", getApplicationContext().getPackageName(), null);
                                intent.setData(uri);
                                startActivity(intent);
                            }
                        }
                        , new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                DialogUtil.cancel();
                            }
                        });
            }
        }
    }


    @Override
    public void showProgressDialog() {
        DialogUtil.progress(this);
    }

    @Override
    public void hideProgressDialog() {
        DialogUtil.cancel();
    }

    @Override
    public void showError(int code, String error) {
        showRetryDialog(ExceptionCodeUtil.convertMsg(SettingActivity.this, code, error), new IRetryListener() {
            @Override
            public void retry() {
                doGetVersionInfo();
            }
        });
    }

    private void cleanCache() {
        DialogUtil.progress(this);
        CleanDataUtil.clearAllCache(this);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                //显示dialog
                DialogUtil.cancel();
                ToastUtil.show(SettingActivity.this, getString(R.string.clean_success));
                setCacheSize();
            }
        }, 3000);
    }

    private void initData() {
        setCacheSize();
    }

    private void setCacheSize() {
        try {
            cacheSizeTextView.setText(CleanDataUtil.getTotalCacheSize(this));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDefaultConfig(R.layout.activity_setting, getString(R.string.setting));

        initView();
        initData();
    }

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, SettingActivity.class);
        activity.startActivity(intent);
    }


}
