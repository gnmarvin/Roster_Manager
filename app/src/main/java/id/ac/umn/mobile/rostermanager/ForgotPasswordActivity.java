package id.ac.umn.mobile.rostermanager;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ForgotPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);


        final TextView emailText = (TextView) findViewById(R.id.email);
        final Button sendRequestButton = (Button) findViewById(R.id.send_request);
        sendRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DisplayToast(emailText.getText().toString());
                if (emailText.getText().toString() == "") {
                    DisplayToast("Please fill your email");
                }
                else if (emailText.getText().toString() == "admin@admin") {
                    DisplayToast("Check your email to fix your account");
                }
                else {
                    DisplayToast("Account not found");
                }
            }
        });
    }

    private void DisplayToast(String pesan) {
        Toast.makeText(getBaseContext(), pesan, Toast.LENGTH_SHORT).show();
    }

}
