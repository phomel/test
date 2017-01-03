package com.joy.mytest.manager;

import android.util.Log;

import com.joy.mytest.api.ApiServer;
import com.joy.mytest.application.App;
import com.joy.mytest.util.LogUtil;
import com.joy.mytest.util.NetUtils;
import com.zhy.http.okhttp.cookie.*;
import com.zhy.http.okhttp.cookie.store.PersistentCookieStore;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
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
    private static OkHttpClient mOkHttpClient;
    private static final String TAG = "OkHttp";

    //短缓存1分钟
    public static final int CACHE_AGE_SHORT = 60;
    //长缓存有效期为1天
    public static final int CACHE_STALE_LONG = 60 * 60 * 24;
    //自己的base_url
    public static final String BASE_URL = "http://www.baidu.com";

    private ApiServer apiServer;
    private static RetrofitManager instance = new RetrofitManager();

    public static RetrofitManager getInstance() {
        return instance;
    }

    private RetrofitManager() {
        initOkHttpClient();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiServer = retrofit.create(ApiServer.class);
    }

    private void initOkHttpClient() {
        //日志拦截，使用okhttputils的HttpLoggingInterceptor，设置请求级别
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.e(TAG, message);
            }
        });
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //缓存的cookieJar负责管理cookies
        com.zhy.http.okhttp.cookie.CookieJarImpl cookieJar = new com.zhy.http.okhttp.cookie.CookieJarImpl(new PersistentCookieStore(App.getInstance()));
        //单例
        if (mOkHttpClient == null) {
            synchronized (OkHttpClient.class) {
                if (mOkHttpClient == null) {
                    // 指定缓存路径,缓存大小10Mb
                    Cache cache = new Cache(new File(App.getInstance().getCacheDir(), "HttpCache"),
                            1024 * 1024 * 10);
                    mOkHttpClient = new OkHttpClient.Builder()
                            .cache(cache)
                            .addInterceptor(interceptor)
                            .addNetworkInterceptor(mCacheControlInterceptor)
                            .cookieJar(cookieJar)
                            .retryOnConnectionFailure(true)
                            .connectTimeout(60, TimeUnit.SECONDS)
                            .readTimeout(60, TimeUnit.SECONDS)
                            .writeTimeout(60, TimeUnit.SECONDS)
                            .build();
                }
            }
        }
    }

    public static ApiServer getApiServer() {
        return instance.apiServer;
    }

    // 响应头拦截器，用来配置缓存策略
    private Interceptor mCacheControlInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetUtils.isConnected(App.getInstance())) {
                //没网时只使用缓存
                //自定义请求头，可以在响应头对请求头的header进行拦截，配置不同的缓存策略
                request = request.newBuilder()
                        .header("head-request", request.toString())
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }
            Response response = chain.proceed(request);
            if (NetUtils.isConnected(App.getInstance())) {
                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                Log.e("Interceptor", "response: " + response.toString());
                //添加头信息，配置Cache-Control
                //removeHeader("Pragma") 使缓存生效
                return response.newBuilder()
                        .header("Cache-Control", "public, max-age=" + CACHE_AGE_SHORT)
                        .removeHeader("Pragma")
                        .build();
            } else {
                Log.e("Interceptor", "net not connect");
                return response.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + CACHE_STALE_LONG)
                        .removeHeader("Pragma")
                        .build();
            }
        }
    };

}
