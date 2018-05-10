package com.example.mayur.pdm;

/**
 * Created by HP on 19-Apr-18.
 */

public class RatingInfo {
    public String feedback;
    public Long rating;
    //timestamp, customer

    public RatingInfo(){
    }

    public RatingInfo(String feedback, Long rating) {
        this.feedback = feedback;
        this.rating = rating;
    }
}
