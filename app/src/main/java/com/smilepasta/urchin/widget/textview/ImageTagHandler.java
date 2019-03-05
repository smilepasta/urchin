package com.smilepasta.urchin.widget.textview;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.Html;
import android.text.Spanned;
import android.text.style.ClickableSpan;
import android.text.style.ImageSpan;
import android.view.View;

import com.smilepasta.urchin.utils.AppUtil;
import com.smilepasta.urchin.utils.StringUtil;

import org.xml.sax.XMLReader;

import java.util.Locale;

/**
 * Author: huangxiaoming
 * Date: 2019/2/20
 * Desc: TagHandler的handleTag方法来处理img标签点击事件
 * Version: 1.0
 */
public class ImageTagHandler implements Html.TagHandler {

    private Context mContext;

    public ImageTagHandler(Context context) {
        this.mContext = context;
    }

    @Override
    public void handleTag(boolean opening, String tag, Editable output, XMLReader xmlReader) {

        // 处理标签<img>
        if (tag.toLowerCase(Locale.getDefault()).equals("img")) {
            // 获取长度
            int len = output.length();
            // 获取图片地址
            ImageSpan[] images = output.getSpans(len - 1, len, ImageSpan.class);
            String imgURL = images[0].getSource();

            // 使图片可点击并监听点击事件
            output.setSpan(new ClickableImage(mContext, imgURL), len - 1, len, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
    }

    private class ClickableImage extends ClickableSpan {

        private String url;
        private Context context;

        public ClickableImage(Context context, String url) {
            this.context = context;
            this.url = url;
        }

        @Override
        public void onClick(View widget) {
            // 进行图片点击之后的处理
            if (StringUtil.isNotEmpty(url)) {
                AppUtil.startPhotoViewActivity(url, 0, (Activity) context);
            }
        }
    }
}
