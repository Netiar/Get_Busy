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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.moringaschool.get_busy.R;
import com.moringaschool.get_busy.constants.Constants;
import com.moringaschool.get_busy.databinding.ActivityLoginBinding;
import com.moringaschool.get_busy.databinding.ActivityRegisterBinding;
import com.moringaschool.get_busy.models.Validator;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityRegisterBinding logBind;
    FirebaseAuth myAuth;
    FirebaseAuth.AuthStateListener myAuthListener;
    SharedPreferences userData;
    SharedPreferences.Editor userDataEditor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logBind = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(logBind.getRoot());


        myAuth = FirebaseAuth.getInstance();
        createAuthListener();

        logBind.btnSubmit.setOnClickListener(this);
        userData = PreferenceManager.getDefaultSharedPreferences(this);
        userDataEditor = userData.edit();


    }

    private void createAuthListener() {
        myAuthListener = firebaseAuth -> {
            FirebaseUser currentUser = firebaseAuth.getCurrentUser();
            if (currentUser != null){
                Intent newIntent = new Intent(RegisterActivity.this, MainActivity.class);
                newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(newIntent);
                finish();
            }
        };
    }

    @Override
    public void onClick(View v) {
        if(v == logBind.btnSubmit){
            createUser();
        }

    }

    private void createUser() {

        String userName = Objects.requireNonNull(logBind.userName.getEditText()).getText().toString().trim();
        String userPhone = logBind.userPhone.getEditText().getText().toString().trim();
        String userEmail = logBind.userTicket.getEditText().getText().toString().trim();
        String userPassword = logBind.userSeat.getEditText().getText().toString().trim();

        if(!Validator.validateName(logBind.userName) || !Validator.validateEmail(logBind.userTicket) || !Validator.validatePassword(logBind.userSeat, logBind.userCoach) || !Validator.validatePass(logBind.userSeat)){
            return;
        }

        userDataEditor.putString(Constants.USER_EMAIL, userEmail).apply();
        userDataEditor.putString(Constants.USER_PASSWORD, userPassword).apply();
        userDataEditor.putString(Constants.USER_NAME, userName).apply();


        myAuth.createUserWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                Toast.makeText(this, "User created successfully", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "User creation failed", Toast.LENGTH_SHORT).show();
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