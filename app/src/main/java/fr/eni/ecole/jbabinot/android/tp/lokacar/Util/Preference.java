package fr.eni.ecole.jbabinot.android.tp.lokacar.Util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by amercier2016 on 27/06/2017.
 */

public class Preference {

    private static final String PREF_ID_AGENCE = "agenceId";

    private static SharedPreferences get(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static int getIdAgence(Context context){
        return get(context).getInt(PREF_ID_AGENCE, -1);
    }

    public static void setIdAgence(Context context, int agenceId){
        get(context).edit().putInt(PREF_ID_AGENCE, agenceId).commit();
    }
}
