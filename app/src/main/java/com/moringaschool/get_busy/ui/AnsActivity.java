package com.moringaschool.get_busy.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.moringaschool.get_busy.R;
import com.moringaschool.get_busy.databinding.ActivityAnsBinding;

public class AnsActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityAnsBinding ansBind;
    int userScore = 0;
    int mainScore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ansBind = ActivityAnsBinding.inflate(getLayoutInflater());
        setContentView(ansBind.getRoot());

        Intent intent = getIntent();
         userScore = intent.getIntExtra("userScore",0);
        int mainScore = userScore * 20;
        ansBind.text2.setText("You have scored "+ mainScore + "%");
        if(userScore > 2){
            ansBind.text3.setText("Excellent Keep it up!!");

        }else{
            ansBind.text3.setText("YOUNG STUDENT GO THROUGH THE LMS KEENLY!!");
            ansBind.text4.setVisibility(View.GONE);
        }

        ansBind.text1.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
       this.userScore = 0;
       this.mainScore = 0;
       startActivity(new Intent(AnsActivity.this, MainActivity.class));

    }
}