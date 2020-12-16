package com.example.busful;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class History extends Activity {

    private final String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

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
    }
}