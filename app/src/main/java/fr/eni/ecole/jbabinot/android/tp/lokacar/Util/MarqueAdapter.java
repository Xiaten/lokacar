package fr.eni.ecole.jbabinot.android.tp.lokacar.Util;

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

import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Marque;
import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Region;
import fr.eni.ecole.jbabinot.android.tp.lokacar.R;

/**
 * Created by amercier2016 on 28/06/2017.
 */

public class MarqueAdapter extends ArrayAdapter<Marque> {

    private LayoutInflater inflater;
    private int resId;

    public MarqueAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Marque> objects) {
        super(context, resource, objects);

        inflater = LayoutInflater.from(context);
        resId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        return getViewRegion(convertView, position);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getViewRegion(convertView, position);
    }

    private class ViewHolder {
        TextView textViewMarque;
    }

    private View getViewRegion(View convertView, int position) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(resId, null);

            viewHolder = new ViewHolder();
            viewHolder.textViewMarque = (TextView) convertView.findViewById(R.id.textViewMarque);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Marque item = getItem(position);

        viewHolder.textViewMarque.setText(item.nom);

        return convertView;
    }
}
