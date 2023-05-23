package com.example.sensapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3350; // Time in milliseconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView imageView = findViewById(R.id.imageView);
        TextView titleTextView = findViewById(R.id.titleTextView);
        TextView versionTextView = findViewById(R.id.versionTextView);
        TextView releaseDateTextView = findViewById(R.id.releaseDateTextView);

        titleTextView.setText(getString(R.string.app_name));
        versionTextView.setText(getString(R.string.version_number));
        releaseDateTextView.setText(getString(R.string.release_date));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
