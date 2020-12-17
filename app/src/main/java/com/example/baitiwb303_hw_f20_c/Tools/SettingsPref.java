package com.example.baitiwb303_hw_f20_c.Tools;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.baitiwb303_hw_f20_c.Models.AccountM;
import com.example.baitiwb303_hw_f20_c.R;

import java.util.Locale;

import static com.example.baitiwb303_hw_f20_c.Tools.Constant.AccountId;

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

    public static void setAccount(String id, String username, String pass, Context context) {
        editorShared = getMainShardPreferences(context).edit();
        editorShared.putString(Constant.AccountId, id);
        editorShared.putString(Constant.UserName, username);
        editorShared.putString(Constant.Pass, pass);
        editorShared.commit();
    }

    public static AccountM getAccount(Context context) {
        SharedPreferences sharedPreferences = getMainShardPreferences(context);
        AccountM accountM = new AccountM();
        accountM.setAccount_id(sharedPreferences.getString(Constant.AccountId,""));
        accountM.setUser_name(sharedPreferences.getString(Constant.UserName,""));
        accountM.setPassword(sharedPreferences.getString(Constant.Pass,""));
        accountM.setPrivilege(sharedPreferences.getString(Constant.prev,""));
        return accountM;
    }
}
