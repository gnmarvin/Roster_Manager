package id.ac.umn.mobile.rostermanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final Button loginButton = (Button) findViewById(R.id.login);
        final TextView forgotText = (TextView) findViewById(R.id.forgot);
        final EditText usernameEdit = (EditText) findViewById(R.id.username_edit);
        final EditText passwordEdit = (EditText) findViewById(R.id.password);

        forgotText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(i);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameLogin = usernameEdit.getText().toString();
                String passwordLogin = passwordEdit.getText().toString();
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                i.putExtra("USERNAME", usernameEdit.getText().toString());
                startActivity(i);
            }
        });
    }
}
