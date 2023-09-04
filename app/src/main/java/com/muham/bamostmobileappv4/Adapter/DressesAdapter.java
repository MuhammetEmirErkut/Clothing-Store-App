package com.muham.bamostmobileappv4.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.muham.bamostmobileappv4.Dresses;
import com.muham.bamostmobileappv4.R;

import java.util.Collections;
import java.util.List;

public class DressesAdapter extends RecyclerView.Adapter<DressesAdapter.DressesViewHolder> {
    private Context context;

    private List<Dresses> dressesList;

    public DressesAdapter(Context context, List<Dresses> dressesList) {
        this.context = context;
        this.dressesList = dressesList;
    }

    @NonNull
    @Override
    public DressesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.dress, parent, false);
        return new DressesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DressesViewHolder holder, int position) {
        Dresses dress = dressesList.get(position);
        holder.imageView.setImageResource(dress.getResimId());
        holder.textViewName.setText(dress.getIsim());
        holder.textViewPrice.setText(String.valueOf(dress.getFiyat()));
    }

    @Override
    public int getItemCount() {
        return dressesList.size();
    }

    public class DressesViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textViewName;
        TextView textViewPrice;

        public DressesViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textViewName = itemView.findViewById(R.id.dressNameTextView);
            textViewPrice = itemView.findViewById(R.id.priceTextView);
        }
    }

}
