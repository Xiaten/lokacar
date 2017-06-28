package fr.eni.ecole.jbabinot.android.tp.lokacar.Model;

import android.support.v4.text.util.LinkifyCompat;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.io.Serializable;
import java.util.Date;

import fr.eni.ecole.jbabinot.android.tp.lokacar.AppDatabase;

/**
 * Created by jbabinot2015 on 26/06/2017.
 */
@Table(database = AppDatabase.class)
public class Location extends BaseModel implements Serializable{
    @Column
    @PrimaryKey
    @ForeignKey
    public Voiture voiture;

    @Column
    @PrimaryKey
    @ForeignKey
    public Client client;

    @Column
    public Date dateFrom;

    @Column
    public Date dateTo;

    public Location() {
    }

    public Location(Voiture voiture, Client client, Date dateFrom, Date dateTo) {
        this.voiture = voiture;
        this.client = client;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }
}
