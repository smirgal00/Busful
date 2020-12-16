package com.example.busful;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


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
//
//    protected void switchToRoutes(View v) {
//        startActivity(new Intent(this, ).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
//    }
//
//    protected void switchToRoutes(View v) {
//        startActivity(new Intent(this, ).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
//    }
}