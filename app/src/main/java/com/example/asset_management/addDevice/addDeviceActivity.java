package com.example.asset_management.addDevice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asset_management.R;
import com.example.asset_management.recycleView.Device;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class addDeviceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_device);


        Button btnSave = findViewById(R.id.button4);
        btnSave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                try {
                    addDevice();
                } catch (IOException e) {
                    Context context = getApplicationContext();
                    int duration = Toast.LENGTH_SHORT;
                    Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

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

        Device device = new Device (stringInventoryNumber,stringEditModel,stringManufacturer,stringCategorie,stringStatus);

        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(getApplicationContext().openFileOutput("configgg.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(device.toString());
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Toast.makeText(getApplicationContext(),"File Error",Toast.LENGTH_SHORT).show();
        }

        Gson gson = new Gson();
        gson.toJson(device);
//        try {
//            FileWriter fileWriter = new FileWriter("device.json");
//            gson.toJson(device, fileWriter);
//            fileWriter.flush();
//            fileWriter.close();
//        }catch (IOException e){
//            Toast.makeText(getApplicationContext(),"JSON Error",Toast.LENGTH_SHORT).show();
//        }

//        Writer writer = new FileWriter("newDevice.json");
//        Gson gson = new GsonBuilder().create();
//        gson.toJson(device, writer);
//        writer.flush(); //flush data to file   <---
//        writer.close(); //close write          <---
//        FileWriter fw = new FileWriter("ausgabe1.txt");
//        BufferedWriter bw = new BufferedWriter(fw);
//
//        bw.write("test test test");
//        bw.write("tset tset tset");
//
//        bw.close();
        Toast.makeText(getApplicationContext(),device.toString(),Toast.LENGTH_SHORT).show();
    }
}
