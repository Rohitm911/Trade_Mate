package com.example.trademate;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.AdapterAllCategoriesViewHolder> {

    ArrayList<CategoriesHelperClass> categories;
    OnNoteListener3 mOnNoteListener;

    public CategoriesAdapter(ArrayList<CategoriesHelperClass> categories,OnNoteListener3 mOnNoteListener) {
        this.categories = categories;
        this.mOnNoteListener=mOnNoteListener;
    }

    public static class AdapterAllCategoriesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        RelativeLayout relativeLayout;
        ImageView imageView;
        TextView textView;
        OnNoteListener3 onNoteListener;

        public AdapterAllCategoriesViewHolder(@NonNull View itemView,OnNoteListener3 onNoteListener) {
            super(itemView);
            this.onNoteListener=onNoteListener;

            relativeLayout = itemView.findViewById(R.id.background_gradient);
            imageView = itemView.findViewById(R.id.categories_image);
            textView = itemView.findViewById(R.id.categories_title);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            onNoteListener.onNoteClick3(getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public AdapterAllCategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_card_design, parent, false);
        AdapterAllCategoriesViewHolder lvh = new AdapterAllCategoriesViewHolder(view,mOnNoteListener);
        return lvh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterAllCategoriesViewHolder holder, int position) {

        CategoriesHelperClass helperClass = categories.get(position);
        holder.imageView.setImageResource(helperClass.getImage());
        holder.textView.setText(helperClass.getTitle());
        holder.relativeLayout.setBackground(helperClass.getGradient());
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
    public interface OnNoteListener3{
        void onNoteClick3(int position);
    }
}
