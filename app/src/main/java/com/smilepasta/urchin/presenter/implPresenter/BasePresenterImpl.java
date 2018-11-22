package com.smilepasta.urchin.presenter.implPresenter;

import com.smilepasta.urchin.presenter.BasePresenter;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Author: huangxiaoming
 * Date: 2018/11/20
 * Desc:
 * Version: 1.0
 */
public class BasePresenterImpl implements BasePresenter {

    private CompositeSubscription mCompositeSubscription;

    protected void addSubscription(Subscription s) {
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeSubscription();
        }
        this.mCompositeSubscription.add(s);
    }

    @Override
    public void unsubscrible() {

        // TODO: 18/4/3 find when unsubscrible
        if (this.mCompositeSubscription != null) {
            this.mCompositeSubscription.unsubscribe();
        }
    }
}
