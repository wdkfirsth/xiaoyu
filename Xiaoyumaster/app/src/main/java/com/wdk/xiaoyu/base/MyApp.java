package com.wdk.xiaoyu.base;

import android.app.Activity;
import android.app.Application;

import com.wdk.xiaoyu.net.NetWorkManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @class name: MyApp
 * @anthor ：卫士
 * @time :Create on 2018/10/15  3:58
 */
public  class MyApp extends Application {
    private List<Activity> allActivities =null;
    //全局app
   public static MyApp app=null;
    @Override
    public void onCreate() {
        super.onCreate();
        //网络工具初始化
        NetWorkManager.getInstance().init();
        app=this;

        allActivities=new ArrayList<>();

    }

    public  static MyApp getApp() {
        return app;
    }

    //添加activity
    public void addActivity(Activity activity){
        if(!allActivities.contains(activity)){
            allActivities.add(activity);
        }
    }
    //移除activity
    public void removeActivity(Activity activity){
        if (allActivities.contains(activity)) {
            allActivities.remove(activity);
            activity.finish();
        }
    }
    //删除全部activity
    public void exitApp(){
        for(Activity activity:allActivities){
            activity.finish();
        }
    }
}
