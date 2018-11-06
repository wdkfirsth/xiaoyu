package com.wdk.xiaoyu.net;

import com.wdk.xiaoyu.net.publicWebBean.GetPublicWebs;
import com.wdk.xiaoyu.net.userShowBean.GetUserBean;
import com.wdk.xiaoyu.net.userandfriendsbean.GetUserAndFriendWebId;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * @class name: Request
 * @anthor ：卫士
 * @time :Create on 2018/10/22  22:26
 */
public interface Request {
    //用户普通读取接口
    public static final String USERURL="https://api.weibo.com/2/";
    public static final String HEADER="Content-Type:application/json;charset=UTF-8";

    //根据用户id获取用户信息
    @GET("users/show.json")
    Observable<GetUserBean> GetUserShows(@Query("access_token")String access_token,@Query("uid") long uid  );

    //返回最新的公共微博statuses/public_timeline.json
    //获取当前登录用户及其所关注（授权）用户的最新微博
    @GET("statuses/home_timeline.json")
    Observable<GetPublicWebs> GetPublicWebs(@Query("access_token") String access_token ,@Query("page") int page);

    //根据微博Id获取单条微博内容statuses/show.json
    @GET("statuses/show.json")
    Observable<GetPublicWebs.GetPublicWeb> GetUserAndFriendsWeb(@Query("access_token") String access_token,@Query("id") long id);

    //获取当前登陆用户及其所关注用户的最新微博的ID
    //statuses/friends_timeline/ids.json
    @GET("statuses/friends_timeline/ids.json")
    Observable<GetUserAndFriendWebId> GetUserAndFriendWebId(@Query("access_token") String access_token);
}
