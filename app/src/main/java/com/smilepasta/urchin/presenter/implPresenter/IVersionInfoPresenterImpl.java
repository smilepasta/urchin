package com.smilepasta.urchin.presenter.implPresenter;

import com.smilepasta.urchin.bean.req.VersionReqBean;
import com.smilepasta.urchin.bean.resp.VersionRespBean;
import com.smilepasta.urchin.http.UrchinHttpManager;
import com.smilepasta.urchin.http.service.UrchinService;
import com.smilepasta.urchin.presenter.IVersionInfoPresenter;
import com.smilepasta.urchin.presenter.implView.IVersionInfoView;

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
public class IVersionInfoPresenterImpl extends BasePresenterImpl implements IVersionInfoPresenter {

    private IVersionInfoView view;

    public IVersionInfoPresenterImpl(IVersionInfoView view) {
        this.view = view;
    }

    @Override
    public void getVersionInfo(VersionReqBean versionReqBean) {
        view.showProgressDialog();
        Subscription s = UrchinHttpManager.createService(UrchinService.class)
                .getVersionInfo(versionReqBean)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<VersionRespBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.hideProgressDialog();
                        view.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(VersionRespBean dataBean) {
                        view.hideProgressDialog();
                        view.getVersionInfo(dataBean);
                    }
                });
        addSubscription(s);

    }
}
