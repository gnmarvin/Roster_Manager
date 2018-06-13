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

public class MySchedulesFragment extends Fragment {
    List<MySchedulesModel> mySchedulesList;
    RecyclerView rv;

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

        mySchedulesList = new ArrayList<>();
        mySchedulesList.add(new MySchedulesModel("Sunday Service",
                "Sunday, 17 June 2017",
                "07.00-10.00","Photographer 1",
                "Yosua Aryo Putra"));

        MySchedulesAdapter adapter= new MySchedulesAdapter(getContext(), mySchedulesList);
        rv.setAdapter(adapter);
        return rootview;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("My Schedules");
    }
}
