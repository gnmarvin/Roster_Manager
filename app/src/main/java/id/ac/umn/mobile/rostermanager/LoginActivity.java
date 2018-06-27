package id.ac.umn.mobile.rostermanager;

import android.content.Intent;
import android.support.annotation.NonNull;
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
                    public void onResponse(@NonNull Call<JsonElement> call, @NonNull Response<JsonElement> response) {
                        JsonElement element = response.body();
                        JsonObject obj = element.getAsJsonObject();
                        JsonObject error = obj.get("error").getAsJsonObject();
                        String error_code = error.get("error_code").getAsString();
                        if (error_code.equals("SYS01004")) {
                            String error_message = error.get("error_message").getAsString();
                            Toast.makeText(LoginActivity.this, error_message, Toast.LENGTH_SHORT).show();
                        } else {
                            JsonObject profile = obj.get("profile").getAsJsonObject();
                            String full_name = profile.get("full_name").getAsString();
                            String email = profile.get("email").getAsString();
                            String mobile_no = profile.get("mobile_no").getAsString();
                            String mobile_whatsapp = profile.get("mobile_whatsapp").getAsString();
                            String mobile_line = profile.get("mobile_line").getAsString();
                            String contact_id = profile.get("contact_id").getAsString();
                            Intent i = new Intent(LoginActivity.this, MainActivity.class);
                            Bundle extras = new Bundle();
                            extras.putString("USERNAME", full_name);
                            extras.putString("EMAIL", email);
                            extras.putString("MOBILE_NO", mobile_no);
                            extras.putString("MOBILE_WHATSAPP", mobile_whatsapp);
                            extras.putString("MOBILE_LINE", mobile_line);
                            extras.putString("CONTACT_ID", contact_id);
                            i.putExtras(extras);
                            startActivity(i);
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonElement> call, Throwable t) {
                    }
                });

            }
        });
    }
}
