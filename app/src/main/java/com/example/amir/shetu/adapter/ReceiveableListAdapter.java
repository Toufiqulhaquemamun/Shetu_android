package com.example.amir.shetu.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.amir.shetu.R;
import com.example.amir.shetu.interfaces.other.ProductClickListener;
import com.example.amir.shetu.manager.StaticDataManager;
import com.example.amir.shetu.model.ReceiveableList;
import com.example.amir.shetu.retorfit.API;
import com.example.amir.shetu.retorfit.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReceiveableListAdapter extends RecyclerView.Adapter<ReceiveableListAdapter.ViewHolder> {


    public List<ReceiveableList.RcvProductList> rcvProductLists;
    public ProductClickListener listener;
    private API api= RetrofitInstance.getApi();

    public ReceiveableListAdapter(List<ReceiveableList.RcvProductList> rcvProductLists, ProductClickListener listener) {
        this.rcvProductLists = rcvProductLists;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tradelist_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final ReceiveableList.RcvProductList list = rcvProductLists.get(position);
        Log.d("NAme", list.getStatus());
        Log.d("NAme", list.getStatusDisplayName());
        holder.buyername.setText(list.getBuyerName() + " " + list.getBuyerPhone());
        holder.sellername.setText(list.getSellerName() + " " + list.getSellerPhone());
        holder.biddate.setText(list.getShippingTime());
        holder.bidexpiredate.setText(list.getCommodityName());
        holder.status.setText(list.getStatusDisplayName());

        if (list.getStatus().equals(StaticDataManager.SHIPPING))
        {
            holder.btnShipping.setVisibility(View.VISIBLE);
            holder.btnShipping.setText("গ্রহন করা হয়েছে");

        }else{
            holder.btninvalidCommodity.setVisibility(View.VISIBLE);
            holder.btninvalidCommodity.setText("পণ্য পাইনি");
        }
        holder.btnShipping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.productClick(view,position);
                listener.itemClick(view,list);
                api.ChangeBidStatus(list.getBidId(),StaticDataManager.AgentReceived).enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        if (response.code() == StaticDataManager.OK)
                        {
                            Log.d("Response", String.valueOf(response.body()));
                        }
                    }

                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {

                    }
                });

            }
        });

        holder.btninvalidCommodity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.productClick(view,position);
                listener.itemClick(view,list);
                api.ChangeBidStatus(list.getBidId(),StaticDataManager.SHIPPING).enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        if (response.code() == StaticDataManager.OK)
                        {
                            Log.d("Response", String.valueOf(response.body()));
                        }
                    }

                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {

                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return rcvProductLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView buyername,sellername,biddate,bidexpiredate,status;
        private Button btnverifyCommodity,btninvalidCommodity,btnundoverifyCommodity,btnvalidCommodity,btnShipping,btnCancelShip;
        private View itemView;
        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView=itemView;
            buyername=itemView.findViewById(R.id.buyer_name);
            sellername=itemView.findViewById(R.id.seller_name);
            biddate=itemView.findViewById(R.id.bid_date_time);
            bidexpiredate=itemView.findViewById(R.id.bid_end_time);
            status=itemView.findViewById(R.id._status);
            btnverifyCommodity=itemView.findViewById(R.id.btn_verifyCommodity);
            btninvalidCommodity=itemView.findViewById(R.id.btn_invalidcommodity);
            btnundoverifyCommodity=itemView.findViewById(R.id.btn_undoverifyCommodity);
            btnvalidCommodity=itemView.findViewById(R.id.btn_validcommodity);
            btnShipping=itemView.findViewById(R.id.btn_ShipCommodity);
            btnCancelShip=itemView.findViewById(R.id.btn_cancelShip);
        }
    }
}
