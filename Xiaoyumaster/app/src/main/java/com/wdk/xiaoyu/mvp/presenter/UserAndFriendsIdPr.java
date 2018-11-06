package com.wdk.xiaoyu.mvp.presenter;

import com.wdk.xiaoyu.mvp.contract.UserAndFriendsIdCons;
import com.wdk.xiaoyu.mvp.model.UserAndFriendsIdModel;
import com.wdk.xiaoyu.net.publicWebBean.GetPublicWebs;
import com.wdk.xiaoyu.net.userandfriendsbean.GetUserAndFriendWebId;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @class name: UserAndFriendsIdPr
 * @anthor ：卫士
 * @time :Create on 2018/10/30  18:59
 */
public class UserAndFriendsIdPr {
    private UserAndFriendsIdModel model;
    private UserAndFriendsIdCons.View view;
    public  UserAndFriendsIdPr(UserAndFriendsIdModel model,UserAndFriendsIdCons.View view){
        this.model=model;
        this.view=view;
    }
    public void getData(String s1){
        Observable<GetUserAndFriendWebId> getUserAndFriendWebIdObservable=model.GetUserAndFriendsId(s1);
        getUserAndFriendWebIdObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<GetUserAndFriendWebId>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GetUserAndFriendWebId getUserAndFriendWebId) {
                        view.getDataSuccess(getUserAndFriendWebId);
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
