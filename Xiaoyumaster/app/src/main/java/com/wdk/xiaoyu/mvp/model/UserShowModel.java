package com.wdk.xiaoyu.mvp.model;

import com.wdk.xiaoyu.mvp.contract.UserShowCons;
import com.wdk.xiaoyu.net.NetWorkManager;
import com.wdk.xiaoyu.net.userShowBean.GetUserBean;

import java.util.Map;

import io.reactivex.Observable;

/**
 * @class name: UserShowModel
 * @anthor ：卫士
 * @time :Create on 2018/10/23  3:34
 */
public class UserShowModel implements UserShowCons.Model {


    @Override
    public Observable<GetUserBean> GetUserShows(String s1, long uid) {
        return NetWorkManager.getRequest().GetUserShows(s1,uid);
    }
}
