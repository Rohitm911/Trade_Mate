package com.example.trademate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserProfile extends AppCompatActivity {

    TextInputLayout fullName, email, phoneNo, password;
    TextView fullNameLabel, usernameLabel;
    DatabaseReference reference;
    String user_username, user_name, user_email, user_phoneNo, user_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        reference = FirebaseDatabase.getInstance().getReference("user");
        fullName = findViewById(R.id.full_name_profile);
        email = findViewById(R.id.email_profile);
        phoneNo = findViewById(R.id.phone_no_profile);
        password = findViewById(R.id.password_profile);
        fullNameLabel = findViewById(R.id.fullname_field);
        usernameLabel = findViewById(R.id.username_field);

        showAllUserData();
    }

    private void showAllUserData() {

        Intent intent = getIntent();
        user_username = intent.getStringExtra("username");
        user_name = intent.getStringExtra("name");
        user_email = intent.getStringExtra("email");
        user_phoneNo = intent.getStringExtra("phoneNo");
        user_password = intent.getStringExtra("password");

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

    public void goToDashBoard(View view){
        Intent intent = new Intent(getApplicationContext(), UserDashboard.class);
        startActivity(intent);
        finish();
    }
}
