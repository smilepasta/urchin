package com.smilepasta.urchin.presenter.implPresenter;

import com.smilepasta.urchin.http.HttpManager;
import com.smilepasta.urchin.http.service.ZhiHuService;
import com.smilepasta.urchin.presenter.INewsDetailPresenter;
import com.smilepasta.urchin.presenter.implView.INewsDetailView;
import com.smilepasta.urchin.ui.zhihu.ZhiHuNewsDetailBean;

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
public class INewsDetailPresenterImpl extends BasePresenterImpl implements INewsDetailPresenter {

    private INewsDetailView view;

    public INewsDetailPresenterImpl(INewsDetailView view) {
        this.view = view;
    }

    @Override
    public void getNewsDetail(String detailId) {
        view.showProgressDialog();
        Subscription s = HttpManager.createService(ZhiHuService.class)
                .getNewsDetail(detailId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ZhiHuNewsDetailBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.hideProgressDialog();
                        view.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(ZhiHuNewsDetailBean dataBean) {
                        view.hideProgressDialog();
                        view.getNewsDetail(dataBean);
                    }
                });
        addSubscription(s);
    }
}
