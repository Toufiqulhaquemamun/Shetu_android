package com.example.amir.shetu.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.amir.shetu.R;
import com.example.amir.shetu.interfaces.other.ProductClickListener;
import com.example.amir.shetu.manager.StaticDataManager;
import com.example.amir.shetu.model.CommodityPostList;

import java.util.List;

public class SaleBidsAdapter extends RecyclerView.Adapter<SaleBidsAdapter.MyViewHolder> {

    private List<CommodityPostList.Data> productsList;
    private ProductClickListener listener;
    private String url;


    public SaleBidsAdapter(List<CommodityPostList.Data> productList, ProductClickListener listener) {
        this.productsList = productList;
        this.listener = listener;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, count, price;
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            count = view.findViewById(R.id.count);
            thumbnail = view.findViewById(R.id.thumbnail);
            price = view.findViewById(R.id.price);


        }
    }


    @Override
    public SaleBidsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sale_bids_card, parent, false);

        return new SaleBidsAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final SaleBidsAdapter.MyViewHolder holder, final int position) {
        final CommodityPostList.Data product = productsList.get(position);
        holder.title.setText(product.getName());
        holder.count.setText(product.getQuantity()+" "+product.getUnit());
        holder.price.setText(product.getPrice()+" "+"Tk");

        url = StaticDataManager.COMMODITY_IMAGE_PREFIX + "" + product.getImage();
        Glide.with(holder.title.getContext()).load(url).into(holder.thumbnail);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.productClick(v,product.getId());
            }
        });
    }


    @Override
    public int getItemCount() {
        return productsList.size();
    }

}

