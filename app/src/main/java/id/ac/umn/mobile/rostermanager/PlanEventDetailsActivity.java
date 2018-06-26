
package id.ac.umn.mobile.rostermanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class PlanEventDetailsActivity extends AppCompatActivity {
    String event_name, event_start_date, event_start_time;
    EditText detail_event_name, detail_event_date, detail_event_time,
    edit_photo_team, edit_campers_team, edit_photo_quota, edit_photo_tx_quota, edit_photo_pk_quota, edit_photo_extra_quota, edit_video_quota, edit_video_extra_quota,
    edit_campers_quota, edit_ast_campers_quota, edit_dolly_quota, edit_vtr_quota, edit_switcher_quota, edit_support_quota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_event_details);

        Intent detailEvent = getIntent();
        Bundle extras = detailEvent.getExtras();
        event_name = extras.getString("EVENT_NAME");
        event_start_date = extras.getString("EVENT_START_DATE");
        event_start_time = extras.getString("EVENT_START_TIME");
        detail_event_name = findViewById(R.id.edit_name_event_plan_event);
        detail_event_name.setText(event_name);
        detail_event_date = findViewById(R.id.edit_date_event_plan_event);
        detail_event_date.setText(event_start_date);
        detail_event_time = findViewById(R.id.edit_time_start_event_plan_event);
        detail_event_time.setText(event_start_time);
//        edit_photo_team x;
//        edit_campers_team x;
//        edit_photo_quota x;
//        edit_photo_tx_quota x;
//        edit_photo_pk_quota x;
//        edit_photo_extra_quota x;
//        edit_video_quota x;
//        edit_video_extra_quota x;
//        edit_campers_quota x;
//        edit_ast_campers_quota x;
//        edit_dolly_quota x;
//        edit_vtr_quota x;
//        edit_switcher_quota x;
//        edit_support_quota x;
    }

    public void showDatePickerDialog(View v) {
        DatePickerFragment datePickerFragment = new DatePickerFragment(); //test
        datePickerFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void showTimePickerDialog(View view){
        TimePickerFragment timePickerFragment = new TimePickerFragment();
        timePickerFragment.show(getFragmentManager(), "datePicker");
    }

    public void processDatePickerResult(int year, int month, int day) {
        String month_string = Integer.toString(month + 1);
        String day_string = Integer.toString(day);
        String year_string = Integer.toString(year);
        String dateMessage = (year_string + "-" + month_string + "-" + day_string);
        EditText dateText = (EditText) findViewById(R.id.edit_date_event_plan_event);
        dateText.setText(dateMessage);
    }

    public void processTimePickerResult(int hourOfDay, int minute) {
        String hour_string = Integer.toString(hourOfDay);
        String minute_string = Integer.toString(minute);
        String timeMessage = (hour_string + ":" + minute_string);
        EditText startEventTime = (EditText) findViewById(R.id.edit_time_start_event_plan_event);
        EditText endEventTime = (EditText) findViewById(R.id.edit_time_end_event_plan_event);
        if (startEventTime.isFocused()) {
            startEventTime.setText(timeMessage);
        } else if (endEventTime.isFocused()) {
            endEventTime.setText(timeMessage);
        }
    }
}
