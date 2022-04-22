package com.creatures.mycateringadminpanel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        int splash_time = 3000; //This is 3 seconds

        ImageView imageView = findViewById(R.id.logo_image_view);
        Animation animation = new AlphaAnimation(0,1);
        animation.setDuration(2500);

        imageView.setAnimation(animation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(MainActivity.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        },splash_time);

    }
}