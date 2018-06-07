package id.ac.umn.mobile.rostermanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PlanCrewsDetailsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private List<ListCrewDetailsModel> listCrewDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_crews_details);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_list_crew_plan_crew);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listCrewDetails = new ArrayList<>();

        listCrewDetails.add(
                new ListCrewDetailsModel(
                        "George Joseph K",
                        "Photographer 1",
                        "Team D",
                        "OK"
                )
        );
        listCrewDetails.add(
                new ListCrewDetailsModel(
                        "George Joseph K",
                        "Photographer 1",
                        "Team D",
                        "OK"
                )
        );

        ListCrewDetailsAdapter adapter = new ListCrewDetailsAdapter(this, listCrewDetails);

        recyclerView.setAdapter(adapter);
    }
}
