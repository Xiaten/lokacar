package fr.eni.ecole.jbabinot.android.tp.lokacar.DAO;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Location;
import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Location_Table;
import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Voiture;
import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Voiture_Table;

/**
 * Created by jbabinot2015 on 27/06/2017.
 */

public class VoitureDao{
    public static List<Voiture> getAll(){
        return SQLite.select().from(Voiture.class).queryList();
    }

    public static Voiture get(String immatriculation){
        return SQLite.select()
                .from(Voiture.class)
                .where(Voiture_Table.immatriculation.eq(immatriculation))
                .querySingle();
    }

    public static boolean isLoue(String immatriculation){
        boolean loue = false;
        List<Location> locations = SQLite.select().
                from(Location.class).
                where(Location_Table.voiture_immatriculation.is(immatriculation)).
                queryList();
        if(locations != null){
            int i = 0;
            while(!loue && i<locations.size()){
                if(locations.get(i).dateFrom != null && locations.get(i).dateTo == null){
                    loue = true;
                }
                i++;
            }
        }
        return loue;
    }

     public static List<Voiture> getByAgence(int idAgence){
        return SQLite.select().from(Voiture.class).where(Voiture_Table.agence_id.is(idAgence)).queryList();
    }
}
