package com.smilepasta.urchin.presenter.implPresenter;

import android.content.Context;

import com.smilepasta.urchin.bean.req.AddImageReqBean;
import com.smilepasta.urchin.bean.resp.base.BaseRespBean;
import com.smilepasta.urchin.http.UrchinHttpManager;
import com.smilepasta.urchin.http.service.UrchinService;
import com.smilepasta.urchin.presenter.IAddImagePresenter;
import com.smilepasta.urchin.presenter.base.BasePresenterImpl;
import com.smilepasta.urchin.presenter.callback.ApiCallback;
import com.smilepasta.urchin.presenter.callback.ObserverCallBack;
import com.smilepasta.urchin.presenter.implView.IAddImageView;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Author: huangxiaoming
 * Date: 2018/11/20
 * Desc:
 * Version: 1.0
 */
public class AddImagePresenterImpl extends BasePresenterImpl implements IAddImagePresenter {

    private IAddImageView view;

    public AddImagePresenterImpl(IAddImageView view, Context context) {
        super(context);
        this.view = view;
    }

    @Override
    public void addImage(AddImageReqBean addImageReqBean) {
        view.showProgressDialog();
        Subscription s = UrchinHttpManager.createService(UrchinService.class)
                .addImage(addImageReqBean)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ObserverCallBack<>(context, new ApiCallback<BaseRespBean>() {
                    @Override
                    public void onSuccess(BaseRespBean model) {
                        view.addImage();
                    }

                    @Override
                    public void onFailure(int code, String msg) {
                        view.showError(code, msg);
                    }

                    @Override
                    public void onCompleted() {
                        view.hideProgressDialog();
                    }
                }));
        addSubscription(s);
    }
}
