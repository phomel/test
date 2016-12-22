package com.joy.mytest.util;

import android.util.Log;

/**
 * 日志工具类
 * Created by Administrator on 2016/8/31.
 */
public class LogUtil {
    private static final String TAG = "joy123";

    public static void i(String message) {
        if (Constants.LOG_ENABLE) {
            Log.i(TAG, message);
        }
    }

    public static void e(String message) {
        if (Constants.LOG_ENABLE) {
            Log.e(TAG, message);
        }
    }

    public static void v(String message) {
        if (Constants.LOG_ENABLE) {
            Log.v(TAG, message);
        }
    }

    public static void d(String message) {
        if (Constants.LOG_ENABLE) {
            Log.d(TAG, message);
        }
    }

    public static void w(String message) {
        if (Constants.LOG_ENABLE) {
            Log.w(TAG, message);
        }
    }
}
