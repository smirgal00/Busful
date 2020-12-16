package com.example.busful;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Camera;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.renderscript.Sampler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Routes extends Activity {
    private Intent intent;
    private String name;
    private Map<String, String> value;
    private ListView listView;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routes);
        listView = findViewById(R.id.list);
        spinner = findViewById(R.id.zone);
        spinner.setAdapter(ArrayAdapter.createFromResource(this,
                R.array.zoneSpinner,
                R.layout.spinner_item));

        setListView();

        value = new HashMap<>();

        intent = new Intent(this, Maps.class);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance()
                .getReference("bus_info");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot s : snapshot.getChildren()) {
                    for (DataSnapshot s1 : s.getChildren()) {
                        value.put(s.getKey(), s1.getValue().toString());
                    }
                }

                listView.setOnItemClickListener((parent, view, position, id) -> {
                    name = parent.getItemAtPosition(position).toString();
                    intent.putExtra("routes", value.get(name));
                    startActivity(intent);
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void setListView() {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();

                switch (item) {
                    case "Zona 1": {
                        listView.setAdapter(ArrayAdapter
                                .createFromResource(Routes.this, R.array.zone1,
                                        android.R.layout.simple_list_item_1));
                        break;
                    }
                    case "Zona 2": {
                        listView.setAdapter(ArrayAdapter
                                .createFromResource(Routes.this, R.array.zone2,
                                        android.R.layout.simple_list_item_1));
                        break;
                    }
                    case "Zona 3": {
                        listView.setAdapter(ArrayAdapter
                                .createFromResource(Routes.this, R.array.zone3,
                                        android.R.layout.simple_list_item_1));
                        break;
                    }
                    case "Zona 4": {
                        listView.setAdapter(ArrayAdapter
                                .createFromResource(Routes.this, R.array.zone4,
                                        android.R.layout.simple_list_item_1));
                        break;
                    }
                    case "Zona 5": {
                        listView.setAdapter(ArrayAdapter
                                .createFromResource(Routes.this, R.array.zone5,
                                        android.R.layout.simple_list_item_1));
                        break;
                    }
                    case "Zona 6": {
                        listView.setAdapter(ArrayAdapter
                                .createFromResource(Routes.this, R.array.zone6,
                                        android.R.layout.simple_list_item_1));
                        break;
                    }
                    case "Zona 7": {
                        listView.setAdapter(ArrayAdapter
                                .createFromResource(Routes.this, R.array.zone7,
                                        android.R.layout.simple_list_item_1));
                        break;
                    }
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}