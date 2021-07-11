package com.april.project_uas;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class session {
    private SharedPreferences pref ;
    public session(Context cntx) {

        pref = PreferenceManager.getDefaultSharedPreferences(cntx);
    }
    public void setNim(String nim){
        pref.edit().putString("nim", nim).commit();
    }
    public String getnim() {
        String nim= pref.getString("nim","");
        return nim;
    }
}
