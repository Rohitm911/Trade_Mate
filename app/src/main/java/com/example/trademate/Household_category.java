package com.example.trademate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;

import android.view.View;

import java.util.ArrayList;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Household_category extends AppCompatActivity {
    RecyclerView.Adapter adapter;
    RecyclerView householdRecycler;
    ArrayList<All_ItemsHelper> all_household = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_household_category);


        //retrieve data from firebase
        Query q=FirebaseDatabase.getInstance().getReference("items").orderByChild("category").equalTo("household");

        q.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    All_ItemsHelper h = ds.getValue(All_ItemsHelper.class);
                    all_household.add(h);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        householdRecycler=findViewById(R.id.household_recycler);
        householdRecycler.setHasFixedSize(true);
        householdRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new All_ItemsAdapter(all_household);
        householdRecycler.setAdapter(adapter);


    }
}
