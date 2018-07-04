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

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
                    String email = emailText.getText().toString();
                    APIService webServiceAPI = APIClient.getApiClient().create(APIService.class);
                    Call<JsonElement> recovery = webServiceAPI.Recovery(email);
                    recovery.enqueue(new Callback<JsonElement>() {
                        @Override
                        public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                            JsonElement element = response.body();
                            JsonObject obj = element.getAsJsonObject();
                            JsonObject error = obj.get("error").getAsJsonObject();
                            String error_code = error.get("error_code").getAsString();
                            if (error_code.equals("SYS01005")) {
                                String error_message = error.get("error_message").getAsString();
                                Toast.makeText(ForgotPasswordActivity.this, error_message, Toast.LENGTH_SHORT).show();
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
