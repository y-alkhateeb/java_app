package com.example.baitiwb303_hw_f20_c.Tools;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.baitiwb303_hw_f20_c.R;

import java.util.Locale;

public class SettingsPref {
    private static SharedPreferences.Editor editorShared;

    public static SharedPreferences getMainShardPreferences(Context context) {
        return context.getSharedPreferences(Constant.PREFS_FILENAME, Context.MODE_PRIVATE);
    }

    public static void setTheme(int theme, Context context) {
        editorShared = getMainShardPreferences(context).edit();
        editorShared.putInt("Theme", theme);
        editorShared.commit();
    }

    public static int getTheme(Context context) {
        SharedPreferences sharedPreferences = getMainShardPreferences(context);
        return sharedPreferences.getInt("Theme", R.style.FirstTheme);
    }

}
