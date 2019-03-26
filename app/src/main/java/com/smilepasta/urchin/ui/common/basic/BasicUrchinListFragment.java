package com.smilepasta.urchin.ui.common.basic;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smilepasta.urchin.R;
import com.smilepasta.urchin.ui.common.viewholder.LoadMoreFooter;
import com.smilepasta.urchin.utils.UIUtil;
import com.smilepasta.urchin.widget.recyclerview.BasicRecyclerAdapter;
import com.smilepasta.urchin.widget.recyclerview.CustomDividerItemDecoration;
import com.smilepasta.urchin.widget.recyclerview.CustomRecyclerView;
import com.smilepasta.urchin.widget.recyclerview.WrapContentLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: huangxiaoming
 * Date: 2018/5/9
 * Desc: 知乎接口分页需要继承的basic fragment
 * Version: 1.0
 */
public abstract class BasicUrchinListFragment<T> extends BasicFragment implements LoadMoreFooter.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    private static final int DEFAULT_PAGE_INDEX = 1;
    private static final int DEFAULT_PAGE_SIZE = 5;

    //当前fragment所在的layout
    private View rootView;
    //状态布局
    public LinearLayout statusLayout;
    //显示没有加载出数据的原因
    public TextView loadStatusTextView;
    //重新获取
    public AppCompatButton loadRetryBtn;
    //列表
    public CustomRecyclerView listView;
    //通用的adapter
    public BasicRecyclerAdapter<T> basicAdapter;
    //下拉刷新
    public SwipeRefreshLayout swipeRefreshLayout;
    //加载更多的底部view
    public LoadMoreFooter loadMoreFooter;
    //是否已经初始化数据
    public boolean isInitData = false;
    //第几页,默认第一页
    public int pageIndex = DEFAULT_PAGE_INDEX;
    //一页几条，默认5条
    public int pageSize = DEFAULT_PAGE_SIZE;

    //列表数据集
    public List<T> mDataList = new ArrayList<>();

    /**
     * 初始化公共的view
     */
    public void initListView() {
        //init listview
        listView = (CustomRecyclerView) rootView.findViewById(R.id.rv_list);
        listView.setLayoutManager(new WrapContentLinearLayoutManager(mContext, LinearLayout.VERTICAL, false));

        //init loadmorefooter
        loadMoreFooter = new LoadMoreFooter(mContext, listView, this);

        //init swiperefreshlayout
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.srl_refresh);
        swipeRefreshLayout.setColorSchemeColors(
                UIUtil.getColor(R.color.colorPrimaryDark), UIUtil.getColor(R.color.orange_1), UIUtil.getColor(R.color.green_1));
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
    public void setListAdapter(BasicRecyclerAdapter<T> adapter) {
        if (adapter != null && listView != null) {
            this.basicAdapter = adapter;
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
        pageIndex = DEFAULT_PAGE_INDEX;
        onChildRefresh();
    }

    /**
     * 处理页面加载成功后
     *
     * @param dataList 当前页的数据集
     */
    public void processPageLoadSuccess(List<T> dataList) {
        if (!isInitData) {
            //初始化页面时获取到了数据做处理
            basicAdapter.clearData();
            if (dataList != null && dataList.size() > 0) {
                //初始化获取到了数据
                showSuccessStatus(dataList);
            } else {
                //初始化没有获取到数据
                showNotDataStatus();
            }
            hideSwipeRefreshLayout();
        } else {
            //加载更多页数据处理
            if (dataList != null) {
                showMoreDataStatus(dataList);
            }else{
                showMoreDataStatus(new ArrayList<>());
            }
        }
        pageIndex++;
    }

    /**
     * 加载更多页数据处理
     *
     * @param dataList 当前数据集
     */
    private void showMoreDataStatus(List<T> dataList) {
        mDataList.addAll(dataList);
        basicAdapter.setData(mDataList);
        hideSwipeRefreshLayout();
        //判断下一页是否有数据，如果没有数据，就显示加载完成，如果有数据，就显示加载中
        if (dataList.size() == 0) {
            loadMoreFooter.setState(LoadMoreFooter.STATE_FINISHED);
        } else {
            loadMoreFooter.setState(LoadMoreFooter.STATE_ENDLESS);
        }
    }

    /**
     * 初始化时暂无数据显示
     */
    public void showNotDataStatus() {
        statusLayout.setVisibility(View.VISIBLE);
        listView.setVisibility(View.GONE);
        swipeRefreshLayout.setNestedScrollingEnabled(false);
        loadStatusTextView.setText(getString(R.string.tips_4));
        loadRetryBtn.setText(getString(R.string.refresh));
    }

    /**
     * 初始化时获取数据成功
     *
     * @param dataList 当前数据集
     */
    public void showSuccessStatus(List<T> dataList) {
        isInitData = true;
        listView.setVisibility(View.VISIBLE);
        statusLayout.setVisibility(View.GONE);
        mDataList.addAll(dataList);
        basicAdapter.setData(mDataList);
        loadMoreFooter.setState(LoadMoreFooter.STATE_ENDLESS);
    }

    /**
     * 处理页面加载失败
     *
     * @param error 提示给用户的错误信息
     */
    public void processPageLoadFailed(String error) {
        if (!isInitData) {
            //加载第一页时出现错误的处理方式
            showFailedStatus(error);
        } else {
            //加载更多页时出现错误的处理方式
            loadMoreFooter.setErrorStatus(error);
        }
    }

    /**
     * 加载失败时显示
     */
    public void showFailedStatus(String error) {
        statusLayout.setVisibility(View.VISIBLE);
        loadRetryBtn.setVisibility(View.VISIBLE);
        loadStatusTextView.setText(error);
        loadRetryBtn.setText(getString(R.string.retry));

        swipeRefreshLayout.setVisibility(View.GONE);
    }

    /**
     * 下拉刷新
     */
    protected abstract void onChildRefresh();

    /**
     * 加载更多
     */
    protected abstract void onChildLoadMore();

    /**
     * 获取布局id
     *
     * @return 布局id
     */
    protected abstract int getLayoutId();

    /**
     * 初始化布局
     */
    protected abstract void initView(View view);

    /**
     * 初始化数据
     */
    protected abstract void initData();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(getLayoutId(), container, false);
            initView(rootView);
        }
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (rootView != null) {
            ((ViewGroup) rootView.getParent()).removeView(rootView);
        }
    }

}
