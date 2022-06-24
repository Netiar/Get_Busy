package com.moringaschool.get_busy.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.get_busy.R;
import com.moringaschool.get_busy.models.Result;

import java.util.List;

public class RandomAdapter extends RecyclerView.Adapter<RandomAdapter.myViewHolder>{

    Context context;
    List<Result> resultList;

    public RandomAdapter(Context context, List<Result> resultList) {
        this.context = context;
        this.resultList = resultList;
    }

    @NonNull
    @Override
    public RandomAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_saved, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RandomAdapter.myViewHolder holder, int position) {
        holder.BindData(resultList.get(position));
    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        TextView activityName, activityType, activityParticipant;

        public myViewHolder(@NonNull View itemView) {

            super(itemView);
            activityName=itemView.findViewById(R.id.actName);
            activityType=itemView.findViewById(R.id.actType);
            activityParticipant=itemView.findViewById(R.id.actParticipants);

        }
        public void BindData(Result result){
            activityName.setText("Activity Name: " + result.getActivity().toString());
            activityType.setText("Activity Category: " +result.getType().toString());
            activityParticipant.setText("Participants: " +result.getParticipants().toString());
        }
    }

}
