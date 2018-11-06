package com.wdk.xiaoyu.mvp.contract;

import com.wdk.xiaoyu.net.publicWebBean.GetPublicWebs;

import io.reactivex.Observable;

/**
 * @class name: UserAndFriendsWebCons
 * @anthor ：卫士
 * @time :Create on 2018/10/30  19:19
 */
public class UserAndFriendsWebCons {
    public interface View{
        void getDataSuccess(GetPublicWebs.GetPublicWeb getPublicWeb);
        void getDataFail(Throwable throwable);
    }
    public interface Model{
        public Observable<GetPublicWebs.GetPublicWeb> GetUserAndFriendsWeb(String s1,long id);
    }
}
