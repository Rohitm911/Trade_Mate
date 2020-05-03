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
    OnNoteListener mOnNoteListener;

    public All_ItemsAdapter(ArrayList<All_ItemsHelper> all_items,OnNoteListener mOnNoteListener) {
        this.all_items=all_items;
        this.mOnNoteListener=mOnNoteListener;
    }
    public static class AllItemsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imageView;
        TextView title, short_desc, price;
        OnNoteListener onNoteListener;

        public AllItemsViewHolder(@NonNull View itemView,OnNoteListener onNoteListener) {
            super(itemView);
            this.onNoteListener=onNoteListener;
            imageView = itemView.findViewById(R.id.electronics_image);
            title = itemView.findViewById(R.id.electronics_title);
            short_desc = itemView.findViewById(R.id.electronics_desc);
            price = itemView.findViewById(R.id.electronics_price);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onNoteListener.onNoteClick(getAdapterPosition());
        }
    }
    @NonNull
    @Override
    public All_ItemsAdapter.AllItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.allcat_card_design, parent, false);
        All_ItemsAdapter.AllItemsViewHolder allitemsViewHolder = new All_ItemsAdapter.AllItemsViewHolder(view,mOnNoteListener);
        return allitemsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull All_ItemsAdapter.AllItemsViewHolder holder, int position) {
        All_ItemsHelper helperClass = all_items.get(position);
        Picasso.get().load(helperClass.getImage()).into(holder.imageView);
        // holder.imageView.setImageResource(helperClass.getUrl());
        holder.title.setText(helperClass.getName());
        holder.short_desc.setText(helperClass.getShort_desc());
        holder.price.setText("Rs. "+helperClass.getPrice());
    }

    @Override
    public int getItemCount() {
        return all_items.size();
    }

    public interface OnNoteListener{
        void onNoteClick(int position);
    }

}
