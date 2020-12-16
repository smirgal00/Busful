package com.example.busful;

import android.app.Activity;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.busful.Database.ZoneInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Prices extends Activity {

    private Spinner spinner;
    private TextView textView;
    private DatabaseReference databaseReference;
    private List<ZoneInfo> zoneInfoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prices);
        textView = findViewById(R.id.info);
        spinner = findViewById(R.id.zone);
        spinner.setAdapter(ArrayAdapter.createFromResource(this,
                R.array.zoneSpinner,
                R.layout.spinner_item));
        zoneInfoList = new ArrayList<>();

        setDatabase();
        setLayout();
    }

    public void setDatabase() {
        databaseReference = FirebaseDatabase.getInstance()
                .getReference()
                .child("prices");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot s : snapshot.getChildren()) {
                    ZoneInfo zoneInfo = s.getValue(ZoneInfo.class);
                    zoneInfoList.add(zoneInfo);
                }

                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        textView.setText("");
                        textView.setText(zoneInfoList.get(position).toString());
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void setLayout() {

    }
}