package com.smilepasta.urchin.presenter.implPresenter;

import android.content.Context;

import com.smilepasta.urchin.http.HttpManager;
import com.smilepasta.urchin.http.service.ZhiHuService;
import com.smilepasta.urchin.presenter.IBeforeNewsPresenter;
import com.smilepasta.urchin.presenter.base.BasePresenterImpl;
import com.smilepasta.urchin.presenter.callback.ApiCallback;
import com.smilepasta.urchin.presenter.callback.ObserverCallBack;
import com.smilepasta.urchin.presenter.implView.IBeforeNewsView;
import com.smilepasta.urchin.ui.zhihu.ZhiHuNewsBean;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Author: huangxiaoming
 * Date: 2018/11/20
 * Desc:
 * Version: 1.0
 */
public class BeforeNewsPresenterImpl extends BasePresenterImpl implements IBeforeNewsPresenter {

    private IBeforeNewsView view;

    public BeforeNewsPresenterImpl(IBeforeNewsView view, Context context) {
        super(context);
        this.view = view;
    }

    @Override
    public void getBeforeNews(int pageDate) {
        view.showProgressDialog();
        Subscription s = HttpManager.createService(ZhiHuService.class)
                .getBeforeNews(pageDate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ObserverCallBack<>(context, new ApiCallback<ZhiHuNewsBean>() {
                    @Override
                    public void onSuccess(ZhiHuNewsBean model) {
                        view.getBeforeNews(model);
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
