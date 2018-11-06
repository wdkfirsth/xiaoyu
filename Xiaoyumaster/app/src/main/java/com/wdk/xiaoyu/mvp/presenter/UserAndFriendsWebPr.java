package com.wdk.xiaoyu.mvp.presenter;

import com.wdk.xiaoyu.mvp.contract.UserAndFriendsWebCons;
import com.wdk.xiaoyu.mvp.model.UserAndFriendsIdModel;
import com.wdk.xiaoyu.mvp.model.UserAndFriendsWebModel;
import com.wdk.xiaoyu.net.publicWebBean.GetPublicWebs;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @class name: UserAndFriendsWebPr
 * @anthor ：卫士
 * @time :Create on 2018/10/30  19:22
 */
public class UserAndFriendsWebPr {
    private UserAndFriendsWebModel model;
    private UserAndFriendsWebCons.View view;
    public UserAndFriendsWebPr(UserAndFriendsWebModel model,UserAndFriendsWebCons.View view){
        this.model=model;
        this.view=view;
    }

    public void getData(String s1,long id){
        Observable<GetPublicWebs.GetPublicWeb> getPublicWebObservable=model.GetUserAndFriendsWeb(s1,id);
        getPublicWebObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<GetPublicWebs.GetPublicWeb>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GetPublicWebs.GetPublicWeb getPublicWeb) {
                        view.getDataSuccess(getPublicWeb);
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
