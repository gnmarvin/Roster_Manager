package id.ac.umn.mobile.rostermanager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.Dialog;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.encoder.ByteMatrix;

public class ViewProfile extends AppCompatActivity {
    String full_name = "";               //variable buat nampung username inputan user
    String email = "";
    String mobile_no = "";
    String mobile_whatsapp = "";
    String mobile_line = "";
    String contact_id = "";
    TextView name_this, email_this, phone_this, line_this, whatsapp_this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_profile);

        //Get the data from main activity
        Intent i = getIntent();
        Bundle extras = i.getExtras();
        full_name = extras.getString("USERNAME");
        email = extras.getString("EMAIL");
        mobile_no = extras.getString("MOBILE_NO");
        mobile_whatsapp = extras.getString("MOBILE_WHATSAPP");
        mobile_line = extras.getString("MOBILE_LINE");
        contact_id = extras.getString("CONTACT_ID");

        // QR code generator ini
        QRCodeWriter writer = new QRCodeWriter();
        ImageView contactIdQr = (ImageView) findViewById(R.id.qr_code_image);
        try {
            BitMatrix bitMatrix = writer.encode(contact_id, BarcodeFormat.QR_CODE, 512, 512);
                    int width = 512;
                    int height = 512;
                    Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
                    for (int x = 0; x < width; x++) {
                        for (int y = 0; y < height; y++) {
                            if (!bitMatrix.get(x, y)) {
                                bmp.setPixel(x, y, Color.WHITE);
                            } else {
                                bmp.setPixel(x, y, Color.BLACK);
                            }
                        }
                    }
                    contactIdQr.setImageBitmap(bmp);
        } catch (WriterException e) {
            e.printStackTrace();
        }

        name_this = findViewById(R.id.name);
        name_this.setText(full_name);
        email_this = findViewById(R.id.email);
        email_this.setText(email);
        phone_this = findViewById(R.id.phone);
        phone_this.setText(mobile_no);
        line_this = findViewById(R.id.line);
        line_this.setText(mobile_line);
        whatsapp_this = findViewById(R.id.whatsapp);
        whatsapp_this.setText(mobile_whatsapp);
    }
}
