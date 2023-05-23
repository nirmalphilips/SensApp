package com.example.sensapp;




import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button UserGuide = findViewById(R.id.userguide);
        UserGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, UserGuide.class);
                startActivity(intent);

            }
        });
        Button PhoneInformation = findViewById(R.id.phoneinformation);
        PhoneInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PhoneInformation.class);
                startActivity(intent);
            }
        });

        Button Sensor = findViewById(R.id.sensor);
        Sensor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SensorActivity.class);
                startActivity(intent);

            }
        });


        Button Sensors = findViewById(R.id.availablesensors);
        Sensors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AvailableSensors.class);
                startActivity(intent);

            }
        });


    }
}