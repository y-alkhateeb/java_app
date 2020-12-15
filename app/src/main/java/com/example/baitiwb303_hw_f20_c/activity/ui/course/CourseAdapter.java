package com.example.baitiwb303_hw_f20_c.activity.ui.course;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baitiwb303_hw_f20_c.Models.CourseM;
import com.example.baitiwb303_hw_f20_c.R;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.MyViewHolder> {
    private List<CourseM> courseMList;

    public Context context;


    public CourseAdapter(Context context, List<CourseM> courseMList) {
        this.courseMList = courseMList;
        this.context = context;
    }


    @NonNull
    @Override
    public CourseAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_course, parent, false);
        return new CourseAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.MyViewHolder holder, int position) {
        holder.courseNameView.setText(courseMList.get(position).getCourse_tittle());
    }

    @Override
    public int getItemCount() {
        return courseMList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView courseNameView;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            courseNameView = itemView.findViewById(R.id.course_title);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, CourseDetailsActivity.class);
                    intent.putExtra("course_details",courseMList.get(getAdapterPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}


