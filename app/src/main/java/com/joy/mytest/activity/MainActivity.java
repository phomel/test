package com.joy.mytest.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.joy.mytest.R;
import com.joy.mytest.base.BaseActivity;
import com.joy.mytest.bean.UserBean;
import com.joy.mytest.util.Constants;
import com.joy.mytest.util.SPUtils;
import com.joy.mytest.util.ToastUtil;
import com.joy.mytest.widget.BannerLayout;

import butterknife.BindView;

/**
 * Created by Administrator on 2016/12/21.
 */

public class MainActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.bl_banner)
    BannerLayout mBanner;

    public static String departName;
    public static UserBean user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        checkUser();
        mToolbar.setNavigationIcon(R.drawable.arrow_white);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(0, R.anim.anim_left_exit);
            }
        });

        if (user != null){
            mToolbar.setTitle(user.getUser().getRole().getName());
        } else {
            mToolbar.setTitle(R.string.user_unLogin);
        }
        setSupportActionBar(mToolbar);

    }

    private void checkUser() {
        if (getIntent() == null) {
            if (SPUtils.getInstance(context).getObject(Constants.User) == null) {
                ToastUtil.showShortMessage(context, R.string.user_unLogin);
            } else {
                user = (UserBean) SPUtils.getInstance(context).getObject(Constants.User);
            }
        } else {
            user = (UserBean) getIntent().getSerializableExtra(Constants.User);
        }
        if (user != null) {
            departName = user.getUser().getRole().getName();
        }
    }

}
