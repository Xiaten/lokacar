package fr.eni.ecole.jbabinot.android.tp.lokacar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

import fr.eni.ecole.jbabinot.android.tp.lokacar.DAO.LocationDao;
import fr.eni.ecole.jbabinot.android.tp.lokacar.DAO.PhotoDao;
import fr.eni.ecole.jbabinot.android.tp.lokacar.DAO.VoitureDao;
import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Photo;
import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Voiture;

public class DetailsActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_REFRESH = 41;
    private TextView textViewMarque;
    private TextView textViewModele;
    private TextView textViewAnnee;
    private TextView textViewKm;
    private TextView textViewImmatriculation;
    private TextView textViewPrix;
    private Button buttonSubmit;
    private TextView textViewEtat;
    private ImageView imageViewVoiture;

    Voiture voiture;

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

        init();
    }

    private void init(){
        Intent intent = getIntent();
        voiture = VoitureDao.get(intent.getStringExtra("id"));
        List<Photo> listPhoto = PhotoDao.getByVoiture(voiture.immatriculation);
        textViewMarque.setText(voiture.modele.marque.nom);
        textViewModele.setText(voiture.modele.nom);
        textViewAnnee.setText(String.valueOf(voiture.annee));
        textViewKm.setText(String.valueOf(voiture.km));
        textViewPrix.setText(String.valueOf(voiture.prix));
        textViewImmatriculation.setText(voiture.immatriculation);
//        Picasso.with(DetailsActivity.this).load(listPhoto.get(0).chemin).into(imageViewVoiture);
        if(listPhoto != null && listPhoto.size()>0) {
            File file = new File(listPhoto.get(0).chemin);
//            Picasso.with(contextVoit).load(listPhoto.get(0).chemin).into(viewHolder.imageViewListVoiture);
            Picasso.with(DetailsActivity.this).load(file).into(imageViewVoiture);
        } else {
            Picasso.with(DetailsActivity.this).load(getString(R.string.photo_default)).into(imageViewVoiture);
        }
        
        if(VoitureDao.isLoue(voiture.immatriculation)){
            textViewEtat.setText(R.string.details_state_loue);
            buttonSubmit.setText(R.string.details_button_retour);
            buttonSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(DetailsActivity.this, RetourActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("location", LocationDao.getByVoiture(voiture.immatriculation));
                    intent.putExtras(bundle);
                    startActivityForResult(intent, REQUEST_CODE_REFRESH);
                }
            });
        }else{
            textViewEtat.setText(R.string.details_state_dispo);
            buttonSubmit.setText(R.string.details_button_location);
            buttonSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(DetailsActivity.this, LocationActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("voiture",voiture);
                    intent.putExtras(bundle);
                    startActivityForResult(intent, REQUEST_CODE_REFRESH);
                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_REFRESH){
            if(resultCode == RESULT_OK){
                if(data.getBooleanExtra("refresh", false)){
                    init();
                    Intent intent = new Intent();
                    intent.putExtra("refresh", true);
                    setResult(RESULT_OK, intent);
                }
            }
        }
    }
}
