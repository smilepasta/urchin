package com.smilepasta.urchin.ui.demo;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smilepasta.urchin.R;
import com.smilepasta.urchin.model.UserProto;
import com.smilepasta.urchin.ui.common.PhotoViewActivity;
import com.smilepasta.urchin.ui.common.basic.BasicFragment;
import com.smilepasta.urchin.utils.GlideUtil;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProtoBufFragment extends BasicFragment {


    private ArrayList<String> imgList;

    public ProtoBufFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_proto_buf, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        UserProto.UserData.Builder builder = UserProto.UserData.newBuilder();
        builder.setId(1008);
        builder.setName("虽然一直在走，又好像一直在原地。是时间慢了你的脚步，还是时光快了，你却毫无察觉。");
        builder.setMsg("https://cdn.smilepasta.com/images/wugongshan_richu2.jpg,https://cdn.smilepasta.com/images/wugongshan_richu.jpg");

        TextView contentTextView = view.findViewById(R.id.tv_content);
        AppCompatImageView roundImageView = view.findViewById(R.id.iv_img);
        AppCompatImageView roundImageView2 = view.findViewById(R.id.iv_img2);

        contentTextView.setText(builder.getName());
        String[] imgStrArr = builder.getMsg().split(",");
        imgList = new ArrayList<>();
        imgList.add(imgStrArr[0]);
        imgList.add(imgStrArr[1]);

        GlideUtil.loadImage(imgList.get(0), roundImageView);
        GlideUtil.loadImage(imgList.get(1), roundImageView2);

        roundImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhotoViewActivity.start((Activity) mContext, imgList, PhotoViewActivity.IMAGE_PATH_TYPE_URL, 0);
            }
        });

        roundImageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhotoViewActivity.start((Activity) mContext, imgList, PhotoViewActivity.IMAGE_PATH_TYPE_URL, 1);
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


}
