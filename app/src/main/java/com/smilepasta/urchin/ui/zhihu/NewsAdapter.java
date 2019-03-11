package com.smilepasta.urchin.ui.zhihu;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.smilepasta.urchin.R;
import com.smilepasta.urchin.utils.GlideUtil;
import com.smilepasta.urchin.utils.StringUtil;
import com.smilepasta.urchin.widget.recyclerview.BasicRecyclerAdapter;

import java.util.List;


public class NewsAdapter extends BasicRecyclerAdapter<ZhiHuNewsBean.StoriesBean> {

    NewsAdapter(Context context, List<ZhiHuNewsBean.StoriesBean> list) {
        super(context, list, R.layout.item_news);
    }

    @Override
    public void convert(ViewHolder holder, ZhiHuNewsBean.StoriesBean item, int position) {
        if (item != null) {
            TextView txtTextView = holder.getView(R.id.tv_txt);
            ImageView ivImageView = holder.getView(R.id.iv_img);
            if (item.getImages() != null
                    && item.getImages().size() > 0
                    && StringUtil.isNotEmpty(item.getImages().get(0))) {
                GlideUtil.loadImage(item.getImages().get(0), ivImageView);
            }
            if (StringUtil.isNotEmpty(item.getTitle())) {
                txtTextView.setText(item.getTitle());
            }
        }
    }


}
