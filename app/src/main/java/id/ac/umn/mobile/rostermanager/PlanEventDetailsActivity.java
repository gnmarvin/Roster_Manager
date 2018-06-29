
package id.ac.umn.mobile.rostermanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlanEventDetailsActivity extends AppCompatActivity {
    int size;
    String event_name, event_start_date, event_start_time, event_end_time, event_cod;
    String[] team_campers = new String[20];
    String[] team_photo = new String[20];
    String[] id_team_campers = new String[20];
    String[] id_team_photo = new String[20];
    String[] event_name_list = new String[50];
    String[] event_id = new String[50];

    EditText detail_event_name, detail_event_date, detail_event_time_start, detail_event_time_end, edit_cod_event,
    edit_photo_team, edit_campers_team, edit_photo_quota, edit_photo_tx_quota, edit_photo_pk_quota, edit_photo_extra_quota, edit_video_quota, edit_video_extra_quota,
    edit_campers_quota, edit_ast_campers_quota, edit_dolly_quota, edit_vtr_quota, edit_switcher_quota, edit_support_quota;

    final AutoCompleteTextView autolistevent = findViewById(R.id.edit_name_event_plan_event);
    final ImageView dropdownlistevent = findViewById(R.id.drop_down_name_event_plan_event);
    final AutoCompleteTextView autolistteamphoto = findViewById(R.id.edit_photo_team_event_plan_event);
    final ImageView dropdownlistteamphoto = findViewById(R.id.drop_down_photo_team_event_plan_event);
    final AutoCompleteTextView autolistteamcampers = findViewById(R.id.edit_camper_team_event_plan_event);
    final ImageView dropdownlistteamcampers = findViewById(R.id.drop_down_camper_team_event_plan_event);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_event_details);

        
        Intent detailEvent = getIntent();
        Bundle extras = detailEvent.getExtras();
        if (extras.getString("NEW") == null) {
            event_name = extras.getString("EVENT_NAME");
            event_start_date = extras.getString("EVENT_START_DATE");
            event_start_time = extras.getString("EVENT_START_TIME");
            event_end_time = extras.getString("EVENT_END_TIME");
            event_cod = extras.getString("EVENT_COD");

            detail_event_name = findViewById(R.id.edit_name_event_plan_event);
            detail_event_name.setText(event_name);
            detail_event_date = findViewById(R.id.edit_date_event_plan_event);
            detail_event_date.setText(event_start_date);
            detail_event_time_start = findViewById(R.id.edit_time_start_event_plan_event);
            detail_event_time_start.setText(event_start_time);
            detail_event_time_end = findViewById(R.id.edit_time_end_event_plan_event);
            detail_event_time_end.setText(event_end_time);
            edit_cod_event = findViewById(R.id.edit_cod_event_plan_event);
            edit_cod_event.setText(event_cod);
//        edit_photo_team x;
//        edit_campers_team x;
//        edit_photo_quota x;
//        edit_photo_tx_quota x;
//        edit_photo_pk_quota x;
//        edit_photo_extra_quota x;
//        edit_video_quota x;
//        edit_video_extra_quota x;
//        edit_campers_quota x;
//        edit_ast_campers_quota x;
//        edit_dolly_quota x;
//        edit_vtr_quota x;
//        edit_switcher_quota x;
//        edit_support_quota x;
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
        retrofit2.Call<JsonElement> listEvent = webServiceAPI.EventList();
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
                }
                size = 0;
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {

            }
        });

        retrofit2.Call<JsonElement> listTeam = webServiceAPI.TeamList();
        listTeam.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                JsonElement element = response.body();
                JsonObject obj = element.getAsJsonObject();
                JsonArray data = obj.get("organization").getAsJsonArray();
                for (size = 0; size < data.size(); size++) {
                    JsonObject singleData = data.get(size).getAsJsonObject();
                    if(singleData.get("organization_code_parent").getAsString().equals("CCCPOPS")){
                        if(singleData.get("organization_code").getAsString().contains("CAMPERS")){
                            team_campers[size] = singleData.get("organization_name").getAsString();
                            id_team_campers[size] = singleData.get("id").getAsString();
                        }
                        else if(singleData.get("organization_code").getAsString().contains("PHOTO")){
                            team_photo[size] = singleData.get("organization_name").getAsString();
                            id_team_photo[size] = singleData.get("id").getAsString();

                        }
                    }

                }
                ArrayAdapter<String> adapterteamcampers;
                adapterteamcampers = new ArrayAdapter<String>(PlanEventDetailsActivity.this, android.R.layout.simple_dropdown_item_1line, team_campers);
                autolistteamcampers.setAdapter(adapterteamcampers);
                dropdownlistteamcampers.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        autolistteamcampers.showDropDown();
                    }
                });

                //Dropdown autocomplete team
                ArrayAdapter<String> adapterteamphoto;
                adapterteamphoto = new ArrayAdapter<String>(PlanEventDetailsActivity.this, android.R.layout.simple_dropdown_item_1line, team_photo);
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
    }
}
