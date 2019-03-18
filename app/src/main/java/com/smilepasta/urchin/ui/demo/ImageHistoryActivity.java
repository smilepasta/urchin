package com.smilepasta.urchin.ui.demo;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.smilepasta.urchin.R;
import com.smilepasta.urchin.bean.resp.ImageRespBean;
import com.smilepasta.urchin.presenter.implPresenter.GetImagePresenterImpl;
import com.smilepasta.urchin.presenter.implView.IGetImageView;
import com.smilepasta.urchin.ui.common.basic.TextBarActivity;
import com.smilepasta.urchin.utils.DialogUtil;
import com.smilepasta.urchin.utils.ToastUtil;

/**
 * Author: huangxiaoming
 * Date: 2019/3/18
 * Desc: 图片上传历史
 * Version: 1.0
 */
public class ImageHistoryActivity extends TextBarActivity implements IGetImageView {


    private TextView imageTextView;

    @Override
    protected void menuIconAction(Bundle bundle) {

    }

    private void initView() {
        imageTextView = findViewById(R.id.tv_image);
    }

    private void initData() {
        GetImagePresenterImpl getImagePresenter = new GetImagePresenterImpl(this, this);
        getImagePresenter.getImage();
    }

    @Override
    public void getImage(ImageRespBean imageRespBean) {
        imageTextView.setText(imageRespBean.toString());
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
