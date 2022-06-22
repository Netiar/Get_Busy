package com.moringaschool.get_busy.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.moringaschool.get_busy.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void StartRandomActivity(View view) {
        Intent intent = new Intent(this, RandomActivity.class);
        startActivity(intent);
    }
    public void StartEducationalActivity(){
        Intent intent = new Intent(this, EducationalActivity.class);
        startActivity(intent);
    }
}