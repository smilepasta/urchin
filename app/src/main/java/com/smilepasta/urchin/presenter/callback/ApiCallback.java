package com.smilepasta.urchin.presenter.callback;

/**
 * Author: huangxiaoming
 * Date: 2019/3/4
 * Desc:
 * Version: 1.0
 */
public interface ApiCallback<T> {

    /**
     * 成功
     *
     * @param model
     */
    void onSuccess(T model);

    /**
     * 失败时请求失败错误码和服务器返回的自定义错误码处理
     *
     * @param code 错误码
     * @param msg  如果是请求失败错误码，则用本地自己写意的msg，如果是服务器返回的自定义错误码刚用服务器返回的msg
     */
    void onFailure(int code, String msg);

    /**
     * 请求完成时执行
     */
    void onCompleted();
}
