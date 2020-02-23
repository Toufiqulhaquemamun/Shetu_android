package com.example.amir.shetu.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.amir.shetu.R;
import com.example.amir.shetu.interfaces.other.ProductClickListener;
import com.example.amir.shetu.manager.StaticDataManager;
import com.example.amir.shetu.model.TradeList;
import com.example.amir.shetu.retorfit.API;
import com.example.amir.shetu.retorfit.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TradeListAdapter extends RecyclerView.Adapter<TradeListAdapter.ViewHolder> {

    private List<TradeList.TradelistSme> tradeList;
    private ProductClickListener listener;
    private API api= RetrofitInstance.getApi();


    public TradeListAdapter(List<TradeList.TradelistSme> tradeList,ProductClickListener listener) {
        this.tradeList=tradeList;
        this.listener=listener;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.tradelist_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        final TradeList.TradelistSme Sme= tradeList.get(position);
        final int pos = holder.getAdapterPosition();
//        Log.d("Adapter",Sme.getBuyerName());
        holder.linernid_date.setVisibility(View.VISIBLE);
        holder.buyername.setText(Sme.getBuyerName()+" "+Sme.getBuyerPhone());
        holder.sellername.setText(Sme.getSellerName()+" "+Sme.getSellerPhone());
        holder.biddate.setText(Sme.getBidDateTime());
        holder.bidexpiredate.setText(Sme.getExpiryDate());
        holder.status.setText(Sme.getStatusDisplayName());
        Log.d("Status",Sme.getStatus());
        Log.d("BidID",Sme.getBidId().toString());
        if(Sme.getStatus().equals(StaticDataManager.MoneyReceiptverified))
        {
            holder.btnShipping.setVisibility(View.VISIBLE);
           holder.btninvalidCommodity.setVisibility(View.VISIBLE);
        }
        else if(Sme.getStatus().equals(StaticDataManager.InvalidProduct))
        {
            holder.btnvalidCommodity.setVisibility(View.VISIBLE);
        }
        else if(Sme.getStatus().equals(StaticDataManager.AgentVerified))
        {
            holder.btnundoverifyCommodity.setVisibility(View.VISIBLE);
        }
        else if(Sme.getStatus().equals(StaticDataManager.PendingForSHipping))
        {
            holder.btnShipping.setVisibility(View.VISIBLE);
        }

        else if(Sme.getStatus().equals(StaticDataManager.CancelSHipping))
        {
            holder.btnCancelShip.setVisibility(View.VISIBLE);
        }
        else if(Sme.getStatus().equals(StaticDataManager.APPROVED))
        {
            holder.btnverifyCommodity.setVisibility(View.VISIBLE);
        }

        holder.btnverifyCommodity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.productClick(v,pos);
                listener.itemClick(holder.itemView,Sme);
                api.ChangeBidStatus(Sme.getBidId(),"AgentVerified").enqueue(new Callback<Boolean>() {
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

        holder.btnundoverifyCommodity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.productClick(v,pos);
                api.ChangeBidStatus(Sme.getBidId(),StaticDataManager.MoneyReceiptverified).enqueue(new Callback<Boolean>() {
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
            public void onClick(View v) {
                listener.productClick(v,pos);
                api.ChangeBidStatus(Sme.getBidId(),StaticDataManager.InvalidProduct).enqueue(new Callback<Boolean>() {
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

        holder.btnvalidCommodity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.productClick(v,pos);
                api.ChangeBidStatus(Sme.getBidId(),StaticDataManager.AgentVerified).enqueue(new Callback<Boolean>() {
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
        holder.btnShipping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.productClick(v,pos);
                api.ChangeBidStatus(Sme.getBidId(),StaticDataManager.CancelSHipping).enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {

                    }

                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {

                    }
                });
            }
        });
        holder.btnCancelShip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.productClick(v,pos);
                api.ChangeBidStatus(Sme.getBidId(),StaticDataManager.PendingForSHipping).enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {

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
        return tradeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView buyername,sellername,biddate,bidexpiredate,status;
        private Button btnverifyCommodity,btninvalidCommodity,btnundoverifyCommodity,btnvalidCommodity,btnShipping,btnCancelShip;
        private LinearLayout linerproductname,linerbuyier,linearseller,linernid_date,linearbid_enddate,linearbid_status,linernid_deliverytime,linernid_tokenentry;
        private View itemView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView=itemView;
            linearseller=itemView.findViewById(R.id.linearseller);
            linernid_date=itemView.findViewById(R.id.linernid_date);
            linearbid_enddate=itemView.findViewById(R.id.linearbid_enddate);
            linearbid_status=itemView.findViewById(R.id.linearbid_status);
            linernid_deliverytime=itemView.findViewById(R.id.linernid_deliverytime);
            linernid_tokenentry=itemView.findViewById(R.id.linernid_tokenentry);
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
