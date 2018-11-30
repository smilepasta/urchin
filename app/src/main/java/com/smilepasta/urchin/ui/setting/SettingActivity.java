package com.smilepasta.urchin.ui.setting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.smilepasta.urchin.Constant;
import com.smilepasta.urchin.R;
import com.smilepasta.urchin.ui.common.WebViewActivity;
import com.smilepasta.urchin.ui.common.basic.TextBarActivity;
import com.smilepasta.urchin.utils.UIUtil;

public class SettingActivity extends TextBarActivity {


    @Override
    protected void menuIconAction(Bundle bundle) {
    }

    private void initView() {
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
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDefaultConfig(R.layout.activity_setting, UIUtil.getString(this, R.string.setting));

        initView();
    }


    public static void start(Activity activity) {
        Intent intent = new Intent(activity, SettingActivity.class);
        activity.startActivity(intent);
    }

}
