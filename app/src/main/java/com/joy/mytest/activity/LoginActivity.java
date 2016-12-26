package com.joy.mytest.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;

import com.joy.mytest.R;
import com.joy.mytest.base.BaseActivity;
import com.joy.mytest.listener.CallbackListener;
import com.joy.mytest.mvp.presenter.ILoginPresenter;
import com.joy.mytest.mvp.presenter.LoginPresenter;
import com.joy.mytest.mvp.view.IloginView;
import com.joy.mytest.mvp.view.ILoginView;
import com.joy.mytest.util.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements ILoginView {

    private String userName;
    private String pwd;

    @BindView(R.id.account_et)
    EditText userEt;
    @BindView(R.id.pwd_et)
    EditText pwdEt;
    private ILoginPresenter loginPresenter;

    @Override
    public int getContentViewId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initViews() {
        loginPresenter = new LoginPresenter(this, this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public String getPwd() {
        return pwd;
    }

    @Override
    public void setUserName() {
        userName = userEt.getText().toString().trim();
    }

    @Override
    public void setPwd() {
        pwd = pwdEt.getText().toString().trim();
    }

    @OnClick(R.id.login_btn)
    public void login() {
        String userInput = userEt.getText().toString().trim();
        String pwdInput = pwdEt.getText().toString().trim();
        if (TextUtils.isEmpty(userInput) || userInput == null) {
            ToastUtil.showShortMessage(context, R.string.user_illegal);
        } else if (TextUtils.isEmpty(pwdInput) || pwdInput == null) {
            ToastUtil.showShortMessage(context, R.string.pwd_illegal);
        } else {
            setUserName();
            setPwd();
            loginPresenter.login(getUserName(), getPwd(), new CallbackListener() {
                @Override
                public void onSuccess(Object data) {
                    startActivity(LoginActivity.class, true, data);
                }

                @Override
                public void onFailure(String errMessage) {

                }
            });
        }
    }
}
