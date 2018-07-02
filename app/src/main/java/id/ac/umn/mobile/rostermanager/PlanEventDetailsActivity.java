
package id.ac.umn.mobile.rostermanager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlanEventDetailsActivity extends AppCompatActivity {
    int size;
    String token_id;
    String[] team_campers = new String[20];
    String[] team_photo = new String[20];
    String[] id_team_campers = new String[20];
    String[] id_team_photo = new String[20];
    String[] event_name_list = new String[50];
    String[] event_id = new String[50];
    Integer counter_campers = 0, counter_photo = 0, counter_list_event = 0;

    EditText detail_event_name, detail_event_date, detail_event_time_start, detail_event_time_end, detail_cod_event,
    detail_photo_team, detail_campers_team, edit_photo_quota, edit_photo_tx_quota, edit_photo_pk_quota, edit_photo_extra_quota, edit_video_quota, edit_video_extra_quota,
    edit_campers_quota, edit_ast_campers_quota, edit_dolly_quota, edit_vtr_quota, edit_switcher_quota, edit_support_quota;

    AutoCompleteTextView autolistevent, autolistteamphoto, autolistteamcampers;
    ImageView dropdownlistevent, dropdownlistteamphoto, dropdownlistteamcampers;
    Button saveButton, cancelButton;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_event_details);
        SharedData sharedData = SharedData.getInstance();
        token_id = sharedData.getToken_id();

        autolistevent = findViewById(R.id.edit_name_event_plan_event);
        dropdownlistevent = findViewById(R.id.drop_down_name_event_plan_event);
        autolistteamphoto = findViewById(R.id.edit_photo_team_event_plan_event);
        dropdownlistteamphoto = findViewById(R.id.drop_down_photo_team_event_plan_event);
        autolistteamcampers = findViewById(R.id.edit_camper_team_event_plan_event);
        dropdownlistteamcampers = findViewById(R.id.drop_down_camper_team_event_plan_event);
        saveButton = findViewById(R.id.save_event_plan_event);
        cancelButton = findViewById(R.id.cancel_event_plan_event);
//        detail_event_name = findViewById(R.id.edit_name_event_plan_event);
        detail_event_date = findViewById(R.id.edit_date_event_plan_event);
        detail_event_time_start = findViewById(R.id.edit_time_start_event_plan_event);
        detail_event_time_end = findViewById(R.id.edit_time_end_event_plan_event);
        detail_cod_event = findViewById(R.id.edit_cod_event_plan_event);
        
        Intent detailEvent = getIntent();
        Bundle extras = detailEvent.getExtras();
        if (extras.getString("NEW") == null) {
            callRestList();
//            detail_event_name.setText(event_name);
            autolistevent.setText(extras.getString("EVENT_NAME"));
            detail_event_date.setText(extras.getString("EVENT_START_DATE"));
            detail_event_time_start.setText(extras.getString("EVENT_START_TIME"));
            detail_event_time_end.setText(extras.getString("EVENT_END_TIME"));
            detail_cod_event.setText(extras.getString("EVENT_COD"));
            autolistteamphoto.setText(extras.getString("EVENT_TEAM_PHOTO"));
            autolistteamcampers.setText(extras.getString("EVENT_TEAM_CAMPERS"));
        } else if (extras.getString("NEW") != null) {
            addNewEvent();
        }
    }

    public void showDatePickerDialog(View v) {
        DatePickerFragment datePickerFragment = new DatePickerFragment(); //test
        datePickerFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void showTimePickerDialog(View view){
        TimePickerFragment timePickerFragment = new TimePickerFragment();
        timePickerFragment.show(getFragmentManager(), "datePicker");
    }

    public void processDatePickerResult(int year, int month, int day) {
        String month_string = Integer.toString(month + 1);
        String day_string = Integer.toString(day);
        String year_string = Integer.toString(year);
        String dateMessage = (year_string + "-" + month_string + "-" + day_string);
        EditText dateText = (EditText) findViewById(R.id.edit_date_event_plan_event);
        dateText.setText(dateMessage);
    }

    public void processTimePickerResult(int hourOfDay, int minute) {
        String hour_string = Integer.toString(hourOfDay);
        String minute_string = Integer.toString(minute);
        String timeMessage = (hour_string + ":" + minute_string);
        EditText startEventTime = (EditText) findViewById(R.id.edit_time_start_event_plan_event);
        EditText endEventTime = (EditText) findViewById(R.id.edit_time_end_event_plan_event);
        if (startEventTime.isFocused()) {
            startEventTime.setText(timeMessage);
        } else if (endEventTime.isFocused()) {
            endEventTime.setText(timeMessage);
        }
    }

    public void callRestList(){
        APIService webServiceAPI = APIClient.getApiClient().create(APIService.class);
        retrofit2.Call<JsonElement> listEvent = webServiceAPI.EventList(token_id);
        listEvent.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                JsonElement element = response.body();
                JsonObject obj = element.getAsJsonObject();
                JsonArray data = obj.get("event").getAsJsonArray();
                for (size = 0; size < data.size(); size++) {
                    JsonObject singleData = data.get(size).getAsJsonObject();
                    event_name_list[size] = singleData.get("event_name").getAsString();
                    event_id[size] = singleData.get("id").getAsString();
                    counter_list_event++;
                }
                size = 0;
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {

            }
        });

        retrofit2.Call<JsonElement> listTeam = webServiceAPI.TeamList(token_id);
        listTeam.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(@NonNull Call<JsonElement> call, @NonNull Response<JsonElement> response) {
                JsonElement element = response.body();
                JsonObject obj = element.getAsJsonObject();
                JsonArray data = obj.get("organization").getAsJsonArray();
                for (size = 0; size < data.size(); size++) {
                    JsonObject singleData = data.get(size).getAsJsonObject();
                    if (!singleData.get("organization_parent_code").isJsonNull()) {
                        if(singleData.get("organization_parent_code").getAsString().equals("CCCPOPS")){
                            if(singleData.get("organization_code").getAsString().contains("CAMP")){
                                team_campers[counter_campers] = singleData.get("organization_name").getAsString();
                                id_team_campers[counter_campers] = singleData.get("id").getAsString();
                                counter_campers++;
                            }
                            else if(singleData.get("organization_code").getAsString().contains("PHOTO")){
                                team_photo[counter_photo] = singleData.get("organization_name").getAsString();
                                id_team_photo[counter_photo] = singleData.get("id").getAsString();
                                counter_photo++;
                            }
                        }
                    }
                }

                ArrayAdapter<String> adapterlistevent;
                List<String> listeventvalues = new ArrayList<String>();
                for(int i =0; i < counter_list_event ; i++){
                    listeventvalues.add(event_name_list[i]+" "+event_id[i]);
                }
                adapterlistevent = new ArrayAdapter<String>(PlanEventDetailsActivity.this, android.R.layout.simple_dropdown_item_1line, listeventvalues);
                autolistevent.setAdapter(adapterlistevent);
                dropdownlistevent.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        autolistevent.showDropDown();
                    }
                });

                ArrayAdapter<String> adapterteamcampers;
                List<String> campersvalues = new ArrayList<String>();
                for (int i = 0; i < counter_campers; i++) {
                    campersvalues.add(team_campers[i]);
                }
                adapterteamcampers = new ArrayAdapter<String>(PlanEventDetailsActivity.this, android.R.layout.simple_dropdown_item_1line, campersvalues);
                autolistteamcampers.setAdapter(adapterteamcampers);
                dropdownlistteamcampers.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        autolistteamcampers.showDropDown();
                    }
                });

                ArrayAdapter<String> adapterteamphoto;
                List<String> photovalues = new ArrayList<String>();
                for (int i = 0; i < counter_photo; i++) {
                    photovalues.add(team_photo[i]);
                }
                adapterteamphoto = new ArrayAdapter<String>(PlanEventDetailsActivity.this, android.R.layout.simple_dropdown_item_1line, photovalues);
                autolistteamphoto.setAdapter(adapterteamphoto);
                dropdownlistteamphoto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        autolistteamphoto.showDropDown();
                    }
                });
                size = 0;
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {

            }
        });
    }

    public void addNewEvent() {
        callRestList();
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
