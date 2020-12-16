package com.example.busful;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class Objectives extends Activity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_objectives);

        listView = findViewById(R.id.obj);

        listView.setAdapter(new ArrayAdapter<>(this,
                R.layout.listview_item,
                getResources().getStringArray(R.array.objlist)));

        listView.setOnItemClickListener((parent, view, position, id) -> {
            String loc = parent.getItemAtPosition(position).toString();
            Intent intent = new Intent(this, ObjectivesMap.class);
            intent.putExtra("location", loc);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        });
    }
}