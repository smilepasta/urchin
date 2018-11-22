package com.smilepasta.urchin.http.interceptor;

import com.smilepasta.urchin.utils.LogUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Author: huangxiaoming
 * Date: 2018/11/20
 * Desc: 日志拦截器
 * Version: 1.0
 */
public class LoggingInerceptor implements Interceptor {

    private static LoggingInerceptor loggingInerceptor;

    public static LoggingInerceptor getInstence() {
        if (loggingInerceptor == null) {
            synchronized (LoggingInerceptor.class) {
                if (loggingInerceptor == null) {
                    loggingInerceptor = new LoggingInerceptor();
                }
            }
        }
        return loggingInerceptor;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        okhttp3.MediaType mediaType = response.body().contentType();

        String content = "";
        ResponseBody responseBody = response.body();
        if (responseBody != null) {
            content = responseBody.string();
            LogUtil.defLog(response.request().url().toString());
            LogUtil.defLog(content);
        }

        return response.newBuilder()
                .body(ResponseBody.create(mediaType, content))
                .build();
    }
}