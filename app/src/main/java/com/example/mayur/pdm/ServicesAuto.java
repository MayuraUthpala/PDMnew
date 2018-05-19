package com.example.mayur.pdm;

/**
 * Created by acer on 5/15/2018.
 */

public class ServicesAuto {

    private String ServiceName;
    private String ServicePrice;

    public ServicesAuto() {
    }

    public ServicesAuto(String ServiceName, String ServicePrice){
        this.ServiceName=ServiceName;
        this.ServicePrice=ServicePrice;
    }

    public String getServiceName() {
        return ServiceName;
    }

    public void setServiceName(String serviceName) {
        this.ServiceName = serviceName;
    }

    public String getServicePrice() {
        return ServicePrice;
    }

    public void setServicePrice(String servicePrice) {
        this.ServicePrice = servicePrice;
    }
}
