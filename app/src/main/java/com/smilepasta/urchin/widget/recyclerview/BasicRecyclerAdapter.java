package com.smilepasta.urchin.widget.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Author: huangxiaoming
 * Date: 2019/3/11
 * Desc:
 * Version: 1.0
 */
public abstract class BasicRecyclerAdapter<T> extends RecyclerView.Adapter<BasicRecyclerAdapter.ViewHolder> {

    protected Context mContext;
    protected int mLayoutId;
    protected List<T> mList;

    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    public BasicRecyclerAdapter() {
    }

    public BasicRecyclerAdapter(Context context, List<T> list, int layoutId) {
        mContext = context;
        mList = list;
        mLayoutId = layoutId;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        ViewHolder viewHolder = ViewHolder.getInstance(mContext, mLayoutId, parent);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if (mList.size() > 0)
            convert(holder, mList.get(position), position);
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView, position);
                }
            });
        }
        if (mOnItemLongClickListener != null) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int position = holder.getLayoutPosition();
                    mOnItemLongClickListener.onItemLongClick(holder.itemView, position);
                    return true; // 返回true 表示消耗了事件 事件不会继续传递
                }
            });
        }
    }

    public abstract void convert(ViewHolder holder, T t, int position);

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener mOnItemLongClickListener) {
        this.mOnItemLongClickListener = mOnItemLongClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(View view, int position);
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {

        private SparseArray<View> mViews;
        private View mConvertView;

        public ViewHolder(View itemView) {
            super(itemView);
            mConvertView = itemView;
            mViews = new SparseArray<>();
        }

        public static ViewHolder getInstance(Context context, int layoutId, ViewGroup parent) {
            View itemView = LayoutInflater.from(context).inflate(layoutId, parent, false);
            ViewHolder holder = new ViewHolder(itemView);
            return holder;
        }

        public <T extends View> T getView(int viewId) {
            View view = mViews.get(viewId);
            if (view == null) {
                view = mConvertView.findViewById(viewId);
                mViews.put(viewId, view);
            }
            return (T) view;
        }

    }

    /**
     * 设置数据
     *
     * @param list
     */
    public void setData(List<T> list) {
        this.mList = list;
        int startPosition = getItemCount();
        notifyItemRangeInserted(startPosition, mList.size());
    }

    /**
     * 清除数据
     */
    public void clearData() {
        mList.clear();
        notifyDataSetChanged();
    }


    /**
     * 删除单条数据
     *
     * @param position
     */
    public void removeData(int position) {
        mList.remove(position);
        //删除动画
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

    /**
     * 添加单条数据
     *
     * @param t
     * @param position
     */
    public void addData(T t, int position) {
        if (t != null) {
            mList.add(position, t);
            notifyItemInserted(position);
        }
    }

}
