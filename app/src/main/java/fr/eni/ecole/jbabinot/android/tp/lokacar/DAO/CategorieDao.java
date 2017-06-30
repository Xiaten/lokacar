package fr.eni.ecole.jbabinot.android.tp.lokacar.DAO;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Categorie;
import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Categorie_Table;

/**
 * Created by amercier2016 on 29/06/2017.
 */

public class CategorieDao {

    public static List<Categorie>getAll(){
        return SQLite.select().from(Categorie.class).orderBy(Categorie_Table.nom, true).queryList();
    }
}
