package id.ac.umn.mobile.rostermanager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MySchedulesFragment extends Fragment {
    List<MySchedulesModel> mySchedulesList;
    RecyclerView rv;
    String contact_id, token_id, set_event_name, set_roster_date, set_roster_job_name, set_roster_start_time, set_roster_end_time, set_cod_full_name;
    String[] event_name = new String[20];
    String[] roster_date = new String[20];
    String[] roster_job_name = new String[20];
    String[] roster_start_time = new String[20];
    String[] roster_end_time = new String[20];
    String[] cod_full_name = new String[20];
    int size = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        View rootview = inflater.inflate(R.layout.fragment_my_schedules, container, false);

        rv = rootview.findViewById(R.id.recycler_my_schedule);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);

        SharedData sharedData = SharedData.getInstance();
        contact_id = sharedData.getContact_id();
        token_id = sharedData.getToken_id();
        APIService webServiceAPI = APIClient.getApiClient().create(APIService.class);
        retrofit2.Call<JsonElement> mySchedule = webServiceAPI.MySchedule(token_id, contact_id);
        mySchedule.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                JsonElement element = response.body();
                JsonObject obj = element.getAsJsonObject();
                JsonArray data = obj.get("event_roster").getAsJsonArray();
                for (int i = 0; i < data.size(); i++) {
                    JsonObject singleData = data.get(i).getAsJsonObject();
                    set_event_name = singleData.get("event_name").getAsString();
                    set_roster_date = singleData.get("roster_date").getAsString();
                    set_roster_job_name = singleData.get("roster_job_name").getAsString();
                    set_roster_start_time = singleData.get("roster_start_time").getAsString();
                    set_roster_end_time = singleData.get("roster_end_time").getAsString();
                    set_cod_full_name = singleData.get("cod_full_name").getAsString();
                    setter(set_event_name, set_roster_date, set_roster_job_name, set_roster_start_time, set_roster_end_time, set_cod_full_name, size);
                    size++;
                }
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
            }
        });

        mySchedulesList = new ArrayList<>();
        return rootview;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("My Schedules");
    }

    public void setter(String set_event_name, String set_roster_date, String set_roster_job_name, String set_roster_start_time, String set_roster_end_time, String set_cod_full_name, Integer size) {
        event_name[size] = set_event_name;
        roster_date[size] = set_roster_date;
        roster_job_name[size] = set_roster_job_name;
        roster_start_time[size] = set_roster_start_time;
        roster_end_time[size] = set_roster_end_time;
        cod_full_name[size] = set_cod_full_name;

        mySchedulesList.add(new MySchedulesModel(event_name[size],
                roster_date[size],
                roster_start_time[size] + " - " + roster_end_time[size],roster_job_name[size],
                cod_full_name[size]));

        MySchedulesAdapter adapter= new MySchedulesAdapter(getContext(), mySchedulesList);
        rv.setAdapter(adapter);
    }
}
