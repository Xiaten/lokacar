package fr.eni.ecole.jbabinot.android.tp.lokacar.Model;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import fr.eni.ecole.jbabinot.android.tp.lokacar.AppDatabase;

/**
 * Created by jbabinot2015 on 26/06/2017.
 */
@Table(database = AppDatabase.class)
public class Photo extends BaseModel{
    @Column
    @PrimaryKey(autoincrement = true)
    public int id;

    @Column
    public String chemin;

    @Column
    @ForeignKey
    public Voiture voiture;

    public Photo(){

    }

    public Photo(String chemin, Voiture voiture) {
        this.chemin = chemin;
        this.voiture = voiture;
    }
}
