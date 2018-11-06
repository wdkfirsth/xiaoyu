package com.wdk.xiaoyu.mvp.presenter;

import android.util.Log;

import com.wdk.xiaoyu.mvp.contract.UserShowCons;
import com.wdk.xiaoyu.net.userShowBean.GetUserBean;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @class name: UserShowPr
 * @anthor ：卫士
 * @time :Create on 2018/10/23  3:34
 */
public class UserShowPr  {
    private UserShowCons.View view;
    private UserShowCons.Model model;
    public UserShowPr(UserShowCons.Model model,UserShowCons.View view){
        this.view=view;
        this.model=model;
    }
    public void GetData(String s1,long uid){
        Observable<GetUserBean> getUserBeanObservable=model.GetUserShows(s1,uid);
            getUserBeanObservable.observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Observer<GetUserBean>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(GetUserBean getUserBean) {
                            view.getDataSuccess(getUserBean);
                        }

                        @Override
                        public void onError(Throwable e) {
                            view.getDataFail(e);

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
    }
}
