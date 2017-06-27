package fr.eni.ecole.jbabinot.android.tp.lokacar.DAO;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Agence;
import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Agence_Table;
import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Region;
import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Region_Table;

/**
 * Created by amercier2016 on 27/06/2017.
 */

public class AgenceDao {

    public static List<Agence> getAll(){
        return SQLite.select().from(Agence.class).queryList();
    }

    public static List<Agence> getListByRegion(int regionId){

        List<Agence> list;
        list = SQLite.select().from(Agence.class).where(Agence_Table.region_id.is(regionId)).queryList();
        return list;
    }

    public static Agence get(int agenceId){
        return SQLite.select()
                .from(Agence.class)
                .where(Agence_Table.id.eq(agenceId))
                .querySingle();
    }
}
