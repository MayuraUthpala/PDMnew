package com.example.mayur.pdm.SpareParts.utils;

import com.example.mayur.pdm.SpareParts.models.CartSparepart;
import com.example.mayur.pdm.SpareParts.models.Sparepart;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by LENOVO on 5/1/2018.
 */

public class DBHelper {

    FirebaseDatabase database;
    DatabaseReference databaseReference;

    public final String LIST = "sparepartlist";
    public final String STRUCTURE = "sparepartstructure";
    public final String CARTORDER = "sparepartorders";

    public DBHelper(){
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference().child("Spareparts");
    }

    public DatabaseReference getRootDatabaseReference(){
        return databaseReference;
    }

    public DatabaseReference getSparepartDatabaseReference(){
        return databaseReference.child(LIST);

    }public DatabaseReference getSparepartDatabaseReference(String path){
        return databaseReference.child(LIST).child(path);
    }

    public DatabaseReference getSparepartStructureDatabaseReference(){
        return databaseReference.child(STRUCTURE);
    }

    public DatabaseReference getSparepartOrdersDatabaseReference(){
        return databaseReference.child(CARTORDER);
    }

    public DatabaseReference getSparepartStructureDatabaseReference(String path){
        return databaseReference.child(STRUCTURE).child(path);
    }

    public DatabaseReference getDatabaseReference(String path){
        return databaseReference.child(path);
    }

    public String pushObject(String path, Object object){
        String key = databaseReference.child(path).push().getKey();
        databaseReference.child(path).child(key).setValue(object);
        return key;
    }

    public String pushSparepart(Sparepart object){
        String key = databaseReference.child(LIST).push().getKey();
        databaseReference.child(LIST).child(key).setValue(object);
        databaseReference.child(STRUCTURE).child(object.manufacture)
                .child(object.model).child(object.year)
                .child(object.category).push().setValue(object.subCategory);
        return key;
    }

    public void updateObject(String path, Object object){
        databaseReference.child(path).setValue(object);
    }

    public void updateSparepart(String key, Object object){
        databaseReference.child(LIST).child(key).setValue(object);
    }

    public String pushSparepartOrder(CartSparepart object){
        String key = databaseReference.child(CARTORDER).push().getKey();
        databaseReference.child(CARTORDER).child(key).setValue(object);
        return key;
    }
}
