package com.joy.mytest.mvp.model;

import android.content.Context;

import com.joy.mytest.activity.LoginActivity;
import com.joy.mytest.base.BaseActivity;
import com.joy.mytest.bean.UserBean;
import com.joy.mytest.listener.CallbackListener;
import com.joy.mytest.manager.RetrofitManager;
import com.joy.mytest.util.Constants;
import com.joy.mytest.util.SPUtils;
import com.joy.mytest.util.ToastUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2016/12/21.
 */

public class LoginModel implements ILoginModel {

    private Context context;
    private Response<UserBean> response;

    public Response<UserBean> getResponse() {
        return response;
    }

    public void setResponse(Response<UserBean> response) {
        this.response = response;
    }

    public LoginModel(Context context) {
        this.context = context;
    }

    @Override
    public String getToken() {
        String string = SPUtils.getInstance(context).getStringValue(Constants.TOKEN);
        return string;
    }

    @Override
    public void saveToken(String token) {
        SPUtils.getInstance(context).putStringValue(Constants.TOKEN, token);
    }

    @Override
    public void loginQuery(String userName, String pwd, final CallbackListener listener) {
        Call<UserBean> call = RetrofitManager.getApiServer().login(userName, pwd);
        call.enqueue(new Callback<UserBean>() {
            @Override
            public void onResponse(Call<UserBean> call, Response<UserBean> response) {
                setResponse(response);
                UserBean userBean = response.body();
                saveToken(userBean.getToken());
                SPUtils.getInstance(context).putObject(Constants.User, userBean);
                listener.onSuccess(userBean);
                //初次登陆使用账号密码请求服务器，绑定用户手机的mac地址，下次使用mac地址请求
                //服务器每次返回token，token与本地不一致时，token过期，重新使用账号密码请求
//                if (userBean.getToken() != getToken()) {
//                    ToastUtil.showShortMessage(context, R.string.token_expired);
//                    saveToken(userBean.getToken());
//                }
            }

            @Override
            public void onFailure(Call<UserBean> call, Throwable t) {
                listener.onFailure(t.getMessage());
                ToastUtil.showShortMessage(context, t.getMessage());
            }
        });
    }
}
