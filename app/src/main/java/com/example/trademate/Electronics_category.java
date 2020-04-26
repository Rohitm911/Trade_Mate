package com.example.trademate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class Electronics_category extends AppCompatActivity {

    RecyclerView.Adapter adapter;
    RecyclerView electronicsRecycler;
    ArrayList<All_ItemsHelper> all_electronics = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electronics_category);

        //retrieve data from firebase
        Query q=FirebaseDatabase.getInstance().getReference("items").orderByChild("category").equalTo("electronics");

        q.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot ds : dataSnapshot.getChildren()) {
                        All_ItemsHelper h = ds.getValue(All_ItemsHelper.class);
                        all_electronics.add(h);
                    }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        electronicsRecycler=findViewById(R.id.electronics_recycler);
        electronicsRecycler.setHasFixedSize(true);
        electronicsRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new All_ItemsAdapter(all_electronics);
        electronicsRecycler.setAdapter(adapter);




    }
}
