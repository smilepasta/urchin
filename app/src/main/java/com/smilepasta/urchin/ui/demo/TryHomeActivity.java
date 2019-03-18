package com.smilepasta.urchin.ui.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.smilepasta.faceu.FaceuAuthActivity;
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
        findViewById(R.id.btn_protobuf).setOnClickListener(v -> ProtoBufActivity.start(TryHomeActivity.this));
        findViewById(R.id.btn_audio).setOnClickListener(v -> AudioActivity.start(TryHomeActivity.this));
        findViewById(R.id.btn_notification).setOnClickListener(v -> NotificationActivity.start(TryHomeActivity.this));
        findViewById(R.id.btn_image_manage).setOnClickListener(v -> ImageSelectActivity.start(TryHomeActivity.this));
        findViewById(R.id.btn_faceu).setOnClickListener(v -> {
            Intent intent = new Intent(TryHomeActivity.this, FaceuAuthActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDefaultConfig(R.layout.activity_try, getString(R.string.try_set));

        initView();
    }

}
