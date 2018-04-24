package com.example.mayur.pdm;

/**
 * Created by HP on 19-Apr-18.
 */

public class RatingInfo {
    public String feedback;
    public Float rating;
    //timestamp, customer

    public RatingInfo(){

    }

    public RatingInfo(String feedback, Float rating) {
        this.feedback = feedback;
        this.rating = rating;
    }
}
