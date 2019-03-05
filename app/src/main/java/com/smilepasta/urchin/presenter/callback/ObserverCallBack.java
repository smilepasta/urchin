package com.smilepasta.urchin.presenter.callback;

import android.net.ParseException;

import com.smilepasta.urchin.http.exception.ExceptionCode;

import org.json.JSONException;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.HttpException;
import rx.Observer;

/**
 * Author: huangxiaoming
 * Date: 2019/3/4
 * Desc:
 * Version: 1.0
 */
public class ObserverCallBack<T> implements Observer<T> {

    private ApiCallback<T> apiCallback;

    public ObserverCallBack(ApiCallback<T> apiCallback) {
        this.apiCallback = apiCallback;
    }

    @Override
    public void onCompleted() {
    }

    @Override
    public void onError(Throwable e) {
//        e.printStackTrace();
        int code = ExceptionCode.RESPONSECODE_1404;
        if (e instanceof UnknownHostException) {
            code = ExceptionCode.RESPONSECODE_1001;
        } else if (e instanceof SocketTimeoutException) {
            code = ExceptionCode.RESPONSECODE_1002;
        } else if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            if (httpException.code() >= 500 && httpException.code() < 600) {
                code = ExceptionCode.RESPONSECODE_1004;
            } else if (httpException.code() >= 400 && httpException.code() < 500) {
                code = ExceptionCode.RESPONSECODE_1005;
            } else if (httpException.code() >= 300 && httpException.code() < 400) {
                code = ExceptionCode.RESPONSECODE_1006;
            }
        } else if (e instanceof ParseException || e instanceof JSONException) {
            code = ExceptionCode.RESPONSECODE_1003;
        }
        //先调用onCompleted方法，取消显示当前的进度条加载框，再调用onFailure方法，处理失败效果
        //如果后调用onCompleted方法，会导致所有的Dialog会被cancel掉
        apiCallback.onCompleted();
        //因为这里e.getMessage()请求返回的异常信息不够友好，所以这里直接至为空，到ui层再去调用本地的code对应的message
        apiCallback.onFailure(code, "");

    }

    @Override
    public void onNext(T t) {
        //先调用onCompleted方法，取消显示当前的进度条加载框，再调用onSuccess方法，处理成功效果
        //如果后调用onCompleted方法，会导致所有的Dialog会被cancel掉
        apiCallback.onCompleted();
        apiCallback.onSuccess(t);
    }

}
