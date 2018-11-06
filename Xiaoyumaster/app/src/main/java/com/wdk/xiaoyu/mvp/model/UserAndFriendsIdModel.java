package com.wdk.xiaoyu.mvp.model;

import com.wdk.xiaoyu.mvp.contract.UserAndFriendsIdCons;
import com.wdk.xiaoyu.net.NetWorkManager;
import com.wdk.xiaoyu.net.userandfriendsbean.GetUserAndFriendWebId;

import io.reactivex.Observable;

/**
 * @class name: UserAndFriendsIdModel
 * @anthor ：卫士
 * @time :Create on 2018/10/30  18:57
 */
public class UserAndFriendsIdModel implements UserAndFriendsIdCons.Model {
    @Override
    public Observable<GetUserAndFriendWebId> GetUserAndFriendsId(String s1) {
        return NetWorkManager.getRequest().GetUserAndFriendWebId(s1);
    }
}
