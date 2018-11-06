package com.wdk.xiaoyu.mvp.model;

import com.wdk.xiaoyu.mvp.contract.PublicWebs;
import com.wdk.xiaoyu.net.NetWorkManager;
import com.wdk.xiaoyu.net.publicWebBean.GetPublicWebs;

import java.util.List;

import io.reactivex.Observable;

/**
 * @class name: PublicWebModel
 * @anthor ：卫士
 * @time :Create on 2018/10/29  5:13
 */
public class PublicWebModel implements PublicWebs.Model {


    @Override
    public Observable<GetPublicWebs> GetPublicWebs(String s1, int page) {
        return NetWorkManager.getRequest().GetPublicWebs(s1,page);
    }
}
