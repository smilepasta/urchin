package com.smilepasta.urchin.ui.photo;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smilepasta.urchin.R;
import com.smilepasta.urchin.bean.resp.ImageRespBean;
import com.smilepasta.urchin.ui.common.PhotoViewActivity;
import com.smilepasta.urchin.utils.GlideUtil;
import com.smilepasta.urchin.utils.StringUtil;
import com.smilepasta.urchin.utils.TextUtil;
import com.smilepasta.urchin.widget.recyclerview.BasicRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: huangxiaoming
 * Date: 2019/3/19
 * Desc: 已上传图片列表适配
 * Version: 1.0
 */
public class ImageHistoryAdapter extends BasicRecyclerAdapter<ImageRespBean.DataBean> {

    ImageHistoryAdapter(Context context, List<ImageRespBean.DataBean> list) {
        super(context, list, R.layout.item_date_image);
    }

    @Override
    public void convert(ViewHolder holder, ImageRespBean.DataBean item, int position) {
        if (item != null) {
            TextView dateTextView = holder.getView(R.id.tv_date);
            TextView descTextView = holder.getView(R.id.tv_desc);
            LinearLayout imageLayout = holder.getView(R.id.ll_img);

            if (StringUtil.isNotEmpty(item.getCreatedAt())) {
                dateTextView.setText(item.getImage_flag());
            }
            if (StringUtil.isNotEmpty(item.getDesc())) {
                descTextView.setText(item.getDesc());
            }
            if (StringUtil.isNotEmpty(item.getImages())) {
                ArrayList<String> imgList = TextUtil.getListByString(item.getImages());
                //列表上最多只显示6张图片
                ArrayList<String> tempList = new ArrayList<>();
                if (imgList.size() > 6) {
                    //如果大于 6个，就截取前面6个图片
                    for (int i = 0; i < 6; i++) {
                        tempList.add(imgList.get(i));
                    }
                } else {
                    //如果小于等于6，就不做截取
                    tempList = imgList;
                }
                initHorizontalScrollViewPhoto(imageLayout, tempList);
            }
        }
    }

    private void initHorizontalScrollViewPhoto(LinearLayout photoLayout, ArrayList<String> imgList) {
        photoLayout.removeAllViews();
        for (int i = 0; i < imgList.size(); i++) {
            LinearLayout layout = new LinearLayout(mContext);
            layout.setLayoutParams(new ViewGroup.LayoutParams(240, 240));
            layout.setGravity(Gravity.CENTER_VERTICAL);
            layout.setTag(i);

            ImageView imageView = new ImageView(mContext);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(220, 220));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);

            GlideUtil.loadImage(imgList.get(i), imageView);

            layout.addView(imageView);

            layout.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    PhotoViewActivity.startStr((Activity) mContext, imgList, Integer.parseInt(v.getTag().toString()));
                }
            });
            photoLayout.addView(layout);
        }
    }
}
