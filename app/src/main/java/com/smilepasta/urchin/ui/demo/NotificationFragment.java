package com.smilepasta.urchin.ui.demo;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RemoteViews;

import com.smilepasta.urchin.R;
import com.smilepasta.urchin.ui.common.basic.BasicFragment;
import com.smilepasta.urchin.ui.zhihu.ZhiHuHomeActivity;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Author: huangxiaoming
 * Date: 2019/2/25
 * Desc: 消息通知
 * Version: 1.0
 */
public class NotificationFragment extends BasicFragment {

    public NotificationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        view.findViewById(R.id.btn_show_notification).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showNotification();
            }

        });

        view.findViewById(R.id.btn_custom_notification).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomNotification();
            }
        });
    }

    /**
     * 弹出自定义消息
     */
    private void showCustomNotification() {
        NotificationManager manager = (NotificationManager) mContext.getSystemService(NOTIFICATION_SERVICE);
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
            Intent localIntent = new Intent(mContext, ZhiHuHomeActivity.class);
            PendingIntent localPendingIntent = PendingIntent.getActivity(mContext, 0, localIntent, 0);
            NotificationCompat.Builder localBuilder = new NotificationCompat.Builder(mContext, CHANNEL_ONE_ID);
            localBuilder.setSmallIcon(R.mipmap.ic_launcher)  //小图标
                    .setChannelId(CHANNEL_ONE_ID)
                    .setWhen(System.currentTimeMillis()) //通知的时间
                    .setOngoing(false)
                    .setTicker(mContext.getString(R.string.app_name))
                    .setAutoCancel(true)  //设置点击信息后自动清除通知
                    .setContentIntent(localPendingIntent)
                    .setDefaults(NotificationCompat.FLAG_FOREGROUND_SERVICE)
                    .setContent(new RemoteViews(mContext.getPackageName(), R.layout.view_notification));
            manager.notify(1, localBuilder.build());
        }

    }

    /**
     * 弹出默认消息样式
     */
    private void showNotification() {
        NotificationManager manager = (NotificationManager) mContext.getSystemService(NOTIFICATION_SERVICE);
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
            Intent localIntent = new Intent(mContext, ZhiHuHomeActivity.class);
            PendingIntent localPendingIntent = PendingIntent.getActivity(mContext, 0, localIntent, 0);
            NotificationCompat.Builder localBuilder = new NotificationCompat.Builder(mContext, CHANNEL_ONE_ID);
            localBuilder.setSmallIcon(R.mipmap.ic_launcher)  //小图标
                    .setContentTitle(getString(R.string.tips_17))
                    .setContentText(getString(R.string.tips_18)) //内容
                    .setChannelId(CHANNEL_ONE_ID)
                    .setWhen(System.currentTimeMillis()) //通知的时间
                    .setOngoing(false)
                    .setTicker(mContext.getString(R.string.app_name))
                    .setAutoCancel(false)  //设置点击信息后自动清除通知
                    .setContentIntent(localPendingIntent)
                    .setDefaults(NotificationCompat.FLAG_FOREGROUND_SERVICE);
            manager.notify(1, localBuilder.build());
        }

    }
}