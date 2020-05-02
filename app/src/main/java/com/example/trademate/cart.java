package com.example.trademate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class cart extends AppCompatActivity {
    ArrayList<All_ItemsHelper> cart_items=new ArrayList<>();
    RecyclerView cartRecycler;
    RecyclerView.Adapter adapter;

    TextView totalp;
    TextView totalitems;
    Button clearcart;
    Button checkout;
    int total_price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);



        final DatabaseReference ref= FirebaseDatabase.getInstance().getReference("cart");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                cart_items.clear();
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    All_ItemsHelper h = ds.getValue(All_ItemsHelper.class);
                    cart_items.add(h);
                }
                total_price=0;
                for(All_ItemsHelper i: cart_items)
                {
                    total_price=total_price+Integer.parseInt(i.getPrice());
                }
                totalp=findViewById(R.id.total);
                totalp.setText("Cart total: "+total_price);
                totalitems=findViewById(R.id.itemcount);
                totalitems.setText("Total items: "+cart_items.size());
                cartRecycler=findViewById(R.id.cart_recycler);
                cartRecycler.setHasFixedSize(true);
                cartRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
                adapter = new Cart_adapter(cart_items);
                cartRecycler.setAdapter(adapter);


            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        clearcart=findViewById(R.id.clear_btn);
        clearcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref.removeValue();

            }
        });
        checkout=findViewById(R.id.checkout_btn);
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(cart.this, Payment.class);
                intent.putExtra("amt",""+total_price);

                startActivity(intent);
            }
        });



    }
}
