package com.smilepasta.urchin.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;

import com.smilepasta.urchin.UrchinApp;

/**
 * Author:huangxiaoming
 * Date:2018/4/2
 * Desc:
 * Version:1.0
 */
public class UIUtil {
    /**
     * 获取dimen
     */
    public static int getDimens(int resId) {
        return getContext().getResources().getDimensionPixelSize(resId);
    }

    /**
     * dp2px
     */
    public static int dip2px(float dpValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * px2dp
     */
    public static int px2dip(float pxValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 根据设备信息获取当前分辨率下指定单位对应的像素大小；
     * px,dip,sp -> px
     */
    public float getRawSize(Context c, int unit, float size) {
        Resources r;
        if (c == null) {
            r = Resources.getSystem();
        } else {
            r = c.getResources();
        }
        return TypedValue.applyDimension(unit, size, r.getDisplayMetrics());
    }

    public static Context getContext() {
        return UrchinApp.getInstance();
    }


    public static final String RESOURCE = "android.resource://";


    public static Uri idToUri(int resourceId) {
        return Uri.parse(RESOURCE + getContext().getPackageName() + "/" + resourceId);
    }

    public static int getColor(int resId) {
        return ContextCompat.getColor(getContext(), resId);
    }

    public static Drawable getDrawable(int resId) {
        return ContextCompat.getDrawable(getContext(), resId);
    }

    public static String getString(int resId) {
        return getContext().getString(resId);
    }

    /**
     * 105是每个gridview的item的固定总宽高，通过计算，得出最合适显示的gridview的列数
     */
    public static int getGridViewSuitableCount(Activity activity) {
        int width = activity.getWindowManager().getDefaultDisplay().getWidth();
        int widthDp = UIUtil.px2dip(Float.parseFloat(width + ""));
        return widthDp / 105;
    }

}
