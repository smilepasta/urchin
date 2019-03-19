package com.smilepasta.urchin.ui.demo;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.widget.TextView;

import com.smilepasta.urchin.R;
import com.smilepasta.urchin.bean.UserProto;
import com.smilepasta.urchin.ui.common.PhotoViewActivity;
import com.smilepasta.urchin.ui.common.basic.TextBarActivity;
import com.smilepasta.urchin.utils.GlideUtil;

import java.util.ArrayList;

/**
 * Author: huangxiaoming
 * Date: 2019/3/15
 * Desc: protobuf使用方法
 * Version: 1.0
 */
public class ProtoBufActivity extends TextBarActivity {


    private ArrayList<String> imgList;

    private void initView() {
        UserProto.UserData.Builder builder = UserProto.UserData.newBuilder();
        builder.setId(1008);
        builder.setName("虽然一直在走，又好像一直在原地。是时间慢了你的脚步，还是时光快了，你却毫无察觉。");
        builder.setMsg("https://cdn.smilepasta.com/images/wugongshan_richu2.jpg,https://cdn.smilepasta.com/images/wugongshan_richu.jpg");

        TextView contentTextView = findViewById(R.id.tv_content);
        AppCompatImageView roundImageView = findViewById(R.id.iv_img);
        AppCompatImageView roundImageView2 = findViewById(R.id.iv_img2);

        contentTextView.setText(builder.getName());
        String[] imgStrArr = builder.getMsg().split(",");
        imgList = new ArrayList<>();
        imgList.add(imgStrArr[0]);
        imgList.add(imgStrArr[1]);

        GlideUtil.loadImage(imgList.get(0), roundImageView);
        GlideUtil.loadImage(imgList.get(1), roundImageView2);

        roundImageView.setOnClickListener(v -> PhotoViewActivity.startStr(ProtoBufActivity.this, imgList, 0));

        roundImageView2.setOnClickListener(v -> PhotoViewActivity.startStr(ProtoBufActivity.this, imgList, 1));
    }


    @Override
    protected void menuIconAction(Bundle bundle) {

    }

    public static void start(Activity activity) {
        activity.startActivity(new Intent(activity, ProtoBufActivity.class));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDefaultConfig(R.layout.activity_proto_buf, getString(R.string.protobuf_demo));

        initView();
    }

}
