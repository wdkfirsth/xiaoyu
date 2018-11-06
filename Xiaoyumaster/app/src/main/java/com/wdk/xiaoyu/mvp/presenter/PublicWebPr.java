package com.wdk.xiaoyu.mvp.presenter;

import com.wdk.xiaoyu.mvp.contract.PublicWebs;
import com.wdk.xiaoyu.mvp.model.PublicWebModel;
import com.wdk.xiaoyu.net.publicWebBean.GetPublicWebs;
import com.wdk.xiaoyu.net.userShowBean.GetUserBean;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @class name: PublicWebPr
 * @anthor ：卫士
 * @time :Create on 2018/10/29  5:14
 */
public class PublicWebPr {
    private PublicWebModel model;
    private PublicWebs.View view;
    public PublicWebPr(PublicWebModel model,PublicWebs.View view){
        this.model=model;
        this.view=view;
    }


    public void getData(String s1,int page){
        Observable<GetPublicWebs> getPublicWebsObservable=model.GetPublicWebs(s1,page);
        getPublicWebsObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<GetPublicWebs>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GetPublicWebs getPublicWebs) {
                        view.getDataSuccess(getPublicWebs);
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
