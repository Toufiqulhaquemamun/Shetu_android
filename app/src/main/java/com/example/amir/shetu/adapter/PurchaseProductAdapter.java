package com.example.amir.shetu.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.amir.shetu.R;
import com.example.amir.shetu.interfaces.other.ProductClickListener;
import com.example.amir.shetu.model.SellHistoryDetail;

import java.util.List;

public class PurchaseProductAdapter extends RecyclerView.Adapter<PurchaseProductAdapter.MyViewHolder> {

    private List<SellHistoryDetail> productsList;
    private ProductClickListener listener;



    public PurchaseProductAdapter(List<SellHistoryDetail> productList, ProductClickListener listener) {
        this.productsList = productList;
        this.listener = listener;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView commodityNameView,buyerNameView;
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            commodityNameView = view.findViewById(R.id.name);
            buyerNameView = view.findViewById(R.id.seller_name_purchase);


        }
    }


    @Override
    public PurchaseProductAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.purchase_product_card, parent, false);

        return new PurchaseProductAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final PurchaseProductAdapter.MyViewHolder holder, final int position) {
        final SellHistoryDetail product = productsList.get(position);
        holder.commodityNameView.setText(product.getCommodityName());
        holder.buyerNameView.setText(product.getSellerName());
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
