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
import java.util.List;

public class RecAdapter extends RecyclerView.Adapter<RecAdapter.MyHolder> {
    private Context context;
    List<Result__1> list;

    public RecAdapter(Context context, List<Result__1> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.category, parent, false);
        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.bindData(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView categoryQuestion, level;
        Result__1 ques;
        int position;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            categoryQuestion = itemView.findViewById(R.id.name);
            level = itemView.findViewById(R.id.level);
            itemView.setOnClickListener(this);

        }
        public void bindData(Result__1 datum) {

            categoryQuestion.setText(datum.getCategory());
            level.setText("This category contains " + datum.getType() + "questions of a " + datum.getDifficulty()+ " difficulty level");
            this.ques = datum;

        }

      @Override
        public void onClick(View v) {

        }

    }
}



