package com.example.busful;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class MainMenuAdmin extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu_admin);
    }

    public void switchToPricess(View v) {
        startActivity(new Intent(this, PriceChange.class)
                .addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
    }

    public void switchToRoutess(View v) {
        startActivity(new Intent(this, RouteChange.class)
                .addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
    }

    public void signOut(View v) {
        FirebaseAuth.getInstance().signOut();
        finish();
    }
}