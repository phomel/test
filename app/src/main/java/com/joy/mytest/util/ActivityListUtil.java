package com.joy.mytest.util;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

public class ActivityListUtil {

    private static List<Activity> activityList = new ArrayList<Activity>();

    public static void addActivity(Activity activity) {
        if (!activityList.contains(activity)) {
            activityList.add(activity);
        }
    }

    public static void removeActivity(Activity activity) {
        if (activityList.contains(activity)) {
            activityList.remove(activity);
        }
    }

    public static void finishAllActivity() {
        if (!activityList.isEmpty()) {
            for (Activity activity : activityList) {
                activity.finish();
            }
        }
    }

    public static void exitApp() {
        finishAllActivity();
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}