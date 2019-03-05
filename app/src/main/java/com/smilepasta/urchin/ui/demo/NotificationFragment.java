package com.smilepasta.urchin.ui.demo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smilepasta.urchin.R;
import com.smilepasta.urchin.ui.common.basic.BasicFragment;
import com.smilepasta.urchin.utils.NotificationUtil;

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
                new NotificationUtil(mContext).sendNotification("自定义消息", "你有一条新的消息");
            }
        });
    }
}
