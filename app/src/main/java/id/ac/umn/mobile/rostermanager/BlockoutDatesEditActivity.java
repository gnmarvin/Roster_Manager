package id.ac.umn.mobile.rostermanager;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.CalendarContract;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class BlockoutDatesEditActivity extends AppCompatActivity implements TextWatcher {
    EditText blockoutStartDate, blockoutEndDate, blockoutReason;
    Button saveBlockoutEvent, cancelBlockoutEvent;
    Long calendarStartSet, calendarEndSet;
    CustomDatePicker custom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blockout_dates_edit);
        blockoutStartDate = (EditText) findViewById(R.id.edit_start_date_block);
        blockoutEndDate = (EditText) findViewById(R.id.edit_end_date_block);
        blockoutReason = (EditText) findViewById(R.id.edit_reason_blockout_dates);
        saveBlockoutEvent = (Button) findViewById(R.id.save_blockout_event);
        cancelBlockoutEvent = (Button) findViewById(R.id.cancel_blockout_event);

        //disable keyboard on blockoutStartdate and blockourEndDate Edittext
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            blockoutStartDate.setShowSoftInputOnFocus(false);
            blockoutEndDate.setShowSoftInputOnFocus(false);
        }
        blockoutStartDate.setInputType(0);
        blockoutEndDate.setInputType(0);
        saveBlockoutEvent.setEnabled(false);
        //add textwatcher on every edittext
        blockoutStartDate.addTextChangedListener(this);
        blockoutEndDate.addTextChangedListener(this);
        blockoutReason.addTextChangedListener(this);

        //ask calendar write permission
        final int PERMISSION_REQUEST_CODE = 1;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {

            if (checkSelfPermission(Manifest.permission.WRITE_CALENDAR) == PackageManager.PERMISSION_DENIED) {

                Log.d("permission", "permission denied");
                String[] permissions = {Manifest.permission.WRITE_CALENDAR};

                requestPermissions(permissions, PERMISSION_REQUEST_CODE);

            }
        }

        custom = new CustomDatePicker(this, new CustomDatePicker.ICustomDateTimeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onSet(Dialog dialog, Calendar calendarSelected, Date dateSelected, int year, String monthFullName, String monthShortName, int monthNumber, int date, String weekDayFullName, String weekDayShortName, int hour24, int hour12, int min, int sec, String AM_PM) {
                if (blockoutStartDate.isFocused()) {
                    blockoutStartDate.setText("");
                    blockoutStartDate.setText(weekDayFullName + ", " + year + "-" + (monthNumber + 1) + "-" + calendarSelected.get(Calendar.DAY_OF_MONTH) + " " + hour24 + ":" + min + ":" + sec);
                    calendarStartSet = getLongAsDate(year, monthNumber + 1, date, hour24, min, sec);
                } else if (blockoutEndDate.isFocused()) {
                    blockoutEndDate.setText("");
                    blockoutEndDate.setText(weekDayFullName + ", " + year + "-" + (monthNumber + 1) + "-" + calendarSelected.get(Calendar.DAY_OF_MONTH) + " " + hour24 + ":" + min + ":" + sec);
                    calendarEndSet = getLongAsDate(year, monthNumber + 1, date, hour24, min, sec);
                }
            }

            @Override
            public void onCancel() {
            }
        });
        custom.set24HourFormat(true);
        custom.setDate(Calendar.getInstance());

        blockoutStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                custom.showDialog();
            }
        });

        blockoutEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                custom.showDialog();
            }
        });

        cancelBlockoutEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //balik ke fragmnt blockout
            }
        });

        saveBlockoutEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEventsToCalendar(BlockoutDatesEditActivity.this);
                Toast.makeText(BlockoutDatesEditActivity.this, "Events Inserted Please Check Your Calendar", Toast.LENGTH_SHORT).show();
                //balik ke fragment blockout
            }
        });
    }

    //convert edittext date to millis
    public long getLongAsDate(int year, int month, int date, int hour, int minute, int second) {
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.SECOND, second);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.HOUR, hour);
        calendar.set(Calendar.DAY_OF_MONTH, date);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.YEAR, year);
        return calendar.getTimeInMillis();
    }

    public long addEventsToCalendar(Activity currentActivity) {
        long calendarID = 3;
        long dtStartTime = calendarStartSet;
        long dtEndTime = calendarEndSet;

        ContentResolver contentResolver = getContentResolver();
        ContentValues values = new ContentValues();
        values.put(CalendarContract.Events.DTSTART, dtStartTime);
        values.put(CalendarContract.Events.DTEND, dtEndTime);
        values.put(CalendarContract.Events.TITLE, "BLOCKOUT DATES");
        values.put(CalendarContract.Events.DESCRIPTION, blockoutReason.getText().toString());
        values.put(CalendarContract.Events.ALL_DAY, true);
        values.put(CalendarContract.Events.CALENDAR_ID, calendarID);
        values.put(CalendarContract.Events.EVENT_TIMEZONE, "Jakarta");
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            //return TODO;
        }
        Uri uri = contentResolver.insert(CalendarContract.Events.CONTENT_URI, values);
        long eventID = Long.parseLong(uri.getLastPathSegment());
        return eventID;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        EditText blockoutStartDate = (EditText) findViewById(R.id.edit_start_date_block);
        EditText blockoutEndDate = (EditText) findViewById(R.id.edit_end_date_block);
        EditText blockoutReason = (EditText) findViewById(R.id.edit_reason_blockout_dates);
        if (blockoutStartDate.getText().length() <= 0 || blockoutEndDate.getText().length() <= 0 || blockoutReason.getText().length() <= 0) {
            saveBlockoutEvent.setEnabled(false);
        } else if (blockoutStartDate.getText().length() > 0 && blockoutEndDate.getText().length() > 0 && blockoutReason.getText().length() > 0) {
            saveBlockoutEvent.setEnabled(true);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
