package com.example.mayur.pdm.SpareParts.views;

import android.app.SearchManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.mayur.pdm.R;
import com.example.mayur.pdm.SpareParts.adapters.SparePartsAdapter;
import com.example.mayur.pdm.SpareParts.models.CartSparepart;
import com.example.mayur.pdm.SpareParts.models.CartSparepartItem;
import com.example.mayur.pdm.SpareParts.models.Sparepart;
import com.example.mayur.pdm.SpareParts.utils.DBHelper;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class SparePartsActivity extends AppCompatActivity {

    RecyclerView sparepartsRecyclerView;
    SwipeRefreshLayout sparepaersSwiperefreshLayout;

    Query query;
    ValueEventListener valueEventListener = null;

    ArrayList<Sparepart> sparepartDataSet;

    DBHelper dbHelper;
    private SearchView searchView;

    static List<CartSparepartItem> cart = new ArrayList<>();
    double subtotal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spare_parts);
        Toolbar toolbar = findViewById(R.id.spareparts_tool_bar);
        toolbar.setTitle("Spare Parts");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        dbHelper = new DBHelper();

        sparepartsRecyclerView = findViewById(R.id.sparepartsRecyclerView);
        sparepartsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        sparepartsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        sparepaersSwiperefreshLayout = findViewById(R.id.sparepartsSwipeRefreshLayout);

        sparepaersSwiperefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                sparepaersSwiperefreshLayout.setRefreshing(true);
//                String path = "Audi/" + "A06/" + "2018/" + "Cooling and Heating/"+"Intercooler";
                updateDatabaseListener();
                sparepaersSwiperefreshLayout.setRefreshing(false);
            }
        });

//        Sparepart sparepart = new Sparepart("BMW", "1 Series", "2017", "Brakes", "Brake Discs", "Eicher Premium Brake Disc", "Used", "10000", 2, "Front");
//        String path = "Audi/" + "A06/" + "2018/" + "Cooling and Heating/"+"Intercooler";
//        dbHelper.pushSparepart(path, sparepart);

        updateDatabaseListener();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_spareparts, menu);
        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String query) {
                // filter recycler view when query submitted

                return false;
            }

            @Override
            public boolean onQueryTextChange(final String query) {
                // filter recycler view when text is changed
                if (TextUtils.isEmpty(query)) {
                    updateDatabaseListener();
                } else {
                    ValueEventListener listener = new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            sparepartDataSet = new ArrayList<>();
                            sparepaersSwiperefreshLayout.setRefreshing(true);
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                Sparepart sparepart = snapshot.getValue(Sparepart.class);
                                if (sparepart.sparePartName.toLowerCase().contains(query.toLowerCase())) {
                                    sparepart = sparepart.setKey(snapshot.getKey());
                                    sparepartDataSet.add(0, sparepart);
                                }
                            }
                            sparepartsRecyclerView.setAdapter(new SparePartsAdapter(SparePartsActivity.this, sparepartDataSet));
                            sparepaersSwiperefreshLayout.setRefreshing(false);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Log.d("SpareParts", "Spareparts dailed to retrieve");
                        }
                    };
                    updateDatabaseListener(listener);
                }
                return false;
            }
        });
        return true;
    }

    public void updateDatabaseListener() {
        if (valueEventListener != null) {
            query.removeEventListener(valueEventListener);
        }

//        query = dbHelper.getDatabaseReference(path);
        query = dbHelper.getSparepartDatabaseReference();
        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                sparepartDataSet = new ArrayList<>();
                sparepaersSwiperefreshLayout.setRefreshing(true);
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Sparepart sparepart = snapshot.getValue(Sparepart.class);
                    sparepart = sparepart.setKey(snapshot.getKey());
                    sparepartDataSet.add(0, sparepart);
                }
                sparepartsRecyclerView.setAdapter(new SparePartsAdapter(SparePartsActivity.this, sparepartDataSet));
                sparepaersSwiperefreshLayout.setRefreshing(false);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("SpareParts", "Spareparts dailed to retrieve");
            }
        };

        query.addValueEventListener(valueEventListener);

    }

    public void updateDatabaseListener(ValueEventListener listener) {
        if (valueEventListener != null) {
            query.removeEventListener(valueEventListener);
        }
        valueEventListener = listener;
        query.addValueEventListener(valueEventListener);

    }

    public void addToCart(final Sparepart sparepart) {
        Log.d("SpareParts", "item : " + sparepart.sparePartName);

        LayoutInflater inflater = SparePartsActivity.this.getLayoutInflater();
        View content = inflater.inflate(R.layout.dialog_sparepart_cart_item, null);
        AlertDialog.Builder dialog = new AlertDialog.Builder(new ContextWrapper(SparePartsActivity.this), R.style.AppTheme_Dialog);
        dialog.setView(content);

        dialog.setTitle("Enter Quantity");
        TextView txt_sparepart_name = content.findViewById(R.id.txt_sparepart_name);
        final TextView txt_sparepart_subtotal = content.findViewById(R.id.txt_sparepart_subtotal);
        final TextInputEditText txt_sparepart_qty = content.findViewById(R.id.txt_sparepart_qty);

        txt_sparepart_name.setText("Name : " + sparepart.sparePartName);

        txt_sparepart_qty.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.d("Spareparts", "Dialog count subtotal : " + charSequence);
               subtotal = 0.00;
                if (txt_sparepart_qty == null || TextUtils.isEmpty(txt_sparepart_qty.getText().toString()))
                    subtotal = 0.00;
                else
                    subtotal = Double.parseDouble(sparepart.sparePartPrice) * Double.parseDouble(txt_sparepart_qty.getText().toString());
                txt_sparepart_subtotal.setText("SubTotal : " + String.valueOf(subtotal));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        dialog.setPositiveButton(R.string.addtocart, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialog, int which) {

                final CartSparepartItem cartSparepartItem = new CartSparepartItem(sparepart.sparePartName, txt_sparepart_qty.getText().toString(), sparepart.sparePartPrice,String.valueOf(subtotal));
                cart.add(cartSparepartItem);
                Log.d("SpareParts", "Cart Count : " + cart.size());
                dialog.dismiss();
                final AlertDialog.Builder builder = new AlertDialog.Builder(new ContextWrapper(SparePartsActivity.this), R.style.AppTheme_Dialog);

                final String invoice = UUID.randomUUID().toString();
                builder.setTitle("Confirm Order")
                        .setMessage("Your Order: \n" +
                                "Invoice No : " + invoice + "\n" +
                                "Total : " + cartSparepartItem.subtotal + "\n" +
                                "Save your invoice no for get the order")
                        .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String orderkey = dbHelper.pushSparepartOrder(new CartSparepart(invoice, cartSparepartItem.name, cartSparepartItem.subtotal));
                                dbHelper.getSparepartOrdersDatabaseReference().child(orderkey).child("order").setValue(cartSparepartItem);
                                Log.d("Sparepart", "Cart add order key : " + orderkey);
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });

        dialog.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener()

        {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
