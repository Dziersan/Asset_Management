package com.example.asset_management.recycleView;
/**
 * Device
 * <p>
 *     Version 1.0
 * </p>
 * 11.05.2020
 */
public class Device {
    private String inventoryNumber;
    private String manufacturer;
    private String model;
    private String status;

    private String deviceCategorie;

    public Device(){
    }

    public Device(String inventoryNumber, String manufacturer,String deviceCategorie, String model,  String status) {
        this.inventoryNumber = inventoryNumber;
        this.manufacturer = manufacturer;
        this.deviceCategorie = deviceCategorie;
        this.model = model;
        this.status = status;
    }

    public String getInventoryNumber() {
        return inventoryNumber;
    }

    public void setInventoryNumber(String inventoryNumber) {
        this.inventoryNumber = inventoryNumber;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getDeviceCategorie() {
        return deviceCategorie;
    }

    public void setDeviceCategorie(String deviceCategorie) {
        this.deviceCategorie = deviceCategorie;
    }

    @Override
    public String toString() {
        return "Device{" +
                "inventoryNumber='" + inventoryNumber + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", status='" + status + '\'' +
                ", deviceCategorie='" + deviceCategorie + '\'' +
                '}';
    }
}
