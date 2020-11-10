package com.example.busful;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.content.ContentValues.TAG;

public class RegisterScreen extends Activity {

    private EditText username;
    private EditText email;
    private EditText password;
    private EditText coPassword;
    private Button register;
    private Toast toast;
    private CoordinatorLayout layout;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);
        username = (EditText) findViewById(R.id.user);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.pass);
        coPassword = (EditText) findViewById(R.id.rep_pass);
        register = (Button) findViewById(R.id.register);
        layout = (CoordinatorLayout) findViewById(R.id.layout);
        auth = FirebaseAuth.getInstance();
    }

    public void regAction(View v) {
        String pass;
        String email;
        pass = getPassword();
        email = getEmail();
        register(email, pass);

    }

    private String getPassword() {
        String password;
        String rePassword;


        password = this.password.getText().toString();
        rePassword = this.coPassword.getText().toString();

        if (checkP(password, rePassword)) {
            toast = Toast.makeText(
                    getApplicationContext(),
                    "Passwords do not match!",
                    Toast.LENGTH_LONG);

            toast.show();
            this.password.setText("");
            this.coPassword.setText("");
        }

        return password;
    }

    private String getEmail() {
        String email;

        email = this.email.getText().toString();
        Pattern pattern = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        Matcher matcher = pattern.matcher(email);

        if (!matcher.find()) {
            toast = Toast.makeText(
                getApplicationContext(),
                    "Invalid email address!",
                    Toast.LENGTH_LONG
            );

            toast.show();
            this.email.setText("");
        }

        return email;
    }

    private boolean checkP(String password, String coPassword) {
        return password.equals(null)
                || coPassword.equals(null)
                || !password.equals(coPassword)
                || password.equals("")
                || coPassword.equals("");
    }

    private void register(String email, String password) {
        this.auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(
                        this,
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    toast = Toast.makeText(
                                            getApplicationContext(),
                                            "User created succesfully!",
                                            Toast.LENGTH_LONG
                                    );

                                    toast.show();
                                    updateUI();
                                }
                                else {
                                    Log.d(TAG, "createUser:failure", task.getException());
                                    toast = Toast.makeText(
                                            getApplicationContext(),
                                            "User could not be created!",
                                            Toast.LENGTH_LONG
                                    );

                                    toast.show();
                                }
                            }
                        }
                );
    }

    private void updateUI() {
        FirebaseAuth.getInstance().signOut();
        finish();
    }
}