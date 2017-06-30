package fr.eni.ecole.jbabinot.android.tp.lokacar;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import fr.eni.ecole.jbabinot.android.tp.lokacar.DAO.VoitureDao;
import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Voiture;
import fr.eni.ecole.jbabinot.android.tp.lokacar.Util.Constant;
import fr.eni.ecole.jbabinot.android.tp.lokacar.Util.Preference;

public class ListActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_REFRESH = 41;
    private static final int REQUEST_CODE_SEARCH= 22;
    private ListView listViewVehicules;
    private List<Voiture> listVoiture;
    private ArrayAdapter adapter;
    private int agenceId;
    private String messageError = "";
    private FloatingActionButton fabAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listViewVehicules = (ListView) findViewById(R.id.listViewVehicules);
        fabAdd = (FloatingActionButton) findViewById(R.id.fabAdd);
        agenceId = Preference.getIdAgence(ListActivity.this);
//        agenceId = (int) getIntent().getExtras().get("id");
        if (agenceId != -1) {
            listVoiture = VoitureDao.getByAgence(agenceId);
            messageError = getString(R.string.list_error_no_voiture);
            showList(listVoiture, messageError);
        }
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListActivity.this, AddCarActivity.class);
                startActivityForResult(intent, REQUEST_CODE_REFRESH);
            }
        });
    }

    private void showList(List<Voiture> listVoiture, String messageError){
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
            Toast.makeText(ListActivity.this, messageError, Toast.LENGTH_LONG).show();
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
            case R.id.action_search:
                getSearch();
                break;
            case android.R.id.home :
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void getSearch() {
        Intent intent = new Intent(ListActivity.this, SearchActivity.class);
        startActivityForResult(intent, REQUEST_CODE_SEARCH);
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
        if(resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_REFRESH) {
                if (data.getBooleanExtra("refresh", false)) {
                    listVoiture = VoitureDao.getByAgence(agenceId);
                    messageError = getString(R.string.list_error_no_voiture);
                    showList(listVoiture, messageError);
                }
            } else if (requestCode == REQUEST_CODE_SEARCH) {
                messageError = getString(R.string.search_error_not_car);
                switch (data.getStringExtra("whatList")){
                    case Constant.IS_MARQUE:
                        listVoiture = VoitureDao.getListByMarque(data.getIntExtra("whatId", 0), data.getIntExtra("id", 0));
                        showList(listVoiture, messageError);
                        break;
                    case Constant.IS_MODELE:
                        listVoiture = VoitureDao.getListByMarqueAndModele(data.getIntExtra("whatId", 0), data.getIntExtra("id", 0));
                        showList(listVoiture, messageError);
                        break;
                    case Constant.IS_CATEGORY:
                        listVoiture = VoitureDao.getListByCategorie(data.getIntExtra("whatId", 0), data.getIntExtra("id", 0));
                        showList(listVoiture, messageError);
                        break;
                    case Constant.IS_PRICE:
                        listVoiture = VoitureDao.getListByPrice(data.getIntExtra("whatId", 0), data.getIntExtra("id", 0));
                        showList(listVoiture, messageError);
                        break;
                }
            }
        }
    }
}
