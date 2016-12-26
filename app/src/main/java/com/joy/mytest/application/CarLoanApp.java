package com.joy.mytest.application;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by Administrator on 2016/12/21.
 */

public class CarLoanApp extends Application {

    public static CarLoanApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        LeakCanary.install(this);
    }
}
