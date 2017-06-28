package fr.eni.ecole.jbabinot.android.tp.lokacar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

import fr.eni.ecole.jbabinot.android.tp.lokacar.DAO.ModeleDao;
import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Agence;
import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Marque;
import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Marque_Table;
import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Modele;
import fr.eni.ecole.jbabinot.android.tp.lokacar.Util.MarqueAdapter;
import fr.eni.ecole.jbabinot.android.tp.lokacar.Util.Preference;
import me.srodrigo.androidhintspinner.HintAdapter;
import me.srodrigo.androidhintspinner.HintSpinner;

import static fr.eni.ecole.jbabinot.android.tp.lokacar.R.id.spinnerAgence;

public class SearchActivity extends AppCompatActivity {

    private Spinner spinnerMarque;
    private Spinner spinnerModele;
    List<Marque> listMarque;
    List<Modele> listModele;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        listMarque();
    }

    public void listMarque(){
        listMarque = SQLite.select().from(Marque.class).orderBy(Marque_Table.nom, true).queryList();
        spinnerMarque = (Spinner) findViewById(R.id.spinnerMarque);

        HintSpinner<Marque> hintSpinnerMarque = new HintSpinner<>(
            spinnerMarque,
            new HintAdapter<>(SearchActivity.this, R.string.serach_placeholder_marque, listMarque),
            new HintSpinner.Callback<Marque>() {
                @Override
                public void onItemSelected(int position, final Marque itemAtPosition) {
                    // Here you handle the on item selected event (this skips the hint selected event)
                    int marqueId = listMarque.get(position).id;
                    listModele = ModeleDao.getListByModele(marqueId);
                    spinnerModele = (Spinner) findViewById(R.id.spinnerModele);

                    HintSpinner<Modele> hintSpinnerModele = new HintSpinner<>(
                        spinnerModele,
                        new HintAdapter<>(SearchActivity.this, R.string.serach_placeholder_modele, listModele),
                        new HintSpinner.Callback<Modele>() {
                            @Override
                            public void onItemSelected(int position2, Modele itemAtPosition2) {
                                // Here you handle the on item selected event (this skips the hint selected event)
                            }
                        });
                    hintSpinnerModele.init();
                }
            });
        hintSpinnerMarque.init();
    }
}
