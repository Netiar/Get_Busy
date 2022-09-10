package com.moringaschool.get_busy.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.moringaschool.get_busy.R;
import com.moringaschool.get_busy.constants.Constants;
import com.moringaschool.get_busy.databinding.FragmentQuestBinding;
import com.moringaschool.get_busy.models.Result__1;


public class QuestFragment extends Fragment {
    Result__1 question;
    FragmentQuestBinding fragBind;



    public QuestFragment() {
        // Required empty public constructor
    }


    public static QuestFragment newInstance(Result__1 quest) {
        QuestFragment fragment = new QuestFragment();
        Bundle args = new Bundle();
        args.putSerializable("ques", quest);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            question = (Result__1) getArguments().getSerializable("ques");

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragBind = FragmentQuestBinding.inflate(inflater, container, false);
        // Inflate the layout for this fragment
        View v = fragBind.getRoot();

        fragBind.catName.setText("Category Name " +question.getCategory());
        fragBind.questName.setText(question.getQuestion());
        fragBind.questAns.setText("Correct Answer is: "+ question.getCorrectAnswer());
        fragBind.questType.setText("This is a " +question.getType() + " Choice question");
        fragBind.questMode.setText("Level difficulty: "+question.getDifficulty());


        fragBind.questAns1.setText("1. " +question.getIncorrectAnswers().get(0));
        fragBind.questAns2.setText("2. " +question.getIncorrectAnswers().get(1));
        fragBind.questAns3.setText("3. " +question.getIncorrectAnswers().get(2));
//        fragBind.questAns4.setText("4. " +question.getCorrectAnswer());

//        fragBind.save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.EDUCATIONAL_QUESTS);
//                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
//                String userId = currentUser.getUid();
//
//                ref.child(userId).setValue(question);
//            }
//        });

        return v;
    }
}