package com.smilepasta.urchin.ui.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.smilepasta.urchin.R;
import com.smilepasta.urchin.ui.common.basic.TextBarActivity;

public class TryHomeActivity extends TextBarActivity {

    @Override
    protected void menuIconAction(Bundle bundle) {

    }

    public static void start(Activity activity) {
        activity.startActivity(new Intent(activity, TryHomeActivity.class));
    }

    private void initView() {
        findViewById(R.id.btn_try).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNextActivity(TryDemoActivity.DEMO_KEY_VALUE_PROTOBUF);
            }
        });
        findViewById(R.id.btn_audio).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNextActivity(TryDemoActivity.DEMO_KEY_VALUE_AUDIO);
            }
        });
        findViewById(R.id.btn_notification).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNextActivity(TryDemoActivity.DEMO_KEY_VALUE_NOTIFICATION);
            }
        });
    }

    private void startNextActivity(String keyVal) {
        TryDemoActivity.start(TryHomeActivity.this, keyVal);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDefaultConfig(R.layout.activity_try, getString(R.string.try_set));

        initView();
    }

}
