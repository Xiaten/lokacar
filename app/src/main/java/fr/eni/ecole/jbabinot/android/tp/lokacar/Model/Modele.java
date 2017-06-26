package fr.eni.ecole.jbabinot.android.tp.lokacar.Model;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by jbabinot2015 on 26/06/2017.
 */

public class Modele extends BaseModel {
    @Column
    @PrimaryKey(autoincrement = true)
    public int id;

    @Column
    public String nom;

    @Column
    public int nbPlaces;

    @Column
    @ForeignKey
    public Type type;

    @Column
    @ForeignKey
    public Marque marque;
}
