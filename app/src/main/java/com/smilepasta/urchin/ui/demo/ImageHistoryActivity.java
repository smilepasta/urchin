package com.smilepasta.urchin.ui.demo;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.smilepasta.urchin.R;
import com.smilepasta.urchin.bean.resp.ImageRespBean;
import com.smilepasta.urchin.presenter.implPresenter.GetImagePresenterImpl;
import com.smilepasta.urchin.presenter.implView.IGetImageView;
import com.smilepasta.urchin.ui.common.basic.TextBarActivity;
import com.smilepasta.urchin.utils.DialogUtil;
import com.smilepasta.urchin.utils.ToastUtil;
import com.smilepasta.urchin.widget.recyclerview.WrapContentLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: huangxiaoming
 * Date: 2019/3/18
 * Desc: 图片上传历史
 * Version: 1.0
 */
public class ImageHistoryActivity extends TextBarActivity implements IGetImageView {

    private List<ImageRespBean.DataBean.ImageArrBean> dataList = new ArrayList<>();
    private ImageHistoryAdapter imageHistoryAdapter;

    @Override
    protected void menuIconAction(Bundle bundle) {

    }

    private void initView() {
        RecyclerView imageHistoryRecyclerView = findViewById(R.id.rv_image_history);
        imageHistoryRecyclerView.setLayoutManager(new WrapContentLinearLayoutManager(this, LinearLayout.VERTICAL, false));
        imageHistoryAdapter = new ImageHistoryAdapter(this, dataList);
        imageHistoryRecyclerView.setAdapter(imageHistoryAdapter);
    }

    private void initData() {
        GetImagePresenterImpl getImagePresenter = new GetImagePresenterImpl(this, this);
        getImagePresenter.getImage();
    }

    @Override
    public void getImage(ImageRespBean imageRespBean) {
        if (imageRespBean != null
                && imageRespBean.getData() != null
                && imageRespBean.getData().getImageArr() != null
                && imageRespBean.getData().getImageArr().size() > 0) {
            dataList = imageRespBean.getData().getImageArr();
            imageHistoryAdapter.setData(dataList);
        }
    }

    @Override
    public void showProgressDialog() {
        DialogUtil.progress(this);
    }

    @Override
    public void hideProgressDialog() {
        DialogUtil.cancel();
    }

    @Override
    public void showError(int code, String error) {
        ToastUtil.show(this, error);
    }

    public static void start(Activity activity) {
        activity.startActivity(new Intent(activity, ImageHistoryActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDefaultConfig(R.layout.activity_image_history, getString(R.string.image_history));
        initView();
        initData();
    }


}
