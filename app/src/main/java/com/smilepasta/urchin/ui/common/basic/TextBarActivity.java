package com.smilepasta.urchin.ui.common.basic;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.smilepasta.urchin.R;

/**
 * Author:huangxiaoming
 * Date:2018/4/2
 * Desc:
 * Version:1.0
 */
public abstract class TextBarActivity extends BasicActivity {
    protected LinearLayout contentLayout;
    //标题layout
    protected RelativeLayout titleLayout;
    //返回图标
    protected ImageView goBackImageView;
    //标题文字
    protected TextView titleTextView;
    //通用滚动条
    protected NestedScrollView nestedScrollView;

    protected final static String ACTION_TYPE = "action_type";
    protected final static String ACTION_TYPE_MENU = "menu";
    private ImageView menuImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textbar);
        titleTextView = (TextView) findViewById(R.id.titleTextView);
        nestedScrollView = findViewById(R.id.contentScrollView);
    }

    protected void setChildContentView(@LayoutRes int layoutResID) {
        titleLayout = (RelativeLayout) findViewById(R.id.titleLayout);
        contentLayout = (LinearLayout) findViewById(R.id.contentLayout);
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(layoutResID, null);
        contentLayout.addView(view);
    }

    protected void setContentViewBackgroundColor(int backColor) {
        if (titleLayout == null) {
            titleLayout = (RelativeLayout) findViewById(R.id.titleLayout);
        }
        titleLayout.setBackgroundColor(backColor);
    }

    /**
     * 返回
     */
    protected void setGoback() {
        goBackImageView = (ImageView) findViewById(R.id.goBack);
        goBackImageView.setVisibility(View.VISIBLE);
        goBackImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAfterTransition();
            }
        });
    }

    /**
     * 菜单
     */
    protected void setMenu() {
        ImageView menuImageView = findViewById(R.id.iv_menu);
        menuImageView.setVisibility(View.VISIBLE);
        menuImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(ACTION_TYPE, ACTION_TYPE_MENU);
                menuIconAction(bundle);
            }
        });
    }

    protected void setTitleTextView(int res) {
        titleTextView.setText(res);
    }

    protected void setTitleTextView(String title) {
        titleTextView.setText(title);
    }

    protected void setTitleTextViewColor(int color) {
        titleTextView.setTextColor(color);
    }

    protected void setDefaultConfig(@LayoutRes int layoutResID, String title) {
        setChildContentView(layoutResID);
        setTitleTextView(title);
        setGoback();
    }

    protected abstract void menuIconAction(Bundle bundle);

    /**
     * 防止重复点击
     *
     * @param v
     */
    public void disabledView(final View v) {
        v.setClickable(false);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                v.setClickable(true);
            }
        }, 1000);
    }
}
