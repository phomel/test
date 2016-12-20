package com.joy.mytest.api;

import com.joy.mytest.bean.UserBean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2016/12/19.
 */
public interface ApiServer {

    /**
     * 登录
     *
     * @param username
     * @param pwd
     * @return
     */
    @FormUrlEncoded
    @POST("user/m/login")
    Call<UserBean> login(@Field("username") String username, @Field("pwd") String pwd);
}