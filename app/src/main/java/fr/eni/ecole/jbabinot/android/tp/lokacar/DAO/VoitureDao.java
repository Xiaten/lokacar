package fr.eni.ecole.jbabinot.android.tp.lokacar.DAO;

import com.raizlabs.android.dbflow.sql.SQLiteType;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.List;

import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Location;
import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Location_Table;
import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Modele;
import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Modele_Table;
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

    public static List<Voiture> getListLoue(int agenceId){
        List<Voiture> listAll = getByAgence(agenceId);
        List<Location> listLoc = SQLite.select().from(Location.class).where(Location_Table.dateFrom.isNotNull()).and(Location_Table.dateTo.isNull()).queryList();
        List<Voiture> listLoue = new ArrayList<>();
        for (int x = 0; x < listAll.size(); x++){
            for (int y = 0; y < listLoc.size(); y++){
                if (listAll.get(x).immatriculation.equals(listLoc.get(y).voiture.immatriculation)){
                    listLoue.add(listAll.get(x));
                }
            }
        }
        return listLoue;
    }

    public static List<Voiture> getListDispo(int agenceId){
        List<Voiture> listAll = getByAgence(agenceId);
        List<Voiture> listLoue = getListLoue(agenceId);
//        List<Location> listLoc = SQLite.select().from(Location.class).where(Location_Table.dateFrom.isNotNull()).and(Location_Table.dateTo.isNotNull()).queryList();
        List<Voiture> listDispo = new ArrayList<>();
        for (int x = 0; x < listAll.size(); x++){
            if(!listLoue.contains(listAll.get(x))){
                listDispo.add(listAll.get(x));
            }
        }
        return listDispo;
    }

    public static List<Voiture> getListPrice(){
        return SQLite.select(Voiture_Table.prix).distinct().from(Voiture.class).orderBy(Voiture_Table.prix, true).queryList();
    }


    public static List<Voiture> getListByMarque(int marqueId, int agenceId) {
        List<Modele> listModele = SQLite.select().from(Modele.class).where(Modele_Table.marque_id.is(marqueId)).queryList();
        List<Voiture> listAll = getByAgence(agenceId);
        List<Voiture> listByMarque = new ArrayList<>();
        for (int x = 0; x < listAll.size(); x++){
            if(listModele.contains(listAll.get(x).modele)){
                listByMarque.add(listAll.get(x));
            }
        }
        return listByMarque;
    }

    public static List<Voiture> getListByMarqueAndModele(int modeleId, int agenceId) {
        return SQLite.select().from(Voiture.class).where(Voiture_Table.modele_id.is(modeleId)).and(Voiture_Table.agence_id.is(agenceId)).queryList();
    }

    public static List<Voiture> getListByCategorie(int categorieId, int agenceId) {
        List<Modele> listModele = SQLite.select().from(Modele.class).where(Modele_Table.categorie_id.is(categorieId)).queryList();
        List<Voiture> listAll = getByAgence(agenceId);
        List<Voiture> listByCategorie = new ArrayList<>();
        for (int x = 0; x < listAll.size(); x++){
            if(listModele.contains(listAll.get(x).modele)){
                listByCategorie.add(listAll.get(x));
            }
        }
        return listByCategorie;
    }

    public static List<Voiture> getListByPrice(int price, int agenceId) {
        List<Voiture> listAll = getByAgence(agenceId);
        List<Voiture> listByPrice = new ArrayList<>();
        for (int x = 0; x < listAll.size(); x++){
            if (listAll.get(x).prix < price){
                listByPrice.add(listAll.get(x));
            }
        }
        return listByPrice;
    }
}
