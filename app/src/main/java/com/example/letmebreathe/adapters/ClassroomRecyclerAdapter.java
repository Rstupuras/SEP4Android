package com.example.letmebreathe.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.letmebreathe.R;
import com.example.letmebreathe.models.EnvironmentalData;

import java.util.ArrayList;


public class ClassroomRecyclerAdapter extends RecyclerView.Adapter<ClassroomRecyclerAdapter.ViewHolder> {


    final private OnListItemClickListener onListItemClickListener;
    private ArrayList<EnvironmentalData> classrooms;
    private Context context;

    public ClassroomRecyclerAdapter(Context context, ArrayList<EnvironmentalData> classrooms, OnListItemClickListener onListItemClickListener) {
        this.onListItemClickListener = onListItemClickListener;
        this.classrooms = classrooms;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.classroom_list_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.classId.setText(classrooms.get(i).getLocation());
    }

    @Override
    public int getItemCount() {
        return classrooms.size();
    }


    public ArrayList<EnvironmentalData> getEnvironmentalDataList() {
        return classrooms;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView classId;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            classId = itemView.findViewById(R.id.tv_classroomId);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onListItemClickListener.onListItemClick(getAdapterPosition());
        }
    }

    public interface OnListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }
}
