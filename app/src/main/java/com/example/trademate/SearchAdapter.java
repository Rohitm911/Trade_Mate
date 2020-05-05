package com.example.trademate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.annotations.NotNull;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder>{

    Context context;
    ArrayList<String> itemNameList;
    ArrayList<String> itemImageList;
    ArrayList<All_ItemsHelper> searcheditems;
    OnNoteListener4 mOnNoteListener;


    public SearchAdapter(Context context, ArrayList<String> itemNameList, ArrayList<String> itemImageList,ArrayList<All_ItemsHelper> searcheditems,OnNoteListener4 mOnNoteListener) {
        this.context = context;
        this.itemNameList = itemNameList;
        this.itemImageList = itemImageList;
        this.searcheditems=searcheditems;
        this.mOnNoteListener=mOnNoteListener;

    }

    public static class SearchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView itemImage;
        TextView itemName;
        OnNoteListener4 onNoteListener;

        public SearchViewHolder(@NotNull View itemView,OnNoteListener4 onNoteListener){
            super(itemView);
            this.onNoteListener=onNoteListener;

            itemImage = itemView.findViewById(R.id.itemImage);
            itemName = itemView.findViewById(R.id.item_name);
            itemView.setOnClickListener(this);

        }
        @Override
        public void onClick(View v) {
            onNoteListener.onNoteClick4(getAdapterPosition());
        }

    }


    @NonNull
    @Override
    public SearchAdapter.SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_list_items,parent,false);
        return new SearchAdapter.SearchViewHolder(view,mOnNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.SearchViewHolder holder, int position) {
        holder.itemName.setText(itemNameList.get(position));
        Glide.with(context).asBitmap().load(itemImageList.get(position)).placeholder(R.mipmap.ic_launcher_round).into(holder.itemImage);

//        holder.itemName.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context, "Item Clicked", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return itemNameList.size();
    }

    public interface OnNoteListener4{
        void onNoteClick4(int position);
    }
}
