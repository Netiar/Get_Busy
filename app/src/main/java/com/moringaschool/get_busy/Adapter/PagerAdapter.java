package com.moringaschool.get_busy.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.moringaschool.get_busy.fragments.QuestFragment;
import com.moringaschool.get_busy.models.Result__1;

import java.util.List;

public class PagerAdapter extends FragmentPagerAdapter {
    List<Result__1> allQuests;

    public PagerAdapter(@NonNull FragmentManager fm, int behavior, List<Result__1> allQuest) {
        super(fm, behavior);

        this.allQuests = allQuest;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        QuestFragment questFragment = QuestFragment.newInstance(allQuests.get(position));
        return questFragment;
    }

    @Override
    public int getCount() {
        return allQuests.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return allQuests.get(position).getCategory();
    }
}
