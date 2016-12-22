package com.joy.mytest.mvp.model;

import com.joy.mytest.listener.CallbackListener;

/**
 * Created by Administrator on 2016/12/21.
 */

public interface ILoginModel {
    String getToken();
    void saveToken(String token);
    void loginQuery(String userName, String pwd, CallbackListener listener);
}
