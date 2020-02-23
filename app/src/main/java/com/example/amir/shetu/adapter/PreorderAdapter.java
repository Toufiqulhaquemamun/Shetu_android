package com.example.amir.shetu.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.amir.shetu.R;
import com.example.amir.shetu.interfaces.other.ProductClickListener;
import com.example.amir.shetu.model.PreOrder;
import com.example.amir.shetu.model.PreorderList;

import java.util.List;

public class PreorderAdapter extends RecyclerView.Adapter<PreorderAdapter.ViewHolder> {

    private List<PreOrder> preorderList;
    private ProductClickListener listener;

    public PreorderAdapter(List<PreOrder> preorderList, ProductClickListener listener) {
        this.preorderList=preorderList;
        this.listener=listener;
    }


    @NonNull
    @Override
    public PreorderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.preorder_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PreorderAdapter.ViewHolder holder, final int position) {
        final PreOrder list = preorderList.get(position);

        holder.productname.setText(list.getCommodity().bnName);
        holder.quantity.setText(list.getExpectedQuantity().toString()+" "+list.getUnit().name);
        holder.productRate.setText(list.getExpectedPrice().toString()+" টাকা/"+list.getUnit().name);
        holder.deliveryDate.setText(list.getExpectedDeliveryStartDate()+"-"+list.getExpectedDeliveryEndDate());
        holder.location.setText(list.getDeliveryLocation().getStreetAddress()+","+list.getDeliveryLocation().getUpazila().getBnName()+","+list.getDeliveryLocation().getDistrict().getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.itemClick(holder.itemView,list);

            }
        });

    }

    @Override
    public int getItemCount() {
        return preorderList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView productname,deliveryDate,productRate,quantity,location;
        private View itemView;
        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView=itemView;
            productname=itemView.findViewById(R.id.product_name);
            location=itemView.findViewById(R.id.product_delivery_place);
            deliveryDate=itemView.findViewById(R.id.product_delivery_time);
            productRate=itemView.findViewById(R.id.product_rate);
            quantity=itemView.findViewById(R.id.product_amount);


        }
    }
}
