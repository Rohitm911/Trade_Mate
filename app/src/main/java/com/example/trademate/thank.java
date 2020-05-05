package com.example.trademate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class thank extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thank);
        Button b=findViewById(R.id.go_to_home);

        sharedPreferences= getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        final String uname=sharedPreferences.getString("usernameKey","xyz");

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference ref1= FirebaseDatabase.getInstance().getReference("user");
                ref1.child(uname).child("cart").removeValue();
                startActivity(new Intent(getApplicationContext(), UserDashboard.class));
                finish();
            }
        });

    }
}
