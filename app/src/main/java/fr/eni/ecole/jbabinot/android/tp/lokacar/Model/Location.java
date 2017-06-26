package fr.eni.ecole.jbabinot.android.tp.lokacar.Model;

import android.support.v4.text.util.LinkifyCompat;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.Date;

/**
 * Created by jbabinot2015 on 26/06/2017.
 */

public class Location extends BaseModel{
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
}
