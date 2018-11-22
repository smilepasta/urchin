package com.smilepasta.urchin.ui.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.smilepasta.urchin.R;
import com.smilepasta.urchin.bean.ZhiHuNewsBean;
import com.smilepasta.urchin.ui.common.listener.IOnItemClickListener;
import com.smilepasta.urchin.utils.GlideUtil;
import com.smilepasta.urchin.utils.StringUtil;
import com.smilepasta.urchin.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private List<ZhiHuNewsBean.StoriesBean> zhihuDailyItems = new ArrayList<>();
    private IOnItemClickListener onItemClickListener;
    private Context mContext;

    public NewsAdapter(Context context) {
        this.mContext = context;
    }

    public void setOnItemClickListener(IOnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NewsViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_news, parent, false));
    }


    @Override
    public void onBindViewHolder(final NewsViewHolder holder, int position) {
        ZhiHuNewsBean.StoriesBean zhihuDailyItem = zhihuDailyItems.get(holder.getAdapterPosition());
        if (zhihuDailyItem != null) {
            if (zhihuDailyItem.getImages() != null
                    && zhihuDailyItem.getImages().size() > 0
                    && StringUtil.isNotEmpty(zhihuDailyItem.getImages().get(0)))
                GlideUtil.loadImage(zhihuDailyItem.getImages().get(0), holder.iv);
            if (StringUtil.isNotEmpty(zhihuDailyItem.getTitle())) {
                holder.tv.setText(zhihuDailyItem.getTitle());
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(position);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return zhihuDailyItems.size();
    }


    public void setData(List<ZhiHuNewsBean.StoriesBean> list) {
        this.zhihuDailyItems = list;
        int startPosition = getItemCount();
        notifyItemRangeInserted(startPosition, this.zhihuDailyItems.size());
    }

    public void clearData() {
        zhihuDailyItems.clear();
        notifyDataSetChanged();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder {

        ImageView iv;
        TextView tv;

        public NewsViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv_txt);
            iv = (ImageView) itemView.findViewById(R.id.iv_img);
        }
    }
}
