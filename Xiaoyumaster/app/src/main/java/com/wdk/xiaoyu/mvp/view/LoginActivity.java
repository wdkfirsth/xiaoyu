package com.wdk.xiaoyu.mvp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.auth.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbAuthListener;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.wdk.xiaoyu.R;
import com.wdk.xiaoyu.base.BaseActivity;
import com.wdk.xiaoyu.common.Constans;
import com.wdk.xiaoyu.tool.PreUtils;

/**
 * @class name: LoginActivity
 * @anthor ：卫士
 * @time :Create on 2018/10/15  3:51
 */
public class LoginActivity extends BaseActivity {
    /** 封装了 "access_token"，"expires_in"，"refresh_token"，并提供了他们的管理功能  */
    private Oauth2AccessToken mAccessToken;
    /** 注意：SsoHandler 仅当 SDK 支持 SSO 时有效 */
    private SsoHandler mSsoHandler;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);
        WbSdk.install(this,new AuthInfo(this, Constans.APP_KEY,Constans.REDIRECT_URL,Constans.SCOPE));
        mSsoHandler = new SsoHandler(LoginActivity.this);
        mSsoHandler.authorizeClientSso(new SelfWbAuthListener());
    }
    private class SelfWbAuthListener implements WbAuthListener{

        @Override
        public void onSuccess(final Oauth2AccessToken oauth2AccessToken) {
            LoginActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    PreUtils.setString(LoginActivity.this,"access_token",oauth2AccessToken.getToken());
                    PreUtils.setString(LoginActivity.this,"uid",oauth2AccessToken.getUid());
                    mAccessToken=oauth2AccessToken;
                    if(mAccessToken.isSessionValid()){
                        //保存Token到sharedPreferences
                        AccessTokenKeeper.writeAccessToken(LoginActivity.this,mAccessToken);
                        show_toast("登陆成功！");
                        startActivity(new Intent(LoginActivity.this,IndexActivity.class));
                        finish();
                    }
                }
            });
        }

        @Override
        public void cancel() {
            show_toast("取消成功！");

        }

        @Override
        public void onFailure(WbConnectErrorMessage wbConnectErrorMessage) {
            show_toast("登陆失败！");

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(mSsoHandler != null){
            mSsoHandler.authorizeCallBack(requestCode,resultCode,data);
            finish();
        }
    }
}
