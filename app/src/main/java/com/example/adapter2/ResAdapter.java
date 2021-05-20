package com.example.adapter2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static androidx.core.content.ContextCompat.startActivity;

public class ResAdapter extends RecyclerView.Adapter<ResAdapter.ResViewHolder> implements Filterable {

    private List<ResModel> list;
    private List<ResModel> listSearch;
    private Intent intent;
    private final Context parent;

    public ResAdapter(List<ResModel> list, Context parent) {
        this.list = list;
        listSearch = new ArrayList<>(list);
        this.parent = parent;
    }
    
    

    @NonNull
    @Override
    public ResViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ResViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_res, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ResViewHolder holder, int position) {

        ResModel res = list.get(position);

        holder.textDes.setText(res.des);
        holder.textName.setText(res.name);
        Picasso.get().load(res.getImageId()).into(holder.imagePreview);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


            public class ResViewHolder extends RecyclerView.ViewHolder {

        TextView textName, textDes;
        ImageView imagePreview;

        public ResViewHolder(@NonNull View itemView) {
            super(itemView);

            textName = itemView.findViewById(R.id.txt_name);
            textDes = itemView.findViewById(R.id.txtDes);
            imagePreview = itemView.findViewById(R.id.imagePreview);
            
            imagePreview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intent = new Intent(parent, Timer.class);
                    parent.startActivity(intent);

                }
            });
        }

    }

    @Override
    public Filter getFilter() {
        return listFilter;
    }
    private final Filter listFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<ResModel> filteredlist = new ArrayList<>();

            if(constraint == null || constraint.length() == 0){
                filteredlist.addAll(listSearch);
            }else {
                String filterpattern = constraint.toString().toLowerCase().trim();

                for (ResModel model : listSearch){
                    if (model.getText1().toLowerCase().contains(filterpattern)){
                        filteredlist.add(model);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredlist;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            listSearch.clear();
            listSearch.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };
}
