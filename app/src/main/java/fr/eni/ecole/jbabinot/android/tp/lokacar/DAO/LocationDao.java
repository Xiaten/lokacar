package fr.eni.ecole.jbabinot.android.tp.lokacar.DAO;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.Date;

import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Client;
import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Location;
import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Location_Table;
import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Voiture;

/**
 * Created by jbabinot2015 on 28/06/2017.
 */

public class LocationDao {
    public static Location get(int idClient, String immatriculation){
        return SQLite.select().from(Location.class).where(Location_Table.client_id.is(idClient)).and(Location_Table.voiture_immatriculation.eq(immatriculation)).querySingle();
    }

    public static void startLocation (Voiture voiture, Client client, Date date) {
        Location location = new Location();
        location.voiture = voiture;
        location.client = client;
        location.dateFrom = date;
        location.save();
    }

    public static void endLocation (int idClient, String immatriculation, Date date, int km){
        Location location = get(idClient, immatriculation);
        location.dateTo = date;
        Voiture voiture = location.voiture;
        voiture.km = km;
        voiture.save();
        location.save();
    }

    public static Location getByVoiture(String immatriculation){
        return SQLite.select().from(Location.class).where(Location_Table.voiture_immatriculation.eq(immatriculation)).and(Location_Table.dateTo.isNull()).querySingle();
    }
}
