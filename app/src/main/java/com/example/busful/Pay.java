package com.example.busful;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.PatternMatcher;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Pay extends Activity {

    private String zone;
    private EditText cc;
    private EditText date;
    private EditText cvv;
    private EditText name;
    private EditText phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        cc = findViewById(R.id.nr);
        date = findViewById(R.id.exp);
        cvv = findViewById(R.id.cv);
        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);

        zone = getIntent().getExtras().getString("zone");
    }

    public void pay(View v) {

        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phone.getText().toString(),
                null,
                "Biletul pentru " + zone + "a fost activat. Acesta este valabil o ora",
                null,
                null);

        Toast.makeText(
                getApplicationContext(),
                "Plata a fost realizata cu succes!",
                Toast.LENGTH_LONG
        ).show();

        finish();
    }
}