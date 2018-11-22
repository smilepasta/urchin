package com.smilepasta.urchin.ui.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smilepasta.urchin.R;
import com.smilepasta.urchin.bean.ZhiHuNewsBean;
import com.smilepasta.urchin.presenter.implPresenter.IBeforeNewsPresenterImpl;
import com.smilepasta.urchin.presenter.implView.IBeforeNewsView;
import com.smilepasta.urchin.ui.common.basic.BasicZhiHuListFragment;
import com.smilepasta.urchin.ui.common.listener.IOnItemClickListener;
import com.smilepasta.urchin.ui.main.NewsDetailActivity;
import com.smilepasta.urchin.ui.main.adapter.NewsAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: huangxiaoming
 * Date: 2018/11/22
 * Desc:
 * Version: 1.0
 */
public class NewsListFragment extends BasicZhiHuListFragment implements IBeforeNewsView {

    private IBeforeNewsPresenterImpl latestNewsPresenter;
    private NewsAdapter newsAdapter;
    private List<ZhiHuNewsBean.StoriesBean> newList = new ArrayList<>();

    @Override
    protected void onChildRefresh() {
        doGetLatestNews();
    }

    @Override
    protected void onChildLoadMore() {
        doGetLatestNews();
    }

    @Override
    public void getBeforeNews(ZhiHuNewsBean dataBean) {
        //判断是否是第一次获取数据
        if (!isInitData) {
            newsAdapter.clearData();
            //判断是否第一页有数据，如果没数据就返回false
            if (!isFirstDataGetSuccess(dataBean.getStories() == null ? 0 : dataBean.getStories().size())) {
                return;
            }
        }
        if (dataBean.getStories() != null) {
            newList.addAll(dataBean.getStories());
            newsAdapter.setData(newList);
            //判断第二页的数据是否小于10条，如果小于10条，就显示加载数据完毕，有更多就设置加载中状态
            checkLoadFinishStatus(dataBean.getStories().size());
        }
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void hideProgressDialog() {

    }

    @Override
    public void showError(String error) {
        showErrorView();
    }

    private void initData() {
        latestNewsPresenter = new IBeforeNewsPresenterImpl(this);
        doGetLatestNews();
    }

    private void doGetLatestNews() {
        latestNewsPresenter.getBeforeNews(pageDate);
    }


    private void initView() {
        initParentView();
        newsAdapter = new NewsAdapter(mContext);
        newsAdapter.setOnItemClickListener(new IOnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(mContext, NewsDetailActivity.class);
                startActivity(intent);
            }
        });
        setListAdapter(newsAdapter);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.include_list_view, container, false);
            initView();
        }
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }
}
