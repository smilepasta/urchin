package com.smilepasta.urchin.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.design.widget.BottomSheetDialog;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.smilepasta.urchin.R;

/**
 * Author: huangxiaoming
 * Date: 2018/5/7
 * Desc:
 * Version: 1.0
 */
public class DialogUtil {

    public static Dialog dialog;
    public static ProgressDialog progressDialog;
    public static BottomSheetDialog bottomSheetDialog;

    public static void cancel() {
        if (dialog != null) {
            dialog.cancel();
        }
        if (progressDialog != null) {
            progressDialog.cancel();
        }
        if (bottomSheetDialog != null) {
            bottomSheetDialog.cancel();
        }
    }

    public static void progress(Context context) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCanceledOnTouchOutside(false);
//        progressDialog.setMessage(context.getString(R.string.tips_6));
        progressDialog.show();
    }

    public static void progress(Context context, String message) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage(message);
        progressDialog.show();
    }

    /**
     * 只有确认的监听
     *
     * @param context
     * @param title
     * @param content
     * @param clickListener
     */
    public static void query(Context context, CharSequence title, CharSequence content, View.OnClickListener clickListener) {

        try {
            dialog = new AlertDialog.Builder(context).create();
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
            Window window = dialog.getWindow();
            window.setContentView(R.layout.dialog_normal);
            window.clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
            TextView titleTextView = (TextView) window.findViewById(R.id.titleTextView);
            titleTextView.setText(title);
            TextView contentTextView = (TextView) window.findViewById(R.id.contentTextView);
            contentTextView.setText(content);
            TextView confirmTextView = (TextView) window.findViewById(R.id.confirmTextView);
            confirmTextView.setOnClickListener(clickListener);
            TextView cancelTextView = (TextView) window.findViewById(R.id.cancelTextView);
            cancelTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.cancel();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 只有确认的监听,可以自己输入确认与取消的文本
     *
     * @param context
     * @param title
     * @param content
     * @param clickListener
     */
    public static void query(Context context, CharSequence title, CharSequence content, String cancelText, String confirmText, View.OnClickListener clickListener) {
        try {
            dialog = new AlertDialog.Builder(context).create();
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
            Window window = dialog.getWindow();
            window.setContentView(R.layout.dialog_normal);
            window.clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
            TextView titleTextView = (TextView) window.findViewById(R.id.titleTextView);
            titleTextView.setText(title);
            TextView contentTextView = (TextView) window.findViewById(R.id.contentTextView);
            contentTextView.setText(content);
            TextView confirmTextView = (TextView) window.findViewById(R.id.confirmTextView);
            confirmTextView.setText(confirmText);
            confirmTextView.setOnClickListener(clickListener);
            TextView cancelTextView = (TextView) window.findViewById(R.id.cancelTextView);
            cancelTextView.setText(cancelText);
            cancelTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.cancel();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 不能点击空白、按返回键取消dialog，只能通过点击取消按钮取消
     *
     * @param context         上下文
     * @param title           标题
     * @param content         内容
     * @param cancelText      取消文字
     * @param confirmText     确认文字
     * @param confirmListener 取消监听
     * @param cancelListener  确认监听
     */
    public static void query(Context context, CharSequence title, CharSequence content, String cancelText, String confirmText, View.OnClickListener confirmListener, View.OnClickListener cancelListener) {

        try {
            dialog = new AlertDialog.Builder(context).create();
            dialog.setCanceledOnTouchOutside(false);
            dialog.setCancelable(false);
            dialog.show();
            Window window = dialog.getWindow();
            window.setContentView(R.layout.dialog_normal);
            window.clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
            TextView titleTextView = (TextView) window.findViewById(R.id.titleTextView);
            titleTextView.setText(title);
            TextView contentTextView = (TextView) window.findViewById(R.id.contentTextView);
            contentTextView.setText(content);
            TextView confirmTextView = (TextView) window.findViewById(R.id.confirmTextView);
            confirmTextView.setText(confirmText);
            confirmTextView.setOnClickListener(confirmListener);
            TextView cancelTextView = (TextView) window.findViewById(R.id.cancelTextView);
            cancelTextView.setText(cancelText);
            cancelTextView.setOnClickListener(cancelListener);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 可以设置取消和确认的监听
     *
     * @param context
     * @param title
     * @param content
     * @param clickListener
     * @param clickListener1
     */
    public static void query(Context context, CharSequence title, CharSequence content, View.OnClickListener clickListener, View.OnClickListener clickListener1) {

        try {
            dialog = new AlertDialog.Builder(context).create();
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
            Window window = dialog.getWindow();
            window.setContentView(R.layout.dialog_normal);
            window.clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
            TextView titleTextView = (TextView) window.findViewById(R.id.titleTextView);
            titleTextView.setText(title);
            TextView contentTextView = (TextView) window.findViewById(R.id.contentTextView);
            contentTextView.setText(content);
            TextView confirmTextView = (TextView) window.findViewById(R.id.confirmTextView);
            confirmTextView.setOnClickListener(clickListener);
            TextView cancelTextView = (TextView) window.findViewById(R.id.cancelTextView);
            cancelTextView.setOnClickListener(clickListener1);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 只显示确认按钮的弹窗
     *
     * @param activity
     * @param title
     * @param content
     * @param clickListener
     */
    public static void queryConfirm(Activity activity, CharSequence title, CharSequence content, View.OnClickListener clickListener) {

        try {
            dialog = new AlertDialog.Builder(activity).create();
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
            Window window = dialog.getWindow();
            window.setContentView(R.layout.dialog_normal);
            window.clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
            TextView titleTextView = (TextView) window.findViewById(R.id.titleTextView);
            titleTextView.setText(title);
            TextView contentTextView = (TextView) window.findViewById(R.id.contentTextView);
            contentTextView.setText(content);
            TextView confirmTextView = (TextView) window.findViewById(R.id.confirmTextView);
            confirmTextView.setOnClickListener(clickListener);
            TextView cancelTextView = (TextView) window.findViewById(R.id.cancelTextView);
            cancelTextView.setVisibility(View.GONE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}