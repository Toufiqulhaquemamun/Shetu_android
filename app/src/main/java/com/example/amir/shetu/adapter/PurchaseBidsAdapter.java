package com.example.amir.shetu.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.amir.shetu.R;
import com.example.amir.shetu.interfaces.other.PurchasedBidsClickListener;
import com.example.amir.shetu.model.BiddedCommodityPostList;

import java.util.List;

public class PurchaseBidsAdapter extends RecyclerView.Adapter<PurchaseBidsAdapter.MyViewHolder> {

    
    private List<BiddedCommodityPostList> productList;
    private PurchasedBidsClickListener listener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView productName,status;

        public MyViewHolder(View view) {
            super(view);
            productName =view.findViewById(R.id.product_name);
            status = view.findViewById(R.id.status);

        }
    }


    public PurchaseBidsAdapter(List<BiddedCommodityPostList> productList, PurchasedBidsClickListener listener) {
        this.productList = productList;
        this.listener=listener;
    }

    @Override
    public PurchaseBidsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.purchase_bids_card, parent, false);

        return new PurchaseBidsAdapter.MyViewHolder(itemView);
    }



    @Override
    public void onBindViewHolder(final PurchaseBidsAdapter.MyViewHolder holder, final int position) {

        final BiddedCommodityPostList product = productList.get(position);

        holder.productName.setText(product.getCommodityName());

        holder.status.setText(product.getBidStatus());

        Log.d("Status",product.getBidStatus());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.PurchasedBids(v,product.commodityId,product.getId());
            }
        });

    }



    @Override
    public int getItemCount() {
        return productList.size();
    }



}

