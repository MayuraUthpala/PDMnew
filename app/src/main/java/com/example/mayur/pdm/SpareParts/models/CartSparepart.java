package com.example.mayur.pdm.SpareParts.models;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by LENOVO on 5/19/2018.
 */
@IgnoreExtraProperties
public class CartSparepart {
    public String KEY;
    public String invoiceno;
    public String name;
    public String total;

    public CartSparepart(String invoiceno, String name, String total) {
        this.invoiceno = invoiceno;
        this.name = name;
        this.total = total;
    }
    public CartSparepart(){

    }

    public CartSparepart setKey(String key){
        this.KEY = key;
        return this;
    }
}
