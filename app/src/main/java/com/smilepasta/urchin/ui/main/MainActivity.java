package com.smilepasta.urchin.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.smilepasta.urchin.R;
import com.smilepasta.urchin.ui.common.basic.TextBarActivity;
import com.smilepasta.urchin.ui.demo.TryHomeActivity;
import com.smilepasta.urchin.ui.zhihu.ZhiHuHomeActivity;
import com.smilepasta.urchin.utils.UIUtil;

public class MainActivity extends TextBarActivity {

    @Override
    protected void menuIconAction(Bundle bundle) {

    }

    private void initView() {
        findViewById(R.id.btn_zhihu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ZhiHuHomeActivity.class));
            }
        });
        findViewById(R.id.btn_try).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TryHomeActivity.class));
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setChildContentView(R.layout.activity_main);
        setTitleTextView(UIUtil.getString(this, R.string.app_name));

        initView();

    }

}
