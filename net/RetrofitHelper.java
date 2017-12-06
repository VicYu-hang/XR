package com.biwe.day08.net;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by VicYu on 2017/12/6.
 */

public class RetrofitHelper {

    private static OkHttpClient okHttpClient;
    private static ServerApi serverApi;

    static {
        initOkHttpClient();
    }

    public static OkHttpClient initOkHttpClient() {

        if (okHttpClient == null) {
            synchronized (OkHttpClient.class) {
                if (okHttpClient == null) {
                    okHttpClient = new OkHttpClient.Builder()
                            .build();
                }
            }
        }
        return okHttpClient;
    }

    public static ServerApi getServerApi() {

        if (serverApi==null) {
            synchronized (ServerApi.class){
                if(serverApi==null){
                    serverApi=onCreateApi(ServerApi.class,Api.HOST);
                }
            }
        }
        return serverApi;

    }

    public static <T> T onCreateApi(Class<T> tClass, String url) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(tClass);
    }
}
