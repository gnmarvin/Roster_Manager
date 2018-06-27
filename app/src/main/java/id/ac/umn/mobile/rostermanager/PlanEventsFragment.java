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

        APIService webServiceAPI = APIClient.getApiClient().create(APIService.class);
        retrofit2.Call<JsonElement> listEvent = webServiceAPI.Event();

        listEvent.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(retrofit2.Call<JsonElement> call, Response<JsonElement> response) {
                planEventList = new ArrayList<>();
                JsonElement element = response.body();
                JsonObject obj = element.getAsJsonObject();
                JsonArray data = obj.get("event_roster").getAsJsonArray();
                for (int i = 0; i < data.size(); i++) {
                    JsonObject singleData = data.get(i).getAsJsonObject();
                    String event_name  = singleData.get("event_name").getAsString();
                    String event_start_date = singleData.get("roster_date").getAsString();
                    String event_start_time = singleData.get("roster_start_time").getAsString();
                    String event_end_time = singleData.get("roster_end_time").getAsString();
                    String cod = singleData.get("cod_full_name").getAsString();

                    planEventList.add(
                            new PlanEventsModel(
                                event_name,
                                event_start_date,
                                event_start_time,
                                cod,
                                    "Team D",
                                    "3/10",
                                    "Team A",
                                    "8/10"));
                }
                PlanEventsAdapter adapter = new PlanEventsAdapter(getContext(), planEventList);

                rv.setAdapter(adapter);
            }
            @Override
            public void onFailure(retrofit2.Call<JsonElement> call, Throwable t) {

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


}
