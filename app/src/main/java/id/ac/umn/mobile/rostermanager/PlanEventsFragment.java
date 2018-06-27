package id.ac.umn.mobile.rostermanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
    String[] event_id, event_name , event_start_date, event_start_time, event_end_time,
            cod, team_photo, team_campers;
    int[] quota_photo, quota_campers;

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

        planEventList = new ArrayList<>(); // untuk menyimpan list event yang ada

        /*
        UNTUK AMBIL DATA LIST EVENT
         */
        final int[] datasize = new int[1]; // deklarasi variable untuk menyimpan banyaknya data event yang ada

        APIService webServiceAPI = APIClient.getApiClient().create(APIService.class);
        retrofit2.Call<JsonElement> listEvent = webServiceAPI.Event();
        listEvent.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(retrofit2.Call<JsonElement> call, Response<JsonElement> response) {
                JsonElement element = response.body();
                JsonObject obj = element.getAsJsonObject();
                JsonArray data = obj.get("event_roster").getAsJsonArray();

                datasize[0] = data.size(); //mendapatkan nilai banyaknya data event yang ada

                for (int i = 0; i < data.size(); i++) {
                    JsonObject singleData = data.get(i).getAsJsonObject();
                    event_name[i]  = singleData.get("event_name").getAsString();
                    event_start_date[i] = singleData.get("roster_date").getAsString();
                    event_start_time[i] = singleData.get("roster_start_time").getAsString();
                    event_end_time[i] = singleData.get("roster_end_time").getAsString();
                    cod[i] = singleData.get("cod_full_name").getAsString();
                    event_id[i] = singleData.get("id").getAsString();
                }
            }
            @Override
            public void onFailure(retrofit2.Call<JsonElement> call, Throwable t) {

            }
        });


        /*
        UNTUK AMBIL DATA LIST JOB / TEAM DI SUATU EVENT
         */
        for(int i = 0; i < datasize[0]; i++){
            retrofit2.Call<JsonElement> listJob = webServiceAPI.Job(event_id[i]);
            final int event_pos = i;
            listJob.enqueue(new Callback<JsonElement>() {
                @Override
                public void onResponse(retrofit2.Call<JsonElement> call, Response<JsonElement> response) {
                    JsonElement element = response.body();
                    JsonObject obj = element.getAsJsonObject();
                    JsonArray data = obj.get("event_roster_job").getAsJsonArray();
                    for (int j = 0; j < data.size(); j++) {
                        JsonObject singleData = data.get(j).getAsJsonObject();
                        if(singleData.get("organization_code").getAsString().contains("PHOTO"))
                        {
                            team_photo[event_pos] = singleData.get("organiztion_name").getAsString();
                            quota_photo[event_pos] += singleData.get("roster_job_quota").getAsInt();

                        }
                        else if(singleData.get("organization_code").getAsString().contains("CAMPERS"))
                        {
                            team_campers[event_pos] = singleData.get("organiztion_name").getAsString();
                            quota_campers[event_pos] += singleData.get("roster_job_quota").getAsInt();
                        }
                    }
                }
                @Override
                public void onFailure(retrofit2.Call<JsonElement> call, Throwable t) {

                }
            });
        }


        /* display data yang sudah diambil dari kedua rest json ke adapter */
        for (int i = 0; i < datasize[0]; i++) {
            planEventList.add(
                    new PlanEventsModel(
                            event_name[i],
                            event_start_date[i],
                            event_start_time[i],
                            event_end_time[i],
                            cod[i],
                            team_photo[i],
                            "/".concat(String.valueOf(quota_photo[i])),
                            team_campers[i],
                            "/".concat(String.valueOf(quota_campers[i]))));
        }
        PlanEventsAdapter adapter = new PlanEventsAdapter(getContext(), planEventList);

        rv.setAdapter(adapter);

        return rootView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Plan Events");
    }


}
