package com.smilepasta.urchin.ui.photo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.smilepasta.urchin.R;
import com.smilepasta.urchin.bean.resp.ImageRespBean;
import com.smilepasta.urchin.ui.common.PhotoViewActivity;
import com.smilepasta.urchin.ui.common.basic.TextBarActivity;
import com.smilepasta.urchin.utils.StringUtil;
import com.smilepasta.urchin.utils.TextUtil;
import com.smilepasta.urchin.widget.recyclerview.BasicRecyclerAdapter;

import java.util.ArrayList;

/**
 * Author: huangxiaoming
 * Date: 2019/3/26
 * Desc: 图片详情
 * Version: 1.0
 */
public class ImageDetailActivity extends TextBarActivity {

    public final static String IMAGE_DETAIL_KEY = "image_detail";
    private ArrayList<String> imgList;

    @Override
    protected void menuIconAction(Bundle bundle) {

    }

    private void initView() {
        RecyclerView recyclerView = findViewById(R.id.rv_image);
        TextView descTextView = findViewById(R.id.tv_desc);
        TextView dateTextView = findViewById(R.id.tv_date);

        Intent intent = getIntent();
        if (intent != null) {
            ImageRespBean.DataBean image = intent.getParcelableExtra(IMAGE_DETAIL_KEY);
            if (image != null) {
                if (StringUtil.isNotEmpty(image.getDesc())) {
                    descTextView.setText(image.getDesc());
                }
                if (StringUtil.isNotEmpty(image.getImage_flag())) {
                    dateTextView.setText(image.getImage_flag());
                }
                if (StringUtil.isNotEmpty(image.getImages())) {
                    imgList = TextUtil.getListByString(image.getImages());
                    recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
                    ImageHistoryItemAdapter imageHistoryItemAdapter = new ImageHistoryItemAdapter(this, imgList);
                    imageHistoryItemAdapter.setOnItemClickListener(new BasicRecyclerAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            PhotoViewActivity.startStr(ImageDetailActivity.this, imgList, position);
                        }
                    });
                    recyclerView.setAdapter(imageHistoryItemAdapter);
                }
            }
        }


    }

    public static void start(ImageRespBean.DataBean image, Activity activity) {
        Intent intent = new Intent(activity, ImageDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(IMAGE_DETAIL_KEY, image);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDefaultConfig(R.layout.activity_image_detail, getString(R.string.image_detail));
        initView();
    }


}
