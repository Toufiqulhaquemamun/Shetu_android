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
import com.example.amir.shetu.manager.StaticDataManager;
import com.example.amir.shetu.model.PreorderOrderList;
import com.example.amir.shetu.retorfit.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PreOrderPurchaseBidsAdapter extends RecyclerView.Adapter<PreOrderPurchaseBidsAdapter.MyViewHolder> {

    private List<PreorderOrderList> OrderList;
    private PreOrderPurchaseBidsAdapter.Listener listener;

    public PreOrderPurchaseBidsAdapter(List<PreorderOrderList> orderList, PreOrderPurchaseBidsAdapter.Listener listener) {
        OrderList = orderList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.preorder_purchase_bids, parent, false);
        return new MyViewHolder(itemView) ;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final PreorderOrderList list = OrderList.get(position);
        Log.d("ID",list.getId().toString());

        if(list.getStatusId()==1)
        {
            holder.status.setText("Start");
            holder.startbtn.setText("থামুন");
        }
        else if(list.getStatusId()==2)
        {
            holder.status.setText("Pause");
            holder.startbtn.setText("শুরু করুন");
        }
        else if(list.getStatusId()==3)
        {
            holder.status.setText("Stop");
            holder.startbtn.setText("বন্ধ করুন");
        }
        holder.productName.setText(list.getBnName());
        holder.grade.setText(list.getBnGrade());

        holder.bidbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.itemClick(view, StaticDataManager.ALL_BIDS,list.getId(),position);
            }
        });

        holder.startbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.itemClick(view, StaticDataManager.STARTBID,list.getId(),position);
                if(list.getStatusId()==1) {
                    RetrofitInstance.getApi().changePreOrderBidStatus(list.getId(), 2).enqueue(new Callback<Boolean>() {
                        @Override
                        public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                            Log.d("Success",response.body().toString());
                        }
                        @Override
                        public void onFailure(Call<Boolean> call, Throwable t) {
                            Log.d("Failre",t.getMessage());
                        }
                    });
                }
                else if(list.getStatusId()==2) {
                    RetrofitInstance.getApi().changePreOrderBidStatus(list.getId(),1).enqueue(new Callback<Boolean>() {
                        @Override
                        public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                            Log.d("Success",response.body().toString());
                        }

                        @Override
                        public void onFailure(Call<Boolean> call, Throwable t) {
                            Log.d("Failure", String.valueOf(t.getCause()));
                        }
                    });
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return OrderList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView productName,status,grade;
        public Button startbtn ,bidbtn,editbtn;
        public MyViewHolder(View itemView) {
            super(itemView);
            productName =itemView.findViewById(R.id.product_name);
            status = itemView.findViewById(R.id.status);
            grade= itemView.findViewById(R.id.grade);
            startbtn=itemView.findViewById(R.id.start_btn);
            editbtn=itemView.findViewById(R.id.modify_btn);
            bidbtn=itemView.findViewById(R.id.btn_bids);
        }
    }


    public interface Listener {
        public void itemClick(View itemView,String name,int bidId,int pos);
        <T> void productClick(View itemView, T item);
    }
}
