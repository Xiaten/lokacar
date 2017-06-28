package fr.eni.ecole.jbabinot.android.tp.lokacar.DAO;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Client;
import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Client_Table;

/**
 * Created by jbabinot2015 on 28/06/2017.
 */

public class ClientDao {
    public static Client getByTel(String tel){
        return SQLite.select()
                .from(Client.class)
                .where(Client_Table.tel.eq(tel))
                .querySingle();
    }
}
