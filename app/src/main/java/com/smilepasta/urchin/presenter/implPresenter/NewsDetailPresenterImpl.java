package com.smilepasta.urchin.presenter.implPresenter;

import com.smilepasta.urchin.http.HttpManager;
import com.smilepasta.urchin.http.service.ZhiHuService;
import com.smilepasta.urchin.presenter.INewsDetailPresenter;
import com.smilepasta.urchin.presenter.base.BasePresenterImpl;
import com.smilepasta.urchin.presenter.callback.ApiCallback;
import com.smilepasta.urchin.presenter.callback.ObserverCallBack;
import com.smilepasta.urchin.presenter.implView.INewsDetailView;
import com.smilepasta.urchin.ui.zhihu.ZhiHuNewsDetailBean;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Author: huangxiaoming
 * Date: 2018/11/20
 * Desc:
 * Version: 1.0
 */
public class NewsDetailPresenterImpl extends BasePresenterImpl implements INewsDetailPresenter {

    private INewsDetailView view;

    public NewsDetailPresenterImpl(INewsDetailView view) {
        this.view = view;
    }

    @Override
    public void getNewsDetail(String detailId) {
        view.showProgressDialog();
        Subscription s = HttpManager.createService(ZhiHuService.class)
                .getNewsDetail(detailId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ObserverCallBack<>(new ApiCallback<ZhiHuNewsDetailBean>() {
                    @Override
                    public void onSuccess(ZhiHuNewsDetailBean model) {
                        view.getNewsDetail(model);
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
