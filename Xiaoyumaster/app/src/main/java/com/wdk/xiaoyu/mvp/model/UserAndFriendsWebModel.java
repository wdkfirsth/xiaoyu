package com.wdk.xiaoyu.mvp.model;

import com.wdk.xiaoyu.mvp.contract.UserAndFriendsWebCons;
import com.wdk.xiaoyu.net.NetWorkManager;
import com.wdk.xiaoyu.net.publicWebBean.GetPublicWebs;

import io.reactivex.Observable;

/**
 * @class name: UserAndFriendsWebModel
 * @anthor ：卫士
 * @time :Create on 2018/10/30  19:21
 */
public class UserAndFriendsWebModel implements UserAndFriendsWebCons.Model {
    @Override
    public Observable<GetPublicWebs.GetPublicWeb> GetUserAndFriendsWeb(String s1, long id) {
        return NetWorkManager.getRequest().GetUserAndFriendsWeb(s1,id);
    }
}
