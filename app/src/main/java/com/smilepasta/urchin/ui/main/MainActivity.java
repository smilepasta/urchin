package com.smilepasta.urchin.ui.main;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

import com.smilepasta.urchin.R;
import com.smilepasta.urchin.ui.common.basic.TextBarActivity;
import com.smilepasta.urchin.ui.demo.TryHomeActivity;
import com.smilepasta.urchin.ui.setting.SettingActivity;
import com.smilepasta.urchin.ui.zhihu.ZhiHuHomeActivity;
import com.smilepasta.urchin.utils.StringUtil;

public class MainActivity extends TextBarActivity {

    public final static String FROM_KEY = "from";
    public final static String FROM_VALUE_LANGUAGE = "from_language";

    private GlobalBroadcastReceiver globalBroadcastReceiver;

    @Override
    protected void menuIconAction(Bundle bundle) {

    }

    private void initView() {
        findViewById(R.id.btn_zhihu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZhiHuHomeActivity.start(MainActivity.this);
            }
        });
        findViewById(R.id.btn_try).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TryHomeActivity.start(MainActivity.this);
            }
        });
        findViewById(R.id.btn_setting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SettingActivity.start(MainActivity.this);
            }
        });
    }

    private void initData() {
        globalBroadcastReceiver = new GlobalBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
//        intentFilter.addAction(Constant.BROADCAST_RECEIVER_LANGUAGE_CHECK);//语言切换
        registerReceiver(globalBroadcastReceiver, intentFilter);
    }

    public static void start(Activity activity, String from) {
        Intent intent = new Intent(activity, MainActivity.class);
        if (from != null) {
            intent.putExtra(FROM_KEY, from);
        }
        activity.startActivity(intent);
    }

    public static void start(Activity activity) {
        start(activity, null);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String from = intent.getStringExtra(FROM_KEY);
        if (StringUtil.isNotEmpty(from)) {
            if (from.equals(FROM_VALUE_LANGUAGE)) {
                Intent newIntent = getPackageManager().getLaunchIntentForPackage(getPackageName());
                newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(newIntent);
                finish();
            }
        }
    }

    public class GlobalBroadcastReceiver extends BroadcastReceiver {

        public GlobalBroadcastReceiver() {
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                String action = intent.getAction();
                if (StringUtil.isNotEmpty(action)) {
//                    if (action.equals(Constant.BROADCAST_RECEIVER_LANGUAGE_CHECK)) {
//                    }
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(globalBroadcastReceiver);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setChildContentView(R.layout.activity_main);
        setTitleTextView(getString(R.string.app_name));

        initView();
        initData();
    }


}
