package com.example.busful;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.renderscript.Sampler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.busful.Database.HistoryInfo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class History extends Activity {

    private String uid;
    private String email;
    private Spinner spinner;
    private DatabaseReference historyReference;
    private DatabaseReference getHistoryReference;
    private ArrayAdapter<String> arrayAdapter;
    private List<String> items;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        this.uid = user.getUid();
        this.email = user.getEmail();

        listView = findViewById(R.id.routes);
        items = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(arrayAdapter);

        setLayout();
    }

    private void setLayout() {
        spinner = findViewById(R.id.select);

        List<String[]> temp = new ArrayList<>();
        temp.add(getResources().getStringArray(R.array.zone1));
        temp.add(getResources().getStringArray(R.array.zone2));
        temp.add(getResources().getStringArray(R.array.zone3));
        temp.add(getResources().getStringArray(R.array.zone4));
        temp.add(getResources().getStringArray(R.array.zone5));
        temp.add(getResources().getStringArray(R.array.zone6));
        temp.add(getResources().getStringArray(R.array.zone7));

        Stream<String> stream = Stream.of();
        for (String[] s : temp) {
            stream = Stream.concat(stream, Arrays.stream(s));
        }
        spinner.setAdapter(new ArrayAdapter<>(this,
                R.layout.spinner_item,
                stream.toArray(String[]::new)));

        historyReference = FirebaseDatabase.getInstance()
                .getReference()
                .child("History");

        getHistoryReference = FirebaseDatabase.getInstance()
                .getReference()
                .child("History");

        getHistoryReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HistoryInfo historyInfo = null;
                for (DataSnapshot s : snapshot.getChildren()) {
                    if (s.getKey().equals(History.this.uid)) {
                        historyInfo = s.getValue(HistoryInfo.class);
                    }
                }

                String[] spl = historyInfo.getRouteID().split(" ");
                items.clear();
                items.addAll(Arrays.asList(spl));
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void insertRoute(View v) {
        final String routeID = spinner.getSelectedItem().toString();

        historyReference.child(this.uid)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String temp = "";
                        for (DataSnapshot s : snapshot.getChildren()) {
                            if (s.getKey().equals("routeID")) {
                                temp = temp + s.getValue().toString();
                            }
                        }

                        historyReference.child(History.this.uid)
                                .setValue(new HistoryInfo(History.this.email,
                                        temp + " " + routeID));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }
}