package com.example.mayur.pdm.SpareParts.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mayur.pdm.R;
import com.example.mayur.pdm.SpareParts.models.Sparepart;
import com.example.mayur.pdm.SpareParts.utils.DBHelper;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SPAddActivity extends AppCompatActivity {

    DBHelper dbHelper;
    ValueEventListener valueEventListener;
    Query query;
    ArrayList<String> manufacturerArray;
    String manufacturer;

    ArrayList<String> modelArray;
    String model;
    ArrayList<String> yearArray;
    String year;
    ArrayList<String> categoryArray;
    String category;
    ArrayList<String> subcategoryArray;
    String subcategory;

    final String TAG = "Spareparts";

    public final static int MODE_VIEW = 0;
    public final static int MODE_ADD = 1;
    public final static int MODE_UPDATE = 2;
    public final static int MODE_DELETE = 3;

    public final static String REF_SPAREPART = "sparepart";

    EditText txtName;
    AutoCompleteTextView txtManufacturer;
    AutoCompleteTextView txtModel;
    AutoCompleteTextView txtYear;
    AutoCompleteTextView txtCategory;
    AutoCompleteTextView txtSubCategory;
    EditText txtDescription;
    EditText txtQuantity;
    EditText txtUsage;
    EditText txtPrice;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spadd);

        dbHelper = new DBHelper();

        txtName = findViewById(R.id.spaddName);
        txtManufacturer = findViewById(R.id.spAddManufacturer);
        txtModel = findViewById(R.id.spAddModel);
        txtYear = findViewById(R.id.spAddYear);
        txtCategory = findViewById(R.id.spAddCategory);
        txtSubCategory = findViewById(R.id.spAddSubCategory);
        txtDescription = findViewById(R.id.spAddDescription);
        txtQuantity = findViewById(R.id.spAddQty);
        txtUsage = findViewById(R.id.spAddUsage);
        txtPrice = findViewById(R.id.spAddPrice);
        btnAdd = findViewById(R.id.btnAddspadd);

        int mode = getIntent().getIntExtra("mode", MODE_VIEW);
        if (mode == MODE_VIEW) {
            setEditable(false);

            String sparepartkey = getIntent().getStringExtra(REF_SPAREPART);
            btnAdd.setVisibility(View.GONE);

            query = dbHelper.getSparepartDatabaseReference().orderByKey().equalTo(sparepartkey);
            valueEventListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Sparepart sparepart = snapshot.getValue(Sparepart.class);
                        setValues(sparepart);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.d(TAG, "ERROR db");
                }
            };
            query.addListenerForSingleValueEvent(valueEventListener);
        } else if (mode == MODE_ADD) {
            btnAdd.setVisibility(View.VISIBLE);
            btnAdd.setText("Add");
            setEditable(true);
            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    manufacturer = txtManufacturer.getText().toString();
                    model = txtModel.getText().toString();
                    year = txtYear.getText().toString();
                    category = txtCategory.getText().toString();
                    subcategory = txtSubCategory.getText().toString();
                    String name = txtName.getText().toString();
                    String usage = txtUsage.getText().toString();
                    String price = txtPrice.getText().toString();
                    int qty = Integer.parseInt(txtQuantity.getText().toString());
                    String description = txtDescription.getText().toString();
                    Sparepart sparepart = new Sparepart(manufacturer, model, year, category, subcategory, name, usage, price, qty, description);
//                    String path = manufacturer + "/" + model + "/" + year + "/" + category + "/" + subcategory;

                    String key = dbHelper.pushSparepart(sparepart);
                    if (key != null) {
                        Toast.makeText(SPAddActivity.this, "Spare part successfully added.", Toast.LENGTH_SHORT).show();
                        SPAddActivity.this.finish();
                    } else {
                        Toast.makeText(SPAddActivity.this, "Adding spare part failed.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else if (mode == MODE_UPDATE) {
            final String sparepartkey = getIntent().getStringExtra(REF_SPAREPART);
            btnAdd.setText("Update");
            btnAdd.setVisibility(View.VISIBLE);

            query = dbHelper.getSparepartDatabaseReference().orderByKey().equalTo(sparepartkey);
            valueEventListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Sparepart sparepart = snapshot.getValue(Sparepart.class);
                        setValues(sparepart);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.d(TAG, "ERROR db");
                }
            };
            query.addListenerForSingleValueEvent(valueEventListener);
            setEditable(true);
            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    manufacturer = txtManufacturer.getText().toString();
                    model = txtModel.getText().toString();
                    year = txtYear.getText().toString();
                    category = txtCategory.getText().toString();
                    subcategory = txtSubCategory.getText().toString();
                    String name = txtName.getText().toString();
                    String usage = txtUsage.getText().toString();
                    String price = txtPrice.getText().toString();
                    int qty = Integer.parseInt(txtQuantity.getText().toString());
                    String description = txtDescription.getText().toString();
                    Sparepart sparepart = new Sparepart(manufacturer, model, year, category, subcategory, name, usage, price, qty, description);

                    dbHelper.updateSparepart(sparepartkey,sparepart);
                    if (sparepartkey != null) {
                        Toast.makeText(SPAddActivity.this, "Spare part updated successfully.", Toast.LENGTH_SHORT).show();
                        SPAddActivity.this.finish();
                    } else {
                        Toast.makeText(SPAddActivity.this, "Updating spare part failed.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else if (mode == MODE_DELETE) {
            setEditable(false);
            final String sparepartkey = getIntent().getStringExtra(REF_SPAREPART);
            btnAdd.setVisibility(View.VISIBLE);
            btnAdd.setText("Delete");

            query = dbHelper.getSparepartDatabaseReference().orderByKey().equalTo(sparepartkey);
            valueEventListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Sparepart sparepart = snapshot.getValue(Sparepart.class);
                        setValues(sparepart);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.d(TAG, "ERROR db");
                }
            };
            query.addListenerForSingleValueEvent(valueEventListener);

            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    dbHelper.updateSparepart(sparepartkey,null);
                    if (sparepartkey != null) {
                        Toast.makeText(SPAddActivity.this, "Spare part deleted successfully.", Toast.LENGTH_SHORT).show();
                        SPAddActivity.this.finish();
                    } else {
                        Toast.makeText(SPAddActivity.this, "Deleting spare part failed.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        Button btnCancel = findViewById(R.id.btnAddspcancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    void setValues(Sparepart sparepart) {
        txtName.setText(sparepart.sparePartName);
        txtManufacturer.setText(sparepart.manufacture);
        txtModel.setText(sparepart.model);
        txtYear.setText(sparepart.year);
        txtCategory.setText(sparepart.category);
        txtSubCategory.setText(sparepart.subCategory);
        txtDescription.setText(sparepart.sparePartDescription);
        txtUsage.setText(sparepart.sparePartUsage);
        txtQuantity.setText(String.valueOf(sparepart.sparePartQuantity));
        txtPrice.setText(String.valueOf(sparepart.sparePartPrice));
    }

    void setEditable(boolean editable) {
        txtName.setEnabled(editable);
        txtManufacturer.setEnabled(editable);
        txtModel.setEnabled(editable);
        txtYear.setEnabled(editable);
        txtCategory.setEnabled(editable);
        txtSubCategory.setEnabled(editable);
        txtDescription.setEnabled(editable);
        txtUsage.setEnabled(editable);
        txtQuantity.setEnabled(editable);
        txtPrice.setEnabled(editable);

        if (editable) {
            query = dbHelper.getSparepartStructureDatabaseReference();
            valueEventListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    manufacturerArray = new ArrayList<>();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        String manufact = snapshot.getKey();
                        Log.d(TAG, "MA : " + manufact);
                        manufacturerArray.add(manufact);
                    }
                    ArrayAdapter<String> manufacturerAdapter = new ArrayAdapter<String>(SPAddActivity.this, android.R.layout.simple_list_item_1, manufacturerArray);
                    txtManufacturer.setAdapter(manufacturerAdapter);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.d(TAG, "ERROR db");
                }
            };
            query.addListenerForSingleValueEvent(valueEventListener);

            txtManufacturer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    manufacturer = txtManufacturer.getText().toString();

                    query = dbHelper.getSparepartStructureDatabaseReference(manufacturer);
                    valueEventListener = new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            modelArray = new ArrayList<>();
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                String mode = snapshot.getKey();
                                Log.d(TAG, "MO : " + mode);
                                modelArray.add(mode);
                            }
                            ArrayAdapter<String> modelAdapter = new ArrayAdapter<String>(SPAddActivity.this, android.R.layout.simple_list_item_1, modelArray);
                            txtModel.setAdapter(modelAdapter);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    };
                    query.addListenerForSingleValueEvent(valueEventListener);
                }
            });
            query.addListenerForSingleValueEvent(valueEventListener);
            txtModel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    model = txtModel.getText().toString();
                    query = dbHelper.getSparepartStructureDatabaseReference(manufacturer + "/" + model);
                    valueEventListener = new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            yearArray = new ArrayList<>();
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                String mode = snapshot.getKey();
                                Log.d(TAG, "MO : " + mode);
                                yearArray.add(mode);
                            }
                            ArrayAdapter<String> yearAdapter = new ArrayAdapter<String>(SPAddActivity.this, android.R.layout.simple_list_item_1, yearArray);
                            txtYear.setAdapter(yearAdapter);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    };
                    query.addListenerForSingleValueEvent(valueEventListener);
                }
            });
            query.addListenerForSingleValueEvent(valueEventListener);
            txtYear.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    year = txtYear.getText().toString();
                    query = dbHelper.getSparepartStructureDatabaseReference(manufacturer + "/" + model + "/" + year);
                    valueEventListener = new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            categoryArray = new ArrayList<>();
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                String mode = snapshot.getKey();
                                Log.d(TAG, "MO : " + mode);
                                categoryArray.add(mode);
                            }
                            ArrayAdapter<String> categoryAdapter = new ArrayAdapter<String>(SPAddActivity.this, android.R.layout.simple_list_item_1, categoryArray);
                            txtCategory.setAdapter(categoryAdapter);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    };
                    query.addListenerForSingleValueEvent(valueEventListener);
                }
            });
            txtCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    category = txtCategory.getText().toString();
                    query = dbHelper.getSparepartStructureDatabaseReference(manufacturer + "/" + model + "/" + year + "/" + category);
                    valueEventListener = new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            subcategoryArray = new ArrayList<>();
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                String mode = snapshot.getValue(String.class);
                                Log.d(TAG, "MO : " + mode);
                                subcategoryArray.add(mode);
                            }
                            ArrayAdapter<String> subCategoryAdapter = new ArrayAdapter<String>(SPAddActivity.this, android.R.layout.simple_list_item_1, subcategoryArray);
                            txtSubCategory.setAdapter(subCategoryAdapter);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    };
                    query.addListenerForSingleValueEvent(valueEventListener);
                }
            });
        }
    }

}
