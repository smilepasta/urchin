package com.smilepasta.urchin.ui.common.basic;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.smilepasta.urchin.R;
import com.smilepasta.urchin.utils.DialogUtil;
import com.smilepasta.urchin.utils.UIUtil;

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
    public void showRetryDialog(IRetryListener retryListener) {
        DialogUtil.query(this
                , UIUtil.getString(this, R.string.hint)
                , UIUtil.getString(this, R.string.tips_5)
                , UIUtil.getString(this, R.string.cancel)
                , UIUtil.getString(this, R.string.retry)
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


}
