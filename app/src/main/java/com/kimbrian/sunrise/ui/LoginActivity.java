package com.kimbrian.sunrise.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.kimbrian.sunrise.MainActivity;
import com.kimbrian.sunrise.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editEmail, editPassword;
    Button btnlogin;
    TextView textforgot;
    TextView btnSignup;
    NavigationView nav_view;

    private FirebaseAuth mAuth;
    ProgressBar progressBar;

    private static final String TAG = "FirebaseErrors";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        isUserLogin();

        setContentView(R.layout.activity_login);

        //get firebase auth instance
        mAuth = FirebaseAuth.getInstance();

        //firebaseAuth = FirebaseAuth.getInstance();



        FirebaseApp.initializeApp(this);
        FirebaseApp.initializeApp(LoginActivity.this);

        //button,textview,edittext
        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        btnlogin = findViewById(R.id.btnlogin);
        textforgot = findViewById(R.id.textforgot);
        btnSignup = findViewById(R.id.btnSignup);


        btnSignup.setOnClickListener(this);
        editEmail.setOnClickListener(this);
        editPassword.setOnClickListener(this);
        textforgot.setOnClickListener(this);

        //progressBar = findViewById(R.id.progressBarLog);
        //progressBar.setVisibility(View.INVISIBLE);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
            }
        });


    }

    /**
     * Checks if user is logged in
     */



    private void isUserLogin() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
    }


    private void userLogin() {
        String email = editEmail.getText().toString().trim();
        String password = editPassword.getText().toString().trim();

        //checking if email and password are empty
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter email", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_LONG).show();
            return;
        }
        //if the email and password are not empty display progressbar
        //progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(LoginActivity.this, "Successful Log in.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(user);
                            /*Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                                    Uri.parse("geo:0,0?q=37.423156,-122.084917 (Sunrise)"));
                            startActivity(intent);*/
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });
    }


    @Override
    public void onClick(View view) {
        if (view == btnlogin) {
            finish();

        }

        if (view == btnSignup) {
            finish();
            startActivity(new Intent(this, SignupActivity.class));
        }
        if (view == textforgot) {
            finish();
            startActivity(new Intent(this, ResetPasswordActivity.class));
        }
         /*if (view == nav_view){
             finish();
             startActivity(new Intent(this, NavActivity.class));
         }*/
    }
}

