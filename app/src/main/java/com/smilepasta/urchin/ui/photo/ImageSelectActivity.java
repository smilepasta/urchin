package com.smilepasta.urchin.ui.photo;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.smilepasta.urchin.R;
import com.smilepasta.urchin.bean.req.AddImageReqBean;
import com.smilepasta.urchin.presenter.implPresenter.AddImagePresenterImpl;
import com.smilepasta.urchin.presenter.implView.IAddImageView;
import com.smilepasta.urchin.ui.common.basic.TextBarActivity;
import com.smilepasta.urchin.utils.DialogUtil;
import com.smilepasta.urchin.utils.StringUtil;
import com.smilepasta.urchin.utils.ToastUtil;
import com.smilepasta.urchin.utils.UIUtil;
import com.smilepasta.urchin.widget.ClearableEditText;
import com.smilepasta.urchin.widget.imageselectview.ImageSelectAdapter;
import com.smilepasta.urchin.widget.imageselectview.ImageSelectView;
import com.zhihu.matisse.Matisse;

import java.util.List;

/**
 * Author: huangxiaoming
 * Date: 2019/3/15
 * Desc: 图片上传七牛
 * Version: 1.0
 */
public class ImageSelectActivity extends TextBarActivity implements IAddImageView {

    private ImageSelectView imageSelectView;
    private AddImagePresenterImpl addImagePresenter;
    private ClearableEditText titleEditText;

    private void initView() {
        titleEditText = findViewById(R.id.et_title);

        imageSelectView = findViewById(R.id.image_select_layout);
        findViewById(R.id.btn_upload).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
            }
        });

        imageSelectView.initView(this);
        imageSelectView.setImageUploadQiniuListener(new ImageSelectView.IImageUploadQiniuListener()

        {
            @Override
            public void uploadSuccess(String imageUrls) {
                if (StringUtil.isNotEmpty(imageUrls)) {
                    AddImageReqBean addImageReqBean = new AddImageReqBean();
                    addImageReqBean.setImages(imageUrls);
                    addImageReqBean.setDesc(titleEditText.getText().toString().trim());
                    addImagePresenter.addImage(addImageReqBean);
                }
            }

            @Override
            public void uploadFailed(String error) {
                ToastUtil.show(ImageSelectActivity.this, error);
            }
        });
    }


    private void initData() {
        addImagePresenter = new AddImagePresenterImpl(this, this);
    }

    private void uploadImage() {
        if (!StringUtil.isNotEmpty(titleEditText.getText().toString().trim())) {
            ToastUtil.show(ImageSelectActivity.this, UIUtil.getString(R.string.tips_25));
            return;
        }
        if (imageSelectView.isNoPresenceImage()) {
            ToastUtil.show(ImageSelectActivity.this, UIUtil.getString(R.string.tips_23));
            return;
        }
        showConfirmSubmitDialog(new IConfirmSubmitLisener() {
            @Override
            public void confirmSubmit() {
                imageSelectView.uploadImage();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == ImageSelectAdapter.OPEN_GALLERY) {
                List<Uri> uriList = Matisse.obtainResult(data);
                List<String> pathList = Matisse.obtainPathResult(data);
                if (uriList != null && uriList.size() > 0 && pathList != null && pathList.size() > 0) {
                    imageSelectView.addImage(uriList, pathList);
                }
            }
        }
    }

    @Override
    protected void menuIconAction(Bundle bundle) {
    }

    public static void start(Activity activity) {
        activity.startActivity(new Intent(activity, ImageSelectActivity.class));
    }

    @Override
    public void addImage() {
        ToastUtil.show(this, getString(R.string.upload_success));
        finish();
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDefaultConfig(R.layout.activity_image_select, getString(R.string.image_upload));
        initView();
        initData();
    }


}
