package com.joy.mytest.listener;

import retrofit2.Call;

/**
 * Created by Administrator on 2016/12/21.
 */

public interface CallbackListener {
    void onSuccess(Object data);
    void onFailure(String errMessage);
}
