package com.moringaschool.get_busy.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.moringaschool.get_busy.R;
import com.moringaschool.get_busy.constants.Constants;
import com.moringaschool.get_busy.databinding.ActivityLoginBinding;
import com.moringaschool.get_busy.models.Validator;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityLoginBinding logBind;
    FirebaseAuth myAuth;
    FirebaseAuth.AuthStateListener myAuthListener;
    SharedPreferences userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logBind = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(logBind.getRoot());

        FirebaseApp.initializeApp(this);

        myAuth = FirebaseAuth.getInstance();
        createAuthListener();

        logBind.btnLogin.setOnClickListener(this);
        logBind.btnSign.setOnClickListener(this);
        userData = PreferenceManager.getDefaultSharedPreferences(this);



    }

    private void createAuthListener() {
        myAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser currentUser  = firebaseAuth.getCurrentUser();
                if(currentUser != null){
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }

            }
        };
    }

    @Override
    public void onClick(View v) {
        if(v == logBind.btnLogin){
            validateUser();
        }else if(v == logBind.checkBox){
            logBind.userName.getEditText().setText(userData.getString(Constants.USER_EMAIL, null));
            logBind.userPhone.getEditText().setText(userData.getString(Constants.USER_PASSWORD, null));

        }else{
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        }

    }

    private void validateUser() {
        String email = logBind.userName.getEditText().getText().toString().trim();
        String password = logBind.userPhone.getEditText().getText().toString().trim();

        if(!Validator.validateEmail(logBind.userName) || !Validator.validatePass(logBind.userPhone)){
            return;
        }

        myAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(LoginActivity.this, "Welcome Aboard", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        myAuth.addAuthStateListener(myAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        myAuth.removeAuthStateListener(myAuthListener);
    }
}