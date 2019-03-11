package com.smilepasta.urchin.presenter.base;

import android.content.Context;

import java.net.ContentHandler;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Author: huangxiaoming
 * Date: 2018/11/20
 * Desc:
 * Version: 1.0
 */
public class BasePresenterImpl implements BasePresenter {

    public Context context;

    private CompositeSubscription mCompositeSubscription;

    public BasePresenterImpl(Context context) {
        this.context = context;
    }

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
