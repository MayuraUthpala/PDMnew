package com.example.mayur.pdm.SpareParts.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mayur.pdm.R;
import com.example.mayur.pdm.SpareParts.models.CartSparepart;
import com.example.mayur.pdm.SpareParts.views.SparePartOrderActivity;

import java.util.List;

/**
 * Created by LENOVO on 5/24/2018.
 */

public class SparePartsOrderAdminAdapter extends RecyclerView.Adapter<SparePartsOrderAdminAdapter.SparepartOrderAdminViewHolder> {

    private List<CartSparepart> mDataSet;
    Context mContext;
    SparePartOrderActivity sparePartOrderActivity;

    public SparePartsOrderAdminAdapter(SparePartOrderActivity activity, List<CartSparepart> pDataSet) {
        this.mDataSet = pDataSet;
        this.sparePartOrderActivity = activity;
        this.mContext = activity.getBaseContext();


    }

    public class SparepartOrderAdminViewHolder extends RecyclerView.ViewHolder {
        TextView txt_OrderSpInvoice;
        TextView txt_OrderSpName;
        TextView txt_OrderSpTotal;
        View sparepartOrderView;
        LinearLayout layoutSpCardDescription;

        public SparepartOrderAdminViewHolder(View itemView) {
            super(itemView);
            sparepartOrderView = itemView;
            txt_OrderSpName = itemView.findViewById(R.id.txtOrderSpName);
            txt_OrderSpInvoice = itemView.findViewById(R.id.txtOrderSpInvoice);
            txt_OrderSpTotal = itemView.findViewById(R.id.txtOrderSpTotal);

            layoutSpCardDescription = itemView.findViewById(R.id.layoutSpCardDescription);

        }
    }


    @Override
    public SparepartOrderAdminViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_sparepartorder, parent, false);

        return new SparepartOrderAdminViewHolder(v);

    }

    @Override
    public void onBindViewHolder(SparepartOrderAdminViewHolder holder, int position) {
        final CartSparepart cartSparepart = mDataSet.get(position);
        Log.d("SPARELOG","Adapter : " + cartSparepart.name);
        final Context context = holder.sparepartOrderView.getContext();
        if (cartSparepart != null) {
            holder.txt_OrderSpInvoice.setText(cartSparepart.invoiceno);
            holder.txt_OrderSpName.setText(cartSparepart.name);
            holder.txt_OrderSpTotal.setText(cartSparepart.total);

        }

    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }


}
