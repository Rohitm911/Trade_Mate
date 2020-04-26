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

public class Electronicsadapter extends RecyclerView.Adapter<Electronicsadapter.ElectronicsViewHolder> {

    ArrayList<electronicshelper> all_electronics;

    public Electronicsadapter(ArrayList<electronicshelper> all_electronics) {
        this.all_electronics = all_electronics;
    }

    public static class ElectronicsViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView title, short_desc,price;

        public ElectronicsViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.electronics_image);
            title = itemView.findViewById(R.id.electronics_title);
            short_desc = itemView.findViewById(R.id.electronics_desc);
            price=itemView.findViewById(R.id.electronics_price);
        }
    }
    @NonNull
    @Override
    public ElectronicsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.allcat_card_design, parent, false);
        ElectronicsViewHolder electronicsViewHolder = new ElectronicsViewHolder(view);
        return electronicsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ElectronicsViewHolder holder, int position) {
        electronicshelper helperClass = all_electronics.get(position);
        Picasso.get().load(helperClass.getUrl()).into(holder.imageView);
       // holder.imageView.setImageResource(helperClass.getUrl());
        holder.title.setText(helperClass.getName());
        holder.short_desc.setText(helperClass.getShort_desc());
        holder.price.setText(helperClass.getPrice());
    }

    @Override
    public int getItemCount() {
        return all_electronics.size();
    }

}
