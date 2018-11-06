package com.wdk.xiaoyu.tool;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @class name: PreUtils
 * @anthor ：卫士
 * @time :Create on 2018/10/15  5:44
 */
public  class PreUtils {
    //存boolean类数据
    public static void setBoolean(Context context,String key,Boolean value){
        SharedPreferences sp=context.getSharedPreferences("data",Context.MODE_PRIVATE);
        sp.edit().putBoolean(key,value).commit();
    }

    //取boolean类型数据
    public static Boolean getBoolean(Context context,String key, Boolean defValue){
        SharedPreferences sp=context.getSharedPreferences("data",Context.MODE_PRIVATE);
        return sp.getBoolean(key,defValue);
    }

    //存String类型数据
    public static void setString(Context context,String key,String value){
        SharedPreferences sp=context.getSharedPreferences("data",Context.MODE_PRIVATE);
        sp.edit().putString(key,value).commit();
    }

    //取String类型数据
    public static String getString(Context context,String key,String defValue){
        SharedPreferences sp=context.getSharedPreferences("data",Context.MODE_PRIVATE);
        return sp.getString(key,defValue);
    }

    //存int类型数据
    public static void setInt(Context context,String key,int value){
        SharedPreferences sp=context.getSharedPreferences("data",Context.MODE_PRIVATE);
        sp.edit().putInt(key,value).commit();
    }

    //取int类型数据
    public static int getInt(Context context,String key, int defValue){
        SharedPreferences sp=context.getSharedPreferences("data",Context.MODE_PRIVATE);
        return  sp.getInt(key,defValue);
    }

    //清除全部数据
    public static void clears(Context context){
        SharedPreferences sp=context.getSharedPreferences("data",Context.MODE_PRIVATE);
        sp.edit().clear().commit();
    }
}
