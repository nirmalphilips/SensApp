package com.example.sensapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TempActivity extends AppCompatActivity implements SensorEventListener{
    private SensorManager sensorManager;
    private Sensor temperatureSensor;
    private TextView temperatureTextView;
    private Spinner UnitsSpinner;
    private String[] Units = {"Celsius", "Fahrenheit"};
    private String selectedUnit = "Celsius";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);



        Button TempSaved = findViewById(R.id.tempsaved);
        TempSaved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(TempActivity.this, TempDataActivity.class);
                startActivity(intent);
            }
        });






        Button saveDataButton = findViewById(R.id.tempsave);
        saveDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    File csvFile = new File(getExternalFilesDir(null), "data.csv");
                    FileWriter writer = new FileWriter(csvFile, true);
                    BufferedWriter bw = new BufferedWriter(writer);

                    // Get the current date and time
                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy  |  HH:mm:ss");
                    String dateTime = dateFormat.format(calendar.getTime());

                    // Write the temperature data with date and time to the file
                    String data = String.format("%s,%s,%s", dateTime, " | ",  temperatureTextView.getText());
                    bw.write(data);
                    bw.newLine();
                    bw.close();
                    writer.close();
                    Toast.makeText(TempActivity.this, "Data saved successfully!", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });





        // Initialize the TextView and Spinner
        temperatureTextView = findViewById(R.id.TempSensorValue);
        UnitsSpinner = findViewById(R.id.tempunits);

        // Create an ArrayAdapter to populate the Spinner with temperature units
        ArrayAdapter<String> unitAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Units);
        unitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        UnitsSpinner.setAdapter(unitAdapter);

        // Set the OnItemSelectedListener for the Spinner to update the selected unit variable
        UnitsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedUnit = Units[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        // Get the SensorManager and temperature sensor
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        temperatureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Register the temperature sensor listener when the app is resumed
        sensorManager.registerListener(this, temperatureSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Unregister the temperature sensor listener when the app is paused
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        // Get the temperature value from the sensor and update the TextView with the converted value
        float temperature = sensorEvent.values[0];
        temperatureTextView.setText(convertTemperature(temperature, selectedUnit));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    // A function to convert the temperature to the selected unit
    private String convertTemperature(float temperature, String unit) {
        if (unit.equals("Celsius")) {
            return String.format("%.2f°C", temperature);
        } else {
            float temperatureFahrenheit = (temperature * 9/5) + 32;
            return String.format("%.2f°F", temperatureFahrenheit);
        }



    }
}
