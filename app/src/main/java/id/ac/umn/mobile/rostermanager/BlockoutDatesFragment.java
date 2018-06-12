package id.ac.umn.mobile.rostermanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class BlockoutDatesFragment extends Fragment {
    List<BlockoutDatesModel> blockoutDatesList;
    RecyclerView rv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file

        //change R.layout.yourlayoutfilename for each of your fragments
        final View rootview = inflater.inflate(R.layout.fragment_blockout_dates, container, false);

        rv = rootview.findViewById(R.id.recycler_blockout_dates);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);

        blockoutDatesList = new ArrayList<>();

        //input date & reason
        for(int i= 0; i<12; i++){
            blockoutDatesList.add(new BlockoutDatesModel("27-May-2018","work & job"));
        }

        BlockoutDatesAdapter adapter = new BlockoutDatesAdapter(getContext(), blockoutDatesList);

        rv.setAdapter(adapter);

        FloatingActionButton fab = rootview.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addblockout = new Intent(getContext(), BlockoutDatesEditActivity.class);
                startActivity(addblockout);
            }
        });

        return rootview;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Blockout Dates");
    }
}
