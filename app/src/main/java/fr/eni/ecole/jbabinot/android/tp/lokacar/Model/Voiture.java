package fr.eni.ecole.jbabinot.android.tp.lokacar.Model;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.raizlabs.android.dbflow.structure.Model;

import fr.eni.ecole.jbabinot.android.tp.lokacar.AppDatabase;

/**
 * Created by jbabinot2015 on 26/06/2017.
 */
@Table(database = AppDatabase.class)
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

    public Voiture() {
    }

    public Voiture(String immatriculation, double prix, int annee, int km, Modele modele, Agence agence) {
        this.immatriculation = immatriculation;
        this.prix = prix;
        this.annee = annee;
        this.km = km;
        this.modele = modele;
        this.agence = agence;
    }
}
