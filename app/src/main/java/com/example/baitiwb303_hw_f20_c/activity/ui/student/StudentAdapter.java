package com.example.baitiwb303_hw_f20_c.activity.ui.student;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baitiwb303_hw_f20_c.Models.AccountM;
import com.example.baitiwb303_hw_f20_c.R;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.MyViewHolder> {
    private List<AccountM> studentList;

    public Context context;

//if color pass the color in hex and th name in strings list
//    if is size pass the size list in the strings list

    public StudentAdapter(Context context, List<AccountM> studentList) {
        this.studentList = studentList;
        this.context = context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_student, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String full_name = studentList.get(position).getFirst_name() +"  " +
                studentList.get(position).getLast_name();
        holder.studentNameView.setText(full_name);
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView studentNameView;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            studentNameView = itemView.findViewById(R.id.student_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,StudentDetailsActivity.class);
                    intent.putExtra("student_details",studentList.get(getAdapterPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}

