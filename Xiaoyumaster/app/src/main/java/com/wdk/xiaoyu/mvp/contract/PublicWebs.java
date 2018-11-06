package com.wdk.xiaoyu.mvp.contract;


import com.wdk.xiaoyu.net.publicWebBean.GetPublicWebs;

import java.util.List;

import io.reactivex.Observable;

/**
 * @class name: PublicWebs
 * @anthor ：卫士
 * @time :Create on 2018/10/29  5:12
 */
public class PublicWebs {
    public interface View{
        void getDataSuccess(GetPublicWebs getPublicWebs);
        void getDataFail(Throwable throwable);
    }
    public interface Model{
        public Observable<GetPublicWebs> GetPublicWebs(String s1,int page);
    }
}
