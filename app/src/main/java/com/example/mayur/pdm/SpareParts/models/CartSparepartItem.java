package com.example.mayur.pdm.SpareParts.models;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by LENOVO on 5/19/2018.
 */

@IgnoreExtraProperties
public class CartSparepartItem {
    public String name;
    public String qty;
    public String price;
    public String subtotal;

    public CartSparepartItem(String name, String qty, String price, String subtotal) {
        this.name = name;
        this.qty = qty;
        this.price = price;
        this.subtotal = subtotal;
    }
}
