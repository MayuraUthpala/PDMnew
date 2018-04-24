package com.example.mayur.pdm;

/**
 * Created by HP on 18-Apr-18.
 */

public class ProfileInformation {
    public String fname;
    public String lname;
    public String address;
    public Integer phone;

    public ProfileInformation(){

    }

    public ProfileInformation(String fname, String lname, String address, Integer phone) {
        this.fname = fname;
        this.lname = lname;
        this.address = address;
        this.phone = phone;
    }
}
