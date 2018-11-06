package com.wdk.xiaoyu;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.widget.Toast;

import com.wdk.xiaoyu.base.BaseActivity;
import com.wdk.xiaoyu.mvp.view.LoginActivity;

public class MainActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener{
//    private  SwipeRefreshLayout mFresh;
    @Override
    public void onRefresh() {
//        Toast.makeText(this,"刷新成功！",Toast.LENGTH_SHORT).show();
//        if(mFresh.isRefreshing()){
//            //如果正在刷新
//            mFresh.setRefreshing(false);//取消刷新
//        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        mFresh=(SwipeRefreshLayout)findViewById(R.id.refresh);
//        //设置下拉箭头颜色
//        mFresh.setColorSchemeResources(android.R.color.holo_red_light);
//        //设置下拉背景色
//        mFresh.setProgressBackgroundColorSchemeResource(android.R.color.white);
//        mFresh.setOnRefreshListener(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
            }
        }).start();
    }
}
