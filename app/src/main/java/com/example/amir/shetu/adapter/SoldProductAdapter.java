package com.example.amir.shetu.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.amir.shetu.R;
import com.example.amir.shetu.interfaces.other.ProductClickListener;
import com.example.amir.shetu.model.SellHistoryDetail;

import java.util.List;

public class SoldProductAdapter extends RecyclerView.Adapter<SoldProductAdapter.MyViewHolder> {


    private List<SellHistoryDetail> productsList;

    private ProductClickListener listener;



    public SoldProductAdapter(List<SellHistoryDetail> productList, ProductClickListener listener) {

        this.productsList = productList;

        this.listener = listener;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView commodityNameView,buyerNameView;


        public MyViewHolder(View view) {
            super(view);
            commodityNameView = view.findViewById(R.id.name);

            buyerNameView = view.findViewById(R.id.phone);


        }
    }


    @Override
    public SoldProductAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_card, parent, false);

        return new SoldProductAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final SoldProductAdapter.MyViewHolder holder, final int position) {

        final SellHistoryDetail album = productsList.get(position);

        holder.commodityNameView.setText(album.getCommodityName());

        holder.buyerNameView.setText(album.getBuyerName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.productClick(v,album.getId());
            }
        });
    }


    @Override
    public int getItemCount() {
        return productsList.size();
    }

}

