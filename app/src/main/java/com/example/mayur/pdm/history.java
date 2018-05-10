package com.example.mayur.pdm;

public class history {
    private String Date;
    private String Model;
    private String SecrviceType;
    private String VehicleNo;

    public history(String date, String model, String secrviceType, String vehicleNo) {
        Date = date;
        Model = model;
        SecrviceType = secrviceType;
        VehicleNo = vehicleNo;
    }

    public history(){
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public String getSecrviceType() {
        return SecrviceType;
    }

    public void setSecrviceType(String secrviceType) {
        SecrviceType = secrviceType;
    }

    public String getVehicleNo() {
        return VehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        VehicleNo = vehicleNo;
    }
}
