package fr.eni.ecole.jbabinot.android.tp.lokacar.Model;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.raizlabs.android.dbflow.structure.Model;

/**
 * Created by jbabinot2015 on 26/06/2017.
 */

public class Voiture extends BaseModel {
    @Column
    @PrimaryKey
    public String immatriculation;

    @Column
    public double prix;

    @Column
    public int annee;

    @Column
    public int km;

    @Column
    @ForeignKey
    public Modele modele;

    @Column
    @ForeignKey
    public Agence agence;
}
