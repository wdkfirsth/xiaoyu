package com.wdk.xiaoyu.mvp.view;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.wdk.xiaoyu.R;
import com.wdk.xiaoyu.adapter.IndexRecyclerViewAdapter;
import com.wdk.xiaoyu.adapter.IndexUserWebAdapter;
import com.wdk.xiaoyu.base.BaseActivity;
import com.wdk.xiaoyu.mvp.contract.PublicWebs;
import com.wdk.xiaoyu.mvp.contract.UserAndFriendsIdCons;
import com.wdk.xiaoyu.mvp.contract.UserAndFriendsWebCons;
import com.wdk.xiaoyu.mvp.contract.UserShowCons;
import com.wdk.xiaoyu.mvp.model.PublicWebModel;
import com.wdk.xiaoyu.mvp.model.UserAndFriendsIdModel;
import com.wdk.xiaoyu.mvp.model.UserAndFriendsWebModel;
import com.wdk.xiaoyu.mvp.model.UserShowModel;
import com.wdk.xiaoyu.mvp.presenter.PublicWebPr;
import com.wdk.xiaoyu.mvp.presenter.UserAndFriendsIdPr;
import com.wdk.xiaoyu.mvp.presenter.UserAndFriendsWebPr;
import com.wdk.xiaoyu.mvp.presenter.UserShowPr;
import com.wdk.xiaoyu.net.publicWebBean.GetPublicWebs;
import com.wdk.xiaoyu.net.userShowBean.GetUserBean;
import com.wdk.xiaoyu.net.userandfriendsbean.GetUserAndFriendWebId;
import com.wdk.xiaoyu.tool.PreUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @class name: IndexActivity
 * @anthor ：卫士
 * @time :Create on 2018/10/15  4:31
 */
public class IndexActivity extends BaseActivity implements UserShowCons.View, PublicWebs.View,
        SwipeRefreshLayout.OnRefreshListener,UserAndFriendsIdCons.View,UserAndFriendsWebCons.View {
    @BindView(R.id.index_cir)
    CircleImageView indexCir;
    @BindView(R.id.txt)
    TextView txt;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer)
    DrawerLayout drawer;
    @BindView(R.id.index_recycler)
    RecyclerView indexRecycler;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.find)
    ImageView find;
    private UserShowPr userShowPr;
    private Map<String, String> maps = new HashMap<>();
    private RelativeLayout rHeaderNav;
    private CircleImageView headerCir;
    private TextView userName, description, statuses_count, friends_count, followers_count, favourites_count;
    private ImageView imageView;
    private GetPublicWebs getPublicWebs = new GetPublicWebs();
    private PublicWebPr publicWebPr;
    private IndexRecyclerViewAdapter adapter;
    private IndexUserWebAdapter webAdapter;
    private DivMenu divMenu;
    private UserAndFriendsIdPr userAndFriendsIdPr;
    private UserAndFriendsWebPr userAndFriendsWebPr;
    private  int page=1;
    private boolean isfresh=false;
    private List<GetPublicWebs.GetPublicWeb> getPublicWebList=new ArrayList<>();
    private SimpleTarget<Drawable> simpleTarget = new SimpleTarget<Drawable>() {
        @SuppressLint("NewApi")
        @Override
        public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
            imageView.setBackground(resource);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_index);
        ButterKnife.bind(this);
//        toolbar.setTitle("");
//        setSupportActionBar(toolbar);
        //获取navigationview中headerlayout中的id
//        navView.setItemIconTintList(null);

//        navView.setItemTextColor();

        //设置导航栏菜单宽度设置为屏幕的4/6
        ViewGroup.LayoutParams params = navView.getLayoutParams();
        params.width = getResources().getDisplayMetrics().widthPixels * 6 / 8;
        navView.setLayoutParams(params);

        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.all:
                        show_toast("ddd");
                        break;
                    case R.id.change:
                        if("白天模式".equals(menuItem.getTitle())){
                            menuItem.setTitle("夜间模式");
                            //夜间模式
                            //getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                            //recreate();
                        }else{
                            menuItem.setTitle("白天模式");
                        }



                        break;

                }
                return true;
            }
        });
        View headerView = navView.getHeaderView(0);
        headerCir = headerView.findViewById(R.id.header_cir);
        userName = headerView.findViewById(R.id.username);
        description = headerView.findViewById(R.id.description);
        imageView = headerView.findViewById(R.id.header_img);
        statuses_count = headerView.findViewById(R.id.statuses_count);
        friends_count = headerView.findViewById(R.id.friends_count);
        followers_count = headerView.findViewById(R.id.followers_count);
        favourites_count = headerView.findViewById(R.id.favourites_count);
        //刷新
        //        //设置下拉箭头颜色
        refresh.setColorSchemeResources(android.R.color.holo_green_dark);
        //设置下拉背景色
        refresh.setProgressBackgroundColorSchemeResource(android.R.color.white);
        refresh.setOnRefreshListener(this);
        //当前用户信息
        userShowPr = new UserShowPr(new UserShowModel(), this);
        userShowPr.GetData(PreUtils.getString(this, "access_token", "null"), Long.parseLong(PreUtils.getString(this, "uid", "0")));
        //公共微博
        publicWebPr = new PublicWebPr(new PublicWebModel(), this);
        publicWebPr.getData(PreUtils.getString(this, "access_token", "null"),page);
        Log.i("ff", PreUtils.getString(this, "access_token", "null"));

//        //当前用户及其关注人微博id
//        userAndFriendsIdPr=new UserAndFriendsIdPr(new UserAndFriendsIdModel(),this);
//        userAndFriendsIdPr.getData(PreUtils.getString(this, "access_token", "null"));



    }

    @Override
    public void getDataSuccess(GetUserBean getUserBean) {

        Glide.with(IndexActivity.this)
                .load(getUserBean.getAvatar_hd())
                .into(indexCir);
        Glide.with(IndexActivity.this)
                .load(getUserBean.getCover_image_phone())
                .into(simpleTarget);
        Glide.with(IndexActivity.this)
                .load(getUserBean.getAvatar_hd())
                .into(headerCir);
        userName.setText(getUserBean.getScreen_name());
        description.setText(getUserBean.getDescription());
        statuses_count.setText("微博" + getUserBean.getStatuses_count());
        friends_count.setText("关注" + getUserBean.getFriends_count());
        followers_count.setText("粉丝" + getUserBean.getFollowers_count());
        favourites_count.setText("收藏" + getUserBean.getFavourites_count());
        Log.i("ff", "dd");

    }

    @Override
    public void getDataSuccess(GetPublicWebs getPublicWebss) {
        if(getPublicWebss.getStatuses().size()>0) {
            for(GetPublicWebs.GetPublicWeb getPublicWeb:getPublicWebss.getStatuses()){
                getPublicWebList.add(getPublicWeb);
            }
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
            indexRecycler.setLayoutManager(gridLayoutManager);
            adapter = new IndexRecyclerViewAdapter(getPublicWebList);
            indexRecycler.setAdapter(adapter);
            if(page>1) {
                show_toast("更新了" + getPublicWebss.getStatuses().size() + "条微博");
            }
            isfresh=true;
        }else{
            show_toast("没有新的微博");
            isfresh=true;
        }

        Log.i("ff", "getData");
    }

    @Override
    public void getDataSuccess(GetUserAndFriendWebId getUserAndFriendWebId) {
        Log.i("ff", "获取webId");
        for(int i=0;i<getUserAndFriendWebId.getStatuses().size();i++){

            Log.i("ff", "获取webId:"+Long.parseLong(getUserAndFriendWebId.getStatuses().get(i)));
        }
        userAndFriendsWebPr=new UserAndFriendsWebPr(new UserAndFriendsWebModel(),this);
        userAndFriendsWebPr.getData( PreUtils.getString(this, "access_token", "null"), Long.parseLong(getUserAndFriendWebId.getStatuses().get(0)));

    }

    @Override
    public void getDataSuccess(GetPublicWebs.GetPublicWeb getPublicWeb) {
        Log.i("ff", "获取web");
        getPublicWebList.add(getPublicWeb);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
        indexRecycler.setLayoutManager(gridLayoutManager);
        webAdapter = new IndexUserWebAdapter(getPublicWebList);
        indexRecycler.setAdapter(webAdapter);

    }

    @Override
    public void getDataFail(Throwable throwable) {
        isfresh=true;
        if ("HTTP 403 Forbidden".equals(throwable.getMessage())){
            show_toast("当前用户因请求频繁，当日被限制请求");
        }else{
            show_toast("网络不给力");
        }

        Log.i("ff", "ss" + throwable.getMessage().toString());
    }


    @Override
    public void onRefresh() {
        page++;
        publicWebPr.getData(PreUtils.getString(this, "access_token", "null"),page);

        if (isfresh&&refresh.isRefreshing()) {
            //如果正在刷新
            refresh.setRefreshing(false);//取消刷新
            isfresh=false;
        }
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.divmenu,menu);
//        MenuItem menuItem=menu.findItem(R.id.allwebs);
//        divMenu=(DivMenu) MenuItemCompat.getActionProvider(menuItem);
//
//
//        return true;
//    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.divmenu, menu);
////        MenuItem menuItem = menu.findItem(R.id.allwebs);
////        divMenu = (DivMenu) MenuItemCompat.getActionProvider(menuItem);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
//        divMenu.setIcon(R.mipmap.web);
//        Log.d("ff", "div" + " " + divMenu);


    }

    @OnClick({R.id.index_cir, R.id.find})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.index_cir:
                drawer.openDrawer(navView);
                break;
            case R.id.find:
//                recreate();
                break;
        }
    }
}
