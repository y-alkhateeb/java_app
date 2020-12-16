package com.example.baitiwb303_hw_f20_c.activity.ui.dr;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baitiwb303_hw_f20_c.Models.InstructorM;
import com.example.baitiwb303_hw_f20_c.R;

import java.util.List;


public class InstructorAdapter  extends RecyclerView.Adapter<InstructorAdapter.MyViewHolder>{
    private List<InstructorM> instructorList;

    public Context context;


    public InstructorAdapter(Context context, List<InstructorM> instructorList) {
        this.instructorList = instructorList;
        this.context = context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_dr, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String full_name = instructorList.get(position).getInstructor_first_name() +"  " +
                instructorList.get(position).getInstructor_last_name();
        holder.instructorNameView.setText(full_name);
    }

    @Override
    public int getItemCount() {
        return instructorList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView instructorNameView;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            instructorNameView = itemView.findViewById(R.id.instructor_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, InstructorDetailsActivity.class);
                    intent.putExtra("instructor_details",instructorList.get(getAdapterPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
