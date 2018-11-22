package com.smilepasta.urchin.utils;

import android.view.Gravity;
import android.widget.Toast;

import com.smilepasta.urchin.UrchinApp;

/**
 * Author:huangxiaoming
 * Date:2018/4/10 0010
 * Desc:
 * Version:
 */
public class ToastUtil {

    private static Toast toast;//实现不管我们触发多少次Toast调用，都只会持续一次Toast显示的时长

    /**
     * 短时间显示Toast【居下】
     *
     * @param msg 显示的内容-字符串
     */
    public static void showToastBottom(String msg) {
        if (UrchinApp.getInstance() != null) {
            if (toast == null) {
                toast = Toast.makeText(UrchinApp.getInstance(), msg, Toast.LENGTH_SHORT);
            } else {
                toast.setText(msg);
            }
            //1、setGravity方法必须放到这里，否则会出现toast始终按照第一次显示的位置进行显示（比如第一次是在底部显示，那么即使设置setGravity在中间，也不管用）
            //2、虽然默认是在底部显示，但是，因为这个工具类实现了中间显示，所以需要还原，还原方式如下：
            toast.setGravity(Gravity.BOTTOM, 0, UIUtil.dip2px(64));
            toast.show();
        }
    }

    /**
     * 短时间显示Toast【居中】
     *
     * @param msg 显示的内容-字符串
     */
    public static void show(String msg) {
        if (UrchinApp.getInstance() != null) {
            if (toast == null) {
                toast = Toast.makeText(UrchinApp.getInstance(), msg, Toast.LENGTH_SHORT);
            } else {
                toast.setText(msg);
            }
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }

    /**
     * 短时间显示Toast【居上】
     *
     * @param msg 显示的内容-字符串
     */
    public static void showShortToastTop(String msg) {
        if (UrchinApp.getInstance() != null) {
            if (toast == null) {
                toast = Toast.makeText(UrchinApp.getInstance(), msg, Toast.LENGTH_SHORT);
            } else {
                toast.setText(msg);
            }
            toast.setGravity(Gravity.TOP, 0, 0);
            toast.show();
        }
    }


}
