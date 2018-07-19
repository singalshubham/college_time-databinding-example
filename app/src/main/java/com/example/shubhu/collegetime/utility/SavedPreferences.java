package com.example.shubhu.collegetime.utility;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.shubhu.collegetime.MyApplication;

/**
 * @author Ranosys Technologies
 */
public class SavedPreferences {

    private static SavedPreferences savedPreferences;
    private static final int MODE_PRIVATE = 0;
    private static SharedPreferences preferences;

    public static void init(Context context) {
        if (savedPreferences == null) {
            savedPreferences = new SavedPreferences();
            String user_pref = "com.example.shubhu.collegetime";
            preferences = context.getSharedPreferences(user_pref, Context.MODE_PRIVATE);
        }
    }

    public static SavedPreferences getInstance() {
        if (savedPreferences == null) {
            init(MyApplication.getAppInstance());
        }

        return savedPreferences;
    }

    public void storeUserId(String userId) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("userId", userId);
        editor.apply();
    }

    public CharSequence getUserId() {
        return preferences.getString("userId", "");
    }
}
