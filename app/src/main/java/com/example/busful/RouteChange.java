package com.example.busful;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;
import org.xml.sax.DTDHandler;

import java.util.HashMap;
import java.util.Map;

public class RouteChange extends Activity {

    private DatabaseReference mDatabaseReference;
    private DatabaseReference databaseReference;
    private EditText nr;
    private EditText old;
    private Map<String, String> routes;
    private EditText coord;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_change);
        nr = findViewById(R.id.longg);
        coord = findViewById(R.id.lat);
        textView = findViewById(R.id.lista);
        old = findViewById(R.id.old);
        routes = new HashMap<>();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("bus_info");
        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("bus_info");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot s : snapshot.getChildren()) {
                    routes.put(s.getKey(), s.getValue().toString());
                }

                if (!nr.getText().toString().equals("")) {
                    textView.setText("");
                    textView.setText(routes.get(nr.getText().toString())
                            .replace("+ ", "\n")
                            .replace("{", "")
                            .replace("}", "")
                            .replace("=", "\n"));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void changeRoutes(View v) {
        String nr = this.nr.getText().toString();
        String coord = this.coord.getText().toString();
        String ocoord = this.old.getText().toString();

        if (!nr.equals("") && !coord.equals("") && !ocoord.equals("")) {
            String route = routes.get(nr);
            route = route.replace(ocoord, coord);
            mDatabaseReference.child(nr).setValue(route);
        }

        if (!nr.equals("")) {
            textView.setText("");
            textView.setText(routes.get(nr)
                    .replace("+ ", "\n")
                    .replace("{", "")
                    .replace("}", "")
                    .replace("=", "\n"));
        }

    }
}