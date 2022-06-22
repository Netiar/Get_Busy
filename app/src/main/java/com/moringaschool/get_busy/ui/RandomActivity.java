package com.moringaschool.get_busy.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.moringaschool.get_busy.R;
import com.moringaschool.get_busy.constants.Constants;
import com.moringaschool.get_busy.models.Result;
import com.moringaschool.get_busy.network.BoredApi;
import com.moringaschool.get_busy.network.BoredApiClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RandomActivity extends AppCompatActivity {

    @BindView(R.id.textViewTextMultiLine) TextView mTextViewTextMultiLin;
    @BindView(R.id.options_display) TextView mOptions_display;
    @BindView(R.id.like) ExtendedFloatingActionButton mLike;
    @BindView(R.id.reloadRandom) FloatingActionButton mReloadRandom;

    BottomAppBar bottomAppBar;
    BoredApi api;
    Result result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_view);
        ButterKnife.bind(this);

        FirebaseApp.initializeApp(this);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getInstance().getReference(Constants.RANDOM_ACTS);
        DatabaseReference userRef = firebaseDatabase.getReference(Constants.RANDOM_ACTS);

        bottomAppBar = findViewById(R.id.bottom_bar);
        setSupportActionBar(bottomAppBar);

        api = BoredApiClient.getClient();
        Call<Result> call = api.getRandomItem();

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (response.isSuccessful()){
                    result = response.body();
                    String activity = result.getActivity().toString();
                    mTextViewTextMultiLin.setText(activity);
                    String type = result.getType().toString();
                    mOptions_display.setText( "Type: " + type + " activity!");
                }
            }
            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Log.e("Error Message", "onFailure: ",t );
            }
        });

        mReloadRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                api = BoredApiClient.getClient();
                Call<Result> call = api.getRandomItem();
                call.enqueue(new Callback<Result>() {
                    @Override
                    public void onResponse(Call<Result> call, Response<Result> response) {
                        if (response.isSuccessful()){
                            result = response.body();
                            String activity = result.getActivity().toString();
                            mTextViewTextMultiLin.setText(activity);
                            String type = result.getType().toString();
                            mOptions_display.setText( "Type: " + type + " activity!");
                        }
                    }
                    @Override
                    public void onFailure(Call<Result> call, Throwable t) {
                        Log.e("Error Message", "onFailure: ",t );
                    }
                });
            }
        });
        mLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //add item to firebase db
                databaseReference.child(result.getKey()).setValue(result);
                Toast.makeText(RandomActivity.this, "Activity added to favourites.",
                            Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.bottombarmenu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id= item.getItemId();
        if (id == R.id.favourites){
            startActivity(new Intent(RandomActivity.this, FavouritesActivity.class)); //fav activity
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
