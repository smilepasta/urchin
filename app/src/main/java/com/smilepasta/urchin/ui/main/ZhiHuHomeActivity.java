package com.smilepasta.urchin.ui.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.smilepasta.urchin.R;
import com.smilepasta.urchin.ui.common.basic.TextBarActivity;
import com.smilepasta.urchin.ui.main.fragment.NewsListFragment;
import com.smilepasta.urchin.utils.UIUtil;

public class ZhiHuHomeActivity extends TextBarActivity {

    @Override
    protected void menuIconAction(Bundle bundle) {

    }

    private void initView() {
        NewsListFragment newsListFragment = new NewsListFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment, newsListFragment);
        ft.commit();
    }

    public static void start(Activity activity) {
        activity.startActivity(new Intent(activity, ZhiHuHomeActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDefaultConfig(R.layout.common_fragment, UIUtil.getString(this, R.string.zihhu_news));
        initView();
    }


}
