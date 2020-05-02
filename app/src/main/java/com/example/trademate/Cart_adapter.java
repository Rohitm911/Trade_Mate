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


public class Cart_adapter extends RecyclerView.Adapter<Cart_adapter.CartViewHolder> {
    ArrayList<All_ItemsHelper> cart_items;
    public Cart_adapter(ArrayList<All_ItemsHelper> cart_items) {
        this.cart_items=cart_items;

    }
    public static class CartViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView title, price;


        public CartViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.cart_image);
            title = itemView.findViewById(R.id.cart_title);
            price = itemView.findViewById(R.id.cart_price);

        }


    }
    @NonNull
    @Override
    public Cart_adapter.CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cartitem_design, parent, false);
        Cart_adapter.CartViewHolder cartViewHolder = new Cart_adapter.CartViewHolder(view);
        return cartViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Cart_adapter.CartViewHolder holder, int position) {
        All_ItemsHelper helperClass = cart_items.get(position);
        Picasso.get().load(helperClass.getImage()).into(holder.imageView);
        // holder.imageView.setImageResource(helperClass.getUrl());
        holder.title.setText(helperClass.getName());
        holder.price.setText("Rs. "+helperClass.getPrice());
    }

    @Override
    public int getItemCount() {
        return cart_items.size();
    }

}
