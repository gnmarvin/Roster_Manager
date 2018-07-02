package id.ac.umn.mobile.rostermanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

public class PlanEventsFragment extends Fragment {
    List<PlanEventsModel> planEventList;
    RecyclerView rv;
    String set_event_name, set_event_id, set_cod, set_event_end_time, set_event_start_time, set_event_start_date, token_id, role;
    String[] event_id = new String[20];
    String[] event_name = new String[20];
    String[] event_start_date = new String[20];
    String[] event_start_time = new String[20];
    String[] event_end_time = new String[20];
    String[] cod = new String[20];
    String[] team_photo = new String[20];
    String[] team_campers = new String[20];
    int[] quota_photo = new int[20];
    int[] quota_campers = new int[20];
    int size; // deklarasi variable untuk menyimpan banyaknya data event yang ada

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //get recyclerview from fragmentevent.xml
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_plan_events, container, false);
        rv = (RecyclerView) rootView.findViewById(R.id.recycler_plan_events);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);

        SharedData sharedData = SharedData.getInstance();
        token_id = sharedData.getToken_id();
        planEventList = new ArrayList<>(); // untuk menyimpan list event yang ada

        APIService webServiceAPI = APIClient.getApiClient().create(APIService.class);
        retrofit2.Call<JsonElement> listEvent = webServiceAPI.EventRoster(token_id);
        listEvent.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(retrofit2.Call<JsonElement> call, Response<JsonElement> response) {
                JsonElement element = response.body();
                JsonObject obj = element.getAsJsonObject();
                JsonArray data = obj.get("event_roster").getAsJsonArray();
                for (size = 0; size < data.size(); size++) {
                    JsonObject singleData = data.get(size).getAsJsonObject();
                    set_event_name  = singleData.get("event_name").getAsString();
                    set_event_start_date = singleData.get("roster_date").getAsString();
                    set_event_start_time = singleData.get("roster_start_time").getAsString();
                    set_event_end_time = singleData.get("roster_end_time").getAsString();
                    if (singleData.get("cod_full_name").isJsonNull()) {
                        set_cod = "COD";
                    }
                    else{
                        set_cod = singleData.get("cod_full_name").getAsString();
                    }
                    set_event_id = singleData.get("id").getAsString();
                    setter(set_event_id, set_event_name, set_event_start_date, set_event_start_time, set_event_end_time, set_cod, size);
                }
            }
            @Override
            public void onFailure(retrofit2.Call<JsonElement> call, Throwable t) {
            }
        });
        FloatingActionButton fab = rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addEvent = new Intent(getContext(), PlanEventDetailsActivity.class);
                Bundle extras = new Bundle();
                extras.putString("NEW", "new");
                addEvent.putExtras(extras);
                startActivity(addEvent);
            }
        });
        return rootView;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Plan Events");
    }

    public void setter(String set_event_id, String set_event_name, String set_event_start_date, String set_event_start_time, String set_event_end_time, String set_cod, final Integer size){
        event_name[size] = set_event_name;
        event_id[size] = set_event_id;
        event_start_date[size] = set_event_start_date;
        event_start_time[size] = set_event_start_time;
        event_end_time[size] = set_event_end_time;
        cod[size] = set_cod;
        APIService webServiceAPI = APIClient.getApiClient().create(APIService.class);
        retrofit2.Call<JsonElement> listJob = webServiceAPI.JobRoster(token_id, event_id[size]);
        listJob.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(retrofit2.Call<JsonElement> call, Response<JsonElement> response) {
                JsonElement element = response.body();
                JsonObject obj = element.getAsJsonObject();
                JsonArray data = obj.get("event_roster_job").getAsJsonArray();
                for (int j = 0; j < data.size(); j++) {
                    JsonObject singleData = data.get(j).getAsJsonObject();
                    if(singleData.get("organization_code").isJsonNull()){
                        team_photo[size] = "Photographers";
                        quota_photo[size] = 0;
                        team_campers[size] = "Camera Persons";
                        quota_campers[size] = 0;
                    }
                    else{
                        if(singleData.get("organization_code").getAsString().contains("PHOTO"))
                        {
                            team_photo[size] = singleData.get("organization_name").getAsString();
                            quota_photo[size] += singleData.get("roster_job_quota").getAsInt();
                        }
                        else if(singleData.get("organization_code").getAsString().contains("CAMP"))
                        {
                            team_campers[size] = singleData.get("organization_name").getAsString();
                            quota_campers[size] += singleData.get("roster_job_quota").getAsInt();
                        }
                    }
                }
                addtoCard(event_name[size], event_start_date[size], event_start_time[size], event_end_time[size], cod[size], team_photo[size], quota_photo[size], team_campers[size], quota_campers[size], event_id[size]);
            }
            @Override
            public void onFailure(retrofit2.Call<JsonElement> call, Throwable t) {
            }
        });
    }

    public void addtoCard(String set_event_name, String set_event_start_date, String set_event_start_time, String set_event_end_time, String set_cod, String set_team_photo, int set_quota_photo, String set_team_campers, int set_quota_campers, String set_event_id){
        planEventList.add(
                new PlanEventsModel(
                        set_event_name,
                        set_event_start_date,
                        set_event_start_time,
                        set_event_end_time,
                        set_cod,
                        set_team_photo,
                        Integer.toString(set_quota_photo),
                        set_team_campers,
                        Integer.toString(set_quota_campers),
                        set_event_id));
        PlanEventsAdapter adapter = new PlanEventsAdapter(getContext(), planEventList);
        rv.setAdapter(adapter);
    }


}
