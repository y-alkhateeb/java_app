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
import com.example.baitiwb303_hw_f20_c.database.Section;

import java.util.List;

public class SectionAdapter extends RecyclerView.Adapter<SectionAdapter.MyViewHolder> {
    private final List<SectionsM> sectionList;

    public Context context;


    public SectionAdapter(Context context, List<SectionsM> sectionList) {
        this.sectionList = sectionList;
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
        holder.sectionNameView.setText(sectionList.get(position).getSection_section_no());
    }

    @Override
    public int getItemCount() {
        return sectionList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView sectionNameView;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            sectionNameView = itemView.findViewById(R.id.section_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,SectionDetailsActivity.class);
                    intent.putExtra("section_details", sectionList.get(getAdapterPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}

