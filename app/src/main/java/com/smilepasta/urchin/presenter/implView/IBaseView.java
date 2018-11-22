package com.smilepasta.urchin.presenter.implView;

/**
 * Author:huangxiaoming
 * Date:2018/4/3 0003
 * Desc:
 * Version:
 */
public interface IBaseView {
    void showProgressDialog();

    void hideProgressDialog();

    void showError(String error);
}