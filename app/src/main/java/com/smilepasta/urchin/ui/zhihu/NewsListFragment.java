package com.smilepasta.urchin.ui.zhihu;

import android.content.Intent;
import android.view.View;

import com.smilepasta.urchin.R;
import com.smilepasta.urchin.presenter.implPresenter.BeforeNewsPresenterImpl;
import com.smilepasta.urchin.presenter.implView.IBeforeNewsView;
import com.smilepasta.urchin.ui.common.basic.BasicZhiHuListFragment;
import com.smilepasta.urchin.widget.recyclerview.BasicRecyclerAdapter;

/**
 * Author: huangxiaoming
 * Date: 2018/11/22
 * Desc: 知乎数据列表
 * Version: 1.0
 */
public class NewsListFragment extends BasicZhiHuListFragment<ZhiHuNewsBean.StoriesBean> implements IBeforeNewsView {

    private BeforeNewsPresenterImpl latestNewsPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.include_list_view;
    }

    @Override
    protected void initView(View view) {
        initListView();
        basicAdapter = new NewsAdapter(mContext, mDataList);
        basicAdapter.setOnItemClickListener(new BasicRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(mContext, NewsDetailActivity.class);
                ZhiHuNewsBean.StoriesBean storiesBean = mDataList.get(position);
                if (storiesBean != null) {
                    intent.putExtra(NewsDetailActivity.KEY_DETAIL_ID, storiesBean.getId());
                }
                startActivity(intent);
            }
        });
        setListAdapter(basicAdapter);
    }

    @Override
    public void initData() {
        latestNewsPresenter = new BeforeNewsPresenterImpl(this, mContext);
        doGetLatestNews();
    }

    private boolean isFirst = true;

    /**
     * 获取数据成功
     *
     * @param dataBean 返回的数据实体
     */
    @Override
    public void getBeforeNews(ZhiHuNewsBean dataBean) {
        if (dataBean != null) {
            processPageLoadSuccess(dataBean.getStories());
        }
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void hideProgressDialog() {

    }

    @Override
    protected void onChildRefresh() {
        doGetLatestNews();
    }

    @Override
    protected void onChildLoadMore() {
        doGetLatestNews();
    }

    /**
     * 错误处理
     *
     * @param code  错误码
     * @param error 错误信息
     */
    @Override
    public void showError(int code, String error) {
        processPageLoadFailed(error);
    }

    /**
     * 获取数据
     */
    private void doGetLatestNews() {
        latestNewsPresenter.getBeforeNews(pageDate);
    }

}
