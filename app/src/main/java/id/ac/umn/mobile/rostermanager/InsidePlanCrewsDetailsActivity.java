package id.ac.umn.mobile.rostermanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.Toast;

public class InsidePlanCrewsDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inside_plan_crews_details);

        String[] positionphoto = getResources().getStringArray(R.array.Position_Photo);
        String[] positioncampers = getResources().getStringArray(R.array.Position_Campers);
        String[] teamphoto = getResources().getStringArray(R.array.Team_Photo);
        String[] teamcampers = getResources().getStringArray(R.array.Team_Campers);
        String filterposition = getIntent().getStringExtra("position");

//        final AutoCompleteTextView autolistposition = findViewById(R.id.list_position);
//        final ImageView dropdownlistposition = findViewById(R.id.drop_down_position);
        final AutoCompleteTextView autolistteam = findViewById(R.id.list_team);
        final ImageView dropdownlistteam = findViewById(R.id.drop_down_team);

        Toast.makeText(this, filterposition, Toast.LENGTH_SHORT).show();

        if("photographers".equals(filterposition)){
            Toast.makeText(this, "masuk", Toast.LENGTH_SHORT).show();
            //Dropdown autocomplete position
//            ArrayAdapter<String> adapterpositonphoto= new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, positionphoto);
//            autolistposition.setAdapter(adapterpositonphoto);
//            dropdownlistposition.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    autolistposition.showDropDown();
//                }
//            });

            //Dropdown autocomplete team
            ArrayAdapter<String> adapterteamphoto = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, teamphoto);
            autolistteam.setAdapter(adapterteamphoto);
            dropdownlistteam.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    autolistteam.showDropDown();
                }
            });
        }
        else if("campers".equals(filterposition)){
            //Dropdown autocomplete position
//            ArrayAdapter<String> adapterpositoncampers= new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, positioncampers);
//            autolistposition.setAdapter(adapterpositoncampers);
//            dropdownlistposition.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    autolistposition.showDropDown();
//                }
//            });

            //Dropdown autocomplete team
            ArrayAdapter<String> adapterteamcampers = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, teamcampers);
            autolistteam.setAdapter(adapterteamcampers);
            dropdownlistteam.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    autolistteam.showDropDown();
                }
            });
        }
    }
}
