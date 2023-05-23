package com.example.sensapp;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.PrivateKey;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MotionActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private TextView MotionTextView;
    private Spinner unitSpinner;

    private float mPerSec2ToftPerSec2 = 3.28084f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motion);

        //Data Saving button

        Button TempSaved = findViewById(R.id.motionsaved);
        TempSaved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MotionActivity.this, MotionDataActivity.class);
                startActivity(intent);
            }
        });







        Button saveDataButton = findViewById(R.id.motionsave);
        saveDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    File csvFile = new File(getExternalFilesDir(null), "motion_data.csv");
                    FileWriter writer = new FileWriter(csvFile, true);
                    BufferedWriter bw = new BufferedWriter(writer);

                    // Get the current date and time
                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy  |  HH:mm:ss");
                    String dateTime = dateFormat.format(calendar.getTime());

                    // Write the temperature data with date and time to the file
                    String data = String.format("%s,%s,%s,%s.%s", dateTime, " | ",  MotionTextView.getText(),"   ", unitSpinner.getSelectedItem());
                    bw.write(data);
                    bw.newLine();
                    bw.close();
                    writer.close();
                    Toast.makeText(MotionActivity.this, "Data saved successfully!", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });







        // Initialize textview and spinner
        MotionTextView = findViewById(R.id.MotionSensorValue);
        unitSpinner = findViewById(R.id.MotionUnits);

        // Set up spinner options
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.unit_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unitSpinner.setAdapter(adapter);

        // Set default selection to first option
        unitSpinner.setSelection(0);

        // Set up sensor
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }


    @Override
    protected void onResume() {
        super.onResume();
        // Register the accelerometer sensor listener when the app is resumed
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Unregister the accelerometer sensor listener when the app is paused
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // Get accelerometer values in m/s^2
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        // Convert to selected unit
        String unit = (String) unitSpinner.getSelectedItem();
        if (unit.equals(getString(R.string.unit_ft_per_sec2))) {
            x *= mPerSec2ToftPerSec2;
            y *= mPerSec2ToftPerSec2;
            z *= mPerSec2ToftPerSec2;
        }

        // Update UI
        String valuesText = String.format(Locale.getDefault(), "X: %.2f\nY: %.2f\nZ: %.2f", x, y, z);
        MotionTextView.setText(valuesText);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Not used in this implementation
    }
}
