package com.example.baitiwb303_hw_f20_c.activity.ui.section;

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

import com.example.baitiwb303_hw_f20_c.Models.SectionsM;
import com.example.baitiwb303_hw_f20_c.R;

import com.example.baitiwb303_hw_f20_c.database.Section;

import java.util.ArrayList;
import java.util.List;

public class SectionAdapter extends RecyclerView.Adapter<SectionAdapter.MyViewHolder> implements Filterable {
    private List<SectionsM> sectionList;
    private List<SectionsM> filterSectionList;

    public Context context;
    private ItemFilter mFilter = new ItemFilter();

    public SectionAdapter(Context context, List<SectionsM> sectionList) {
        this.sectionList = sectionList;
        this.filterSectionList = sectionList;
        this.context = context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_section, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.sectionNameView.setText(filterSectionList.get(position).getSection_section_no());
    }

    @Override
    public int getItemCount() {
        if (filterSectionList == null) {
            return 0;
        } else {
            return filterSectionList.size();
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

            final List<SectionsM> list = sectionList;

            int count = list.size();
            final ArrayList<SectionsM> nlist = new ArrayList<SectionsM>(count);

            String filterableString;

            for (int i = 0; i < count; i++) {
                filterableString = list.get(i).getSection_section_no().toString();
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
            filterSectionList = (ArrayList<SectionsM>) results.values;
            notifyDataSetChanged();
        }

    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView sectionNameView;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            sectionNameView = itemView.findViewById(R.id.section_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, SectionDetailsActivity.class);
                    intent.putExtra("section_details", filterSectionList.get(getAdapterPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}

