package id.ac.umn.mobile.rostermanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsidePlanCrewsDetailsActivity extends AppCompatActivity {

    String token_id, crew_id;
    String[][] crew = new String[200][2];
    AutoCompleteTextView autolist;
    ImageView dropdownlist;

    Button saveButton = findViewById(R.id.save_detail_crew);
    Button cancelButton = findViewById(R.id.cancel_detail_crew);
    int size, counter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inside_plan_crews_details);

        SharedData sharedData = SharedData.getInstance();
        token_id = sharedData.getToken_id();
        counter = 0;
//        String[] positionphoto = getResources().getStringArray(R.array.Position_Photo);
//        String[] positioncampers = getResources().getStringArray(R.array.Position_Campers);
//        String[] teamphoto = getResources().getStringArray(R.array.Team_Photo);
//        String[] teamcampers = getResources().getStringArray(R.array.Team_Campers);
//        String filterposition = getIntent().getStringExtra("position");

//        final AutoCompleteTextView autolistposition = findViewById(R.id.list_position);
//        final ImageView dropdownlistposition = findViewById(R.id.drop_down_position);
//        final AutoCompleteTextView autolistteam = findViewById(R.id.list_team);
//        final ImageView dropdownlistteam = findViewById(R.id.drop_down_team);

//        Toast.makeText(this, filterposition, Toast.LENGTH_SHORT).show();
//
//        if("photographers".equals(filterposition)){
//            Toast.makeText(this, "masuk", Toast.LENGTH_SHORT).show();
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
//            ArrayAdapter<String> adapterteamphoto = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, teamphoto);
//            autolistteam.setAdapter(adapterteamphoto);
//            dropdownlistteam.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    autolistteam.showDropDown();
//                }
//            });
//        }
//        else if("campers".equals(filterposition)){
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
//            ArrayAdapter<String> adapterteamcampers = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, teamcampers);
//            autolistteam.setAdapter(adapterteamcampers);
//            dropdownlistteam.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    autolistteam.showDropDown();
//                }
//            });
//        }
        APIService webServiceAPI = APIClient.getApiClient().create(APIService.class);
        retrofit2.Call<JsonElement> crewList = webServiceAPI.CrewList(token_id);
        crewList.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                JsonElement element = response.body();
                JsonObject obj = element.getAsJsonObject();
                JsonArray data = obj.get("crew").getAsJsonArray();
                for (size = 0; size < data.size(); size++) {
                    JsonObject singleData = data.get(size).getAsJsonObject();
                    if(!singleData.get("organization_id").isJsonNull()){
                        crew[counter][0] = singleData.get("full_name").getAsString();
                        crew[counter][1] = singleData.get("id").getAsString();
                        counter++;
                    }
                }

                ArrayAdapter<String> adaptercrew;
                List<String> codvalues = new ArrayList<String>();
                for (int i = 0; i < counter; i++) {
                    codvalues.add(crew[i][0]);
                }
                adaptercrew = new ArrayAdapter<String>(InsidePlanCrewsDetailsActivity.this, android.R.layout.simple_dropdown_item_1line, codvalues);
                autolist.setAdapter(adaptercrew);
                dropdownlist.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        autolist.showDropDown();
                    }
                });
                size = 0;
            }
            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
            }

        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i =0; i < counter; i++){
                    if(autolist.getText().toString().contains(crew[i][0])){
                        crew_id = crew[i][1];
                    }
                }
                Toast.makeText(InsidePlanCrewsDetailsActivity.this, crew_id, Toast.LENGTH_SHORT).show();
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
