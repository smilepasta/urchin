package com.smilepasta.urchin.ui.common.basic;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.smilepasta.urchin.Constant;
import com.smilepasta.urchin.R;
import com.smilepasta.urchin.utils.DialogUtil;
import com.smilepasta.urchin.utils.LogUtil;
import com.smilepasta.urchin.utils.MyContextWrapper;
import com.smilepasta.urchin.utils.PreUtil;
import com.smilepasta.urchin.utils.UIUtil;

import java.util.Locale;

/**
 * Author: huangxiaoming
 * Date: 2018/11/20
 * Desc:
 * Version: 1.0
 */
public class BasicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        String currentLanguage = PreUtil.getPrefString(newBase, Constant.LANGUAGE_TYPE, Constant.LANGUAGE_ZH);
        Locale newLocale;
        if (currentLanguage.equals(Constant.LANGUAGE_ZH)) {
            newLocale = Locale.CHINA;
        } else {
            newLocale = Locale.ENGLISH;
        }
        Context context = MyContextWrapper.wrap(newBase, newLocale);
        super.attachBaseContext(context);
    }


    /**
     * 重试
     */
    public interface IRetryListener {
        void retry();
    }

    public interface IConfirmSubmitLisener {
        void confirmSubmit();
    }

    /**
     * 当页面请求失败的时候，提示用户是否重试
     */
    public void showRetryDialog(String error,IRetryListener retryListener) {

        DialogUtil.query(this
                , getString(R.string.hint)
                , error
                , getString(R.string.cancel)
                , getString(R.string.retry)
                , new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DialogUtil.cancel();
                        //重试
                        if (retryListener != null) {
                            retryListener.retry();
                        }
                    }
                }
                , new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DialogUtil.cancel();
                        finish();
                    }
                });
    }

    /**
     * 当用户提交请求时，二次确认
     */
    public void showConfirmSubmitDialog(IConfirmSubmitLisener confirmSubmitLisener) {
        DialogUtil.query(this
                , UIUtil.getString(R.string.hint)
                , UIUtil.getString(R.string.tips_26)
                , UIUtil.getString(R.string.cancel)
                , UIUtil.getString(R.string.confirm)
                , new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DialogUtil.cancel();
                        //确认
                        if (confirmSubmitLisener != null) {
                            confirmSubmitLisener.confirmSubmit();
                        }
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
