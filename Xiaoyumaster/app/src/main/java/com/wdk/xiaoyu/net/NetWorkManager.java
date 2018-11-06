package com.wdk.xiaoyu.net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @class name: NetWorkManager
 * @anthor ：卫士
 * @time :Create on 2018/10/22  22:25
 */
public class NetWorkManager {
    private static NetWorkManager mInstance;
    private static Retrofit retrofit;
    private static volatile Request request = null;

    public static NetWorkManager getInstance(){
        if(mInstance == null){
            synchronized (NetWorkManager.class){
                if(mInstance == null){
                    mInstance = new NetWorkManager();
                }
            }
        }
        return  mInstance;
    }

    /**
     * 初始化必要对象和参数;
     */
    public void init(){
        OkHttpClient client = new OkHttpClient.Builder()
                .build();
        //初始化Retrofit;
        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(Request.USERURL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }
    public static Request getRequest(){
        if(request == null){
            synchronized (Request.class){
                request = retrofit.create(Request.class);
            }
        }
        return request;
    }
}
