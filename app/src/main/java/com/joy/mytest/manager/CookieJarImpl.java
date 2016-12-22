package com.joy.mytest.manager;

import com.joy.mytest.application.CarLoanApp;
import com.zhy.http.okhttp.cookie.store.CookieStore;
import com.zhy.http.okhttp.cookie.store.PersistentCookieStore;

import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * Created by Administrator on 2016/12/21.
 */

public class CookieJarImpl implements CookieJar {

    private CookieStore cookieStore;

    public CookieJarImpl() {
        this.cookieStore = new PersistentCookieStore(CarLoanApp.instance);
    }

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        cookieStore.add(url, cookies);
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        return cookieStore.get(url);
    }
}
