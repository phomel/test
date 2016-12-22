package com.joy.mytest.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.joy.mytest.util.Constants;
import com.joy.mytest.util.SPUtils;
import com.joy.mytest.util.ToastUtil;

import java.io.Serializable;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/20.
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected Context context;
    private Activity activity;
    public static final int DEFAULT_ANIM = 0;
    public static int enterAnim = DEFAULT_ANIM;
    public static int exitAnim = DEFAULT_ANIM;

    public abstract int getContentViewId();
    protected abstract void initViews();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        context = this;
        activity = this;
        ButterKnife.bind(this);
        initViews();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void startActivity(Class<?> activity, boolean isFinish) {
        startActivity(activity, null, isFinish, DEFAULT_ANIM, DEFAULT_ANIM);
    }

    public void startActivity(Class<?> activity, boolean isFinish, int animEnter, int animExit) {
        startActivity(activity, null, isFinish, animEnter, animExit);
    }

    public void startActivity(Class<?> activity, boolean isFinish, Object data) {
        startActivity(activity, data, isFinish, DEFAULT_ANIM, DEFAULT_ANIM);
    }

    public void startActivity(Class<?> activity, Object data, boolean isFinish, int animEnter, int animExit) {
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(), activity);
        if (data != null)
            if (data instanceof Serializable) {
                intent.putExtra("data", (Serializable) data);
            } else {
                ToastUtil.showShortMessage(context, "Cannot transport data, because it's not Serializable");
            }
        startActivity(intent);
        if (isFinish) {
            finish();
        }
        //在startActivity或者是finish之后使用，才有切换Activity的效果
        if (animEnter != enterAnim) {
            enterAnim = animEnter;
        }
        if (animExit != exitAnim) {
            exitAnim = animExit;
        }
        overridePendingTransition(enterAnim, exitAnim);
    }

    public void startActivityForResult(Class<?> activity, int requstcode) {
        startActivityForResult(activity, null, requstcode, DEFAULT_ANIM, DEFAULT_ANIM);
    }

    public void startActivityForResult(Class<?> activity, int requstcode, int animEnter, int animExit) {
        startActivityForResult(activity, null, requstcode, animEnter, animExit);
    }

    public void startActivityForResult(Class<?> activity, Object data, int requstcode) {
        startActivityForResult(activity, data, requstcode, DEFAULT_ANIM, DEFAULT_ANIM);
    }

    public void startActivityForResult(Class<?> activity, Object data, int requstcode, int animEnter, int animExit) {
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(), activity);
        if (data != null)
            intent.putExtra("data", (Serializable) data);
        startActivityForResult(intent, requstcode);
        overridePendingTransition(animEnter, animExit);
    }

    public String getToken() {
        String string = SPUtils.getInstance(context).getStringValue(Constants.TOKEN);
        return string;
    }
}
