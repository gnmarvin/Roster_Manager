package id.ac.umn.mobile.rostermanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;

public class InsidePlanCrewsDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inside_plan_crews_details);

        String[] positionphoto = getResources().getStringArray(R.array.Position_Photo);
        String[] positioncampers = getResources().getStringArray(R.array.Position_Campers);
        String[] teamphoto = getResources().getStringArray(R.array.Team_Photo);
        String[] teamcampers = getResources().getStringArray(R.array.Team_Campers);

        //Dropdown autocomplete position
        final AutoCompleteTextView autolistposition = findViewById(R.id.list_position);
        ImageView dropdownlistposition = findViewById(R.id.drop_down_position);

        ArrayAdapter<String> adapterpositonphoto= new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, positionphoto);
        autolistposition.setAdapter(adapterpositonphoto);

        dropdownlistposition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autolistposition.showDropDown();
            }
        });

        //Dropdown autocomplete team
        final AutoCompleteTextView autolistteam = findViewById(R.id.list_team);
        ImageView dropdownlistteam = findViewById(R.id.drop_down_team);

        ArrayAdapter<String> adapterteamphoto = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, teamphoto);
        autolistteam.setAdapter(adapterteamphoto);

        dropdownlistteam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autolistteam.showDropDown();
            }
        });

    }
}
