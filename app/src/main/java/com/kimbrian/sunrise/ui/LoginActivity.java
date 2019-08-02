package com.kimbrian.sunrise.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

    private EditText userMail,userPassword;
    private Button btnLogin;
    private ProgressBar loginProgress;
    private FirebaseAuth mAuth;
    private Intent HomeActivity;
    private ImageView loginPhoto;
    private TextView createAccount;
    private TextView textforgot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isUserLogin();
        setContentView(R.layout.activity_login);

        createAccount = findViewById(R.id.btnSignup);
        userMail = findViewById(R.id.editEmail);
        userPassword = findViewById(R.id.editPassword);
        btnLogin = findViewById(R.id.btnlogin);
        loginProgress = findViewById(R.id.progressBar);
        mAuth = FirebaseAuth.getInstance();
        HomeActivity = new Intent(this, com.kimbrian.sunrise.MainActivity.class);

        textforgot = findViewById(R.id.textforgot);

        textforgot.setOnClickListener(this);

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent haha = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(haha);
                finish();
            }
        });



        loginProgress.setVisibility(View.INVISIBLE);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginProgress.setVisibility(View.VISIBLE);
                btnLogin.setVisibility(View.INVISIBLE);

                final String mail = userMail.getText().toString();
                final String password = userPassword.getText().toString();

                if (mail.isEmpty() || password.isEmpty()) {
                    showMessage("Please Verify All Fields");
                    btnLogin.setVisibility(View.VISIBLE);
                    loginProgress.setVisibility(View.INVISIBLE);
                }
                else {
                    signIn(mail,password);
                }
            }
        });

    }

    /**
     * Checks if user is logged in
     */
    private void isUserLogin() {
        FirebaseUser user  = FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null){
            startActivity(  new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
    }

    private void signIn(String mail, String password) {

        mAuth.signInWithEmailAndPassword(mail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    loginProgress.setVisibility(View.INVISIBLE);
                    btnLogin.setVisibility(View.VISIBLE);
                    updateUI();
                }
                else {
                    showMessage(task.getException().getMessage());
                    btnLogin.setVisibility(View.VISIBLE);
                    loginProgress.setVisibility(View.INVISIBLE);
                }
            }
        });

    }

    private void updateUI() {
        startActivity(HomeActivity);
        finish();
    }

    private void showMessage(String text) {
        Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();

        if (user != null) {
            //User is already connected  so we need to redirect him/her to home page
            updateUI();
        }
    }

    @Override
    public void onClick(View view) {
        if(view == textforgot){
            finish();
            startActivity(new Intent(this, ResetPasswordActivity.class));
        }
    }
}

