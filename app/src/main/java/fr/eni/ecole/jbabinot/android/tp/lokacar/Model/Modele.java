package fr.eni.ecole.jbabinot.android.tp.lokacar.Model;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.io.Serializable;

import fr.eni.ecole.jbabinot.android.tp.lokacar.AppDatabase;

/**
 * Created by jbabinot2015 on 26/06/2017.
 */
@Table(database = AppDatabase.class)
public class Modele extends BaseModel implements Serializable {
    @Column
    @PrimaryKey(autoincrement = true)
    public int id;

    @Column
    public String nom;

    @Column
    public int nbPlaces;

    @Column
    @ForeignKey
    public Categorie categorie;

    @Column
    @ForeignKey
    public Marque marque;

    public Modele() {
    }

    public Modele(String nom, int nbPlaces, Categorie categorie, Marque marque) {
        this.nom = nom;
        this.nbPlaces = nbPlaces;
        this.categorie = categorie;
        this.marque = marque;
    }

    @Override
    public String toString() {
        return nom;
    }
}
