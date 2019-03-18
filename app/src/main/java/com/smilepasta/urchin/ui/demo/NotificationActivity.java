package com.smilepasta.urchin.ui.demo;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.RemoteViews;

import com.smilepasta.urchin.R;
import com.smilepasta.urchin.ui.common.basic.TextBarActivity;
import com.smilepasta.urchin.ui.zhihu.ZhiHuHomeActivity;

/**
 * Author: huangxiaoming
 * Date: 2019/2/25
 * Desc: 消息通知
 * Version: 1.0
 */
public class NotificationActivity extends TextBarActivity {

    private void initView() {
        findViewById(R.id.btn_show_notification).setOnClickListener(v -> showNotification());
        findViewById(R.id.btn_custom_notification).setOnClickListener(v -> showCustomNotification());
    }

    /**
     * 弹出自定义消息
     */
    private void showCustomNotification() {
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (manager != null) {
            String CHANNEL_ONE_ID = "com.smilepasta.api";
            String CHANNEL_ONE_NAME = "Channel One";
            NotificationChannel notificationChannel;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                notificationChannel = new NotificationChannel(CHANNEL_ONE_ID,
                        CHANNEL_ONE_NAME, NotificationManager.IMPORTANCE_HIGH);
                notificationChannel.enableLights(true);  //闪光灯
                notificationChannel.setLightColor(Color.RED);         //闪关灯的灯光颜色
                notificationChannel.setShowBadge(true); //桌面launcher的消息角标
                notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC); //锁屏显示通知
                manager.createNotificationChannel(notificationChannel);
            }
            Intent localIntent = new Intent(this, ZhiHuHomeActivity.class);
            PendingIntent localPendingIntent = PendingIntent.getActivity(this, 0, localIntent, 0);
            NotificationCompat.Builder localBuilder = new NotificationCompat.Builder(this, CHANNEL_ONE_ID);
            localBuilder.setSmallIcon(R.mipmap.ic_launcher)  //小图标
                    .setChannelId(CHANNEL_ONE_ID)
                    .setWhen(System.currentTimeMillis()) //通知的时间
                    .setOngoing(false)
                    .setTicker(getString(R.string.app_name))
                    .setAutoCancel(true)  //设置点击信息后自动清除通知
                    .setContentIntent(localPendingIntent)
                    .setDefaults(NotificationCompat.FLAG_FOREGROUND_SERVICE)
                    .setContent(new RemoteViews(getPackageName(), R.layout.view_notification));
            manager.notify(1, localBuilder.build());
        }

    }

    /**
     * 弹出默认消息样式
     */
    private void showNotification() {
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (manager != null) {
            String CHANNEL_ONE_ID = "com.smilepasta.api";
            String CHANNEL_ONE_NAME = "Channel One";
            NotificationChannel notificationChannel;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                notificationChannel = new NotificationChannel(CHANNEL_ONE_ID,
                        CHANNEL_ONE_NAME, NotificationManager.IMPORTANCE_HIGH);
                notificationChannel.enableLights(true);  //闪光灯
                notificationChannel.setLightColor(Color.RED);         //闪关灯的灯光颜色
                notificationChannel.setShowBadge(true); //桌面launcher的消息角标
                notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC); //锁屏显示通知
                manager.createNotificationChannel(notificationChannel);
            }
            Intent localIntent = new Intent(this, ZhiHuHomeActivity.class);
            PendingIntent localPendingIntent = PendingIntent.getActivity(this, 0, localIntent, 0);
            NotificationCompat.Builder localBuilder = new NotificationCompat.Builder(this, CHANNEL_ONE_ID);
            localBuilder.setSmallIcon(R.mipmap.ic_launcher)  //小图标
                    .setContentTitle(getString(R.string.tips_17))
                    .setContentText(getString(R.string.tips_18)) //内容
                    .setChannelId(CHANNEL_ONE_ID)
                    .setWhen(System.currentTimeMillis()) //通知的时间
                    .setOngoing(false)
                    .setTicker(getString(R.string.app_name))
                    .setAutoCancel(false)  //设置点击信息后自动清除通知
                    .setContentIntent(localPendingIntent)
                    .setDefaults(NotificationCompat.FLAG_FOREGROUND_SERVICE);
            manager.notify(1, localBuilder.build());
        }

    }

    @Override
    protected void menuIconAction(Bundle bundle) {

    }

    public static void start(Activity activity) {
        activity.startActivity(new Intent(activity, NotificationActivity.class));
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDefaultConfig(R.layout.activity_notification, getString(R.string.protobuf_demo));

        initView();
    }
}