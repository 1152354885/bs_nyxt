package com.example.navigationview.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ZHT on 2017/4/26.
 * SharedPreferences工具类
 */

public class SPUtil {

    private SPUtil() {}

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getApplicationContext()
                .getSharedPreferences(context.getApplicationContext().getPackageName(), Context.MODE_PRIVATE);
    }

    /**
     * 保存boolean类型配置信息
     * @param key
     * @param value
     */
    public static void saveBoolean(Context context, String key, boolean value) {
        getSharedPreferences(context).edit()
                .putBoolean(key, value)
                .apply();
    }

    /**
     * 获取boolean类型配置信息
     * @param key
     * @param defValue
     * @return
     */
    public static boolean getBoolean(Context context, String key, boolean defValue) {
        return getSharedPreferences(context).getBoolean(key, defValue);
    }

    /**
     * 保存int类型配置信息
     * @param key
     * @param value
     */
    public static void saveInt(Context context, String key, int value) {
        getSharedPreferences(context).edit()
                .putInt(key, value)
                .apply();
    }

    /**
     * 获取int类型配置信息
     * @param key
     * @param defValue
     * @return
     */
    public static int getInt(Context context, String key, int defValue) {
        return getSharedPreferences(context).getInt(key, defValue);
    }

    /**
     * 保存long类型配置信息
     * @param key
     * @param value
     */
    public static void saveLong(Context context, String key, long value) {
        getSharedPreferences(context).edit()
                .putLong(key, value)
                .apply();
    }

    /**
     * 获取long类型配置信息
     * @param key
     * @param defValue
     * @return
     */
    public static long getLong(Context context, String key, Long defValue) {
        return getSharedPreferences(context).getLong(key, defValue);
    }

    /**
     * 保存String类型配置信息
     * @param key
     * @param value
     */
    public static void saveString(Context context, String key, String value) {
        getSharedPreferences(context).edit()
                .putString(key, value)
                .apply();
    }

    /**
     * 获取String类型配置信息
     * @param key
     * @param defValue
     * @return
     */
    public static String getString(Context context, String key, String defValue) {
        return getSharedPreferences(context).getString(key, defValue);
    }
}
