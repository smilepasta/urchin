package com.smilepasta.urchin.presenter.implPresenter;

import com.smilepasta.urchin.bean.ZhiHuNewsBean;
import com.smilepasta.urchin.http.HttpManager;
import com.smilepasta.urchin.http.service.ZhiHuService;
import com.smilepasta.urchin.presenter.IBeforeNewsPresenter;
import com.smilepasta.urchin.presenter.implView.IBeforeNewsView;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Author: huangxiaoming
 * Date: 2018/11/20
 * Desc:
 * Version: 1.0
 */
public class IBeforeNewsPresenterImpl extends BasePresenterImpl implements IBeforeNewsPresenter {

    private IBeforeNewsView view;

    public IBeforeNewsPresenterImpl(IBeforeNewsView view) {
        this.view = view;
    }

    @Override
    public void getBeforeNews(int pageDate) {
        view.showProgressDialog();
        Subscription s = HttpManager.createService(ZhiHuService.class)
                .getBeforeNews(pageDate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ZhiHuNewsBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.hideProgressDialog();
                        view.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(ZhiHuNewsBean dataBean) {
                        view.hideProgressDialog();
                        view.getBeforeNews(dataBean);
                    }
                });
        addSubscription(s);
    }
}
