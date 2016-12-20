package com.joy.mytest.manager;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

/**
 * Created by Administrator on 2016/12/20.
 */
public class AnimManager {
    TranslateAnimation right;
    TranslateAnimation left;
    View runImage;

    public AnimManager(View runImage) {
        this.runImage = runImage;
    }

    // 壁纸动画
    public void runAnimation() {
        right = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0f,
                Animation.RELATIVE_TO_PARENT, -1f,
                Animation.RELATIVE_TO_PARENT, 0f, Animation.RELATIVE_TO_PARENT,
                0f);
        left = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, -1f,
                Animation.RELATIVE_TO_PARENT, 0f, Animation.RELATIVE_TO_PARENT,
                0f, Animation.RELATIVE_TO_PARENT, 0f);
        right.setDuration(30000);
        left.setDuration(30000);
        right.setFillAfter(true);
        left.setFillAfter(true);

        right.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                runImage.startAnimation(left);
            }
        });

        left.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                runImage.startAnimation(right);
            }
        });
        runImage.startAnimation(right);
    }
}
