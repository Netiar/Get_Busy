package com.moringaschool.get_busy.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;

import android.content.Intent;
import android.os.Bundle;

import com.moringaschool.get_busy.Adapter.PagerAdapter;
import com.moringaschool.get_busy.R;
import com.moringaschool.get_busy.databinding.ActivityQuestionBinding;
import com.moringaschool.get_busy.models.Result__1;

import java.util.List;

public class QuestionActivity extends AppCompatActivity {
    ActivityQuestionBinding questBind;
    PagerAdapter adapter;
    List<Result__1> allQuests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        questBind = ActivityQuestionBinding.inflate(getLayoutInflater());
        setContentView(questBind.getRoot());

        Intent newIntent = getIntent();
        allQuests = (List<Result__1>) newIntent.getSerializableExtra("quests");

        adapter = new PagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, allQuests);
        int position = newIntent.getIntExtra("position",0);

        questBind.viewPager.setAdapter(adapter);
        questBind.viewPager.setCurrentItem(position);
    }
}