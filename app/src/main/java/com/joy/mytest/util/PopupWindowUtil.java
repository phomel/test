package com.joy.mytest.util;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

/**
 * Created by Administrator on 2016/9/12.
 */
public class PopupWindowUtil {

    public static PopupWindow showPopupWindow(View view) {
        PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        return popupWindow;
    }

    public static PopupWindow showPopupWindow(View view, int style) {
        PopupWindow popupWindow = showPopupWindow(view);
        popupWindow.setAnimationStyle(style);
        return popupWindow;
    }

    public void setBackgroundAlpha(float backgroundAlpha, Activity activity) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = backgroundAlpha;
        activity.getWindow().setAttributes(lp);
    }
}
