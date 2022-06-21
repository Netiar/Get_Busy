package com.moringaschool.get_busy.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;

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

public class EducationalActivity extends AppCompatActivity {
    RecyclerViewAdapter adapter;
    ActivityEducationalBinding binding;

    public List<Result__1> allItemsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEducationalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        allItemsList=new ArrayList<>();

        EducationalApi client = EducationalApiClient.getClient();
        //https://opentdb.com/api.php?amount=50&category=18&difficulty=medium&type=multiple
        Call<ResultOpenDb> call = client.getAllItems(5,18,"medium","multiple"); //amount=1&category=27&difficulty=easy&type=multiple

        call.enqueue(new Callback<ResultOpenDb>() {
            @Override
            public void onResponse(Call<ResultOpenDb> call, Response<ResultOpenDb> response) {
                if (response.isSuccessful()) {

                    allItemsList = response.body().getResults();
                    Log.d("TAG", "onResponse: " + allItemsList);
                    adapter = new RecyclerViewAdapter(EducationalActivity.this, allItemsList);
                    binding.lvp.setAdapter(adapter);
                    binding.lvp.setLayoutManager(new LinearLayoutManager(EducationalActivity.this));
                    binding.lvp.setHasFixedSize(true);



                }

            }
            @Override
            public void onFailure(Call<ResultOpenDb> call, Throwable t) {
                Log.e("Error Message", "onFailure: ",t );
            }
        });



    }
}