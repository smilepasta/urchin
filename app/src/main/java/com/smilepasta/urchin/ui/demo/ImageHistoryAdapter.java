package com.smilepasta.urchin.ui.demo;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.smilepasta.urchin.R;
import com.smilepasta.urchin.bean.resp.ImageRespBean;
import com.smilepasta.urchin.ui.common.PhotoViewActivity;
import com.smilepasta.urchin.utils.StringUtil;
import com.smilepasta.urchin.utils.TextUtil;
import com.smilepasta.urchin.widget.recyclerview.BasicRecyclerAdapter;

import java.util.List;

/**
 * Author: huangxiaoming
 * Date: 2019/3/19
 * Desc: 已上传图片列表适配
 * Version: 1.0
 */
public class ImageHistoryAdapter extends BasicRecyclerAdapter<ImageRespBean.DataBean.ImageArrBean> {

    ImageHistoryAdapter(Context context, List<ImageRespBean.DataBean.ImageArrBean> list) {
        super(context, list, R.layout.item_date_image);
    }

    @Override
    public void convert(ViewHolder holder, ImageRespBean.DataBean.ImageArrBean item, int position) {
        if (item != null) {
            TextView dateTextView = holder.getView(R.id.tv_date);
            RecyclerView recyclerView = holder.getView(R.id.rv_date_image_list);

            if (StringUtil.isNotEmpty(item.getDay())) {
                dateTextView.setText(item.getDay());
            }
            if (StringUtil.isNotEmpty(item.getImages())) {
                recyclerView.setLayoutManager(new GridLayoutManager(mContext, 4));
                ImageHistoryItemAdapter imageHistoryItemAdapter = new ImageHistoryItemAdapter(mContext, TextUtil.getListByString(item.getImages()));
                imageHistoryItemAdapter.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int startIndex) {
                        ImageRespBean.DataBean.ImageArrBean imageArrBean = mList.get(position);
                        if (imageArrBean != null) {
                            String imageUrls = imageArrBean.getImages();
                            if (StringUtil.isNotEmpty(imageUrls)) {
                                PhotoViewActivity.startStr((Activity) mContext, TextUtil.getListByString(imageUrls), startIndex);
                            }
                        }
                    }
                });
                recyclerView.setAdapter(imageHistoryItemAdapter);
            }

        }
    }
}
