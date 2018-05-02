package com.example.mayur.pdm;

/**
 * Created by Dulip on 4/29/2018.
 */

public class Blog {

    private String Date;
    private String Next_Check;
    private String Replace;
    private String Service;

    public Blog(String Date, String Next_Check, String Replace,String Service)
    {
        this.Date=Date;
        this.Next_Check=Next_Check;
        this.Replace=Replace;
        this.Service=Service;
    }

    public String getDate()
    {
        return Date;
    }
    public void setDate(String Date)
    {
        this.Date = Date;
    }

    public String getNext_Check()
    {
        return Next_Check;
    }
    public void setNext_Check(String Next_Check)
    {
        this.Next_Check = Next_Check;
    }
    public String getReplace()
    {
        return Replace;
    }
    public void setReplace(String Replace)
    {
        this.Replace = Replace;
    }

    public String getService()
    {
        return Service;
    }
    public void setService(String Service)
    {
        this.Service = Service;
    }

    public Blog()
    {

    }
}
