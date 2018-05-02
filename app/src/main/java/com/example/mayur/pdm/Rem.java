package com.example.mayur.pdm;

/**
 * Created by Dulip on 5/1/2018.
 */

public class Rem {
    private String Date;
    private String Service;

    public Rem(String Date,String Service)
    {
        this.Date=Date;
        this.Service=Service;
    }

    public String getDate()
    {
        return Date;
    }
    public void setDate(String Date)
    {
        this.Date=Date;
    }
    public String getService()
    {
        return Service;
    }
    public void setService(String Service)
    {
        this.Service=Service;
    }

    public void Rem()
    {

    }

}
