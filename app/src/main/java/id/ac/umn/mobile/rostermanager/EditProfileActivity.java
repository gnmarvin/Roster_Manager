package id.ac.umn.mobile.rostermanager;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditProfileActivity extends AppCompatActivity {
    String username = "";
    TextView profile_username;
    EditText editUsername, editEmail, editPhone, editLineid, editWhatsapp;
    Button backButton, saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        Bundle extras = getIntent().getExtras();
        username = extras.getString("USERNAME");
        profile_username = (TextView) findViewById(R.id.profile_username);
        profile_username.setText(username);

        editUsername = (EditText) findViewById(R.id.edit_username);
        editEmail = (EditText) findViewById(R.id.edit_email);
        editPhone = (EditText) findViewById(R.id.edit_phone);
        editLineid = (EditText) findViewById(R.id.edit_lineid);
        editWhatsapp = (EditText) findViewById(R.id.edit_whatsapp);
        backButton = (Button) findViewById(R.id.back_button);
        saveButton = (Button) findViewById(R.id.save_button);

        editUsername.addTextChangedListener(filterTextwatcher);
        editEmail.addTextChangedListener(filterTextwatcher);
        editPhone.addTextChangedListener(filterTextwatcher);
        editLineid.addTextChangedListener(filterTextwatcher);
        editWhatsapp.addTextChangedListener(filterTextwatcher);
    }

    private TextWatcher filterTextwatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
}
