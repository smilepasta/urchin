package com.smilepasta.urchin.http;

import com.smilepasta.urchin.http.interceptor.LoggingInerceptor;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Author:huangxiaoming
 * Date:2018/4/2
 * Desc:
 * Version:
 */
public class HttpManager {

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(UrlParser.HOST)
            .addConverterFactory(GsonConverterFactory.create())//加入json解析
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create()); // 使用RxJava作为回调适配器

//    private static File httpCacheDirectory = new File(GasApp.getInstance().getCacheDir(), "gasCache");
//    private static int cacheSize = 10 * 1024 * 1024; // 10 MiB
//    private static Cache cache = new Cache(httpCacheDirectory, cacheSize);

    public static <T> T createService(Class<T> serviceClass) {
        return createService(serviceClass, null);
    }

    public static <T> T createService(Class<T> serviceClass, final String token) {
        if (token != null) {
            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Interceptor.Chain chain) throws IOException {
                    Request original = chain.request();
                    Request.Builder requestBuilder = original.newBuilder()
                            .header("Access-Token", token)
                            .method(original.method(), original.body());

                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });
        }

        OkHttpClient client = httpClient
//                .addNetworkInterceptor(CacheInterceptor.getInstence())
//                .addInterceptor(CacheInterceptor.getInstence())
                .connectTimeout(60, TimeUnit.SECONDS)       //设置连接超时
                .readTimeout(60, TimeUnit.SECONDS)          //设置读超时
                .writeTimeout(60, TimeUnit.SECONDS)          //设置写超时
//                .retryOnConnectionFailure(true)             //是否自动重连
                .addInterceptor(LoggingInerceptor.getInstence())
                .build();
        Retrofit retrofit = builder
                .client(client)
                .build();
        return retrofit.create(serviceClass);
    }
}
