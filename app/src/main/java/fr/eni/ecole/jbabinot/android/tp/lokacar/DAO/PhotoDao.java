package fr.eni.ecole.jbabinot.android.tp.lokacar.DAO;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Photo;
import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Photo_Table;
import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Voiture;

/**
 * Created by amercier2016 on 28/06/2017.
 */

public class PhotoDao {

    public static List<Photo> getByVoiture(String voitureId){
        return SQLite.select().from(Photo.class).where(Photo_Table.voiture_immatriculation.is(voitureId)).queryList();
    }
}
