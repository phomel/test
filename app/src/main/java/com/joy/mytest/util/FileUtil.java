package com.joy.mytest.util;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.IOException;

/**
 * Created by Administrator on 2016/9/17.
 */
public class FileUtil {

    //判断sd是否可用并且可以读写
    public static boolean isExitsSdcard() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    //SD卡路径
    public static String path() {
        if (isExitsSdcard()) {
            String str = Environment.getExternalStorageDirectory().toString();
            return str;
        }
        return null;
    }

    //缓存路径
    public static String cachePath(Context context) {
        if (isExitsSdcard()) {
            String str = context.getExternalCacheDir().getAbsolutePath();
            return str;
        }
        return null;
    }

    //创建目录
    public static boolean createDir(String path) {
        File file = new File(path);
        if (!file.exists()) {
            //不存在则创建相应目录
            boolean b = file.mkdirs();
            return b;
        }
        return true;
    }

    //创建文件
    public static boolean createFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            //不存在则创建相应
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    //删除文件
    public static boolean deleteFile(String path) {
        File file = new File(path);
        if (file.exists()) {
            boolean b = file.delete();
            return b;
        }
        return true;
    }
}
