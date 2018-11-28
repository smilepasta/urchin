package com.smilepasta.urchin.ui.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.smilepasta.urchin.R;
import com.smilepasta.urchin.ui.common.basic.TextBarActivity;
import com.smilepasta.urchin.utils.UIUtil;

public class TryHomeActivity extends TextBarActivity {

    @Override
    protected void menuIconAction(Bundle bundle) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDefaultConfig(R.layout.activity_try, UIUtil.getString(this, R.string.try_set));

        initView();
    }

    private void initView() {
        findViewById(R.id.btn_try).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTryDemoActivity(TryDemoActivity.DEMO_KEY_VALUE_PROTOBUF);
            }
        });
    }

    public void startTryDemoActivity(String val) {
        Intent intent = new Intent(TryHomeActivity.this, TryDemoActivity.class);
        intent.putExtra(TryDemoActivity.DEMO_KEY_NAME, val);
        startActivity(intent);
    }

}
