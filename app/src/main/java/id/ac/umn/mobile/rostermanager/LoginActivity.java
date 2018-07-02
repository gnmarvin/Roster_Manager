package id.ac.umn.mobile.rostermanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CheckBox;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    Button loginButton;
    TextView forgotText;
    EditText usernameEdit, passwordEdit;
    CheckBox saveLoginCheck;
    SharedPreferences loginPreferences;
    SharedPreferences.Editor loginPrefEditor;
    Boolean checkisTrue;

    protected void setFilterTeam(){
        //fungsi untuk menambahkan filter TEAM apa yang bertugas, khususnya untuk TEAM MANAGER
        final SharedData sharedData = SharedData.getInstance();
        APIService webServiceAPI = APIClient.getApiClient().create(APIService.class);
        retrofit2.Call<JsonElement> listTeam = webServiceAPI.TeamList(sharedData.getToken_id());
        //Toast.makeText(LoginActivity.this, sharedData.getRole(), Toast.LENGTH_SHORT).show();
        if(sharedData.getRole().equals("TM")){
            //Toast.makeText(LoginActivity.this, sharedData.getRole(), Toast.LENGTH_SHORT).show();
            listTeam.enqueue(new Callback<JsonElement>() {
                @Override
                public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                    JsonElement element = response.body();
                    JsonObject obj = element.getAsJsonObject();
                    JsonArray organization = obj.get("organization").getAsJsonArray();
                    for(int i= 0; i < organization.size(); i++){
                        JsonObject teamData = organization.get(i).getAsJsonObject();
                        if(!teamData.get("manager_full_name").isJsonNull()){
                            if(teamData.get("manager_full_name").getAsString().equals(sharedData.getName())){
                                sharedData.setCode_team(teamData.get("organization_code").getAsString());
                                sharedData.setName_team(teamData.get("organization_name").getAsString());
                                //Toast.makeText(LoginActivity.this, sharedData.getRole() +" "+ sharedData.getCode_team()+" "+sharedData.getName_team(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
                @Override
                public void onFailure(Call<JsonElement> call, Throwable t) {

                }
            });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final SharedData sharedData = SharedData.getInstance();
        loginButton = (Button) findViewById(R.id.login);
        forgotText = (TextView) findViewById(R.id.forgot);
        usernameEdit = (EditText) findViewById(R.id.username_edit);
        passwordEdit = (EditText) findViewById(R.id.password);
        saveLoginCheck = (CheckBox) findViewById(R.id.saveLogin);
        loginPreferences = getSharedPreferences("Remember", MODE_PRIVATE);
        loginPrefEditor = loginPreferences.edit();
        checkisTrue = loginPreferences.getBoolean("checkisTrue", false);
        if (checkisTrue) {
            usernameEdit.setText(loginPreferences.getString("username", ""));
            saveLoginCheck.setChecked(true);
        }

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
                final String usernameLogin = usernameEdit.getText().toString();
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
                            if (saveLoginCheck.isChecked()) {
                                loginPrefEditor.putBoolean("checkisTrue", true);
                                loginPrefEditor.putString("username", usernameLogin);
                                loginPrefEditor.apply();
                            } else {
                                loginPrefEditor.clear();
                                loginPrefEditor.commit();
                            }
                            JsonObject profile = obj.get("profile").getAsJsonObject();
                            JsonObject roles = obj.get("role").getAsJsonObject();
                            JsonObject token = obj.get("token").getAsJsonObject();
                            String full_name = profile.get("full_name").getAsString();
                            String email = profile.get("email").getAsString();
                            String mobile_no = profile.get("mobile_no").getAsString();
                            String mobile_whatsapp = profile.get("mobile_whatsapp").getAsString();
                            String mobile_line = profile.get("mobile_line").getAsString();
                            String contact_id = profile.get("contact_id").getAsString();
                            String role = roles.get("role").getAsString();
                            String token_id = token.get("token_id").getAsString();
                            Intent i = new Intent(LoginActivity.this, MainActivity.class);
                            sharedData.setName(full_name);
                            sharedData.setEmail(email);
                            sharedData.setMobile_no(mobile_no);
                            sharedData.setMobile_whatsapp(mobile_whatsapp);
                            sharedData.setMobile_line(mobile_line);
                            sharedData.setContact_id(contact_id);
                            sharedData.setRole(role);
                            sharedData.setToken_id(token_id);
                            setFilterTeam();
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

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
