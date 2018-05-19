package com.example.mayur.pdm;

/**
 * Created by acer on 5/1/2018.
 */

public class retbookings {
    private String UserId;
    private String ServiceType;
    private String ServiceCharge;
    private String Date;
    private String TimeSlot;
    private String VehicleNo;
    private String Model;

    public retbookings(String userId, String serviceType, String serviceCharge, String date, String timeSlot, String vehicleNo, String model) {
        this.UserId = userId;
        this.ServiceType = serviceType;
        this.ServiceCharge = serviceCharge;
        this.Date = date;
        this.TimeSlot = timeSlot;
        this.VehicleNo = vehicleNo;
        this.Model = model;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        this.UserId = userId;
    }

    public String getServiceType() {
        return ServiceType;
    }

    public void setServiceType(String serviceType) {
        this.ServiceType = serviceType;
    }

    public String getServiceCharge() {
        return ServiceCharge;
    }

    public void setServiceCharge(String serviceCharge) {
        this.ServiceCharge = serviceCharge;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        this.Date = date;
    }

    public String getTimeSlot() {
        return TimeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.TimeSlot = timeSlot;
    }

    public String getVehicleNo() {
        return VehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.VehicleNo = vehicleNo;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        this.Model = model;
    }

    public retbookings(){

    }
}
