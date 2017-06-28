package fr.eni.ecole.jbabinot.android.tp.lokacar.DAO;

import android.graphics.PorterDuff;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Agence;
import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Modele;
import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Modele_Table;

/**
 * Created by amercier2016 on 28/06/2017.
 */

public class ModeleDao {


    public static List<Modele> getListByModele(int marqueId) {

        List<Modele> list;
        list = SQLite.select().from(Modele.class).where(Modele_Table.marque_id.is(marqueId)).queryList();
        return list;
    }
}
