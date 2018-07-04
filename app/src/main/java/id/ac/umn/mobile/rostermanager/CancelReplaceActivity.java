package id.ac.umn.mobile.rostermanager;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CancelReplaceActivity extends AppCompatActivity {
    String reason_str, position, ref_id;
    EditText reason;
    Button submitbutton, cancelbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_replace);
        reason = findViewById(R.id.reason_cancel_request);
        submitbutton = findViewById(R.id.submit_cancel_request);
        cancelbutton = findViewById(R.id.cancel_cancel_request);

        Intent extras = getIntent();
        position = extras.getStringExtra("POSITION");

        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 AlertDialog.Builder dialogyes = new AlertDialog.Builder(CancelReplaceActivity.this);
                 dialogyes.setTitle("Are you sure?")
                         .setMessage("By clicking yes button, you'll request replacement for your position service.")
                 .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedData sharedData = SharedData.getInstance();
                        ref_id = sharedData.getRef_id(Integer.parseInt(position));
                        EventRosterCrew eventRosterCrew = new EventRosterCrew();
                        CancelCrew cancelCrew = new CancelCrew();
                        eventRosterCrew.setId(ref_id);
                        eventRosterCrew.setRemarks(reason.getText().toString());
                        APIService webServiceAPI = APIClient.getApiClient().create(APIService.class);
                        retrofit2.Call<JsonElement> cancelCrewAPI = webServiceAPI.CancelCrew(sharedData.getToken_id(), cancelCrew.withEventRosterCrew(eventRosterCrew));
                        cancelCrewAPI.enqueue(new Callback<JsonElement>() {
                            @Override
                            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                                JsonElement element = response.body();
                                JsonObject obj = element.getAsJsonObject();
                                JsonObject error = obj.get("error").getAsJsonObject();
                                String error_code = error.get("error_code").getAsString();
                                if (error_code.equals("0")) {
                                } else {
                                    String error_message = error.get("error_message").getAsString();
                                    Toast.makeText(CancelReplaceActivity.this, error_message, Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<JsonElement> call, Throwable t) {

                            }
                        });
                        onBackPressed();
                        dialog.dismiss();
                    }
                });
                dialogyes.show();
            }
        });

        cancelbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(CancelReplaceActivity.this);
                dialog.setMessage("Discard changes?")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onBackPressed();
                        dialog.dismiss();
                    }
                }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
