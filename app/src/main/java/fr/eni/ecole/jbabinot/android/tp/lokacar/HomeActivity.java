package fr.eni.ecole.jbabinot.android.tp.lokacar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import fr.eni.ecole.jbabinot.android.tp.lokacar.DAO.DaoUtil;
import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Agence;
import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Categorie;
import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Client;
import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Location;
import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Marque;
import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Modele;
import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Region;
import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Region_Table;
import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Voiture;

public class HomeActivity extends AppCompatActivity {

    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        DaoUtil.insertData();
        listRegion();

    }

    public void listRegion(){
        List<Region> listRegion = SQLite.select().from(Region.class).orderBy(Region_Table.nom, true).queryList();
        Spinner spinner = (Spinner) findViewById(R.id.spinnerRegion);
        adapter = new RegionAdapter(HomeActivity.this, R.layout.item_region, listRegion);
        //adapter.setDropDownViewResource(R.layout.item_region);
        spinner.setAdapter(adapter);
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
//
//                startActivity(intent);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
    }

    public void toList(View view){
        Intent intent = new Intent(HomeActivity.this, ListActivity.class);
        startActivity(intent);
    }
}
