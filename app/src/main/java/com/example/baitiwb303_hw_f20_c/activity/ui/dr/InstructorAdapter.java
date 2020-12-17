package com.example.baitiwb303_hw_f20_c.activity.ui.dr;

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

import com.example.baitiwb303_hw_f20_c.Models.InstructorM;
import com.example.baitiwb303_hw_f20_c.Models.SectionsM;
import com.example.baitiwb303_hw_f20_c.R;
import com.example.baitiwb303_hw_f20_c.activity.ui.section.SectionAdapter;

import java.util.ArrayList;
import java.util.List;


public class InstructorAdapter  extends RecyclerView.Adapter<InstructorAdapter.MyViewHolder>  implements Filterable {
    private List<InstructorM> instructorList;
    private List<InstructorM> filterInstructorList;

    public Context context;
    private ItemFilter mFilter = new ItemFilter();

    public InstructorAdapter(Context context, List<InstructorM> instructorList) {
        this.instructorList = instructorList;
        this.filterInstructorList = instructorList;
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
        String full_name = filterInstructorList.get(position).getInstructor_first_name() +"  " +
                filterInstructorList.get(position).getInstructor_last_name();
        holder.instructorNameView.setText(full_name);
    }

    @Override
    public int getItemCount() {
        if (filterInstructorList == null) {
            return 0;
        } else {
            return filterInstructorList.size();
        }
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

            final List<InstructorM> list = filterInstructorList;

            int count = list.size();
            final ArrayList<InstructorM> nlist = new ArrayList<InstructorM>(count);

            String filterableString;

            for (int i = 0; i < count; i++) {
                filterableString = list.get(i).getInstructor_first_name();
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
            filterInstructorList = (ArrayList<InstructorM>) results.values;
            notifyDataSetChanged();
        }

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
                    intent.putExtra("instructor_details",filterInstructorList.get(getAdapterPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
