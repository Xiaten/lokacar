package fr.eni.ecole.jbabinot.android.tp.lokacar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import fr.eni.ecole.jbabinot.android.tp.lokacar.DAO.ClientDao;
import fr.eni.ecole.jbabinot.android.tp.lokacar.DAO.LocationDao;
import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Client;
import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Voiture;

public class LocationActivity extends AppActivity {

    private static final int REQUEST_CODE = 42;
    private DatePickerDialog datePicker;
    private Calendar calendar;
    private TextView dateView;
    private Button buttonDate, buttonSubmit;
    ImageButton buttonAddClient, imageButtonSearch;
    private int year, month, day;
    private EditText editTextTel;
    private TextView textViewNom, textViewPrenom;
    NumberFormat numFormat;
    Client client;
    Voiture voiture;
    private TextView textViewMarque, textViewModele, textViewImmat, textViewAnnee, textViewKm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        // ------------ Récupération des éléments du layout
        imageButtonSearch = (ImageButton) findViewById(R.id.imageButtonSearch);
        buttonAddClient = (ImageButton) findViewById(R.id.buttonAddClient);
        dateView = (TextView) findViewById(R.id.textViewDate);
        buttonDate = (Button) findViewById(R.id.buttonDate);
        buttonSubmit = (Button) findViewById(R.id.buttonSubmit);
        editTextTel = (EditText) findViewById(R.id.editTextTel);
        textViewNom = (TextView) findViewById(R.id.textViewNom);
        textViewPrenom = (TextView) findViewById(R.id.textViewPrenom);
        textViewMarque = (TextView) findViewById(R.id.textViewMarque);
        textViewModele = (TextView) findViewById(R.id.textViewModele);
        textViewImmat = (TextView) findViewById(R.id.textViewImmat);
        textViewAnnee = (TextView) findViewById(R.id.textViewAnnee);
        textViewKm = (TextView) findViewById(R.id.textViewKm);


        // ------------ Get intent
        Intent intent = getIntent();
        voiture = (Voiture) intent.getExtras().get("voiture");

        // ------------ Affichage infos voiture
        showVoiture(voiture);

        // ------------ Bouton de recherche d'un client
        imageButtonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                client = ClientDao.getByTel(editTextTel.getText().toString());
                if(client == null){
                    Toast.makeText(LocationActivity.this, "Aucun client enregistré ne possède ce numéro", Toast.LENGTH_SHORT).show();
                } else {
                    showClient(client);
                }
            }
        });

        editTextTel.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    imageButtonSearch.performClick();
                    return true;
                }
                return false;
            }
        });

        // ------------ Bouton d'ajout d'un client
        buttonAddClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LocationActivity.this, AddClientActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        // ------------ Initialisation de la date
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        // ------------ Paramétrage DatePickerDialog
        datePicker = new DatePickerDialog(
                LocationActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int day, int month, int year) {
                showDate(day, month+1, year);
            }
        }, year, month, day);

        // ------------ Bouton de modification de la date
        buttonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker.show();
            }
        });

        // ------------ Paramétrage format des nombre + affichage date par défaut
        numFormat = NumberFormat.getNumberInstance();
        numFormat.setMinimumIntegerDigits(2);
        numFormat.setGroupingUsed(false);
        showDate(year, month+1, day);

        // ------------ Bouton de validation
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    if(client == null) {
                        Toast.makeText(LocationActivity.this,"Veuillez rechercher/ajouter le client locataire.", Toast.LENGTH_SHORT).show();
                    } else {
                        LocationDao.startLocation(voiture, client, format.parse(dateView.getText().toString()));
                        Intent intent = new Intent();
                        intent.putExtra("refresh", true);
                        setResult(RESULT_OK,intent);
                        finish();
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                    Toast.makeText(LocationActivity.this,"Un problème est survenu lors de la validation.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void showDate(int year, int month, int day) {
        dateView.setText(String.format("%s/%s/%s",numFormat.format(day),numFormat.format(month),numFormat.format(year)));
    }

    private void showVoiture(Voiture voiture){
        textViewMarque.setText(voiture.modele.marque.nom);
        textViewModele.setText(voiture.modele.nom);
        textViewImmat.setText(voiture.immatriculation);
        textViewAnnee.setText(String.valueOf(voiture.annee));
        textViewKm.setText(String.valueOf(voiture.km));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE){
            if(resultCode == RESULT_OK){
                client = (Client) data.getExtras().get("client");
                showClient(client);
            }
        }
    }

    private void showClient(Client client){
        editTextTel.setText(String.valueOf(client.tel));
        textViewNom.setText(String.valueOf(client.nom));
        textViewPrenom.setText(String.valueOf(client.prenom));
    }
}
