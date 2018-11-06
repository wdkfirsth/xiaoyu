package com.wdk.xiaoyu.base;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.blankj.utilcode.util.BarUtils;
import com.githang.statusbar.StatusBarCompat;

/**
 * @class name: BaseActivity
 * @anthor ：卫士
 * @time :Create on 2018/10/15  3:56
 */
public class BaseActivity extends AppCompatActivity {
    private  BaseActivity mContext=null;
    private Toast mToast=null;
    private  MyApp myApp=null;
    private static final int BLACK= Color.parseColor("#000000");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=this;

        if(myApp == null){
            myApp= (MyApp) getApplication();
        }
//        StatusBarCompat.setStatusBarColor(mContext,BLACK);
        addActivitys();


    }

    //增加activity
    public void addActivitys(){
        myApp.addActivity(mContext);
    }
    //移除activity
    public void removeActivitys(){
        myApp.removeActivity(mContext);
    }
    //移除所有activity
    public void removeAllActivitys(){
        myApp.exitApp();
    }
    //自定义Toast
    public void show_toast(String text){
        if(mToast == null){
            mToast=Toast.makeText(mContext,text,Toast.LENGTH_SHORT);
        }else {
            mToast.setText(text);
        }
        mToast.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        removeActivitys();
    }
}
