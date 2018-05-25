package com.example.mayur.pdm.SpareParts.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mayur.pdm.R;
import com.example.mayur.pdm.SpareParts.models.Sparepart;
import com.example.mayur.pdm.SpareParts.views.SPAddActivity;

import java.util.List;

/**
 * Created by LENOVO on 5/1/2018.
 */

public class SparePartsaAdminAdapter extends RecyclerView.Adapter<SparePartsaAdminAdapter.SparepartAdminViewHolder> {

    private List<Sparepart> mDataSet;

    public SparePartsaAdminAdapter(Context context, List<Sparepart> pDataSet) {
        this.mDataSet = pDataSet;
    }

    public class SparepartAdminViewHolder extends RecyclerView.ViewHolder {
        TextView txtSpName;
        TextView txtSpAvailability;
        TextView txtSpManufacture;
        TextView txtSpModel;
        TextView txtSpYear;
        TextView txtSpPrice;
        View sparepartView;
        LinearLayout layoutSpCardDescription;
        ImageView btnEdit;
        ImageView btnDelete;

        public SparepartAdminViewHolder(final View itemView) {
            super(itemView);
            sparepartView = itemView;

            txtSpName = itemView.findViewById(R.id.txtSpName);
            txtSpAvailability = itemView.findViewById(R.id.txtSpAvailability);
            txtSpManufacture = itemView.findViewById(R.id.txtSpManufacture);
            txtSpModel = itemView.findViewById(R.id.txtSpModel);
            txtSpYear = itemView.findViewById(R.id.txtSpYear);
            txtSpPrice = itemView.findViewById(R.id.txtSpPrice);
            layoutSpCardDescription = itemView.findViewById(R.id.layoutSpCardDescription);
            btnEdit = itemView.findViewById(R.id.btnSparepartEdit);
            btnDelete = itemView.findViewById(R.id.btnSParepartDelete);

            layoutSpCardDescription.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String sparepartkey = mDataSet.get(getAdapterPosition()).KEY;
                    Intent intent = new Intent(itemView.getContext(), SPAddActivity.class);
                    intent.putExtra("mode", SPAddActivity.MODE_VIEW);
                    intent.putExtra(SPAddActivity.REF_SPAREPART,sparepartkey);
                    itemView.getContext().startActivity(intent);
                }
            });

            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String sparepartkey = mDataSet.get(getAdapterPosition()).KEY;
                    Intent intent = new Intent(itemView.getContext(), SPAddActivity.class);
                    intent.putExtra("mode", SPAddActivity.MODE_UPDATE);
                    intent.putExtra(SPAddActivity.REF_SPAREPART,sparepartkey);
                    itemView.getContext().startActivity(intent);
                }
            });

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String sparepartkey = mDataSet.get(getAdapterPosition()).KEY;
                    Intent intent = new Intent(itemView.getContext(), SPAddActivity.class);
                    intent.putExtra("mode", SPAddActivity.MODE_DELETE);
                    intent.putExtra(SPAddActivity.REF_SPAREPART,sparepartkey);
                    itemView.getContext().startActivity(intent);
                }
            });

        }
    }


    @Override
    public SparepartAdminViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_sparepart_admin, parent, false);

        return new SparepartAdminViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SparepartAdminViewHolder holder, int position) {
        final Sparepart sparepart = mDataSet.get(position);
        final Context context = holder.sparepartView.getContext();

        if (sparepart != null) {
            holder.txtSpName.setText(sparepart.sparePartName);
            holder.txtSpModel.setText(sparepart.model);
            holder.txtSpPrice.setText(sparepart.sparePartPrice);
            holder.txtSpYear.setText(sparepart.sparePartUsage);
            holder.txtSpManufacture.setText(sparepart.manufacture);


            if (sparepart.sparePartQuantity > 0) {
                holder.txtSpAvailability.setTextColor(context.getResources().getColor(R.color.greenText));
            }
            else {
                holder.txtSpAvailability.setTextColor(context.getResources().getColor(R.color.redText));
            }
            holder.txtSpAvailability.setText(String.valueOf(sparepart.sparePartQuantity));
        }
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }


}
