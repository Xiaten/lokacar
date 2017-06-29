package fr.eni.ecole.jbabinot.android.tp.lokacar;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.lang.reflect.Array;
import java.util.List;
import java.util.zip.Inflater;

import fr.eni.ecole.jbabinot.android.tp.lokacar.DAO.PhotoDao;
import fr.eni.ecole.jbabinot.android.tp.lokacar.DAO.VoitureDao;
import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Photo;
import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Voiture;

/**
 * Created by jbabinot2015 on 27/06/2017.
 */

public class VoitureAdapter extends ArrayAdapter<Voiture> {
    private LayoutInflater inflater;
    private int resId;
    private Context contextVoit;

    public VoitureAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Voiture> objects) {
        super(context, resource, objects);
        inflater = LayoutInflater.from(context);
        resId = resource;
        contextVoit = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;

        if(convertView == null){
            convertView = inflater.inflate(resId,null);

            viewHolder = new ViewHolder();
            viewHolder.textViewMarque = (TextView) convertView.findViewById(R.id.textViewMarque);
            viewHolder.textViewModele = (TextView) convertView.findViewById(R.id.textViewModele);
            viewHolder.textViewPrix = (TextView) convertView.findViewById(R.id.textViewPrix);
            viewHolder.textViewEtat = (TextView) convertView.findViewById(R.id.textViewEtat);
            viewHolder.imageViewListVoiture = (ImageView) convertView.findViewById(R.id.imageViewListVoiture);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Voiture item = (Voiture) getItem(position);

        viewHolder.textViewMarque.setText(item.modele.marque.nom);
        viewHolder.textViewModele.setText(item.modele.nom);
        viewHolder.textViewPrix.setText(String.valueOf(item.prix));
        viewHolder.textViewEtat.setText(VoitureDao.isLoue(item.immatriculation)?"Loué":"Disponible");

        List<Photo> listPhoto = PhotoDao.getByVoiture(item.immatriculation);
        if(listPhoto != null && listPhoto.size()>0) {
            File file = new File(listPhoto.get(0).chemin);
//            Picasso.with(contextVoit).load(listPhoto.get(0).chemin).into(viewHolder.imageViewListVoiture);
            Picasso.with(contextVoit).load(file).into(viewHolder.imageViewListVoiture);
        } else {
            Picasso.with(contextVoit).load(contextVoit.getString(R.string.photo_default)).into(viewHolder.imageViewListVoiture);
        }

        return convertView;
    }

    private class ViewHolder {
        TextView textViewMarque;
        TextView textViewModele;
        TextView textViewPrix;
        TextView textViewEtat;
        ImageView imageViewListVoiture;
    }
}
