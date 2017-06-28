package fr.eni.ecole.jbabinot.android.tp.lokacar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.List;

import fr.eni.ecole.jbabinot.android.tp.lokacar.DAO.VoitureDao;
import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Voiture;

public class ListActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_REFRESH = 41;
    private ListView listViewVehicules;
    private List<Voiture> listVoiture;
    private ArrayAdapter adapter;
    private int agenceId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listViewVehicules = (ListView) findViewById(R.id.listViewVehicules);
        agenceId = (int) getIntent().getExtras().get("id");
        if (agenceId != -1) {
            showList(agenceId);
        }
    }

    private void showList(int agenceId){
        listVoiture = VoitureDao.getByAgence(agenceId);
        if (!listVoiture.isEmpty()){
            adapter = new VoitureAdapter(ListActivity.this, R.layout.list_item, listVoiture);
            listViewVehicules.setAdapter(adapter);
            listViewVehicules.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    if (adapter.getItem(position) != null) {
                        Intent intent = new Intent(ListActivity.this, DetailsActivity.class);
                        intent.putExtra("id", ((Voiture)adapter.getItem(position)).immatriculation);
                        startActivityForResult(intent, REQUEST_CODE_REFRESH);
                    }
                }
            });
        }else {
            Toast.makeText(ListActivity.this, getString(R.string.list_error_no_voiture), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id){
            case R.id.action_filter_loue:
                getVoituresLoue();
                break;
            case R.id.action_filter_dispo:
                getVoituresDisponible();
                break;
            case android.R.id.home :
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void getVoituresLoue(){
        int agenceId = (int) getIntent().getExtras().get("id");
        List<Voiture> listVoitureLoue = VoitureDao.getListLoue(agenceId);
        if (!listVoitureLoue.isEmpty()){
            adapter = new VoitureAdapter(ListActivity.this, R.layout.list_item, listVoitureLoue);
            listViewVehicules.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }else {
            Toast.makeText(ListActivity.this, R.string.list_activity_error_not_car_loue, Toast.LENGTH_LONG).show();
        }
    }

    private void getVoituresDisponible(){
        int agenceId = (int) getIntent().getExtras().get("id");
        List<Voiture> listVoitureDispo = VoitureDao.getListDispo(agenceId);
        if (!listVoitureDispo.isEmpty()){
            adapter = new VoitureAdapter(ListActivity.this, R.layout.list_item, listVoitureDispo);
            listViewVehicules.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }else {
            Toast.makeText(ListActivity.this, R.string.list_activity_error_car_not_dispo, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_REFRESH){
            if(resultCode == RESULT_OK){
                if(data.getBooleanExtra("refresh", false)){
                    showList(agenceId);
                }
            }
        }
    }
}
