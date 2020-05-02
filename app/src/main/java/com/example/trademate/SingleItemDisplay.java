package com.example.trademate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class SingleItemDisplay extends AppCompatActivity {
    TextView titlev,pricev,long_descv;
    ImageView imagev;
    Button add_cart;
    int added=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_item_display);
        Intent intent = this.getIntent();
        final String title=intent.getStringExtra("title");
        final String image=intent.getStringExtra("image");
        final String full_desc=intent.getStringExtra("full_desc");
        final String price = intent.getStringExtra("price");
        final String short_desc=intent.getStringExtra("short_desc");
        final String category=intent.getStringExtra("cat") ;
        titlev=findViewById(R.id.tv);
        pricev=findViewById(R.id.p);
        long_descv=findViewById(R.id.descv);
        imagev=findViewById(R.id.iv);
        titlev.setText(title);
        pricev.setText("Rs. "+price);
        long_descv.setText(full_desc);
        Picasso.get().load(image).into(imagev);

        add_cart=(Button)findViewById(R.id.atc);
        add_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //add item to cart.
                if(added==0)
                {
                    All_ItemsHelper h=new All_ItemsHelper(image,title,short_desc,full_desc,price,category);
                    FirebaseDatabase.getInstance().getReference().child("cart").push().setValue(h);
                    Toast.makeText(SingleItemDisplay.this, "Items added to cart.", Toast.LENGTH_SHORT).show();
                    added=1;
                }
                else
                    Toast.makeText(SingleItemDisplay.this, "Items already added.", Toast.LENGTH_SHORT).show();


            }
        });



    }
}
