package com.example.busful;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;


public class MainMenu extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    public void switchToRoutes(View v) {
        startActivity(new Intent(this, Routes.class)
                .addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
    }

    public void switchToPrices(View v) {
        startActivity(new Intent(this, Prices.class)
                .addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
    }

    public void switchToHistory(View v) {
        startActivity(new Intent(this, History.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
    }

    public void switchToTracking(View v) {
        startActivity(new Intent(this, Tracking.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
    }

    public void switchToObjectives(View v) {
        startActivity(new Intent(this, Objectives.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
    }

    public void signOut(View v) {
        FirebaseAuth.getInstance().signOut();
        finish();
    }
}