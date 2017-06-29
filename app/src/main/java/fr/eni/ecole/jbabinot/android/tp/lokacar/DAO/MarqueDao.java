package fr.eni.ecole.jbabinot.android.tp.lokacar.DAO;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Marque;
import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Marque_Table;

/**
 * Created by jbabinot2015 on 29/06/2017.
 */

public class MarqueDao {
    public static List<Marque> getAll(){
        return SQLite.select().from(Marque.class).orderBy(Marque_Table.nom, true).queryList();
    }
}
