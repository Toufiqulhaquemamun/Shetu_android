package com.example.amir.shetu.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.amir.shetu.R;
import com.example.amir.shetu.interfaces.other.ProductClickListener;
import com.example.amir.shetu.model.SMECommodityList;

import java.util.List;

public class AssignProductAdapter extends RecyclerView.Adapter<AssignProductAdapter.MyViewHolder> {

    private List<SMECommodityList> productList;
    private ProductClickListener listener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView communityName,unitPerCost;

        public MyViewHolder(View view) {
            super(view);
            communityName =view.findViewById(R.id.community_name);

            unitPerCost =view.findViewById(R.id.per_unit_cost);
        }
    }


    public AssignProductAdapter(List<SMECommodityList> productList, ProductClickListener listener) {
        this.productList = productList;
        this.listener=listener;
    }

    @Override
    public AssignProductAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_information_card, parent, false);

        return new AssignProductAdapter.MyViewHolder(itemView);
    }



    @Override
    public void onBindViewHolder(final AssignProductAdapter.MyViewHolder holder, final int position) {
        final SMECommodityList product = productList.get(position);
        holder.communityName.setText(product.getCommodityName());
        holder.unitPerCost.setText(product.getCategoryName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.productClick(v,product.getId());
            }
        });




    }



    @Override
    public int getItemCount() {
        return productList.size();
    }

}
