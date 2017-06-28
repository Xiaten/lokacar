package fr.eni.ecole.jbabinot.android.tp.lokacar;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Client;

public class AddClientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client);
    }

    public void validateAddClient(){
        boolean error = false;
        EditText editTextNom = (EditText) findViewById(R.id.editTextNom);
        EditText editTextPrenom = (EditText) findViewById(R.id.editTextPrenom);
        EditText editTextTel = (EditText) findViewById(R.id.editTextTel);
        EditText editTextMail = (EditText) findViewById(R.id.editTextMail);

        if (editTextNom.getText().toString().isEmpty()){
            editTextNom.setError(getString(R.string.add_client_assert_nom));
            error = true;
        }
        if (editTextPrenom.getText().toString().isEmpty()){
            editTextPrenom.setError(getString(R.string.add_client_assert_prenom));
            error = true;
        }
        if (editTextTel.getText().toString().isEmpty()){
            editTextTel.setError(getString(R.string.add_client_assert_tel));
            error = true;
        }
        if (editTextMail.getText().toString().isEmpty()){
            editTextMail.setError(getString(R.string.add_client_assert_mail));
            error = true;
        }

        if (!error){
            Client client = new Client(editTextNom.getText().toString(), editTextPrenom.getText().toString(), editTextMail.getText().toString(), editTextTel.getText().toString());
            client.save();
            Bundle bundle = new Bundle();
            Intent intent = new Intent();
            bundle.putSerializable("client", client);
            intent.putExtras(bundle);
            setResult(Activity.RESULT_OK, intent);
            finish();
            // https://stackoverflow.com/questions/7020583/receive-the-response-from-second-activity
        }
    }
}
