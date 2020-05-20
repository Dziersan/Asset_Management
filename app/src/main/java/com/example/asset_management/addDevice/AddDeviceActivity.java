package com.example.asset_management.addDevice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.toolbox.HttpResponse;
import com.example.asset_management.R;
import com.example.asset_management.recycleView.Device;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * AddDeviceActivity
 * <p>
 *     Version 1.0
 * </p>
 * 11.05.2020
 */

public class AddDeviceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_device);

        Button btnSave = findViewById(R.id.button4);
        btnSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                try {
                    addDevice();
                } catch (IOException e) {
                    Context context = getApplicationContext();
                    int duration = Toast.LENGTH_SHORT;
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });
    }

    /**
     * Takes the input from the AddDeviceActivity fields, creates an Device object and saves it
     * in a json file on the internal storage
     * @throws IOException
     */
    public void addDevice() throws IOException {

        EditText editInventoryNumber = (EditText) findViewById(R.id.editInventoryNumber);
        EditText editSerialNumber = (EditText) findViewById(R.id.editSerialNumber);
        EditText editModel = (EditText) findViewById(R.id.editModel);
        EditText editManufacturer = (EditText) findViewById(R.id.editManufacturer);
        EditText editCategorie = (EditText) findViewById(R.id.editCategorie);
        EditText editStatus = (EditText) findViewById(R.id.editStatus);

        String stringInventoryNumber = editInventoryNumber.getText().toString();
        String stringSerialNumber = editSerialNumber.getText().toString();
        String stringEditModel = editModel.getText().toString();
        String stringManufacturer = editManufacturer.getText().toString();
        String stringCategorie = editCategorie.getText().toString();
        String stringStatus = editStatus.getText().toString();

        Device device = new Device(stringInventoryNumber, stringEditModel, stringManufacturer,
                stringCategorie, stringStatus);

        Gson gson = new Gson();
        String json = gson.toJson(device);

        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(getApplicationContext()
                    .openFileOutput("device.json", Context.MODE_PRIVATE));
            outputStreamWriter.write(json);
            outputStreamWriter.close();
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "File Error", Toast.LENGTH_SHORT).show();
        }

        Toast.makeText(getApplicationContext(), "Gerät wurde hinzugefügt", Toast.LENGTH_SHORT)
                .show();
    }
}