package com.example.baitiwb303_hw_f20_c.activity.ui.enrollment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baitiwb303_hw_f20_c.Models.AccountM;
import com.example.baitiwb303_hw_f20_c.Models.CourseM;
import com.example.baitiwb303_hw_f20_c.Models.EnrollmentM;
import com.example.baitiwb303_hw_f20_c.Models.SectionsM;
import com.example.baitiwb303_hw_f20_c.R;

import java.util.List;

public class EnrollmentDetailsAdapter extends RecyclerView.Adapter<EnrollmentDetailsAdapter.MyViewHolder> {
    private List<EnrollmentM> enrollmentMS;
    private List<CourseM> courseMList;
    private List<SectionsM> sectionsMS;
    private AccountM studentList;

    public Context context;


    public EnrollmentDetailsAdapter(Context context, List<CourseM> courseMList, AccountM studentList, List<SectionsM> sectionsMS, List<EnrollmentM> enrollmentMS) {
        this.enrollmentMS = enrollmentMS;
        this.sectionsMS = sectionsMS;
        this.courseMList = courseMList;
        this.studentList = studentList;
        this.context = context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_enrollment_details, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.studentNameView.setText(studentList.getFirst_name() + " " + studentList.getLast_name());
        holder.enrollment_course_hours.setText(courseMList.get(position).getCourse_hours());
        holder.enrollment_course_title.setText(courseMList.get(position).getCourse_tittle());
        holder.enrollment_section_name.setText(sectionsMS.get(position).getSection_section_no());
        holder.enrollment_section_room.setText(sectionsMS.get(position).getSection_room_no());
        holder.enrollment_grade.setText(enrollmentMS.get(position).getEnrollment_grade());
    }

    @Override
    public int getItemCount() {
        return courseMList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView studentNameView, enrollment_grade, enrollment_course_title, enrollment_course_hours, enrollment_section_name, enrollment_section_room;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            studentNameView = itemView.findViewById(R.id.enrollment_student_name);
            enrollment_grade = itemView.findViewById(R.id.enrollment_grade);
            enrollment_course_title = itemView.findViewById(R.id.enrollment_course_title);
            enrollment_course_hours = itemView.findViewById(R.id.enrollment_course_hours);
            enrollment_section_name = itemView.findViewById(R.id.enrollment_section_name);
            enrollment_section_room = itemView.findViewById(R.id.enrollment_section_room);
        }
    }
}

