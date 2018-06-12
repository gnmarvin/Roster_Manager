package id.ac.umn.mobile.rostermanager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class PlanCrewsFragment extends Fragment {
    List<PlanCrewsModel> planCrewsList;
    RecyclerView rv;

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

        planCrewsList = new ArrayList<>();

        //input data, gunakan loop untuk mengambil data dari database
        planCrewsList.add(
                new PlanCrewsModel(
                        "Sunday Service",
                        "Sunday, 20 May 2018",
                        "08.00-10.00",
                        "Aldy Stevanus",
                        "Team D",
                        "8/10"));
        planCrewsList.add(
                new PlanCrewsModel(
                        "Sunday Service",
                        "Sunday, 20 May 2018",
                        "08.00-10.00",
                        "Aldy Stevanus",
                        "Team D",
                        "8/10"));

        PlanCrewsAdapter adapter = new PlanCrewsAdapter(getContext(), planCrewsList);

        rv.setAdapter(adapter);
        return rootView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Plan Crews");
    }
}
