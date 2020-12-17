package com.example.baitiwb303_hw_f20_c.activity.ui.student;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baitiwb303_hw_f20_c.Models.AccountM;
import com.example.baitiwb303_hw_f20_c.R;

import java.util.ArrayList;
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.MyViewHolder> implements Filterable {
    private List<AccountM> studentList;
    private List<AccountM> filterdStudentList;

    public Context context;
    private ItemFilter mFilter = new ItemFilter();

    public StudentAdapter(Context context, List<AccountM> studentList) {
        this.studentList = studentList;
        this.filterdStudentList = studentList;
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
        String full_name = filterdStudentList.get(position).getFirst_name() + "  " +
                filterdStudentList.get(position).getLast_name();
        holder.studentNameView.setText(full_name);
    }

    @Override
    public int getItemCount() {
        return filterdStudentList.size();
    }

    @Override
    public Filter getFilter() {
        return mFilter;
    }

    private class ItemFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            String filterString = constraint.toString().toLowerCase();

            FilterResults results = new FilterResults();

            final List<AccountM> list = studentList;

            int count = list.size();
            final ArrayList<AccountM> nlist = new ArrayList<AccountM>(count);

            String filterableString;

            for (int i = 0; i < count; i++) {
                filterableString = list.get(i).getFirst_name();
                if (filterableString.toLowerCase().contains(filterString)) {
                    nlist.add(list.get(i));
                }
            }

            results.values = nlist;
            results.count = nlist.size();

            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filterdStudentList = (ArrayList<AccountM>) results.values;
            notifyDataSetChanged();
        }

    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView studentNameView;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            studentNameView = itemView.findViewById(R.id.student_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, StudentDetailsActivity.class);
                    intent.putExtra("student_details", filterdStudentList.get(getAdapterPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}

