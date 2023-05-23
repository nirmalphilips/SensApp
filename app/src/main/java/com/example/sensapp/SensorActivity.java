package com.example.sensapp;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SensorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensors);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        ImageButton ButtonTemp = findViewById(R.id.ButtonTemp);
        ButtonTemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(SensorActivity.this, TempActivity.class);
                startActivity(intent);
            }
        });
        ImageButton ButtonMotion = findViewById(R.id.ButtonMotion);
        ButtonMotion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(SensorActivity.this, MotionActivity.class);
                startActivity(intent);
            }
        });


    }
}