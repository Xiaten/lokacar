package fr.eni.ecole.jbabinot.android.tp.lokacar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

import fr.eni.ecole.jbabinot.android.tp.lokacar.DAO.MarqueDao;
import fr.eni.ecole.jbabinot.android.tp.lokacar.DAO.CategorieDao;
import fr.eni.ecole.jbabinot.android.tp.lokacar.DAO.ModeleDao;
import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Categorie;
import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Marque;
import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Marque_Table;
import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Modele;
import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Voiture;
import fr.eni.ecole.jbabinot.android.tp.lokacar.Util.Constant;
import fr.eni.ecole.jbabinot.android.tp.lokacar.Util.Preference;
import me.srodrigo.androidhintspinner.HintAdapter;
import me.srodrigo.androidhintspinner.HintSpinner;

import static fr.eni.ecole.jbabinot.android.tp.lokacar.R.id.spinnerAgence;

public class SearchActivity extends AppCompatActivity {

    private Spinner spinnerMarque;
    private Spinner spinnerModele;
    private Spinner spinnerCategorie;

    private List<Marque> listMarque;
    private List<Modele> listModele;
    private List<Categorie> listCategorie;

    private List<Voiture> listVoiture;
    private ArrayAdapter<Voiture> adapter;
    private static final int REQUEST_CODE_REFRESH = 41;
    private ListView listViewVehicules;
    private int idMarqueSelected;
    private int idModeleSelected;
    private int idCategorieSelected;
    private EditText editTextPrice;
    private int idAgence;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        listViewVehicules = (ListView) findViewById(R.id.listViewVehicules);
        editTextPrice = (EditText) findViewById(R.id.editTextPrice);
        listMarque();
        listCategorie();
    }

    private void listCategorie() {
        listCategorie = CategorieDao.getAll();
        spinnerCategorie = (Spinner) findViewById(R.id.spinnerCategorie);

        HintSpinner<Categorie> hintSpinnerCategorie = new HintSpinner<>(
            spinnerCategorie,
            new HintAdapter<>(SearchActivity.this, getString(R.string.search_placeholder_categorie), listCategorie),
            new HintSpinner.Callback<Categorie>(){
                @Override
                public void onItemSelected(int position, Categorie itemAtPosition) {
                    idCategorieSelected = listCategorie.get(position).id;
                }
            }
        );
        hintSpinnerCategorie.init();
    }

    public void listMarque(){
        listMarque = MarqueDao.getAll();
        spinnerMarque = (Spinner) findViewById(R.id.spinnerMarque);

        HintSpinner<Marque> hintSpinnerMarque = new HintSpinner<>(
            spinnerMarque,
            new HintAdapter<>(SearchActivity.this, R.string.search_placeholder_marque, listMarque),
            new HintSpinner.Callback<Marque>() {
                @Override
                public void onItemSelected(int position, final Marque itemAtPosition) {
                    // Here you handle the on item selected event (this skips the hint selected event)
                    int marqueId = listMarque.get(position).id;
                    listModele = ModeleDao.getListByModele(marqueId);
                    spinnerModele = (Spinner) findViewById(R.id.spinnerModele);
                    idMarqueSelected = listMarque.get(position).id;

                    HintSpinner<Modele> hintSpinnerModele = new HintSpinner<>(
                        spinnerModele,
                        new HintAdapter<>(SearchActivity.this, R.string.search_placeholder_modele, listModele),
                        new HintSpinner.Callback<Modele>() {
                            @Override
                            public void onItemSelected(int position2, Modele itemAtPosition2) {
                                idModeleSelected = listModele.get(position2).id;
                            }
                        });
                    hintSpinnerModele.init();
                }
            });
        hintSpinnerMarque.init();
    }

    public void validateSearch(View view) {
        idAgence = Preference.getIdAgence(SearchActivity.this);
        //Assert
        boolean error = false;
        String whatList = "";
        int whatId = 0;
        if (idMarqueSelected == 0 && idModeleSelected == 0 && idCategorieSelected == 0 && Double.parseDouble(String.valueOf(editTextPrice.getText())) == 0){
            Toast.makeText(SearchActivity.this, getString(R.string.search_error_nothing_selected), Toast.LENGTH_LONG).show();
            error = true;
        }
        if (idMarqueSelected !=0 && idModeleSelected == 0 && idCategorieSelected == 0 && editTextPrice.getText().toString().isEmpty()){
            whatList = Constant.IS_MARQUE;
            whatId = idMarqueSelected;
        } else if (idMarqueSelected !=0 && idModeleSelected != 0 && idCategorieSelected == 0 && editTextPrice.getText().toString().isEmpty()){
            whatList = Constant.IS_MODELE;
            whatId = idModeleSelected;
        } else if (idMarqueSelected == 0 && idModeleSelected == 0 && idCategorieSelected != 0 && editTextPrice.getText().toString().isEmpty()){
            whatList = Constant.IS_CATEGORY;
            whatId = idCategorieSelected;
        } else  if (idMarqueSelected == 0 && idModeleSelected == 0 && idCategorieSelected == 0 && !editTextPrice.getText().toString().isEmpty() && Integer.parseInt(String.valueOf(editTextPrice.getText())) != 0){
            whatList = Constant.IS_PRICE;
            whatId = Integer.parseInt(String.valueOf(editTextPrice.getText()));
        }

        if (!error){
            Intent intent = new Intent();
            intent.putExtra("id", idAgence);
            intent.putExtra("whatList", whatList);
            intent.putExtra("whatId", whatId);
            setResult(RESULT_OK, intent);
            finish();
        }else {
            Toast.makeText(SearchActivity.this, getString(R.string.search_error_not_car), Toast.LENGTH_LONG).show();
        }
    }
}
