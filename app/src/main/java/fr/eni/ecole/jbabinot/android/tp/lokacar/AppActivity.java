package fr.eni.ecole.jbabinot.android.tp.lokacar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

/**
 * Created by amercier2016 on 21/06/2017.
 */

public class AppActivity extends AppCompatActivity {

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // check actionBar
        if (getSupportActionBar() != null){

            if (!(this instanceof HomeActivity)){
                // afficher la flèche de retour
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        }
    }
}
