package com.example.mayur.pdm;

public class history {
    private String Date;
    private String Model;
    private String SecrviceType;
    private String VehicleNo;
    private Long rating;

    public history(String date, String model, String secrviceType, String vehicleNo, Long rating) {
        Date = date;
        Model = model;
        SecrviceType = secrviceType;
        VehicleNo = vehicleNo;
        this.rating = rating;
    }

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
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
