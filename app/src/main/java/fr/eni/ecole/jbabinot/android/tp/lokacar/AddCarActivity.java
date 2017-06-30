package fr.eni.ecole.jbabinot.android.tp.lokacar;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.io.File;
import java.io.IOException;
import java.util.List;

import fr.eni.ecole.jbabinot.android.tp.lokacar.DAO.AgenceDao;
import fr.eni.ecole.jbabinot.android.tp.lokacar.DAO.MarqueDao;
import fr.eni.ecole.jbabinot.android.tp.lokacar.DAO.ModeleDao;
import fr.eni.ecole.jbabinot.android.tp.lokacar.DAO.PhotoDao;
import fr.eni.ecole.jbabinot.android.tp.lokacar.DAO.VoitureDao;
import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Marque;
import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Modele;
import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Voiture;
import fr.eni.ecole.jbabinot.android.tp.lokacar.Util.PhotoManager;
import fr.eni.ecole.jbabinot.android.tp.lokacar.Util.Preference;
import me.srodrigo.androidhintspinner.HintAdapter;
import me.srodrigo.androidhintspinner.HintSpinner;

public class AddCarActivity extends AppActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 60;
    private static final int REQUEST_TAKE_PHOTO = 63;
    private Spinner spinnerMarque;
    private Spinner spinnerModele;
    private List<Marque> listMarque;
    private List<Modele> listModele;
    private EditText editTextAnnee, editTextKm, editTextImmatriculation, editTextPrix;
    private Button buttonSubmit, buttonPhoto;
    private Marque marque;
    private Modele modele;
    private Voiture voiture;
    private ImageView imageViewPhoto;
    Bitmap image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);

        spinnerMarque = (Spinner) findViewById(R.id.spinnerMarque);
        spinnerModele = (Spinner) findViewById(R.id.spinnerModele);
        editTextAnnee = (EditText) findViewById(R.id.editTextAnnee);
        editTextKm = (EditText) findViewById(R.id.editTextKm);
        editTextImmatriculation = (EditText) findViewById(R.id.editTextImmatriculation);
        editTextPrix = (EditText) findViewById(R.id.editTextPrix);
        buttonSubmit = (Button) findViewById(R.id.buttonSubmit);
        buttonPhoto = (Button) findViewById(R.id.buttonPhoto);
        imageViewPhoto = (ImageView) findViewById(R.id.imageViewPhoto);

        listMarque = MarqueDao.getAll();
        voiture = new Voiture();

        HintSpinner<Marque> hintSpinnerMarque = new HintSpinner<>(
                spinnerMarque,
                new HintAdapter<>(AddCarActivity.this, R.string.search_placeholder_marque, listMarque),
                new HintSpinner.Callback<Marque>() {
                    @Override
                    public void onItemSelected(int position, final Marque itemAtPosition) {
                        marque = itemAtPosition;
                        listModele = ModeleDao.getListByModele(marque.id);

                        HintSpinner<Modele> hintSpinnerModele = new HintSpinner<>(
                                spinnerModele,
                                new HintAdapter<>(AddCarActivity.this, R.string.search_placeholder_modele, listModele),
                                new HintSpinner.Callback<Modele>() {
                                    @Override
                                    public void onItemSelected(int position2, Modele itemAtPosition2) {
                                        modele = itemAtPosition2;
                                    }
                                });
                        hintSpinnerModele.init();
                    }
                });
        hintSpinnerMarque.init();

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(editTextImmatriculation.getText().toString().isEmpty()){
                    editTextImmatriculation.setError("Veuillez saisir la plaque d'immatriculation de la voiture");
                }else if(editTextPrix.getText().toString().isEmpty()){
                    editTextPrix.setError("Veuillez saisir le prix du véhicule");
                }else if(editTextAnnee.getText().toString().isEmpty()){
                    editTextAnnee.setError("Veuillez saisir l'année du véhicule");
                }else if(editTextKm.getText().toString().isEmpty()){
                    editTextKm.setError("Veuillez saisir le nombre de kilomètres de la voiture");
                }else {
                    voiture.immatriculation = editTextImmatriculation.getText().toString();
                    voiture.prix=Double.parseDouble(editTextPrix.getText().toString());
                    voiture.annee= Integer.parseInt(editTextAnnee.getText().toString());
                    voiture.km = Integer.parseInt(editTextKm.getText().toString());
                    voiture.modele = modele;
                    voiture.agence = AgenceDao.get(Preference.getIdAgence(AddCarActivity.this));

                    VoitureDao.add(voiture);

                    if(image != null){
                        PhotoDao.addPhoto(voiture, PhotoManager.saveImage(AddCarActivity.this, image, voiture.immatriculation+".jpg", null).getAbsolutePath());
                    }

                    Intent intent = new Intent();
                    intent.putExtra("refresh",true);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });

        buttonPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            image = (Bitmap) extras.get("data");
            imageViewPhoto.setImageBitmap(image);
        }
    }

}
