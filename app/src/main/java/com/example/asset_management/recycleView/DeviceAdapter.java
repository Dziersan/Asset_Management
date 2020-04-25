package com.example.asset_management.recycleView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asset_management.R;

import java.util.ArrayList;

public class DeviceAdapter extends RecyclerView.Adapter<DeviceAdapter.ViewHolder> {

    private ArrayList<Device> devices;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item,parent,false);
        return new ViewHolder(v);
    }
public DeviceAdapter(ArrayList<Device>devices){
        this.devices = devices;
}

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Device device = devices.get(position);

        holder.inventoryNumber.setText(device.getInventoryNumber());
        holder.deviceCategorie.setText(device.getDeviceCategorie());
        holder.manufacturer.setText(device.getManufacturer());
        holder.status.setText(device.getStatus());
        holder.model.setText(device.getModel());
    }

//    @Override
//    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//    }

    @Override
    public int getItemCount() {
        if (devices != null) {
            return devices.size();
        } else {
            return 0;
        }
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        public final TextView inventoryNumber;
        public final TextView manufacturer;
        public final TextView model;
        public final TextView status;
        public final TextView deviceCategorie;
        public final ImageView image;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            inventoryNumber = view.findViewById(R.id.inventoryNumber);
            manufacturer = view.findViewById(R.id.manufacturer);
            model = view.findViewById(R.id.model);
            status = view.findViewById(R.id.status);
            deviceCategorie = view.findViewById(R.id.deviceCategorie);
            image = view.findViewById(R.id.image);
        }
    }
}
