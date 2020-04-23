package com.example.trademate;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageView logo;
    TextView appName, tagLine;
    Animation topAnim, bottomAnim;
    private static int SPLASH_SCREEN = 4000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logo = findViewById(R.id.logo);
        appName = findViewById(R.id.appName);
        tagLine = findViewById(R.id.tagLine);
        topAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bottom_animation);
        logo.setAnimation(topAnim);
        appName.setAnimation(bottomAnim);
        tagLine.setAnimation(bottomAnim);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this,login.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_SCREEN);
    }
}
