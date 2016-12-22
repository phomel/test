package com.joy.mytest.mvp.presenter;

import com.joy.mytest.listener.CallbackListener;

/**
 * Created by Administrator on 2016/12/21.
 */

public interface ILoginPresenter {

    void login(String userName, String pwd, CallbackListener listener);
}
