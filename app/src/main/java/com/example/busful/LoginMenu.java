package com.example.busful;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginMenu extends Activity {

    private FirebaseAuth auth;
    private EditText user;
    private EditText pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_menu);
        user = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.pass);
        auth = FirebaseAuth.getInstance();
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

    public void login(View v) {
        String user = this.user.getText().toString();
        String pass = this.pass.getText().toString();

        auth.signInWithEmailAndPassword(user, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(
                                    getApplicationContext(),
                                    "Login succesful!",
                                    Toast.LENGTH_LONG
                            ).show();
                            updateUI();
                        }
                        else {
                            Toast.makeText(
                                    getApplicationContext(),
                                    "Login unsuccesful!"
                                    + task.getException().toString(),
                                    Toast.LENGTH_LONG
                            ).show();
                            clearText();
                        }
                    }
                });
    }

    private void updateUI() {
        Intent intent = new Intent(this, MainMenu.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }

    private void clearText() {
        this.user.setText("");
        this.pass.setText("");
    }

}