package com.example.mayur.pdm;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Dulip on 5/18/2018.
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

    private Context mcontext;
    private List<Upload> muploads;

    public ImageAdapter(Context context, List<Upload> uploads)
    {
        mcontext = context;
        muploads = uploads;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mcontext).inflate(R.layout.image_item, parent,false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        Upload uploadcurrent = muploads.get(position);
        holder.tw.setText(uploadcurrent.getName());
        Picasso.with(mcontext)
                .load(uploadcurrent.getUrl())
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .centerCrop()
                .into(holder.iw);

    }

    @Override
    public int getItemCount() {
        return muploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        public TextView tw;
        public ImageView iw;

        public ImageViewHolder(View itemView) {
            super(itemView);

            tw = itemView.findViewById(R.id.tid);
            iw = itemView.findViewById(R.id.iid);
        }
    }
}
