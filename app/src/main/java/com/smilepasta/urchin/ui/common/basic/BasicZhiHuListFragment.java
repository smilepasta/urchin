package com.smilepasta.urchin.ui.common.basic;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smilepasta.urchin.R;
import com.smilepasta.urchin.ui.common.viewholder.LoadMoreFooter;
import com.smilepasta.urchin.utils.DateUtil;
import com.smilepasta.urchin.utils.LogUtil;
import com.smilepasta.urchin.utils.UIUtil;
import com.smilepasta.urchin.widget.recyclerview.CustomDividerItemDecoration;
import com.smilepasta.urchin.widget.recyclerview.CustomRecyclerView;
import com.smilepasta.urchin.widget.recyclerview.WrapContentLinearLayoutManager;

/**
 * Author: huangxiaoming
 * Date: 2018/5/9
 * Desc:
 * Version: 1.0
 */
public abstract class BasicZhiHuListFragment extends BasicFragment implements LoadMoreFooter.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    public TextView loadStatusTextView;
    public AppCompatButton loadRetryBtn;
    public CustomRecyclerView listView;
    public SwipeRefreshLayout swipeRefreshLayout;
    public LinearLayout statusLayout;

    public LoadMoreFooter loadMoreFooter;
    public View rootView;
    public boolean isInitData = false;
    public int pageDate = DateUtil.getPageDate();

    /**
     * 初始化公共的view
     */
    public void initParentView() {
        //init listview
        listView = (CustomRecyclerView) rootView.findViewById(R.id.rv_list);
        listView.setLayoutManager(new WrapContentLinearLayoutManager(mContext, LinearLayout.VERTICAL, false));
        //init loadmorefooter
        loadMoreFooter = new LoadMoreFooter(mContext, listView, this);

        //init swiperefreshlayout
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.srl_refresh);
        swipeRefreshLayout.setColorSchemeColors(
                UIUtil.getColor(R.color.colorPrimaryDark), UIUtil.getColor(R.color.colorPrimary), UIUtil.getColor(R.color.colorAccent));
        swipeRefreshLayout.setOnRefreshListener(this);

        //init status layout
        loadStatusTextView = rootView.findViewById(R.id.tv_status_text);
        loadRetryBtn = rootView.findViewById(R.id.btn_retry);
        loadRetryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swipeRefreshLayout.setVisibility(View.VISIBLE);
                listView.setVisibility(View.GONE);
                swipeRefreshLayout.setRefreshing(true);
                onRefresh();
            }
        });
        statusLayout = rootView.findViewById(R.id.ll_status);
    }

    /**
     * 暂无数据时显示
     */
    public void showNotDataStatus() {
        statusLayout.setVisibility(View.VISIBLE);
        loadRetryBtn.setVisibility(View.GONE);
        loadStatusTextView.setText(UIUtil.getString(mContext, R.string.tips_4));

        swipeRefreshLayout.setVisibility(View.GONE);
    }

    /**
     * 加载失败时显示
     */
    public void showFailedStatus() {
        statusLayout.setVisibility(View.VISIBLE);
        loadRetryBtn.setVisibility(View.VISIBLE);
        loadStatusTextView.setText(UIUtil.getString(mContext, R.string.tips_3));

        swipeRefreshLayout.setVisibility(View.GONE);
    }

    /**
     * 加载成功显示
     */
    public void showSuccessStatus() {
        swipeRefreshLayout.setVisibility(View.VISIBLE);
        listView.setVisibility(View.VISIBLE);
        statusLayout.setVisibility(View.GONE);
    }

    /**
     * 隐藏下拉刷新按钮
     */
    public void hideSwipeRefreshLayout() {
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    /**
     * 为listview每个item加分隔线
     */
    public void setListViewLine() {
        if (listView != null) {
            CustomDividerItemDecoration divider = new CustomDividerItemDecoration(mContext, CustomDividerItemDecoration.VERTICAL);
            divider.setDrawable(UIUtil.getDrawable(R.drawable.recycler_line));
            listView.addItemDecoration(divider);
        }
    }

    /**
     * 为listview设置适配器
     *
     * @param adapter
     */
    public void setListAdapter(RecyclerView.Adapter adapter) {
        if (adapter != null && listView != null) {
            listView.setAdapter(adapter);
        }
    }

    /**
     * 加载更多
     */
    @Override
    public void onLoadMore() {
        //没有在刷新
        //并且第一页，取出数据不足一页
        if (!swipeRefreshLayout.isRefreshing()) {
            onChildLoadMore();
        }
    }

    /**
     * 刷新数据
     */
    @Override
    public void onRefresh() {
        //每一次刷新都是一次重生
        isInitData = false;
        pageDate = DateUtil.getPageDate();
        onChildRefresh();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (rootView != null) {
            ((ViewGroup) rootView.getParent()).removeView(rootView);
        }
    }

    /**
     * 显示加载错误
     */
    public void showErrorView() {
        //加载第一页时出现错误，处理方式
        if (!isInitData) {
            showFailedStatus();
//            return;
        } else {
            loadMoreFooter.setState(LoadMoreFooter.STATE_FAILED); // 加载失败了给错误状态
        }
    }

    /**
     * 设置加载中状态
     */
    public void setLoadingStatus() {
        loadMoreFooter.setState(LoadMoreFooter.STATE_ENDLESS);
    }

    /**
     * 下拉刷新
     */
    protected abstract void onChildRefresh();

    /**
     * 加载更多
     */
    protected abstract void onChildLoadMore();

    protected void checkLoadFinishStatus(int dataSize) {
        hideSwipeRefreshLayout();
        if (dataSize == 0) {
            loadMoreFooter.setState(LoadMoreFooter.STATE_FINISHED);
        } else {
            setLoadingStatus();
        }
        pageDate = DateUtil.getDecrementPageDate(pageDate);
        LogUtil.defLog(pageDate + "");
    }

    /**
     * 判断第一次获取数据是否成功，如果返回false，刚显示无数据
     *
     * @param dataSize 后台返回数据的总数
     * @return
     */
    protected boolean isFirstDataGetSuccess(int dataSize) {
        if (!isInitData) {
            isInitData = true;
            if (dataSize > 0) {
                //如果有数据，就显示初始化成功
                showSuccessStatus();
                return true;
            } else {
                //如果没有数据，就表示还没初始化，下次到这个页面时，要再更新数据
                isInitData = false;
                showNotDataStatus();
                return false;
            }
        }
        return true;
    }


}
