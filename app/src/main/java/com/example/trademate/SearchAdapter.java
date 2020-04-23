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

    public SearchAdapter(Context context, ArrayList<String> itemNameList, ArrayList<String> itemImageList) {
        this.context = context;
        this.itemNameList = itemNameList;
        this.itemImageList = itemImageList;
    }

    public static class SearchViewHolder extends RecyclerView.ViewHolder{
        ImageView itemImage;
        TextView itemName;

        public SearchViewHolder(@NotNull View itemView){

            super(itemView);
            itemImage = itemView.findViewById(R.id.itemImage);
            itemName = itemView.findViewById(R.id.item_name);
        }

    }


    @NonNull
    @Override
    public SearchAdapter.SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_list_items,parent,false);
        return new SearchAdapter.SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.SearchViewHolder holder, int position) {
        holder.itemName.setText(itemNameList.get(position));
        Glide.with(context).asBitmap().load(itemImageList.get(position)).placeholder(R.mipmap.ic_launcher_round).into(holder.itemImage);

        holder.itemName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Item Clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemNameList.size();
    }
}
