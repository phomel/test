package com.joy.mytest.mvp.model;

import com.joy.mytest.bean.BannerBean;
import com.joy.mytest.listener.CallbackListener;
import com.joy.mytest.manager.RetrofitManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2016/12/22.
 */

public class HomeModel implements IHomeModel {
    @Override
    public void bannerQuery(final CallbackListener listener) {
        Call<BannerBean> call = RetrofitManager.getApiServer().getBannerList();
        call.enqueue(new Callback<BannerBean>() {
            @Override
            public void onResponse(Call<BannerBean> call, Response<BannerBean> response) {
                BannerBean bannerBean = response.body();
                listener.onSuccess(bannerBean);
            }

            @Override
            public void onFailure(Call<BannerBean> call, Throwable t) {
                listener.onFailure(t.getMessage());
            }
        });
    }
}
