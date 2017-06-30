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
import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Location;
import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Voiture;

public class RetourActivity extends AppActivity {
    private static final int REQUEST_CODE = 42;
    private DatePickerDialog datePicker;
    private Calendar calendar;
    private TextView dateView;
    private Button buttonDate, buttonSubmit;
    ImageButton buttonAddClient, imageButtonSearch;
    private int year, month, day;
    private TextView textViewNom, textViewPrenom;
    NumberFormat numFormat;
    private TextView textViewMarque, textViewModele, textViewImmat, textViewAnnee, textViewTel, textViewDateDebut;
    private EditText editTextKm;
    private Location location;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retour);

        // ------------ Récupération des éléments du layout
        imageButtonSearch = (ImageButton) findViewById(R.id.imageButtonSearch);
        buttonAddClient = (ImageButton) findViewById(R.id.buttonAddClient);
        dateView = (TextView) findViewById(R.id.textViewDate);
        buttonDate = (Button) findViewById(R.id.buttonDate);
        buttonSubmit = (Button) findViewById(R.id.buttonSubmit);
        textViewTel = (TextView) findViewById(R.id.textViewTel);
        textViewNom = (TextView) findViewById(R.id.textViewNom);
        textViewPrenom = (TextView) findViewById(R.id.textViewPrenom);
        textViewMarque = (TextView) findViewById(R.id.textViewMarque);
        textViewModele = (TextView) findViewById(R.id.textViewModele);
        textViewImmat = (TextView) findViewById(R.id.textViewImmat);
        textViewAnnee = (TextView) findViewById(R.id.textViewAnnee);
        editTextKm = (EditText) findViewById(R.id.editTextKm);
        textViewDateDebut = (TextView) findViewById(R.id.textViewDateDebut);


        // ------------ Get intent
        Intent intent = getIntent();
        location = (Location) intent.getExtras().get("location");

        // ------------ Affichage infos voiture
        showVoiture(location.voiture);

        // ------------ Affichage des infos du client
        showClient(location.client);

        // ------------ Affichage info location
        textViewDateDebut.setText(dateFormat.format(location.dateFrom));

        // ------------ Initialisation de la date
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        // ------------ Paramétrage DatePickerDialog
        datePicker = new DatePickerDialog(
                RetourActivity.this, new DatePickerDialog.OnDateSetListener() {
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
                try {
                    if(editTextKm.getText().toString().isEmpty() || Integer.parseInt(editTextKm.getText().toString()) <= location.voiture.km) {
                        editTextKm.setError("Veuillez renseigner le nouveau kilométrage de la voiture.");
                    } else if(dateFormat.parse(dateView.getText().toString()).before(location.dateFrom)) {
                        dateView.setError("La date de fin de location doit être supérieure à la date de début de location.");
                    }else {
                        LocationDao.endLocation(location.client.id, location.voiture.immatriculation, dateFormat.parse(dateView.getText().toString()), Integer.parseInt(editTextKm.getText().toString()));
                        Intent intent = new Intent();
                        intent.putExtra("refresh", true);
                        setResult(RESULT_OK,intent);
                        finish();
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                    Toast.makeText(RetourActivity.this,"Un problème est survenu lors de la validation.", Toast.LENGTH_SHORT).show();
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
        editTextKm.setText(String.valueOf(voiture.km));
    }

    private void showClient(Client client){
        textViewTel.setText(String.valueOf(client.tel));
        textViewNom.setText(String.valueOf(client.nom));
        textViewPrenom.setText(String.valueOf(client.prenom));
    }
}
