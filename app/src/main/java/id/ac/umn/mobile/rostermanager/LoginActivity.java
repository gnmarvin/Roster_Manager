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

import org.json.JSONObject;

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
                APIService webServiceAPI = APIClient.getApiClient().create(APIService.class);
                Call<JsonElement> callLogin = webServiceAPI.Login(usernameLogin, passwordLogin);

                callLogin.enqueue(new Callback<JsonElement>() {
                    @Override
                    public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                        JsonElement element = response.body();
                        JsonObject obj = element.getAsJsonObject();
                        JsonObject profile = obj.get("profile").getAsJsonObject();
                        String full_name = profile.get("full_name").getAsString();
                        String email = profile.get("email").getAsString();
                        String mobile_no = profile.get("mobile_no").getAsString();
                        String mobile_whatsapp = profile.get("mobile_whatsapp").getAsString();
                        String mobile_line = profile.get("mobile_line").getAsString();
                        Toast.makeText(LoginActivity.this, email, Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
                        Bundle extras = new Bundle();
                        extras.putString("USERNAME", full_name);
                        extras.putString("EMAIL", email);
                        extras.putString("MOBILE_NO", mobile_no);
                        extras.putString("MOBILE_WHATSAPP", mobile_whatsapp);
                        extras.putString("MOBILE_LINE", mobile_line);
                        i.putExtras(extras);
                        startActivity(i);
                    }

                    @Override
                    public void onFailure(Call<JsonElement> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, "failed", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
}
