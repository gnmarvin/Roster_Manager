package id.ac.umn.mobile.rostermanager;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class EventFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    List<Event_model> eventList;
    RecyclerView rv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //get recyclerview from fragmentevent.xml
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_event, container, false);

        rv = (RecyclerView) rootView.findViewById(R.id.recycler_event);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);

        eventList = new ArrayList<>();

        //input data, gunakan loop untuk mengambil data dari database
        eventList.add(
                new Event_model(
                        "Sunday Service",
                        "Sunday, 20 May 2018",
                        "08.00-10.00",
                        "Aldy Stevanus"));
        eventList.add(
                new Event_model(
                        "Sunday Service",
                        "Sunday, 20 May 2018",
                        "10.30-12.30",
                        "Aldy Stevanus"));
        eventList.add(
                new Event_model(
                        "Sunday Service",
                        "Sunday, 20 May 2018",
                        "17.00-19.00",
                        "Yosua Aryo Putra"));

        EventAdapter adapter = new EventAdapter(getContext(), eventList);

        rv.setAdapter(adapter);
        return rootView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("All Events");
    }


}
