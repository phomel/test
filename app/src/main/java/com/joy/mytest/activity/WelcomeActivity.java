package com.joy.mytest.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;

import com.joy.mytest.R;
import com.joy.mytest.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by Administrator on 2016/12/21.
 */

public class WelcomeActivity extends BaseActivity {

    @BindView(R.id.tv)
    TextView textView;
    private AlphaAnimation anim;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initViews() {
        initAnim();
    }

    private void initAnim() {
        //属性动画
        PropertyValuesHolder pv1 = PropertyValuesHolder.ofFloat("alpha", 0.1f, 1.0f);
        PropertyValuesHolder pv2 = PropertyValuesHolder.ofFloat("rotation", 0.0f, 359.0f);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(textView, pv1, pv2);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setDuration(3000L);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (getToken() != null) {
                    startActivity(MainActivity.class, true);
                } else {
                    startActivity(LoginActivity.class, true);
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.start();
    }
}
