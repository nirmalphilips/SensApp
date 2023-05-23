package com.example.sensapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

public class PhoneInformation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phone_information);



        // Get the phone information, Make & Model
        String phoneInfo = "Device Model: " + Build.MODEL + "\n" + "Device Make: " + Build.MANUFACTURER;


        // Get the battery level
        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = registerReceiver(null, ifilter);
        int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);

        // Update the UI with the phone information and battery level
        TextView phoneInfoTextView = findViewById(R.id.phone_info_text_view);
        phoneInfoTextView.setText(phoneInfo);

        TextView batteryLevelTextView = findViewById(R.id.battery_level_text_view);
        batteryLevelTextView.setText("Battery Level: " + level + "%");
    }
}
