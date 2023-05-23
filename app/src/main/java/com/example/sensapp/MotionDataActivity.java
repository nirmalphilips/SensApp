package com.example.sensapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MotionDataActivity extends AppCompatActivity {
    private ListView dataListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motiondata);

        // Get a reference to the ListView
        dataListView = findViewById(R.id.motiondata);

        // Read the data from the CSV file and display it in the ListView
        try {
            File csvFile = new File(getExternalFilesDir(null), "motion_data.csv");
            FileReader reader = new FileReader(csvFile);
            BufferedReader br = new BufferedReader(reader);

            List<String> dataList = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                dataList.add(line);
            }

            br.close();
            reader.close();

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1, dataList);
            dataListView.setAdapter(adapter);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
