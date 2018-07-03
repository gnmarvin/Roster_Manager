
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

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlanEventDetailsActivity extends AppCompatActivity {
    int size;
    String token_id;
    String add_new_event_id, add_organization_id, add_team_campers_id, add_team_photo_id, add_start_date, add_start_time, add_end_time, add_cod_id,
            add_photo_id, add_video_id, add_campers_id, add_astcam_id, add_vtr_id, add_switch_id, add_support_id, add_dolly_id, add_editor_id;
    String add_photo_quota, add_video_quota, add_campers_quota, add_astcam_quota, add_vtr_quota, add_switch_quota, add_support_quota, add_dolly_quota, add_editor_quota, add_created_by_id;
    String[] add_quota = new String[9];
    String[] code_job = new String[9];
    String[] id_job  = new String[9];

    String[][] team_campers = new String[4][2];
    String[][] team_photo = new String[4][2];
    String[][] event_name_list = new String[40][2];
    String[][] crew_cod = new String[30][2];
    Integer counter_campers, counter_photo, counter_list_event, counter_cod, counter_job;

    EditText edit_event_date, edit_event_time_start, edit_event_time_end, edit_cod_event,
    edit_photo_quota, edit_campers_quota, edit_video_quota, edit_ast_campers_quota, edit_dolly_quota, edit_vtr_quota, edit_switch_quota, edit_support_quota, edit_editor_quota;

    AutoCompleteTextView autolistevent, autolistteamphoto, autolistteamcampers, autolistcod;
    ImageView dropdownlistevent, dropdownlistteamphoto, dropdownlistteamcampers, dropdownlistcod;
    Button saveButton, cancelButton;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_event_details);
        final SharedData sharedData = SharedData.getInstance();
        token_id = sharedData.getToken_id();

        autolistevent = findViewById(R.id.edit_name_event_plan_event);
        dropdownlistevent = findViewById(R.id.drop_down_name_event_plan_event);
        autolistteamphoto = findViewById(R.id.edit_photo_team_event_plan_event);
        dropdownlistteamphoto = findViewById(R.id.drop_down_photo_team_event_plan_event);
        autolistteamcampers = findViewById(R.id.edit_camper_team_event_plan_event);
        dropdownlistteamcampers = findViewById(R.id.drop_down_camper_team_event_plan_event);
        autolistcod = findViewById(R.id.edit_cod_event_plan_event);
        dropdownlistcod = findViewById(R.id.drop_down_cod_event_plan_event);
        saveButton = findViewById(R.id.save_event_plan_event);
        cancelButton = findViewById(R.id.cancel_event_plan_event);
        edit_event_date = findViewById(R.id.edit_date_event_plan_event);
        edit_event_time_start = findViewById(R.id.edit_time_start_event_plan_event);
        edit_event_time_end = findViewById(R.id.edit_time_end_event_plan_event);
        edit_photo_quota = findViewById(R.id.edit_photo_quota_event_plan_event);
        edit_campers_quota = findViewById(R.id.edit_campers_quota_event_plan_event);
        edit_video_quota = findViewById(R.id.edit_video_quota_event_plan_event);
        edit_ast_campers_quota = findViewById(R.id.edit_assistant_campers_quota_event_plan_event);
        edit_dolly_quota = findViewById(R.id.edit_dolly_quota_event_plan_event);
        edit_vtr_quota = findViewById(R.id.edit_vtr_quota_event_plan_event);
        edit_switch_quota = findViewById(R.id.edit_switcher_quota_event_plan_event);
        edit_support_quota = findViewById(R.id.edit_support_quota_event_plan_event);
        edit_editor_quota = findViewById(R.id.edit_editor_quota_event_plan_event);

        code_job[0]= "CCCPASTCAMP";
        code_job[1]= "CCCPCAMP";
        code_job[2]= "CCCPDOL";
        code_job[3]= "CCCPEDITOR";
        code_job[4]= "CCCPPHOTO";
        code_job[5]= "CCCPSUPP";
        code_job[6]= "CCCPSWITCH";
        code_job[7]= "CCCPVID";
        code_job[8]= "CCCPVTR";

        Intent detailEvent = getIntent();
        Bundle extras = detailEvent.getExtras();
        if (extras.getString("NEW") == null) {
            callRestList();
            autolistevent.setText(extras.getString("EVENT_NAME"));
            edit_event_date.setText(extras.getString("EVENT_START_DATE"));
            edit_event_time_start.setText(extras.getString("EVENT_START_TIME"));
            edit_event_time_end.setText(extras.getString("EVENT_END_TIME"));
            edit_cod_event.setText(extras.getString("EVENT_COD"));
            autolistteamphoto.setText(extras.getString("EVENT_TEAM_PHOTO"));
            autolistteamcampers.setText(extras.getString("EVENT_TEAM_CAMPERS"));
        } else if (extras.getString("NEW") != null) {
//            addNewEvent();
            callRestList();
            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for(int i =0; i < counter_list_event ; i++){
                        if(autolistevent.getText().toString().equals(event_name_list[i][0])){
                            add_new_event_id = event_name_list[i][1];
                        }
                    }
                    for(int i =0; i < counter_campers ; i++){
                        if(autolistteamcampers.getText().toString().equals(team_campers[i][0])){
                            add_team_campers_id = team_campers[i][1];
                        }
                    }
                    for(int i =0; i < counter_photo ; i++){
                        if(autolistteamphoto.getText().toString().equals(team_photo[i][0])){
                            add_team_photo_id = team_photo[i][1];
                        }
                    }
                    for(int i =0; i < counter_cod ; i++){
                        if(autolistcod.getText().toString().equals(crew_cod[i][0])){
                            add_cod_id = crew_cod[i][1];
                        }
                    }
                    add_organization_id = sharedData.getOrganization_id();
                    add_start_date = edit_event_date.getText().toString();
                    add_start_time = edit_event_time_start.getText().toString();
                    add_end_time = edit_event_time_end.getText().toString();
                    add_quota[0] = edit_ast_campers_quota.getText().toString();
                    add_quota[1] = edit_campers_quota.getText().toString();
                    add_quota[2] = edit_dolly_quota.getText().toString();
                    add_quota[3] = edit_editor_quota.getText().toString();
                    add_quota[4] = edit_photo_quota.getText().toString();
                    add_quota[5] = edit_support_quota.getText().toString();
                    add_quota[6] = edit_switch_quota.getText().toString();
                    add_quota[7] = edit_video_quota.getText().toString();
                    add_quota[8] = edit_vtr_quota.getText().toString();
                    add_created_by_id = sharedData.getContact_id();

                    EventRoster eventRoster = new EventRoster();
                    eventRoster.setOrganizationId(add_organization_id);
                    eventRoster.setRosterDate(add_start_date);
                    eventRoster.setRosterStartTime(add_start_time);
                    eventRoster.setRosterEndTime(add_end_time);
                    eventRoster.setCodId(add_cod_id);
                    eventRoster.setCreatedById(add_created_by_id);

                    EventRosterHasJob eventRosterHasJob = new EventRosterHasJob();

                    for (int i = 0 ; i < 9; i++){
                        Toast.makeText(PlanEventDetailsActivity.this, add_quota[i] + " " + code_job[i] + " " + id_job[i], Toast.LENGTH_SHORT).show();
                    }

//                    Toast.makeText(PlanEventDetailsActivity.this, eventRoster.getOrganizationId(), Toast.LENGTH_SHORT).show();
//                    Toast.makeText(PlanEventDetailsActivity.this, eventRoster.getRosterDate(), Toast.LENGTH_SHORT).show();
//                    Toast.makeText(PlanEventDetailsActivity.this, eventRoster.getRosterStartTime(), Toast.LENGTH_SHORT).show();
//                    Toast.makeText(PlanEventDetailsActivity.this, eventRoster.getRosterEndTime(), Toast.LENGTH_SHORT).show();
//                    Toast.makeText(PlanEventDetailsActivity.this, eventRoster.getCodId(), Toast.LENGTH_SHORT).show();
//                    Toast.makeText(PlanEventDetailsActivity.this, eventRoster.getCreatedById(), Toast.LENGTH_SHORT).show();
//                    Toast.makeText(PlanEventDetailsActivity.this, add_new_event_id, Toast.LENGTH_SHORT).show();
//                    Toast.makeText(PlanEventDetailsActivity.this, add_team_photo_id, Toast.LENGTH_SHORT).show();
//                    Toast.makeText(PlanEventDetailsActivity.this, add_team_campers_id, Toast.LENGTH_SHORT).show();

//                    AddEvents addEvents = new AddEvents();
//                    addEvents.setEventRoster(eventRoster);
//                    EventRosterHasJob eventRosterHasJob = new EventRosterHasJob();
//
//
//                    Intent intent = new Intent(PlanEventDetailsActivity.this, MainActivity.class);
//                    startActivity(intent);
//                    List<String> addjoblist = new ArrayList<String>();
//                    for(int i = 0; i < add_quota.length; i++){
//                        if(add_quota[i].isEmpty()){
//                            eventRosterHasJob.setRosterJobId(sharedData.getJob_id(code_job[i]));
//                            eventRosterHasJob.setRosterJobQuota(add_quota[i]);
//                            eventRosterHasJob.setOrganizationUnitId(add_organization_id);
//                        }
//                        Toast.makeText(PlanEventDetailsActivity.this, eventRosterHasJob.getOrganizationUnitId(), Toast.LENGTH_SHORT).show();
//                        Toast.makeText(PlanEventDetailsActivity.this, eventRosterHasJob.getRosterJobId(), Toast.LENGTH_SHORT).show();
//                        Toast.makeText(PlanEventDetailsActivity.this, eventRosterHasJob.getRosterJobQuota(), Toast.LENGTH_SHORT).show();
//                    }
//
//                    APIService webServiceAPI = APIClient.getApiClient().create(APIService.class);
//                    retrofit2.Call<JsonElement> addEventRoster = webServiceAPI.DeleteEventRoster(sharedData.getToken_id(), addEvents.getEventRoster(), );
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

    /*only for addNewEvent*/
    public void addNewEvent() {
    }
    /*only for addNewEvent*/


    /*call rest and set dropdown list event and team photo, team campers*/
    public void callRestList() {
        counter_campers = 0;
        counter_photo = 0;
        counter_list_event = 0;
        counter_cod = 0;
        counter_job = 0;

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
                    event_name_list[size][0] = singleData.get("event_name").getAsString();
                    //Toast.makeText(PlanEventDetailsActivity.this, singleData.get("id").getAsString(), Toast.LENGTH_SHORT).show();
                    event_name_list[size][1] = singleData.get("id").getAsString();
                    //Toast.makeText(PlanEventDetailsActivity.this,  event_name_list[size][0] + " " + counter_list_event + " " + event_name_list[size][1] , Toast.LENGTH_SHORT).show();
                    counter_list_event++;
                }
                ArrayAdapter<String> adapterlistevent;
                List<String> listeventvalues = new ArrayList<String>();
                for(int i =0; i < counter_list_event ; i++){
                    listeventvalues.add(event_name_list[i][0]);
                }
                adapterlistevent = new ArrayAdapter<String>(PlanEventDetailsActivity.this, android.R.layout.simple_dropdown_item_1line, listeventvalues);
                autolistevent.setAdapter(adapterlistevent);
                dropdownlistevent.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        autolistevent.showDropDown();
                    }
                });
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
                        if (singleData.get("organization_parent_code").getAsString().equals("CCCPOPS")) {
                            if (singleData.get("organization_code").getAsString().contains("CAMP")) {
                                team_campers[counter_campers][0] = singleData.get("organization_name").getAsString();
                                team_campers[counter_campers][1] = singleData.get("id").getAsString();
                                //Toast.makeText(PlanEventDetailsActivity.this, team_campers[counter_campers][1] + " " + counter_campers + " " + team_campers[counter_campers][0], Toast.LENGTH_SHORT).show();
                                counter_campers++;
                            } else if (singleData.get("organization_code").getAsString().contains("PHOTO")) {
                                team_photo[counter_photo][0] = singleData.get("organization_name").getAsString();
                                team_photo[counter_photo][1] = singleData.get("id").getAsString();
                                //Toast.makeText(PlanEventDetailsActivity.this, team_photo[counter_photo][1] + " " + counter_photo + " " + team_photo[counter_photo][0], Toast.LENGTH_SHORT).show();
                                counter_photo++;
                            }
                        }
                    }
                }

                ArrayAdapter<String> adapterteamcampers;
                List<String> campersvalues = new ArrayList<String>();
                for (int i = 0; i < counter_campers; i++) {
                    campersvalues.add(team_campers[i][0]);
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
                    photovalues.add(team_photo[i][0]);
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

        retrofit2.Call<JsonElement> crewList = webServiceAPI.CrewList(token_id);
        crewList.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                JsonElement element = response.body();
                JsonObject obj = element.getAsJsonObject();
                JsonArray data = obj.get("crew").getAsJsonArray();
                for (size = 0; size < data.size(); size++) {
                    JsonObject singleData = data.get(size).getAsJsonObject();
                    if(!singleData.get("organization_id").isJsonNull()){
                        crew_cod[counter_cod][0] = singleData.get("full_name").getAsString();
                        crew_cod[counter_cod][1] = singleData.get("id").getAsString();
                        counter_cod++;
                    }
                }
                ArrayAdapter<String> adaptercrewcod;
                List<String> codvalues = new ArrayList<String>();
                for (int i = 0; i < counter_cod; i++) {
                    codvalues.add(crew_cod[i][0]);
                }
                adaptercrewcod = new ArrayAdapter<String>(PlanEventDetailsActivity.this, android.R.layout.simple_dropdown_item_1line, codvalues);
                autolistcod.setAdapter(adaptercrewcod);
                dropdownlistcod.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        autolistcod.showDropDown();
                    }
                });
                size = 0;
            }
            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
            }

        });

        retrofit2.Call<JsonElement> jobList = webServiceAPI.JobList(token_id);
        jobList.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                JsonElement element = response.body();
                JsonObject obj = element.getAsJsonObject();
                JsonArray data = obj.get("roster_job").getAsJsonArray();
                for(size = 0 ; size < data.size(); size++){
                    JsonObject singleData = data.get(size).getAsJsonObject();
                    for(int i = 0 ; i < data.size() ; i++){
                        if(singleData.get("roster_job_code").getAsString().equals(code_job[i])){
                            id_job[counter_job]=singleData.get("id").getAsString();
                            Toast.makeText(PlanEventDetailsActivity.this, "masuk" + " " + code_job[counter_job] + " " + singleData.get("roster_job_code").getAsString()+ " " + id_job[counter_job], Toast.LENGTH_SHORT).show();
                            counter_job++;
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {

            }
        });
    }
    /*call rest and set dropdown list event and team photo, team campers*/

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
}
