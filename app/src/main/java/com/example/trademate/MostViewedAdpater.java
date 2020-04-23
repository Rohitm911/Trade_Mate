package com.example.trademate;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MostViewedAdpater extends RecyclerView.Adapter<MostViewedAdpater.MostViewedViewHolder> {

    ArrayList<MostViewedHelperClass> mostVieweditems;

    public MostViewedAdpater(ArrayList<MostViewedHelperClass> mostVieweditems) {
        this.mostVieweditems = mostVieweditems;
    }

    public static class MostViewedViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView, title;

        public MostViewedViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.mv_image);
            title = itemView.findViewById(R.id.mv_title);
            textView = itemView.findViewById(R.id.mv_desc);
        }
    }

    @NonNull
    @Override
    public MostViewedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.most_viewed_card_design, parent, false);
        MostViewedViewHolder mostViewedViewHolder = new MostViewedViewHolder(view);
        return mostViewedViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MostViewedViewHolder holder, int position) {
        MostViewedHelperClass helperClass = mostVieweditems.get(position);

        holder.imageView.setImageResource(helperClass.getImage());
        holder.title.setText(helperClass.getTitle());
        holder.textView.setText(helperClass.getDescription());
    }

    @Override
    public int getItemCount() {
        return mostVieweditems.size();
    }
}
