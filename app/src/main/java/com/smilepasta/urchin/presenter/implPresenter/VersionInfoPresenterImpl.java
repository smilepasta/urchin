package com.smilepasta.urchin.presenter.implPresenter;

import com.smilepasta.urchin.bean.req.VersionReqBean;
import com.smilepasta.urchin.bean.resp.VersionRespBean;
import com.smilepasta.urchin.http.UrchinHttpManager;
import com.smilepasta.urchin.http.service.UrchinService;
import com.smilepasta.urchin.presenter.IVersionInfoPresenter;
import com.smilepasta.urchin.presenter.base.BasePresenterImpl;
import com.smilepasta.urchin.presenter.callback.ApiCallback;
import com.smilepasta.urchin.presenter.callback.ObserverCallBack;
import com.smilepasta.urchin.presenter.implView.IVersionInfoView;
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
public class VersionInfoPresenterImpl extends BasePresenterImpl implements IVersionInfoPresenter {

    private IVersionInfoView view;

    public VersionInfoPresenterImpl(IVersionInfoView view) {
        this.view = view;
    }

    @Override
    public void getVersionInfo(VersionReqBean versionReqBean) {
        view.showProgressDialog();
        Subscription s = UrchinHttpManager.createService(UrchinService.class)
                .getVersionInfo(versionReqBean)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ObserverCallBack<>(new ApiCallback<VersionRespBean>() {
                    @Override
                    public void onSuccess(VersionRespBean model) {
                        view.getVersionInfo(model);
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
