package com.example.baitiwb303_hw_f20_c.activity.ui.section;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baitiwb303_hw_f20_c.Models.SectionsM;
import com.example.baitiwb303_hw_f20_c.R;

import java.util.List;

public class CourseAndInstructorAdapter extends RecyclerView.Adapter<CourseAndInstructorAdapter.MyViewHolder> {
    private final List<SectionsM> sectionList;

    public Context context;


    public CourseAndInstructorAdapter(Context context, List<SectionsM> sectionList) {
        this.sectionList = sectionList;
        this.context = context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_course_instructor, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.courseNameView.setText(sectionList.get(position).getCourse_name());
        holder.instructorNameView.setText(sectionList.get(position).getInstructor_name());
    }

    @Override
    public int getItemCount() {
        return sectionList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView courseNameView;
        TextView instructorNameView;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            courseNameView = itemView.findViewById(R.id.course_instructor_name_c);
            instructorNameView = itemView.findViewById(R.id.course_instructor_name_i);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, CourseInstructorDetailsActivity.class);
                    intent.putExtra("section_course_instructor_details", sectionList.get(getAdapterPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}

