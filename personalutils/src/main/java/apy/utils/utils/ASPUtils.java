package apy.utils.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.HashSet;

/**
 * Created by Administrator on 2017/10/18.
 */

public class ASPUtils {

    private static SharedPreferences sp;
    private static Context mContext;

    public static void init(Context context) {
        sp = context.getSharedPreferences(name, mode);
        mContext = context;
    }

    private final static String name = "config";
    private final static int mode = Context.MODE_PRIVATE;

    /**
     * 保存首选项
     *
     * @param context
     * @param key
     * @param value
     */
    public static void saveBoolean(Context context, String key, boolean value) {
        sp = context.getSharedPreferences(name, mode);
        Editor edit = sp.edit();
        edit.putBoolean(key, value);
        edit.apply();
    }

    public static void saveBoolean(String key) {
        sp = UIUtils.getContext().getSharedPreferences(name, mode);
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean(key, true);
        edit.apply();
    }

    public static void remove(Context context, String key) {
        sp = context.getSharedPreferences(name, mode);
        sp.edit().remove(key).apply();
    }

    public static void saveInt(Context context, String key, int value) {
        sp = context.getSharedPreferences(name, mode);
        Editor edit = sp.edit();
        edit.putInt(key, value);
        edit.apply();
    }

    public static void saveString(String key, String value) {
        sp = UIUtils.getContext().getSharedPreferences(name, mode);
        Editor edit = sp.edit();
        edit.putString(key, value);
        edit.apply();
    }

    public static String getString(String key) {
        sp = UIUtils.getContext().getSharedPreferences(name, mode);
        return sp.getString(key, "");
    }

    public static String getStringForResult(String key) {
        sp = UIUtils.getContext().getSharedPreferences(name, mode);
        return sp.getString(key, "0");
    }

    /**
     * 获取首选项
     *
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static boolean getBoolean(Context context, String key, boolean defValue) {
        sp = context.getSharedPreferences(name, mode);
        return sp.getBoolean(key, defValue);
    }

    public static boolean getBoolean(String key) {
        sp = UIUtils.getContext().getSharedPreferences(name, mode);
        return sp.getBoolean(key, false);
    }

    public static int getInt(Context context, String key, int defValue) {
        sp = context.getSharedPreferences(name, mode);
        return sp.getInt(key, defValue);
    }

    public static String getString(Context context, String key, String defValue) {
        sp = context.getSharedPreferences(name, mode);
        return sp.getString(key, defValue);
    }

    public static String getString(String key, String defValue) {
        return sp.getString(key, defValue);
    }


    public static void clear() {
        sp = UIUtils.getContext().getSharedPreferences("config", 0);
        sp.edit().clear().commit();
    }


    public static void putStringSet(String key, HashSet<String> value) {
        sp = UIUtils.getContext().getSharedPreferences(name, mode);
        Editor edit = sp.edit();
        edit.putStringSet(key, value);
        edit.apply();
    }

    public static HashSet<String> getStringSet(String prefCookies, HashSet<String> objects) {
        return (HashSet<String>) sp.getStringSet(prefCookies, objects);
    }
}
