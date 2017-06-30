package fr.eni.ecole.jbabinot.android.tp.lokacar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import fr.eni.ecole.jbabinot.android.tp.lokacar.DAO.AgenceDao;
import fr.eni.ecole.jbabinot.android.tp.lokacar.DAO.DaoUtil;
import fr.eni.ecole.jbabinot.android.tp.lokacar.DAO.RegionDao;
import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Agence;
import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Categorie;
import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Client;
import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Location;
import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Marque;
import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Modele;
import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Region;
import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Region_Table;
import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Voiture;
import fr.eni.ecole.jbabinot.android.tp.lokacar.Util.Preference;

public class HomeActivity extends AppActivity {

    private ArrayAdapter adapterRegion;
    private ArrayAdapter adapterAgence;
    private Spinner spinnerRegion;
    private Spinner spinnerAgence;
    private int idAgenceSelected = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        // chargement BDD
        DaoUtil.insertData();

        // chargement list region et agence en fonction de la sélection region
        listRegion();
    }

    public void listRegion(){
        final List<Region> listRegion = RegionDao.getAll();
        spinnerRegion = (Spinner) findViewById(R.id.spinnerRegion);
        adapterRegion = new RegionAdapter(HomeActivity.this, R.layout.item_region, listRegion);
        adapterRegion.setDropDownViewResource(R.layout.item_region);
        spinnerRegion.setAdapter(adapterRegion);
        spinnerRegion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int regionId = listRegion.get(i).id;
                final List<Agence> listAgence = AgenceDao.getListByRegion(regionId);
                spinnerAgence = (Spinner) findViewById(R.id.spinnerAgence);
                adapterAgence = new AgenceAdapter(HomeActivity.this, R.layout.item_agence, listAgence);
                adapterAgence.setDropDownViewResource(R.layout.item_agence);
                spinnerAgence.setAdapter(adapterAgence);
                spinnerAgence.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        idAgenceSelected = listAgence.get(i).id;
                        Preference.setIdAgence(HomeActivity.this, idAgenceSelected);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void toList(View view){
        //Assert
        boolean error = false;
        spinnerRegion = (Spinner) findViewById(R.id.spinnerRegion);
        spinnerAgence = (Spinner) findViewById(R.id.spinnerAgence);
        if (spinnerRegion.getSelectedItem() == null){
            Toast.makeText(HomeActivity.this, getString(R.string.main_error_region), Toast.LENGTH_LONG).show();
            error = true;
        }
        if (spinnerAgence.getSelectedItem() == null){
            Toast.makeText(HomeActivity.this, R.string.main_error_agence, Toast.LENGTH_LONG).show();
            error = true;
        }

        if (!error){
            Intent intent = new Intent(HomeActivity.this, ListActivity.class);
            intent.putExtra("id", idAgenceSelected);
            startActivity(intent);
        }
    }
}
