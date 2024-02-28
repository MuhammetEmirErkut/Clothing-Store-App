package com.muham.bamostmobileappv4.DressListAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.muham.bamostmobileappv4.Adapter.Dresses;
import com.muham.bamostmobileappv4.DressScreen;
import com.muham.bamostmobileappv4.R;

import java.util.List;

public class DressListAdapter extends RecyclerView.Adapter<DressListAdapter.DressListViewHolder> {

    private Context context;
    private List<Dresses> dressList;

    public DressListAdapter(Context context, List<Dresses> dressList){
        this.context = context;
        this.dressList = dressList;
    }
    @NonNull
    @Override
    public DressListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.dress, parent, false);
        return new DressListAdapter.DressListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DressListViewHolder holder, int position) {
        Dresses dress = dressList.get(position);
        holder.imageView.setImageResource(dress.getResimId());
        holder.textViewName.setText(dress.getIsim());
        holder.textViewPrice.setText(String.valueOf(dress.getFiyat()));

        // Öğe tıklama olayını burada işleyin
        holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Tıklama olayını işle, DressScreen aktivitesine git
                    Intent intent = new Intent(context, DressScreen.class);
                    // İhtiyaca göre ekstra verileri Intent'e ekleyebilirsiniz.
                    context.startActivity(intent);
                }
        });

    }

    @Override
    public int getItemCount() {
        return dressList.size();
    }
    public class DressListViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textViewName;
        TextView textViewPrice;
        public DressListViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textViewName = itemView.findViewById(R.id.dressNameTextView);
            textViewPrice = itemView.findViewById(R.id.priceTextView);
        }
    }
}

