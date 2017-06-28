package fr.eni.ecole.jbabinot.android.tp.lokacar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import fr.eni.ecole.jbabinot.android.tp.lokacar.DAO.PhotoDao;
import fr.eni.ecole.jbabinot.android.tp.lokacar.DAO.VoitureDao;
import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Photo;
import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Voiture;

public class DetailsActivity extends AppCompatActivity {

    private TextView textViewMarque;
    private TextView textViewModele;
    private TextView textViewAnnee;
    private TextView textViewKm;
    private TextView textViewImmatriculation;
    private TextView textViewPrix;
    private Button buttonSubmit;
    private TextView textViewEtat;
    private ImageView imageViewVoiture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        textViewMarque = (TextView) findViewById(R.id.textViewMarque);
        textViewModele = (TextView) findViewById(R.id.textViewModele);
        textViewAnnee = (TextView) findViewById(R.id.textViewAnnee);
        textViewKm = (TextView) findViewById(R.id.textViewKm);
        textViewImmatriculation = (TextView) findViewById(R.id.textViewImmatriculation);
        textViewPrix = (TextView) findViewById(R.id.textViewPrix);
        buttonSubmit = (Button) findViewById(R.id.buttonSubmit);
        textViewEtat = (TextView) findViewById(R.id.textViewEtat);
        imageViewVoiture = (ImageView) findViewById(R.id.imageViewVoiture);

        Intent intent = getIntent();
        Voiture voiture = VoitureDao.get(intent.getStringExtra("id"));
        List<Photo> listPhoto = PhotoDao.getByVoiture(voiture.immatriculation);
        textViewMarque.setText(voiture.modele.marque.nom);
        textViewModele.setText(voiture.modele.nom);
        textViewAnnee.setText(String.valueOf(voiture.annee));
        textViewKm.setText(String.valueOf(voiture.km));
        textViewPrix.setText(String.valueOf(voiture.prix));
        textViewImmatriculation.setText(voiture.immatriculation);
        Picasso.with(DetailsActivity.this).load(listPhoto.get(0).chemin).into(imageViewVoiture);

        if(VoitureDao.isLoue(voiture.immatriculation)){
            textViewEtat.setText(R.string.details_state_loue);
            buttonSubmit.setText(R.string.details_button_retour);
        }else{
            textViewEtat.setText(R.string.details_state_dispo);
            buttonSubmit.setText(R.string.details_button_location);
        }
    }
}
