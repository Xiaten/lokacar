package fr.eni.ecole.jbabinot.android.tp.lokacar.Model;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.io.Serializable;

import fr.eni.ecole.jbabinot.android.tp.lokacar.AppDatabase;

/**
 * Created by jbabinot2015 on 26/06/2017.
 */

@Table(database = AppDatabase.class)
public class Categorie extends BaseModel implements Serializable{
    @Column
    @PrimaryKey(autoincrement = true)
    public int id;

    @Column
    public String nom;

    public Categorie() {
    }

    public Categorie(String nom) {
        this.nom = nom;
    }
}
