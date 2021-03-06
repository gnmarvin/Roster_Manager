package id.ac.umn.mobile.rostermanager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlanCrewsFragment extends Fragment {
    List<PlanCrewsModel> planCrewsList;
    RecyclerView rv;
    String set_event_name, set_event_id, set_cod, set_event_end_time, set_event_start_time, set_event_start_date, token_id, tm_name, code_team;
    String[] event_id = new String[20];
    String[] event_name = new String[20];
    String[] event_start_date = new String[20];
    String[] event_start_time = new String[20];
    String[] event_end_time = new String[20];
    String[] cod = new String[20];
    String[] team = new String[20];
    Boolean isempty = true;
    int[] quota = new int[20];
    int index = 0;
    int size; // deklarasi variable untuk menyimpan banyaknya data event yang ada

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //get recyclerview from fragmentplancrew.xml
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_plan_crews, container, false);

        rv = (RecyclerView) rootView.findViewById(R.id.recycler_plan_crews);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);

        SharedData sharedData = SharedData.getInstance();
        token_id = sharedData.getToken_id();
        planCrewsList = new ArrayList<>();


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
                    set_cod = singleData.get("cod_full_name").getAsString();
                    set_event_id = singleData.get("id").getAsString();
                    setter(set_event_id, set_event_name, set_event_start_date, set_event_start_time, set_event_end_time, set_cod, size);
                }
            }
            @Override
            public void onFailure(retrofit2.Call<JsonElement> call, Throwable t) {
            }
        });
        if(isempty){
            // jika tidak list event untuk team yang bersangkutan, tidak akan ditampilkan, hanya menampilkan toast
            Toast.makeText(getContext(), "Your team was not rostered", Toast.LENGTH_SHORT).show();
        }
//        Toast.makeText(getContext(),sharedData.getCode_team(), Toast.LENGTH_SHORT).show();
//        Toast.makeText(getContext(), sharedData.getOrganization_id(), Toast.LENGTH_SHORT).show();
        return rootView;
    }

    public void setter(String set_event_id, String set_event_name, String set_event_start_date, String set_event_start_time, String set_event_end_time, String set_cod, final Integer size){
        final SharedData sharedData = SharedData.getInstance();
        final String code_team;
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
                    //Toast.makeText(getContext(), singleData.get("organization_code").getAsString(), Toast.LENGTH_SHORT).show();
//                    Toast.makeText(getContext(), sharedData.getCode_team(), Toast.LENGTH_SHORT).show();
//                    Toast.makeText(getContext(), sharedData.getOrganization_id(), Toast.LENGTH_SHORT).show();
                    if(singleData.get("organization_code").getAsString().equals(sharedData.getCode_team()))
                    {
                        team[size] = singleData.get("organization_name").getAsString();
                        quota[size] += singleData.get("roster_job_quota").getAsInt();
                    }
                }
                if(team[size]!=null){
                    if(team[size].equals(sharedData.getName_team())){
                        //membandingkan data nama team pada shared data login / user (TM) dengan nama team yang bertugas dalam setiap event
                        //jika sama, maka akan dibuatkan card baru
                        addtoCard(event_name[size], event_start_date[size], event_start_time[size], event_end_time[size], cod[size], team[size], quota[size], event_id[size]);
                    }
                }
            }
            @Override
            public void onFailure(retrofit2.Call<JsonElement> call, Throwable t) {
            }
        });
    }

    public void addtoCard(String set_event_name, String set_event_start_date, String set_event_start_time, String set_event_end_time, String set_cod, String set_team, int set_quota, String set_event_id){
        planCrewsList.add(
                new PlanCrewsModel(
                        set_event_name,
                        set_event_start_date,
                        set_event_start_time,
                        set_event_end_time,
                        set_cod,
                        set_team,
                        String.valueOf(set_quota),
                        set_event_id));
        PlanCrewsAdapter adapter = new PlanCrewsAdapter(getContext(), planCrewsList);
        rv.setAdapter(adapter);
        isempty = false;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Plan Crews");
    }
}
