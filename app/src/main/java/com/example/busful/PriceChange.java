package com.example.busful;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.busful.Database.ZoneInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PriceChange extends Activity {

    private EditText zone;
    private EditText oprice;
    private EditText nprice;
    private TextView textView;
    private DatabaseReference databaseReference;
    private DatabaseReference mDatabaseReference;
    private Map<String, ZoneInfo> zoneInfos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_change);
        oprice = findViewById(R.id.old_price);
        nprice = findViewById(R.id.new_price);
        zone = findViewById(R.id.zone);
        textView = findViewById(R.id.textView);
        zoneInfos = new HashMap<>();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("prices");
        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("prices");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot s : snapshot.getChildren()) {
                    zoneInfos.put(s.getKey(), s.getValue(ZoneInfo.class));
                }

                if (!zone.getText().toString().equals("")) {
                    textView.setText("");
                    textView.setText(zoneInfos.get("zona" + zone.getText().toString()).toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void changePrice(View v) {
        String opr = oprice.getText().toString();
        String npr = nprice.getText().toString();
        String zonee = zone.getText().toString();

        if (!zonee.equals("") && !opr.equals("") && !npr.equals("")) {
            zoneInfos.get("zona" + zonee).update(Double.parseDouble(opr), Double.parseDouble(npr));
            ZoneInfo z = zoneInfos.get("zona" + zonee);
            mDatabaseReference.child("zona" + zonee).setValue(z);
        }

        if (!zonee.equals("") && opr.equals("") && npr.equals("")) {
            textView.setText("");
            textView.setText(zoneInfos.get("zona" + zonee).toString());
        }
    }
}