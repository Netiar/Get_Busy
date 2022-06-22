package com.moringaschool.get_busy.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moringaschool.get_busy.Adapter.RecyclerViewAdapter;
import com.moringaschool.get_busy.constants.Constants;
import com.moringaschool.get_busy.databinding.ActivityEducationalBinding;
import com.moringaschool.get_busy.databinding.ActivityQuestionBinding;
import com.moringaschool.get_busy.models.Result__1;

import java.util.ArrayList;
import java.util.List;

public class UserQuestionsActivity extends AppCompatActivity {
    ActivityEducationalBinding binding;
    DatabaseReference ref;
    FirebaseUser currentUser;
    RecyclerViewAdapter adapter;
    List<Result__1> userQuestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEducationalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        userQuestions = new ArrayList<>();
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        String userId  = currentUser.getUid();
        ref = FirebaseDatabase.getInstance().getReference(Constants.EDUCATIONAL_QUESTS).child(userId);
        adapter = new RecyclerViewAdapter(this,userQuestions);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot!=null){
                    for(DataSnapshot snap1:snapshot.getChildren()){
                        Result__1 quest1 = snap1.getValue(Result__1.class);
                        userQuestions.add(quest1);
                    }
                    adapter.notifyDataSetChanged();

                    binding.lvp.setAdapter(adapter);
                    binding.lvp.setLayoutManager(new LinearLayoutManager(UserQuestionsActivity.this));
                    binding.lvp.setHasFixedSize(true);
                    successful();

                } else {
                    Toast.makeText(getApplicationContext(), "Snapshot is empty.", Toast.LENGTH_SHORT).show();
                    unSuccessful();
                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }

    public void successful(){
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
    }

}