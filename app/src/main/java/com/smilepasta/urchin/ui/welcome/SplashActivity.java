package com.smilepasta.urchin.ui.welcome;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.smilepasta.urchin.R;
import com.smilepasta.urchin.ui.main.MainActivity;
import com.smilepasta.urchin.utils.AppUtil;
import com.smilepasta.urchin.utils.DialogUtil;
import com.smilepasta.urchin.utils.LogUtil;
import com.smilepasta.urchin.utils.NetworkUtil;
import com.smilepasta.urchin.utils.UIUtil;

/**
 * Author: huangxiaoming
 * Date: 2018/11/20
 * Desc: 引导页
 * Version: 1.0
 */
public class SplashActivity extends AppCompatActivity {
    private static final int ANIMATION_DURATION = 2000;
    private static final float SCALE_END = 1.13F;

    private NetworkBroadcastReceiver networkChangeReceiver;

    ImageView bgImageView;

    private void checkNetwork() {
        if (!NetworkUtil.isNetWorkAvailable(this)) {
            showNetworkErrorDialog();
        }
    }

    private void showNetworkErrorDialog() {
        DialogUtil.query(this
                , UIUtil.getString(this, R.string.hint)
                , getString(R.string.tips_1)
                , new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AppUtil.startSetting(SplashActivity.this, "Wifi");
                    }
                }
                , new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

    class NetworkBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo mobileInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
                NetworkInfo wifiInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                //如果无网络连接activeInfo为null
                NetworkInfo activeInfo = manager.getActiveNetworkInfo();
                if (DialogUtil.dialog != null && DialogUtil.dialog.isShowing()) {
                    DialogUtil.dialog.cancel();
                }
                if (wifiInfo.isConnected()) {
                    // wifi 网络
                    LogUtil.defLog("Network=" + "wifi network");
                    nextActivity();
                } else if (mobileInfo.isConnected()) {
                    // 手机网络
                    LogUtil.defLog("Network=" + "mobile network");
                    nextActivity();
                } else if (null == activeInfo) {
                    // 没有网络
                    LogUtil.defLog("Network=" + "not network");
                    showNetworkErrorDialog();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void nextActivity() {
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
    }

    private void animateImage() {
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(bgImageView, View.SCALE_X, 1f, SCALE_END);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(bgImageView, View.SCALE_Y, 1f, SCALE_END);

        AnimatorSet set = new AnimatorSet();
        set.setDuration(ANIMATION_DURATION).play(animatorX).with(animatorY);
        set.start();

        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
                networkChangeReceiver = new NetworkBroadcastReceiver();
                registerReceiver(networkChangeReceiver, intentFilter);

                checkNetwork();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkChangeReceiver);
    }

    private void initView() {

        bgImageView = findViewById(R.id.iv_splash_bg);
        AppUtil.hideStatusNavigationBar(this);
        animateImage();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initView();
    }

}
