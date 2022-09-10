package com.moringaschool.get_busy.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moringaschool.get_busy.Adapter.RandomAdapter;
import com.moringaschool.get_busy.R;
import com.moringaschool.get_busy.constants.Constants;
import com.moringaschool.get_busy.databinding.ActivityFavouritesBinding;
import com.moringaschool.get_busy.models.Result;

import java.util.ArrayList;
import java.util.List;

public class FavouritesActivity extends AppCompatActivity {

    RandomAdapter adapter;
    ActivityFavouritesBinding favBind;
    List<Result>resultList;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        favBind=ActivityFavouritesBinding.inflate(getLayoutInflater());
        setContentView(favBind.getRoot());

        resultList=new ArrayList<>();
        adapter=new RandomAdapter(this, resultList);
        ref= FirebaseDatabase.getInstance().getReference(Constants.RANDOM_ACTS);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot activities:snapshot.getChildren() ){
                    Result activity= activities.getValue(Result.class);
                    resultList.add(activity);
                }
                adapter.notifyDataSetChanged();
                favBind.savedRecView.setAdapter(adapter);
                favBind.savedRecView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                favBind.savedRecView.setHasFixedSize(true);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}