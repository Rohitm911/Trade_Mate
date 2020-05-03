package com.example.trademate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class UserProfile extends AppCompatActivity {

    TextInputLayout fullName, email, phoneNo, password;
    TextView fullNameLabel, usernameLabel;
    DatabaseReference reference;
    String user_username, user_name, user_email, user_phoneNo, user_password;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_profile);
        reference = FirebaseDatabase.getInstance().getReference("user");
        fullName = findViewById(R.id.full_name_profile);
        email = findViewById(R.id.email_profile);
        phoneNo = findViewById(R.id.phone_no_profile);
        password = findViewById(R.id.password_profile);
        fullNameLabel = findViewById(R.id.fullname_field);
        usernameLabel = findViewById(R.id.username_field);
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        showAllUserData();
    }

    private void showAllUserData() {

        user_username = sharedPreferences.getString("usernameKey", "");
        user_name = sharedPreferences.getString("nameKey", "");
        user_email = sharedPreferences.getString("emailKey", "");
        user_phoneNo = sharedPreferences.getString("phoneNoKey", "");
        user_password = sharedPreferences.getString("passwordKey", "");

//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("user");
//        Query checkUser = reference.orderByChild("username").equalTo(user_username);
//        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if (dataSnapshot.exists()){
//
//                    user_name = dataSnapshot.child(user_username).child("name").getValue(String.class);
//                    user_email = dataSnapshot.child(user_username).child("email").getValue(String.class);
//                    user_phoneNo = dataSnapshot.child(user_username).child("phoneNo").getValue(String.class);
//                    user_password = dataSnapshot.child(user_username).child("password").getValue(String.class);
//
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

        //Log.i("info3",user_name);

        fullNameLabel.setText(user_name);
        usernameLabel.setText(user_username);
        fullName.getEditText().setText(user_name);
        email.getEditText().setText(user_email);
        phoneNo.getEditText().setText(user_phoneNo);
        password.getEditText().setText(user_password);
    }

    private boolean checkChange() {
        Boolean a = false, b = false, c = false, d = false;
        if (!user_name.equals(fullName.getEditText().getText().toString())) {
            a = true;
        }
        if (!user_password.equals(password.getEditText().getText().toString())) {
            b = true;
        }
        if (!user_email.equals(email.getEditText().getText().toString())) {
            c = true;
        }
        if (!user_phoneNo.equals(phoneNo.getEditText().getText().toString())) {
            d = true;
        }
        if (a || b || c || d) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isChangedMade() {
        if (checkChange()) {
            reference.child(user_username).child("name").setValue(fullName.getEditText().getText().toString());
            user_name = fullName.getEditText().getText().toString();

            reference.child(user_username).child("password").setValue(password.getEditText().getText().toString());
            user_password = password.getEditText().getText().toString();

            reference.child(user_username).child("email").setValue(email.getEditText().getText().toString());
            user_email = email.getEditText().getText().toString();

            reference.child(user_username).child("phoneNo").setValue(phoneNo.getEditText().getText().toString());
            user_phoneNo = phoneNo.getEditText().getText().toString();
            return true;
        } else {
            return false;
        }
    }


    public void update(View view) {

        if (isChangedMade()) {
            Toast.makeText(this, "Data has been updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Data is same and can not be updated", Toast.LENGTH_SHORT).show();
        }
    }

    public void goToDashBoard(View view) {
        Intent intent = new Intent(getApplicationContext(), UserDashboard.class);
        startActivity(intent);
        finish();
    }
}
