package com.example.mayur.pdm;

/**
 * Created by acer on 5/1/2018.
 */

public class retbookings {

    private String Date;
    private String Model;
    private String ServiceType;
    private String ServiceCharge;
    private String TimeSlot;
    private String UserId;
    private String VehicleNo;

    public retbookings(String date, String model, String serviceType, String serviceCharge, String timeSlot, String userId, String vehicleNo) {
        Date = date;
        Model = model;
        ServiceType = serviceType;
        ServiceCharge = serviceCharge;
        TimeSlot = timeSlot;
        UserId = userId;
        VehicleNo = vehicleNo;
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

    public String getServiceType() {
        return ServiceType;
    }

    public void setServiceType(String serviceType) {
        ServiceType = serviceType;
    }

    public String getServiceCharge() {
        return ServiceCharge;
    }

    public void setServiceCharge(String serviceCharge) {
        ServiceCharge = serviceCharge;
    }

    public String getTimeSlot() {
        return TimeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        TimeSlot = timeSlot;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getVehicleNo() {
        return VehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        VehicleNo = vehicleNo;
    }
    /*    public retbookings(String userId, String serviceType, String serviceCharge, String date, String timeSlot, String vehicleNo, String model) {
        this.UserId = userId;
        this.ServiceType = serviceType;
        this.ServiceCharge = serviceCharge;
        this.Date = date;
        this.TimeSlot = timeSlot;
        this.VehicleNo = vehicleNo;
        this.Model = model;
    }*/

/*    public String getUserId() {
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
    }*/

    public retbookings(){

    }
}
