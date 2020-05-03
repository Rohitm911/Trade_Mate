package com.example.trademate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FruitsVeg_category extends AppCompatActivity implements All_ItemsAdapter.OnNoteListener{
    RecyclerView.Adapter adapter;
    RecyclerView fruitsvegRecycler;
    ArrayList<All_ItemsHelper> all_fv = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruits_veg_category);

        Query q= FirebaseDatabase.getInstance().getReference("items").orderByChild("category").equalTo("fruits_veg");

        q.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                all_fv.clear();
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    All_ItemsHelper h = ds.getValue(All_ItemsHelper.class);
                    all_fv.add(h);
                }

                fruitsvegRecycler=findViewById(R.id.fruitsveg_recycler);
                fruitsvegRecycler.setHasFixedSize(true);
                fruitsvegRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext() ,LinearLayoutManager.VERTICAL, false));
                adapter = new All_ItemsAdapter(all_fv,FruitsVeg_category.this);
                fruitsvegRecycler.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onNoteClick(int position) {
        All_ItemsHelper all_itemsHelper = all_fv.get(position);
        Intent intent = new Intent(getApplicationContext(), SingleItemDisplay.class);
        intent.putExtra("title",all_itemsHelper.getName());
        intent.putExtra("image",all_itemsHelper.getImage());
        intent.putExtra("full_desc",all_itemsHelper.getFull_desc());
        intent.putExtra("price",all_itemsHelper.getPrice());
        intent.putExtra("cat",all_itemsHelper.getCategory());
        intent.putExtra("short_desc",all_itemsHelper.getShort_desc());
        startActivity(intent);
        //go to new activity.

    }
}
