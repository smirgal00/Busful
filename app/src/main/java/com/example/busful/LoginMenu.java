package com.example.busful;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class LoginMenu extends Activity {

    private FirebaseAuth auth;
    private EditText user;
    private EditText pass;
    private Button login;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_menu);
        user = (EditText) findViewById(R.id.user);
        pass = (EditText) findViewById(R.id.pass);
        login = (Button) findViewById(R.id.login);
        register = (Button) findViewById(R.id.register);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    public void switchToReg(View v) {
        Intent reg = new Intent(this, RegisterScreen.class);
        reg.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(reg);
    }


}