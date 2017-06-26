package fr.eni.ecole.jbabinot.android.tp.lokacar;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by amercier2016 on 26/06/2017.
 */

@Database(name = AppDatabase.NAME, version = AppDatabase.VERSION)
public class AppDatabase {

    public static final String NAME = "Lokacar";

    public static final int VERSION = 1;
}