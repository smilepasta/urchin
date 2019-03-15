package com.smilepasta.urchin.ui.zhihu;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
            RelativeLayout contentLayout = holder.getView(R.id.rl_content);
            TextView deleteTextView = holder.getView(R.id.tv_delete);
            if (item.getImages() != null
                    && item.getImages().size() > 0
                    && StringUtil.isNotEmpty(item.getImages().get(0))) {
                GlideUtil.loadImage(item.getImages().get(0), ivImageView);
            }
            if (StringUtil.isNotEmpty(item.getTitle())) {
                txtTextView.setText(item.getTitle());
            }
            contentLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (newsClickListener != null) {
                        newsClickListener.onItemClick(position);
                    }
                }
            });
            deleteTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (newsClickListener != null) {
                        newsClickListener.onDeleteClick(position);
                    }
                }
            });
        }
    }

    private NewsClickListener newsClickListener;

    public void setNewsClickListener(NewsClickListener newsClickListener) {
        this.newsClickListener = newsClickListener;
    }

    interface NewsClickListener {
        /**
         * 条目点击进入详情
         *
         * @param position
         */
        void onItemClick(int position);

        /**
         * 删除
         *
         * @param position
         */
        void onDeleteClick(int position);


    }
}
