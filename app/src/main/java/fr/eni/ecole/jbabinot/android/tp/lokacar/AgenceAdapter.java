package fr.eni.ecole.jbabinot.android.tp.lokacar;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Agence;
import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Region;

/**
 * Created by amercier2016 on 27/06/2017.
 */

public class AgenceAdapter extends ArrayAdapter<Agence> {

    private LayoutInflater inflater;
    private int resId;

    public AgenceAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Agence> objects) {
        super(context, resource, objects);

        inflater = LayoutInflater.from(context);
        resId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        return getViewAgence(convertView, position);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getViewAgence(convertView, position);
    }

    private class ViewHolder {
        TextView textViewAgence;
    }

    private View getViewAgence(View convertView, int position){
        ViewHolder viewHolder;
        if (convertView == null){
            convertView = inflater.inflate(resId, null);

            viewHolder = new ViewHolder();
            viewHolder.textViewAgence = (TextView) convertView.findViewById(R.id.textViewAgence);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Agence item = getItem(position);

        viewHolder.textViewAgence.setText(item.nom);

        return convertView;
    }
}
