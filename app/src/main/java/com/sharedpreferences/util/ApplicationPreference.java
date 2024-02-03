package com.sharedpreferences.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.Map;

/**
 * Class foe storing primitive preference values for the application.
 * <p/>
 * The singleton scope: a new instance of the bean is created the first time it
 * is needed. It is then retained and the same instance is always injected.
 */
public class ApplicationPreference {

    private static final String TAG = ApplicationPreference.class.getSimpleName();
    // Name of the preference file under data/data/application_preference package
    private static final String preference_name = "shared_preferences";

    private static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(preference_name, Context.MODE_PRIVATE);
    }

    /**
     * Put boolean value to preference.
     *
     * @param key     the key
     * @param value   the value
     * @param context the context
     */
    public static void setBoolean(String key, boolean value, Context context) {
        SharedPreferences preferences = getPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    /**
     * Gets the boolean value from preference.
     *
     * @param key      the key
     * @param defValue the default value
     * @param context  the context
     * @return the boolean
     */
    public static boolean getBoolean(String key, boolean defValue, Context context) {
        SharedPreferences preferences = getPreferences(context);
        return preferences.getBoolean(key, defValue);
    }

    /**
     * Put string value to preference.
     *
     * @param key     the key
     * @param value   the value
     * @param context the Context
     */
    public static void setString(String key, String value, Context context) {
        SharedPreferences preferences = getPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    /**
     * Gets the string value from preference.
     *
     * @param key      the key
     * @param defValue the default value
     * @param context  the context
     * @return the string
     */
    public static String getString(String key, String defValue, Context context) {
        SharedPreferences preferences = getPreferences(context);
        return preferences.getString(key, defValue);
    }

    /**
     * Put long value to preference.
     *
     * @param key     the key
     * @param value   the value
     * @param context the context
     */
    @SuppressLint("NewApi")
    public static void setLong(String key, long value, Context context) {
        SharedPreferences preferences = getPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    /**
     * Gets the long value from preference.
     *
     * @param key      the key
     * @param defValue the default value
     * @param context  the context
     * @return the long
     */
    public static long getLong(String key, long defValue, Context context) {
        SharedPreferences preferences = getPreferences(context);
        return preferences.getLong(key, defValue);
    }

    /**
     * Put integer value to preference.
     *
     * @param key     the key
     * @param value   the value
     * @param context the context
     */
    @SuppressLint("NewApi")
    public static void setInteger(String key, int value, Context context) {
        SharedPreferences preferences = getPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    /**
     * Gets the integer value from preference.
     *
     * @param key      the key
     * @param defValue the default value
     * @param context  the context
     * @return the integer
     */
    public static int getInteger(String key, int defValue, Context context) {
        SharedPreferences preferences = getPreferences(context);
        return preferences.getInt(key, defValue);
    }

    /**
     * Put float value to preference.
     *
     * @param key     the key
     * @param value   the value
     * @param context the context
     */
    public static void setFloat(String key, float value, Context context) {
        SharedPreferences preferences = getPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putFloat(key, value);
        editor.apply();
    }

    /**
     * Gets the float value from preference.
     *
     * @param key      the key
     * @param defValue the default value
     * @param context  the context
     * @return the float
     */
    public static float getFloat(String key, float defValue, Context context) {
        SharedPreferences preferences = getPreferences(context);
        return preferences.getFloat(key, defValue);
    }

    /**
     * Check the preference contain key or not
     *
     * @param key     the key
     * @param context the context
     * @return the boolean
     */
    public static boolean contains(String key, Context context) {
        SharedPreferences preferences = getPreferences(context);
        return preferences.contains(key);
    }

    /**
     * For dev debug
     *
     * @param context the context
     */
    public static void printAllPreference(Context context) {
        SharedPreferences preferences = getPreferences(context);
        Map<String, ?> allEntries = preferences.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            Log.d(TAG, entry.getKey() + ": " + entry.getValue().toString());
        }
    }

    /**
     * Clear all preference data
     *
     * @param context the context
     */
    public static void clearAllPreference(Context context) {
        SharedPreferences preferences = getPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
    }
}
