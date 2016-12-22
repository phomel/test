package com.joy.mytest.manager;

import com.joy.mytest.api.ApiServer;
import com.joy.mytest.util.LogUtil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CookieJar;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/12/21.
 */

public class RetrofitManager {

    private static RetrofitManager retrofitManager = new RetrofitManager();
    public static final String BASE_URL = "http://www.huaxur.com/carloan/";
    public static final Long CACHE_SIZE = 1024 * 1024 * 10L;
    private OkHttpClient okHttpClient;
    private ApiServer apiServer;

    private RetrofitManager() {
        initOkHttpClient();
        initRetrofit();
    }

    private void initRetrofit() {
        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiServer = mRetrofit.create(ApiServer.class);
    }

    public static RetrofitManager getRetrofitManager() {
        return retrofitManager;
    }

    public static ApiServer getApiServer() {
        return retrofitManager.apiServer;
    }

    private void initOkHttpClient() {
        Interceptor mRequestInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                request = request.newBuilder()
                        .addHeader("user- agent", "Carloan")
                        .build();
                return chain.proceed(request);
            }
        };

        //日志拦截
        HttpLoggingInterceptor mLogInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                LogUtil.e(message);
            }
        });
        mLogInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        //缓存配置
        //Cache mCache = new Cache(file, CACHE_SIZE);

        //cookieJar
        CookieJar mCookieJar = new CookieJarImpl();

        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(mRequestInterceptor)
                .addInterceptor(mLogInterceptor)
                //.cache(mCache)
                //.addNetworkInterceptor()
                .cookieJar(mCookieJar)
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build();
    }

}
