package com.wdk.xiaoyu.mvp.contract;

import com.wdk.xiaoyu.net.userShowBean.GetUserBean;

import java.util.Map;

import io.reactivex.Observable;

/**
 * @class name: UserShowCons
 * @anthor ：卫士
 * @time :Create on 2018/10/23  3:34
 */
public class UserShowCons {
    public interface View{
        void getDataSuccess(GetUserBean getUserBean);
        void getDataFail(Throwable throwable);
    }
    public interface Model{
        public Observable<GetUserBean> GetUserShows(String s1,long uid);
    }
}
