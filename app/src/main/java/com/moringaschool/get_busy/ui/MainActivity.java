package com.moringaschool.get_busy.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.moringaschool.get_busy.Adapter.RecAdapter;
import com.moringaschool.get_busy.Adapter.RecyclerViewAdapter;
import com.moringaschool.get_busy.R;
import com.moringaschool.get_busy.constants.Constants;
import com.moringaschool.get_busy.databinding.ActivityMainBinding;
import com.moringaschool.get_busy.models.Result;
import com.moringaschool.get_busy.models.ResultOpenDb;
import com.moringaschool.get_busy.models.Result__1;
import com.moringaschool.get_busy.network.BoredApi;
import com.moringaschool.get_busy.network.BoredApiClient;
import com.moringaschool.get_busy.network.EducationalApi;
import com.moringaschool.get_busy.network.EducationalApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{
    ActivityMainBinding mainBind;
    List<Result__1> allItemsList;
    RecAdapter adapter;
    Call<ResultOpenDb> call2;
    Call<Result> call;
    Result result;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBind = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBind.getRoot());

        allItemsList = new ArrayList<>();

        FirebaseApp.initializeApp(this);
        databaseReference = FirebaseDatabase.getInstance().getReference(Constants.RANDOM_ACTS);

        EducationalApi client = EducationalApiClient.getClient();
        call2 = client.getAllItems1(20, "multiple","medium"); //amount=1&category=27&difficulty=easy&type=multiple
        getResponse(call2);

        BoredApi api = BoredApiClient.getClient();
        call = api.getRandomItem();

        getActivity(call);

        mainBind.materialButton2.setOnClickListener(this);
        mainBind.recView2.setOnClickListener(this);
        setNavigation(mainBind.bottom);


    }

    public void getActivity(Call<Result> call){
        call.clone().enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (response.isSuccessful()){
                    result = response.body();
                    String activity = result.getActivity();
                    mainBind.activityName.setText(activity);
                    mainBind.activity.setText("Dear user the following activity is conducted by a maximum of "+ result.getParticipants() + " participant. ");
                    String type = result.getType().toString();
                    mainBind.activityPart.setText( "Type: " + type + " activity!");
                }
            }
            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Log.e("Error Message", "onFailure: ",t );
            }
        });
    }

    private void getResponse(Call<ResultOpenDb> call) {
        call.enqueue(new Callback<ResultOpenDb>() {
            @Override
            public void onResponse(Call<ResultOpenDb> call, Response<ResultOpenDb> response) {
                if (response.isSuccessful()) {
                    allItemsList = response.body().getResults();
                    Log.d("TAG", "onResponse: " + allItemsList);
                    adapter = new RecAdapter(MainActivity.this, allItemsList);
                    mainBind.recView1.setAdapter(adapter);
                    mainBind.recView1.setLayoutManager(new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false));
                    mainBind.recView1.setHasFixedSize(true);

                }else{

                }
            }
            @Override
            public void onFailure(Call<ResultOpenDb> call, Throwable t) {
                mainBind.textView.setText(t.getMessage());

            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v == mainBind.materialButton2){
            getActivity(call);
        }else{
            //add item to firebase db
            databaseReference.child(result.getKey()).setValue(result);
            Toast.makeText(this, "Activity added to favourites.",
                    Toast.LENGTH_LONG).show();

        }
    }

    public void setNavigation(BottomNavigationView bottom){
        bottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        return true;
                    case R.id.education:
                        startActivity(new Intent(MainActivity.this, UserQuestionsActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.favourites:
                        startActivity(new Intent(MainActivity.this, FavouritesActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.back:
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
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