package id.ac.umn.mobile.rostermanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlanCrewsDetailsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private List<ListCrewDetailsModel> listCrewDetails;

    String token_id, event_id, event_name, crew_name, roster_id, job_name, team_name;
    TextView name_event, date_event, start_time_event, end_time_event, cod_event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_crews_details);
        SharedData sharedData = SharedData.getInstance();
        event_id = sharedData.getEvent_id_plan_crew();
        token_id = sharedData.getToken_id();
        Bundle extras = getIntent().getExtras();
        name_event = findViewById(R.id.txt_name_event_plan_crew);
        date_event = findViewById(R.id.txt_date_plan_crew);
        start_time_event = findViewById(R.id.txt_time_start_plan_crew);
        end_time_event = findViewById(R.id.txt_time_end_plan_crew);
        cod_event = findViewById(R.id.txt_cod_plan_crew);


        name_event.setText(sharedData.getEvent_name_crew_details());
        date_event.setText(sharedData.getDate_event_crew_details());
        start_time_event.setText(sharedData.getStart_time_event_crew_details());
        end_time_event.setText(sharedData.getEnd_time_event_crew_details());
        cod_event.setText(sharedData.getCod_event_crew_details());

        recyclerView = (RecyclerView) findViewById(R.id.recycler_list_crew_plan_crew);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listCrewDetails = new ArrayList<>();

        APIService webServiceAPI = APIClient.getApiClient().create(APIService.class);
        retrofit2.Call<JsonElement> crewRoster = webServiceAPI.CrewRoster(token_id, event_id);
        crewRoster.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                JsonElement element = response.body();
                JsonObject obj = element.getAsJsonObject();
                Toast.makeText(PlanCrewsDetailsActivity.this, "", Toast.LENGTH_SHORT).show();
                JsonArray data = obj.get("event_roster_crew").getAsJsonArray();

                for(int i = 0; i < data.size() ; i++){
                    JsonObject singleData = data.getAsJsonObject();
                    if(singleData.get("crew_full_name").isJsonNull()){
                        crew_name = "CREW";
                    }
                    else{
                        crew_name = singleData.get("crew_full_name").getAsString();
                    }
                    job_name = singleData.get("roster_job_name").getAsString();
                    roster_id = singleData.get("id").getAsString();
                    team_name = singleData.get("organization_name").getAsString();

                    listCrewDetails.add(new ListCrewDetailsModel(
                            crew_name,
                            job_name,
                            team_name,
                            roster_id
                    ));
                }
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {

            }
        });

        ListCrewDetailsAdapter adapter = new ListCrewDetailsAdapter(this, listCrewDetails);

        recyclerView.setAdapter(adapter);
    }
}
