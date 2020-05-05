package com.example.trademate;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class FeaturedAdapter extends RecyclerView.Adapter<FeaturedAdapter.FeaturedViewHolder> {

    ArrayList<FeaturedHelperClass> BestOffer;

    OnNoteListener1 mOnNoteListener;

    public FeaturedAdapter(ArrayList<FeaturedHelperClass> bestOffer,OnNoteListener1 mOnNoteListener) {
        BestOffer = bestOffer;
        this.mOnNoteListener=mOnNoteListener;

    }


    public static class FeaturedViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView image;
        TextView title, desc;
        OnNoteListener1 onNoteListener;

        public FeaturedViewHolder(@NonNull View itemView,OnNoteListener1 onNoteListener) {
            super(itemView);
            this.onNoteListener=onNoteListener;
            //Hooks
            image = itemView.findViewById(R.id.featured_image);
            title = itemView.findViewById(R.id.featured_title);
            desc = itemView.findViewById(R.id.featured_desc);
            itemView.setOnClickListener(this);

        }
        @Override
        public void onClick(View v) {
            onNoteListener.onNoteClick1(getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public FeaturedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.featured_card_design, parent, false);
        FeaturedViewHolder featuredViewHolder = new FeaturedViewHolder(view,mOnNoteListener);
        return featuredViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FeaturedViewHolder holder, int position) {
        FeaturedHelperClass featuredHelperClass = BestOffer.get(position);

       holder.image.setImageResource(featuredHelperClass.getImage());

        holder.title.setText(featuredHelperClass.getTitle());
        holder.desc.setText("Rs. "+featuredHelperClass.getDescription());
    }

    @Override
    public int getItemCount() {
        return BestOffer.size();
    }

    public interface OnNoteListener1{
        void onNoteClick1(int position);
    }
}
