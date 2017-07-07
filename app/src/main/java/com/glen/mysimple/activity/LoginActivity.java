package com.glen.mysimple.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.glen.mysdk.okhttp.listener.DisposeDataListener;
import com.glen.mysimple.R;
import com.glen.mysimple.activity.base.BaseActivity;
import com.glen.mysimple.manager.UserManager;
import com.glen.mysimple.module.user.User;
import com.glen.mysimple.network.http.RequestCenter;

/**
 * Created by Gln on 2017/7/7.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private EditText mUserNameView;
    private EditText mPasswordView;
    private TextView mLoginView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_layout);
        initView();
    }

    private void initView() {
        mUserNameView = (EditText) findViewById(R.id.associate_email_input);
        mPasswordView = (EditText) findViewById(R.id.login_input_password);
        mLoginView = (TextView) findViewById(R.id.login_button);
        mLoginView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_button:
                login();
                break;
        }
    }

    private void login() {
        String userName = mUserNameView.getText().toString().trim();
        String password = mPasswordView.getText().toString().trim();
        if (TextUtils.isEmpty(userName)) {
            return;
        }

        if (TextUtils.isEmpty(password)) {
            return;
        }

        RequestCenter.login(userName, password, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                User user = (User) responseObj;
                UserManager.getInstance().setUser(user);//管理用户信息
                //发送登录广播
                sendLoginBroadcast();


            }

            @Override
            public void onFailure(Object reasonObj) {

            }
        });

    }

    //自定义登陆广播Action
    public static final String LOGIN_ACTION = "com.imooc.action.LOGIN_ACTION";

    //向整个应用发送登陆广播事件
    private void sendLoginBroadcast() {
        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(LOGIN_ACTION));
    }
}
