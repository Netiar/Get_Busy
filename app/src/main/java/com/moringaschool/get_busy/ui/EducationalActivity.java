package com.moringaschool.get_busy.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.moringaschool.get_busy.Adapter.RecyclerViewAdapter;
import com.moringaschool.get_busy.R;
import com.moringaschool.get_busy.databinding.ActivityEducationalBinding;
import com.moringaschool.get_busy.models.ResultOpenDb;
import com.moringaschool.get_busy.models.Result__1;
import com.moringaschool.get_busy.network.EducationalApi;
import com.moringaschool.get_busy.network.EducationalApiClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EducationalActivity extends AppCompatActivity implements View.OnClickListener {
    RecyclerViewAdapter adapter;
    ActivityEducationalBinding binding;
    int userScore;
    Call<ResultOpenDb> call1, call2, call3, call4, call5, call6;
    public List<Result__1> allItemsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEducationalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        allItemsList=new ArrayList<>();

        EducationalApi client = EducationalApiClient.getClient();
        //https://opentdb.com/api.php?amount=50&category=18&difficulty=medium&type=multiple
         call1 = client.getAllItems(5,18,"medium","multiple"); //amount=1&category=27&difficulty=easy&type=multiple
         call2 = client.getAllItems(5,10,"medium","multiple"); //amount=1&category=27&difficulty=easy&type=multiple
         call3 = client.getAllItems(5,12,"medium","multiple"); //amount=1&category=27&difficulty=easy&type=multiple
         call4 = client.getAllItems(5,16,"medium","multiple"); //amount=1&category=27&difficulty=easy&type=multiple
         call5 = client.getAllItems(5,17,"medium","multiple"); //amount=1&category=27&difficulty=easy&type=multiple
         call6 = client.getAllItems(5,19,"medium","multiple"); //amount=1&category=27&difficulty=easy&type=multiple

        binding.cat1.setOnClickListener(this);
        binding.cat2.setOnClickListener(this);
        binding.cat3.setOnClickListener(this);
        binding.cat4.setOnClickListener(this);
        binding.cat5.setOnClickListener(this);
        binding.submit.setOnClickListener(this);
        binding.submit.setOnClickListener(this);
        setNavigation(binding.bottom);

        getResponse(call1);
    }

    private void getResponse(Call<ResultOpenDb> call) {
        call.clone().enqueue(new Callback<ResultOpenDb>() {
            @Override
            public void onResponse(Call<ResultOpenDb> call, Response<ResultOpenDb> response) {
                if (response.isSuccessful()) {
                    allItemsList = response.body().getResults();
                    Log.d("TAG", "onResponse: " + allItemsList);
                    adapter = new RecyclerViewAdapter(EducationalActivity.this, allItemsList);
                    binding.lvp.setAdapter(adapter);
                    binding.lvp.setLayoutManager(new LinearLayoutManager(EducationalActivity.this));
                    binding.lvp.setHasFixedSize(true);

                    adapter = new RecyclerViewAdapter(EducationalActivity.this, allItemsList);
                    binding.lvp.setAdapter(adapter);
                    binding.lvp.setLayoutManager(new LinearLayoutManager(EducationalActivity.this));
                    binding.lvp.setHasFixedSize(true);
                    successful();
                }else{
                    unSuccessful();
                }
            }
            @Override
            public void onFailure(Call<ResultOpenDb> call, Throwable t) {
                Log.e("Error Message", "onFailure: ",t );
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == binding.cat1){
            getResponse(call6);
            return;
        }else if (v == binding.cat2){
            getResponse(call2);
            return;
        }else if (v == binding.cat3){
            getResponse(call3);
            return;
        }else if (v == binding.cat4){
            getResponse(call4);
            return;
        }else if (v == binding.cat5){
            getResponse(call5);
            return;
        } else if(v == binding.cat6){
            startActivity(new Intent(this, UserQuestionsActivity.class));
        }
        else if (v == binding.submit){
            userScore = RecyclerViewAdapter.getScore();
            Toast.makeText(this, "Your Score is " + userScore, Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Keep calm", Toast.LENGTH_SHORT).show();
        }
    }

    public void successful(){
        binding.bottom.setVisibility(View.VISIBLE);
        binding.scroll.setVisibility(View.VISIBLE);
        binding.bored.setVisibility(View.VISIBLE);
        binding.welcome.setVisibility(View.VISIBLE);
        binding.lvp.setVisibility(View.VISIBLE);
        binding.submit.setVisibility(View.VISIBLE);
        binding.progress.setVisibility(View.GONE);
    }
    public void unSuccessful(){
        binding.bored.setVisibility(View.GONE);
        binding.welcome.setVisibility(View.VISIBLE);
        binding.welcome.setText("Please try Again Later");
        binding.lvp.setVisibility(View.GONE);
        binding.submit.setVisibility(View.GONE);
        binding.progress.setVisibility(View.GONE);
        binding.bottom.setVisibility(View.GONE);


    }

    public void setNavigation(BottomNavigationView bottom){
        bottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(EducationalActivity.this, MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.education:
                        return true;
                    case R.id.favourites:
                        startActivity(new Intent(EducationalActivity.this, UserQuestionsActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.back:
                        Intent intent = new Intent(EducationalActivity.this, LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
}