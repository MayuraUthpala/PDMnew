package com.example.mayur.pdm;

/**
 * Created by HP on 19-Apr-18.
 */

public class RatingInfo {
    private String feedback;
    private String UserId;
    private Long rating;
    private String Date;
    private String Model;
    private String VehicleNo;
    //timestamp, customer

    public RatingInfo(){
    }


    public RatingInfo(String feedback, String userId, Long rating, String date,String vehicleNo) {
        this.feedback = feedback;
        UserId = userId;
        this.rating = rating;
        Date = date;
        VehicleNo = vehicleNo;
    }

    public String getVehicleNo() {
        return VehicleNo;
    }

    public String getDate() {
        return Date;
    }

    public String getModel() {
        return Model;
    }

    public String getFeedback() {
        return feedback;
    }

    public String getUserId() {
        return UserId;
    }

    public Long getRating() {
        return rating;
    }
}
