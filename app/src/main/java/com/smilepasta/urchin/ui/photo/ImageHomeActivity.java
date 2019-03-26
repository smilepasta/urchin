package com.smilepasta.urchin.ui.photo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.smilepasta.urchin.R;
import com.smilepasta.urchin.ui.common.basic.TextBarActivity;

/**
 * Author: huangxiaoming
 * Date: 2019/3/26
 * Desc: 图片首页
 * Version: 1.0
 */
public class ImageHomeActivity extends TextBarActivity {

    @Override
    protected void menuIconAction(Bundle bundle) {
        if (bundle != null) {
            String action = bundle.getString(ACTION_TYPE);
            if (action.equals(ACTION_TYPE_UPLOAD)) {
                ImageSelectActivity.start(this);
            }
        }
    }

    private void initView() {
        ImageHistoryFragment fragment = new ImageHistoryFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment, fragment);
        ft.commit();
    }

    public static void start(Activity activity) {
        activity.startActivity(new Intent(activity, ImageHomeActivity.class));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDefaultConfig(R.layout.common_fragment, getString(R.string.day));
        setUpload();
        initView();
    }


}
