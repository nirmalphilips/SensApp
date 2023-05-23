package com.example.sensapp;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class AvailableSensors extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_availablesensors);

        Toast.makeText(getApplicationContext(), "Available Sensors On Your Device", Toast.LENGTH_SHORT).show();

        ListView sensorListView = (ListView) findViewById(R.id.sensorslist);
        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);


       // Get a list of all available sensors on the device
        List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);

        // Create a new list to store the sensor names
        List<String> sensorNames = new ArrayList<>();


       // Loop through each sensor in the list and add its name to the list
        for (Sensor sensor : sensorList) {
            sensorNames.add(sensor.getName());
        }

        // Create a new ArrayAdapter to display the list of sensor names in the ListView and sets the ArrayAdapter as the adapter for the ListView
        ArrayAdapter<String> sensorListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, sensorNames);
        sensorListView.setAdapter(sensorListAdapter);


    }
}
