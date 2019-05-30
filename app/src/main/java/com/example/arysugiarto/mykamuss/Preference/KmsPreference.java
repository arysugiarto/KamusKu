package com.example.arysugiarto.mykamuss.Preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.arysugiarto.mykamuss.R;

public class KmsPreference {
    SharedPreferences prefs;
    Context context;

    public KmsPreference(Context context) {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        this.context = context;
    }

    public void setFirstRun(Boolean input){
        SharedPreferences.Editor editor = prefs.edit();
        String key = context.getResources().getString(R.string.pref_kamus);
        this.context = context;
    }

    public Boolean getFirstRun(){
        String key = context.getResources().getString(R.string.pref_kamus);
        return prefs.getBoolean(key, true);
    }
}
