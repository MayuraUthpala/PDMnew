package com.example.mayur.pdm;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Dulip on 4/24/2018.
 */
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemHolder> {
    private ArrayList<Items> miList;
    Context ctx;




    public static class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public TextView mTextView1;
        public TextView mTextView2;
        public TextView mTextView3;
        public TextView mTextView4;
        public TextView mTextView5;
        public TextView mTextView6;
        public TextView mTextView7;
        public TextView mTextView8;

        ArrayList<Items> service = new ArrayList<Items>();
        Context ctx;

        public ItemHolder(View itemView, Context ctx,ArrayList<Items> service) {
            super(itemView);

            this.service = service;
            this.ctx = ctx;

            itemView.setOnClickListener(this);
            mTextView1 = itemView.findViewById(R.id.txtv1);
            mTextView2 = itemView.findViewById(R.id.txtv2);
            mTextView3 = itemView.findViewById(R.id.txtv3);
            mTextView4 = itemView.findViewById(R.id.txtv4);
            mTextView5 = itemView.findViewById(R.id.txtv5);
            mTextView6 = itemView.findViewById(R.id.txtv6);
            mTextView7 = itemView.findViewById(R.id.txtv7);
            mTextView8 = itemView.findViewById(R.id.txtv8);

        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Items items = this.service.get(position);
            Intent f = new Intent(this.ctx,ItemIn.class);
           // f.putExtra("Service",items.getText1().toString());
            this.ctx.startActivity(f);
        }


    }

    public ItemAdapter(ArrayList<Items> iList, Context ctx)
    {
        miList = iList;
        this.ctx = ctx;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.items, parent, false);
        ItemHolder ih = new ItemHolder(v,ctx, miList);
        return ih;
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        Items currentItem = miList.get(position);
       // holder.mTextView1.setText(currentItem.getText1());
        holder.mTextView2.setText(currentItem.getText2());

        holder.mTextView4.setText(currentItem.getText4());

        //holder.mTextView6.setText(currentItem.getText6());
        holder.mTextView7.setText(currentItem.getText7());


    }

    @Override
    public int getItemCount() {
        return miList.size();
    }
}
