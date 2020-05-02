package com.example.trademate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.os.Handler;
import android.widget.TextView;


public class Payment extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        Intent intent = this.getIntent();
        String amt=intent.getStringExtra("amt");
        tv=findViewById(R.id.amt_paid);
        tv.setText("Amount to be paid: Rs. "+amt);

        Button loadbtn = findViewById(R.id.payment_btn);
        final loadingDialog l = new loadingDialog(Payment.this);
        loadbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                l.startLoad();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                                        public void run() {
                                            l.endload();
                                            Intent i = new Intent(getApplicationContext(), thank.class);
                                            startActivity(i);
                                        }
                                    }, 5000
                );

            }
        });
    }
}
