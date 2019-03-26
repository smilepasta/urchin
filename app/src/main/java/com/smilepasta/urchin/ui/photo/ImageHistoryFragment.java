package com.smilepasta.urchin.ui.photo;

import android.app.Activity;
import android.view.View;

import com.smilepasta.urchin.R;
import com.smilepasta.urchin.bean.req.PageReqBean;
import com.smilepasta.urchin.bean.resp.ImageRespBean;
import com.smilepasta.urchin.presenter.implPresenter.GetImagePresenterImpl;
import com.smilepasta.urchin.presenter.implView.IGetImageView;
import com.smilepasta.urchin.ui.common.basic.BasicUrchinListFragment;
import com.smilepasta.urchin.widget.recyclerview.BasicRecyclerAdapter;

/**
 * Author: huangxiaoming
 * Date: 2019/3/25
 * Desc:
 * Version: 1.0
 */
public class ImageHistoryFragment extends BasicUrchinListFragment<ImageRespBean.DataBean> implements IGetImageView {

    private GetImagePresenterImpl getImagePresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.include_list_view;
    }

    @Override
    protected void initView(View view) {
        initListView();
        ImageHistoryAdapter imageHistoryAdapter = new ImageHistoryAdapter(mContext, mDataList);
        imageHistoryAdapter.setOnItemClickListener(new BasicRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ImageRespBean.DataBean dataBean = mDataList.get(position);
                if (dataBean != null) {
                    ImageDetailActivity.start(dataBean, (Activity) mContext);
                }
            }
        });
        setListAdapter(imageHistoryAdapter);
    }

    @Override
    public void initData() {
        getImagePresenter = new GetImagePresenterImpl(this, mContext);
        doGetImage();
    }


    @Override
    public void showProgressDialog() {

    }

    @Override
    public void hideProgressDialog() {

    }

    @Override
    protected void onChildRefresh() {
        doGetImage();
    }

    @Override
    protected void onChildLoadMore() {
        doGetImage();
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
    private void doGetImage() {
        PageReqBean pageReqBean = new PageReqBean();
        pageReqBean.setPage_index(pageIndex);
        pageReqBean.setPage_size(pageSize);
        getImagePresenter.getImage(pageReqBean);
    }

    @Override
    public void getImage(ImageRespBean imageRespBean) {
        if (imageRespBean != null) {
            processPageLoadSuccess(imageRespBean.getData());
        }
    }
}
