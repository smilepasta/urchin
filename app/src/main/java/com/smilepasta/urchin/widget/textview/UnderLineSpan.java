package com.smilepasta.urchin.widget.textview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextPaint;
import android.text.style.URLSpan;
import android.view.View;

import com.smilepasta.urchin.ui.common.WebViewActivity;

/**
 * Author: huangxiaoming
 * Date: 2019/2/20
 * Desc: 自定义html中的下划线
 * Version: 1.0
 */
public class UnderLineSpan extends URLSpan {
    private Context context;
    private String url;

    public UnderLineSpan(Context context, String url) {
        super(url);
        this.context = context;
        this.url = url;
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        super.updateDrawState(ds);
        ds.setUnderlineText(false);  //设置没有下划线
//        ds.setColor(UIUtil.getColor(R.color.colorAccent));//设置下划线的颜色
    }

    @Override
    public void onClick(View widget) {
        WebViewActivity.start((Activity) context,url);
    }
}