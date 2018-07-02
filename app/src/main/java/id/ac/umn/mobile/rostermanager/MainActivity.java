package id.ac.umn.mobile.rostermanager;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    String full_name = "";               //variable buat nampung username inputan user
    String email = "";
    String mobile_no = "";
    String mobile_whatsapp = "";
    String mobile_line = "";
    String contact_id = "";
    String role = "";
    String token_id;
    TextView header_username, header_email;           //variable buat text dibawah header picture
    ImageView profilePicture;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedData sharedData = SharedData.getInstance();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);//

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //navigations
        full_name = sharedData.getName();
        email = sharedData.getEmail();
        mobile_no = sharedData.getMobile_no();
        mobile_whatsapp = sharedData.getMobile_whatsapp();
        mobile_line = sharedData.getMobile_line();
        contact_id = sharedData.getContact_id();
        role = sharedData.getRole();
        token_id = sharedData.getToken_id();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //header & selected screen
        View header = navigationView.getHeaderView(0);
        header_username = (TextView)header.findViewById(R.id.header_username);
        header_username.setText(full_name);
        header_email = (TextView)header.findViewById(R.id.header_email);
        header_email.setText(email);
        displaySelectedScreen(R.id.nav_my_schedules);

        //click profile picture
        profilePicture = (ImageView)header.findViewById(R.id.profile_picture);
        profilePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ViewProfile.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            moveTaskToBack(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.Notifications) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        //calling the method displayselectedscreen and passing the id of selected menu
        displaySelectedScreen(item.getItemId());
        //make this method blank
        return true;
    }

    public void displaySelectedScreen(int itemId) {

        //creating fragment object
        Fragment fragment = null;

        //initializing the fragment object which is selected
        switch (itemId) {
            case R.id.nav_inbox:
                fragment = new InboxFragment();
                break;
            case R.id.nav_my_schedules:
                fragment = new MySchedulesFragment();
                break;
            case R.id.nav_blockout:
                fragment = new BlockoutDatesFragment();
                break;
            case R.id.nav_plan_events:
                if(!role.equals("OPS")){
                    Toast.makeText(this, "You're not allowed to access this menu", Toast.LENGTH_SHORT).show();
                }
                else{
                    fragment = new PlanEventsFragment();
                }
                break;
            case R.id.nav_plan_crews:
                if(!role.equals("TM")){
                    Toast.makeText(this, "You're not allowed to access this menu", Toast.LENGTH_SHORT).show();
                }
                else{
                    fragment = new PlanCrewsFragment();
                }
                break;
            case R.id.nav_cod:
                fragment = new CodFragment();
                break;
            case R.id.nav_setting:
                fragment = new SettingFragment();
                break;
            case R.id.nav_logout:
                APIService webServiceAPI = APIClient.getApiClient().create(APIService.class);
                retrofit2.Call<JsonElement> logout = webServiceAPI.Logout(token_id);
                logout.enqueue(new Callback<JsonElement>() {
                    @Override
                    public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                        JsonElement element = response.body();
                        JsonObject obj = element.getAsJsonObject();
                        JsonObject error = obj.get("error").getAsJsonObject();
                        String error_code = error.get("error_code").getAsString();
                        if (error_code.equals("0")) {
                            SharedData sharedData = SharedData.getInstance();
                            sharedData.resetData();
                            startActivity(new Intent(MainActivity.this, LoginActivity.class));
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonElement> call, Throwable t) {

                    }
                });
                break;
       }

        //replacing the fragment
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }
}
