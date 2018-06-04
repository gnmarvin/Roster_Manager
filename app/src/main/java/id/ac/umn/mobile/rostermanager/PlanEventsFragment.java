package id.ac.umn.mobile.rostermanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

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

        planEventList = new ArrayList<>();

        //input data, gunakan loop untuk mengambil data dari database
        planEventList.add(
                new PlanEventsModel(
                        "Sunday Service",
                        "Sunday, 20 May 2018",
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

        return rootView;

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Plan Events");
    }


}
