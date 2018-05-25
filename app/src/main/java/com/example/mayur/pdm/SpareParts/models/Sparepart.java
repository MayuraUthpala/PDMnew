package com.example.mayur.pdm.SpareParts.models;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by LENOVO on 4/30/2018.
 */
@IgnoreExtraProperties
public class Sparepart {

    public String KEY;
    public String manufacture;
    public String model;
    public String year;
    public String category;
    public String subCategory;
    public String sparePartName;
    public String sparePartUsage;
    public String sparePartPrice;
    public int sparePartQuantity;
    public String sparePartDescription;

    public Sparepart() {
    }

    public Sparepart(String manufacture, String model, String year, String category, String subCategory, String sparePartName, String sparePartUsage, String sparePartPrice, int sparePartQuantity, String sparePartDescription) {
        this.manufacture = manufacture;
        this.model = model;
        this.year = year;
        this.category = category;
        this.subCategory = subCategory;
        this.sparePartName = sparePartName;
        this.sparePartUsage = sparePartUsage;
        this.sparePartPrice = sparePartPrice;
        this.sparePartQuantity = sparePartQuantity;
        this.sparePartDescription = sparePartDescription;
    }

    public Sparepart setKey(String key){
        this.KEY = key;
        return this;
    }


}
