package com.moringaschool.get_busy.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.moringaschool.get_busy.R;
import com.moringaschool.get_busy.constants.Constants;
import com.moringaschool.get_busy.models.Result__1;
import com.moringaschool.get_busy.ui.QuestionActivity;

import java.io.Serializable;
import java.net.CookieHandler;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyHolder> {

    private Context context;
    List<Result__1> list;

    static int  userScore = 0;


    public RecyclerViewAdapter(Context context, List<Result__1> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.gif_strip, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyHolder holder, int position) {
        holder.bindData(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static int getScore(){
        return userScore;
    }

    public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView  categoryQuestions;
        RadioButton Answer1,  Answer2, Answer3, Answer4;
        MaterialButton save, readMore;
        List<String> result; //CHOICES LIST
        Result__1 ques;
        int position;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            categoryQuestions = itemView.findViewById(R.id.home_team);
            Answer1  = itemView.findViewById(R.id.radio_ans1);
            Answer2= itemView.findViewById(R.id.radio_ans2);
            Answer3 = itemView.findViewById(R.id.radio_ans3);
            Answer4 = itemView.findViewById(R.id.radio_ans4);
            save = itemView.findViewById(R.id.save);
            readMore = itemView.findViewById(R.id.readMore);
            position = getLayoutPosition();
            itemView.setOnClickListener(this);

            Answer1.setOnClickListener(this);
            Answer2.setOnClickListener(this);
            Answer3.setOnClickListener(this);
            Answer4.setOnClickListener(this);
            readMore.setOnClickListener(this);
            save.setOnClickListener(this);

            result= new ArrayList<>();

        }
       public void bindData(Result__1 datum) {

            categoryQuestions.setText(datum.getQuestion());
            result = datum.getIncorrectAnswers();
            result.add(datum.getCorrectAnswer());
            Collections.shuffle(result);

            Answer1.setText(result.get(0));
            Answer2.setText(result.get(1));
            Answer3.setText(result.get(2));
            Answer4.setText(result.get(3));

            this.ques = datum;

        }

        public int returnScore(View view){
//            userScore=0;//score becomes 1 if any of the questions is correct;
//            boolean checked = ((RadioButton) view).isChecked();
//            if (view==Answer1 || view==Answer2 || view==Answer3 || view==Answer4 ){
                if (Answer1.isChecked()){
                    String userAnswer = ((RadioButton) view).getText().toString();
//                Toast.makeText(context, userAnswer, Toast.LENGTH_SHORT).show();
                    if (userAnswer==ques.getCorrectAnswer()){
                        Toast.makeText(context, "yes", Toast.LENGTH_SHORT).show();
                        userScore++;
                    }
                } else if (Answer2.isChecked()){
                    String userAnswer = ((RadioButton) view).getText().toString();
//                Toast.makeText(context, userAnswer, Toast.LENGTH_SHORT).show();
                    if (userAnswer==ques.getCorrectAnswer()){
                        Toast.makeText(context, "yes", Toast.LENGTH_SHORT).show();
                        userScore++;
                    }
                } else if (Answer3.isChecked()){
                    String userAnswer = ((RadioButton) view).getText().toString();
//                Toast.makeText(context, userAnswer, Toast.LENGTH_SHORT).show();
                    if (userAnswer==ques.getCorrectAnswer()){
                        Toast.makeText(context, "yes", Toast.LENGTH_SHORT).show();
                        userScore++;
                    }
                } else if (Answer4.isChecked()){
                    String userAnswer = ((RadioButton) view).getText().toString();
//                Toast.makeText(context, userAnswer, Toast.LENGTH_SHORT).show();
                    if (userAnswer==ques.getCorrectAnswer()){
                        Toast.makeText(context, "yes", Toast.LENGTH_SHORT).show();
                        userScore++;
                    }
                }

//            }

//            if(view == Answer1 || view==Answer2){
//                if (checked){
//                    userScore++;
//                    selectedAns=
//                    Toast.makeText(context, view.getText().toString(), Toast.LENGTH_SHORT).show();
//
//                }else{
//                    return userScore;
//                }
//            }
            return userScore;
        }


        @Override
        public void onClick(View v) {
            if (v==readMore || v==itemView){
//                list2=list;
                int position = getLayoutPosition();
                Intent intent = new Intent(context, QuestionActivity.class);
                intent.putExtra("quests", (Serializable) list);
                intent.putExtra("position", position);
                context.startActivity(intent);

            }
            if(v==save){
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.EDUCATIONAL_QUESTS);
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String userId = user.getUid();

                ref.child(userId).push().setValue(ques);
                Toast.makeText(context, "Successfully saved", Toast.LENGTH_SHORT).show();
//                    Toast.makeText(context, "score" + userScore, Toast.LENGTH_SHORT).show();
            }
            if (v==Answer1 || v==Answer2 || v==Answer3 || v==Answer4){
                returnScore(v);
            }
        }

    }

}
