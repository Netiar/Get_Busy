package com.moringaschool.get_busy.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.get_busy.R;
import com.moringaschool.get_busy.models.Result__1;

import java.net.CookieHandler;
import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyHolder> {
    private Context context;
    List<Result__1> list;

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

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView categoryName, categoryQuestions, categoryAnswers, Answer1,  Answer2, Answer3, Answer4;
        List<String> result;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.home_team);
            categoryQuestions = itemView.findViewById(R.id.federation);
            categoryAnswers = itemView.findViewById(R.id.competition);
            Answer1 = itemView.findViewById(R.id.results);
            Answer2 = itemView.findViewById(R.id.season);
            Answer3 = itemView.findViewById(R.id.prediction);
            Answer4 = itemView.findViewById(R.id.status);
            result= new ArrayList<>();


        }
       public void bindData(Result__1 datum) {
            categoryName.setText(datum.getCategory());
            categoryQuestions.setText(datum.getQuestion());
            categoryAnswers.setText(datum.getCategory());
            Answer1.setText(datum.getCorrectAnswer());


            result=datum.getIncorrectAnswers();
            Answer2.setText(result.get(0));
            Answer3.setText(result.get(1));
            Answer4.setText(result.get(2));

        }
    }

}
