package com.moringaschool.get_busy.models;

import android.util.Patterns;

import com.google.android.material.textfield.TextInputLayout;

public class Validator {

    public static boolean validateName(TextInputLayout name){
        String username = name.getEditText().getText().toString().trim();

        if(username.isEmpty()){
            name.setError("Please Enter your username");
            name.setErrorEnabled(true);
            return false;
        }else{
            name.setError(null);
            name.setErrorEnabled(false);
            return true;
        }
    }

    public static boolean validateEmail(TextInputLayout name){
        String userEmail = name.getEditText().getText().toString().trim();

        if(userEmail.isEmpty()){
            name.setError("Please Enter your userEmail");
            name.setErrorEnabled(true);
            return false;
        }else if(!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()){
            name.setError("Please Enter a valid userEmail");
            name.setErrorEnabled(true);
            return false;

        }
        else{
            name.setError(null);
            name.setErrorEnabled(false);
            return true;
        }
    }

    public static boolean validatePassword(TextInputLayout pass1, TextInputLayout pass2){
        String pass = pass1.getEditText().getText().toString().trim();
        String password = pass2.getEditText().getText().toString().trim();

        if(!pass.equals(password)){
            pass2.setError("Passwords should match");
            pass2.setErrorEnabled(true);
            return false;
        }else if(password.isEmpty()) {
            pass2.setError("Please Enter your Password");
            pass2.setErrorEnabled(true);
            return false;
        }else{
            pass2.setError(null);
            pass2.setErrorEnabled(false);
            return true;
        }
    }

    public static boolean validatePass(TextInputLayout name){
        String userPassword = name.getEditText().getText().toString().trim();

        if(userPassword.isEmpty()){
            name.setError("Please Enter your Password");
            name.setErrorEnabled(true);
            return false;
        }else{
            name.setError(null);
            name.setErrorEnabled(false);
            return true;
        }
    }


}
