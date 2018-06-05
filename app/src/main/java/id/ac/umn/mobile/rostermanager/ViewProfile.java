package id.ac.umn.mobile.rostermanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.app.Dialog;

public class ViewProfile extends AppCompatActivity {
    String full_name = "";               //variable buat nampung username inputan user
    String email = "";
    String mobile_no = "";
    String mobile_whatsapp = "";
    String mobile_line = "";
    TextView name_this, email_this, phone_this, line_this, whatsapp_this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_profile);

        Intent i = getIntent();
        Bundle extras = i.getExtras();
        full_name = extras.getString("USERNAME");
        email = extras.getString("EMAIL");
        mobile_no = extras.getString("MOBILE_NO");
        mobile_whatsapp = extras.getString("MOBILE_WHATSAPP");
        mobile_line = extras.getString("MOBILE_LINE");

        name_this = findViewById(R.id.name);
        name_this.setText(full_name);
        email_this = findViewById(R.id.email);
        email_this.setText(email);
        phone_this = findViewById(R.id.phone);
        phone_this.setText(mobile_no);
        line_this = findViewById(R.id.line);
        line_this.setText(mobile_line);
        whatsapp_this = findViewById(R.id.whatsapp);
        whatsapp_this.setText(mobile_whatsapp);
    }
}
