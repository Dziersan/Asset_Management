package com.example.asset_management.recycleView;

import android.content.Context;
import android.os.Bundle;

import com.example.asset_management.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class recycleActivity extends AppCompatActivity {
    private RecyclerView devices;
    private RecyclerView.Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        ArrayList<Device> devices = initDevices();

        this.devices = findViewById(R.id.devices);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        this.devices.setLayoutManager(mLayoutManager);

        adapter = new DeviceAdapter(devices);
        this.devices.setAdapter(adapter);

//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

    }

    private ArrayList<Device> initDevices(){
        ArrayList<Device> list = new ArrayList<>();

        list.add(new Device("1","Makita", "Winkelschleifer","Z125", "Reserviert"));
        list.add(new Device("2","Makita", "Winkelschleifer","Z125", "Verfügbar"));
        list.add(new Device("3","Makita", "Winkelschleifer","Z350", "Außer Betrieb"));
        list.add(new Device("4","Makita", "Winkelschleifer","Z125", "Reserviert"));
        list.add(new Device("5","Makita", "Winkelschleifer","Z350", "Reserviert"));
        list.add(new Device("6","Makita", "Winkelschleifer","Z350", "Verfügbar"));
        list.add(new Device("7","Makita", "Winkelschleifer","Z125", "Verfügbar"));
        list.add(new Device("8","Makita", "Winkelschleifer","Z350", "Reserviert"));
        list.add(new Device("9","Makita", "Winkelschleifer","Z125", "Verfügbar"));
        return list;
    }

}
