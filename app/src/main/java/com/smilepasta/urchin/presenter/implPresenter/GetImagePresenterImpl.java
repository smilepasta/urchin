package com.smilepasta.urchin.presenter.implPresenter;

import android.content.Context;

import com.smilepasta.urchin.bean.req.PageReqBean;
import com.smilepasta.urchin.bean.resp.ImageRespBean;
import com.smilepasta.urchin.http.UrchinHttpManager;
import com.smilepasta.urchin.http.service.UrchinService;
import com.smilepasta.urchin.presenter.IGetImagePresenter;
import com.smilepasta.urchin.presenter.base.BasePresenterImpl;
import com.smilepasta.urchin.presenter.callback.ApiCallback;
import com.smilepasta.urchin.presenter.callback.ObserverCallBack;
import com.smilepasta.urchin.presenter.implView.IGetImageView;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Author: huangxiaoming
 * Date: 2018/11/20
 * Desc:
 * Version: 1.0
 */
public class GetImagePresenterImpl extends BasePresenterImpl implements IGetImagePresenter {

    private IGetImageView view;

    public GetImagePresenterImpl(IGetImageView view, Context context) {
        super(context);
        this.view = view;
    }

    @Override
    public void getImage(PageReqBean pageReqBean) {
        view.showProgressDialog();
        Subscription s = UrchinHttpManager.createService(UrchinService.class)
                .getImage(pageReqBean)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ObserverCallBack<>(context, new ApiCallback<ImageRespBean>() {
                    @Override
                    public void onSuccess(ImageRespBean model) {
                        view.getImage(model);
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
