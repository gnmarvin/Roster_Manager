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

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

public class PlanEventsFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private PlanEventsAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
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
                JsonElement element = response.body();
                JsonObject obj = element.getAsJsonObject();
                String event_name  = obj.get("event_name").getAsString();
                String event_start_date = obj.get("event_start_date").getAsString();
                String event_end_date = obj.get("event_end_date").getAsString();
                String event_type = obj.get("event_type").getAsString();

                //INI CONTOH LOOP BUAT BACA JSON AMPE ABIS
//                JsonElement element = response.body();
//                JsonObject obj = element.getAsJsonObject();
//                JsonArray data = obj.get("result").getAsJsonArray();
//
//                for(int i = 0 ; i < data.size() ; i++){
//                    JsonObject singleData = data.get(i).getAsJsonObject();
//                    String idArtwork = singleData.get("IDArtWork").getAsString();
//                    String titleArtwork = singleData.get("Title").getAsString();
//                    String fileArtwork = singleData.get("DirectoryData").getAsString();
//                    String idArtist = singleData.get("IDArtist").getAsString();
//                    String nameArtist = singleData.get("DisplayName").getAsString();
//                    String emailArtist = singleData.get("EMail").getAsString();
//
//                    ModelArtworkInformation dataArtworkToList = new ModelArtworkInformation(idArtwork, titleArtwork, idArtist, nameArtist, emailArtist, fileArtwork);
//                    artworkList.add(dataArtworkToList);
//                }

                planEventList = new ArrayList<>();



                //input data, gunakan loop untuk mengambil data dari database
                planEventList.add(
                        new PlanEventsModel(
                                event_name,
                                event_start_date,
                                "08.00-10.00",
                                "Aldy Stevanus",
                                "Team D",
                                "3/10",
                                "Team A",
                                "8/10"));
                planEventList.add(
                        new PlanEventsModel(
                                "Sunday Service",
                                "Sunday, 20 May 2018",
                                "10.30-12.30",
                                "Aldy Stevanus",
                                "Team D",
                                "5/10",
                                "Team A",
                                "8/10"));
                planEventList.add(
                        new PlanEventsModel(
                                "Sunday Service",
                                "Sunday, 20 May 2018",
                                "17.00-19.00",
                                "Yosua Aryo Putra",
                                "Team B",
                                "2/10",
                                "Team B",
                                "8/10"));
                planEventList.add(
                        new PlanEventsModel(
                                "She",
                                "Sunday, 2 May 2018",
                                "17.00-19.00",
                                "Yosua Aryo Putra",
                                "Team B",
                                "0/1",
                                "Team B",
                                "1/1"));

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
