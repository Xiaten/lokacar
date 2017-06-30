package fr.eni.ecole.jbabinot.android.tp.lokacar.DAO;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Region;
import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Region_Table;

/**
 * Created by amercier2016 on 27/06/2017.
 */

public class RegionDao {

    public static List<Region> getAll(){
        return SQLite.select().from(Region.class).orderBy(Region_Table.nom, true).queryList();
    }

}
