package com.wdk.xiaoyu.mvp.contract;


import com.wdk.xiaoyu.net.userandfriendsbean.GetUserAndFriendWebId;

import io.reactivex.Observable;

/**
 * @class name: UserAndFriendsIdCons
 * @anthor ：卫士
 * @time :Create on 2018/10/30  18:56
 */
public class UserAndFriendsIdCons {
    public interface View{
        void getDataSuccess(GetUserAndFriendWebId getUserAndFriendWebId);
        void getDataFail(Throwable throwable);
    }
    public interface Model{
        public Observable<GetUserAndFriendWebId> GetUserAndFriendsId(String s1);
    }
}
