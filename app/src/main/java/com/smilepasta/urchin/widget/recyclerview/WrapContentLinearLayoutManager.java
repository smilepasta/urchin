package com.smilepasta.urchin.widget.recyclerview;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Author:huangxiaoming
 * Date:2018/4/11 0011
 * 主要用来处理这个问题：
 * Desc:java.lang.IndexOutOfBoundsException: Inconsistency detected. Invalid view holder adapter positionViewHolder{2064e5c6 position=2 id=-1, oldPos=2, pLpos:-1 scrap [attachedScrap] tmpDetached no parent}
 * Version:
 */
public class WrapContentLinearLayoutManager extends LinearLayoutManager {

    private boolean isScrollEnable = true;

    public WrapContentLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public WrapContentLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        try {
            super.onLayoutChildren(recycler, state);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean canScrollVertically() {
        return isScrollEnable && super.canScrollVertically();
    }

    /**
     * 设置 RecyclerView 是否可以垂直滑动
     *
     * @param isEnable
     */
    public void setScrollEnable(boolean isEnable) {
        this.isScrollEnable = isEnable;
    }

}
