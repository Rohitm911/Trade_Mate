package com.example.trademate;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;


public class All_ItemsAdapter extends RecyclerView.Adapter<All_ItemsAdapter.AllItemsViewHolder>{
    ArrayList<All_ItemsHelper> all_items;

    public All_ItemsAdapter(ArrayList<All_ItemsHelper> all_items) {
        this.all_items=all_items;
    }
    public static class AllItemsViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView title, short_desc, price;

        public AllItemsViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.electronics_image);
            title = itemView.findViewById(R.id.electronics_title);
            short_desc = itemView.findViewById(R.id.electronics_desc);
            price = itemView.findViewById(R.id.electronics_price);
        }
    }
    @NonNull
    @Override
    public All_ItemsAdapter.AllItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.allcat_card_design, parent, false);
        All_ItemsAdapter.AllItemsViewHolder allitemsViewHolder = new All_ItemsAdapter.AllItemsViewHolder(view);
        return allitemsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull All_ItemsAdapter.AllItemsViewHolder holder, int position) {
        All_ItemsHelper helperClass = all_items.get(position);
        Picasso.get().load(helperClass.getImage()).into(holder.imageView);
        // holder.imageView.setImageResource(helperClass.getUrl());
        holder.title.setText(helperClass.getName());
        holder.short_desc.setText(helperClass.getShort_desc());
        holder.price.setText(helperClass.getPrice());
    }

    @Override
    public int getItemCount() {
        return all_items.size();
    }

}
