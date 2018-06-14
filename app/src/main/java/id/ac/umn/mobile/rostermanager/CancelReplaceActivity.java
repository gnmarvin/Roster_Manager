package id.ac.umn.mobile.rostermanager;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Map;

public class CancelReplaceActivity extends AppCompatActivity {
    String reason_str;
    EditText reason;
    Button submitbutton, cancelbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_replace);
        reason = findViewById(R.id.reason_cancel_request);
        submitbutton = findViewById(R.id.submit_cancel_request);
        cancelbutton = findViewById(R.id.cancel_cancel_request);

        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reason_str = reason.getText().toString();
                Toast.makeText(CancelReplaceActivity.this, reason_str, Toast.LENGTH_SHORT).show();
                 AlertDialog.Builder dialogyes = new AlertDialog.Builder(CancelReplaceActivity.this);
                 dialogyes.setTitle("Are you sure?")
                         .setMessage("By clicking yes button, you'll request replacement for your position service.")
                 .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //belum ubah statusnya, tambahin status pending, complete di myscheduleadapter
                        onBackPressed();
                        dialog.dismiss();
                    }
                });
                dialogyes.show();
            }
        });

        cancelbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(CancelReplaceActivity.this);
                dialog.setMessage("Discard changes?")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onBackPressed();
                        dialog.dismiss();
                    }
                }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
