package com.joy.mytest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.joy.mytest.api.ApiServer;
import com.joy.mytest.base.BaseActivity;
import com.joy.mytest.bean.UserBean;
import com.joy.mytest.manager.AnimManager;
import com.joy.mytest.widget.LargeImageView;
import com.joy.mytest.widget.LargeScrollerImageView;

import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends BaseActivity {

    @BindView(R.id.large_iv)
    LargeScrollerImageView largeImageView;

    @BindView(R.id.iv)
    ImageView iv;

    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //testRetrofitAndGson();
        testLargeIv();
    }

    private void testLargeIv() {
        try {
            InputStream in = getAssets().open("iv.jpg");
            largeImageView.setInputStream(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        AnimManager anim = new AnimManager(largeImageView);
//        anim.runAnimation();
    }

    /**
     * JavaBean不需要解析json的全部属性
     */
    private void testRetrofitAndGson() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.huaxur.com/carloan/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiServer server = retrofit.create(ApiServer.class);
        Call<UserBean> call = server.login("test2", "123456");
        call.enqueue(new Callback<UserBean>() {
            @Override
            public void onResponse(Call<UserBean> call, Response<UserBean> response) {
                UserBean userBean = response.body();
                Log.e("haha-----------", "username:" + userBean.getUser().getUsername() + "---token:" + userBean.getToken());
            }

            @Override
            public void onFailure(Call<UserBean> call, Throwable t) {
                Log.e("haha-----------", t.getMessage());
            }
        });
    }
}
