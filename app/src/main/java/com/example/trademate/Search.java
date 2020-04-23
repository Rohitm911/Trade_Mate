package com.example.trademate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.WindowManager;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Search extends AppCompatActivity {

    TextInputLayout seachText;
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    FirebaseUser firebaseUser;

    ArrayList<String> itemNameList;
    ArrayList<String> itemImageList;
    SearchAdapter searchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_search);

        seachText = findViewById(R.id.search_main);
        recyclerView = findViewById(R.id.search_recycler);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        itemImageList = new ArrayList<>();
        itemNameList = new ArrayList<>();

        seachText.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty()){
                    setAdapter(s.toString());
                }else{
                    itemImageList.clear();
                    itemNameList.clear();
                    recyclerView.removeAllViews();
                }
            }
        });

    }

    private void setAdapter(final String searchedString) {
        databaseReference.child("items").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                itemImageList.clear();
                itemNameList.clear();
                recyclerView.removeAllViews();

                int counter = 0;
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    String uid = snapshot.getKey();
                    String itemName = snapshot.child("name").getValue(String.class);
                    String itemImage = snapshot.child("image").getValue(String.class);

                    if (itemName.toLowerCase().contains(searchedString.toLowerCase())){
                        itemNameList.add(itemName);
                        itemImageList.add(itemImage);
                        counter++;
                    }

                    if (counter == 15) break;
                }

                searchAdapter = new SearchAdapter(Search.this, itemNameList,itemImageList);
                recyclerView.setAdapter(searchAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
