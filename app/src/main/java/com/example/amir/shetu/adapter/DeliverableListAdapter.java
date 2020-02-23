package com.example.amir.shetu.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amir.shetu.R;
import com.example.amir.shetu.interfaces.other.ProductClickListener;
import com.example.amir.shetu.manager.StaticDataManager;
import com.example.amir.shetu.model.DeliverableList;
import com.example.amir.shetu.retorfit.API;
import com.example.amir.shetu.retorfit.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeliverableListAdapter extends RecyclerView.Adapter<DeliverableListAdapter.ViewHolder> {

    public List<DeliverableList.Datum> datumList;
    public ProductClickListener listener;
    private API api= RetrofitInstance.getApi();

    public DeliverableListAdapter(List<DeliverableList.Datum> datumList, ProductClickListener listener) {
        this.datumList = datumList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public DeliverableListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tradelist_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final DeliverableListAdapter.ViewHolder holder, int position) {
        final DeliverableList.Datum list= datumList.get(position);
        holder.linerproductname.setVisibility(View.VISIBLE);
        holder.linernid_deliverytime.setVisibility(View.VISIBLE);
        holder.buyername.setText(list.getBuyerName()+" "+list.getBuyerPhone());
        holder.sellername.setText(list.getSellerName()+" "+list.getSellerPhone());
        holder.product_delivery_time.setText(list.getShippingTime());
        holder.product_name.setText(list.getCommodityName());
        holder.status.setText(list.getStatusDisplayName());
        holder.linernid_tokenentry.setVisibility(View.VISIBLE);

            holder.btnShipping.setVisibility(View.VISIBLE);
            holder.btnShipping.setText("নিশ্চিত করুন");



        holder.btnShipping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String token=holder.product_delivery_token.getText().toString().trim();
                Log.d("NIDID<TOKEN", String.valueOf(list.getBidId())+token);
                api.verifySell(list.getBidId(),token).enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        if (response.code() == StaticDataManager.OK)
                        {
                            Log.d("Response", String.valueOf(response.body()));
                            holder.linernid_tokenentry.setVisibility(View.INVISIBLE);
                            holder.btnShipping.setVisibility(View.INVISIBLE);

                        }
                        else{
                            Log.d("Response", String.valueOf(response.body()));
                        }
                    }

                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {
                        Log.d("Eror", t.getMessage());
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return datumList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView product_name,product_delivery_time,buyername,sellername,biddate,bidexpiredate,status;
        private EditText product_delivery_token;
        private Button btnverifyCommodity,btninvalidCommodity,btnundoverifyCommodity,btnvalidCommodity,btnShipping,btnCancelShip;
        private LinearLayout linerproductname,linerbuyier,linearseller,linernid_date,linearbid_enddate,linearbid_status,linernid_deliverytime,linernid_tokenentry;
        private View itemView;
        public ViewHolder(View itemView) {
            super(itemView);
            linerproductname=itemView.findViewById(R.id.linerproductname);
            linerbuyier=itemView.findViewById(R.id.linerbuyier);
            linearseller=itemView.findViewById(R.id.linearseller);
            linernid_date=itemView.findViewById(R.id.linernid_date);
            linearbid_enddate=itemView.findViewById(R.id.linearbid_enddate);
            linearbid_status=itemView.findViewById(R.id.linearbid_status);
            linernid_deliverytime=itemView.findViewById(R.id.linernid_deliverytime);
            linernid_tokenentry=itemView.findViewById(R.id.linernid_tokenentry);
            product_name=itemView.findViewById(R.id.product_name);
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
            product_delivery_time=itemView.findViewById(R.id.product_delivery_time);
            product_delivery_token=itemView.findViewById(R.id.product_delivery_token);
        }
    }
}
