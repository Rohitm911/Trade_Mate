package com.example.trademate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import java.util.ArrayList;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class Books_category extends AppCompatActivity implements All_ItemsAdapter.OnNoteListener{


    RecyclerView.Adapter adapter;
    RecyclerView booksRecycler;
    ArrayList<All_ItemsHelper> all_books = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_category);


        //retrieve data from firebase
        Query q=FirebaseDatabase.getInstance().getReference("items").orderByChild("category").equalTo("books");
        q.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    All_ItemsHelper h = ds.getValue(All_ItemsHelper.class);
                    all_books.add(h);
                }

                booksRecycler=findViewById(R.id.books_recycler);
                booksRecycler.setHasFixedSize(true);
                booksRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
                adapter = new All_ItemsAdapter(all_books,Books_category.this);
                booksRecycler.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });






    }

    @Override
    public void onNoteClick(int position) {
        All_ItemsHelper all_itemsHelper = all_books.get(position);
        Intent intent = new Intent(getApplicationContext(), SingleItemDisplay.class);
        intent.putExtra("title",all_itemsHelper.getName());
        intent.putExtra("image",all_itemsHelper.getImage());
        intent.putExtra("full_desc",all_itemsHelper.getFull_desc());
        intent.putExtra("price",all_itemsHelper.getPrice());
        intent.putExtra("cat",all_itemsHelper.getCategory());
        intent.putExtra("short_desc",all_itemsHelper.getShort_desc());
        startActivity(intent);


    }
}
