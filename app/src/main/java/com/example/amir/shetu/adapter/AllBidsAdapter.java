package com.example.amir.shetu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.amir.shetu.R;
import com.example.amir.shetu.manager.PreferenceManager;
import com.example.amir.shetu.manager.StaticDataManager;
import com.example.amir.shetu.model.AllBidPrice;

import java.util.List;

public class AllBidsAdapter extends RecyclerView.Adapter<AllBidsAdapter.MyViewHolder> {

    private Context mContext;
    private List<AllBidPrice> productList;
    boolean flag;
    private AllBidsAdapter.Listener listener;
    private ImageView receiptImageView;
    double quantityTotal;
    double approveQuantity;




    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView buyerNameView,bidPriceView,bidquantityView,totalPriceView,bidStatusView,receiptView;
        public ImageView receiptImageView,fullImageView;
        private Button approveBtn,cancelBtn;
        private View view;
        public MyViewHolder(View view) {
            super(view);
            this.view=view;
            buyerNameView=view.findViewById(R.id.phone);
            bidPriceView=view.findViewById(R.id.bid_price);
            bidquantityView=view.findViewById(R.id.bid_quantity);
            totalPriceView=view.findViewById(R.id.total_price);
            approveBtn=view.findViewById(R.id.approve);
            cancelBtn=view.findViewById(R.id.cancel);
            bidStatusView=view.findViewById(R.id.bid_status);
            receiptImageView=view.findViewById(R.id.money_receipt);
            receiptView=view.findViewById(R.id.money_receiptseller);
            fullImageView=view.findViewById(R.id.imageView2);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });


        }

    }


    public AllBidsAdapter(Context mContext, List<AllBidPrice> albumList, Listener listener, double quantityTotal) {
        this.mContext = mContext;
        this.productList = albumList;
        this.listener=listener;
        this.quantityTotal=quantityTotal;
    }

    @Override
    public AllBidsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.all_bids_card, parent, false);

        return new AllBidsAdapter.MyViewHolder(itemView);
    }



    @Override
    public void onBindViewHolder(final AllBidsAdapter.MyViewHolder holder, final int position) {
        final AllBidPrice product = productList.get(position);
//        if(product.getStatus().equals(StaticDataManager.APPROVED))
//        {
//            flag=true;
//        }
//        else if(product.getStatus().equals(StaticDataManager.Locked))
//        {
//            flag=true;
//        }
//        else if(product.getStatus().equals(StaticDataManager.MoneyReceiptverified)){
//            flag=true;
//        }
//        else if(product.getStatus().equals(StaticDataManager.SHIPPING)){
//            flag=true;
//        }
//        else if(product.getStatus().equals(StaticDataManager.CancelSHipping)){
//            flag=true;
//        }
//        else if(product.getStatus().equals(StaticDataManager.AgentVerified)){
//            flag=true;
//        }
//        else if(product.getStatus().equals(StaticDataManager.InvalidProduct)){
//            flag=true;
//        }
        Log.d("FlagValue", String.valueOf(flag));
        PreferenceManager.getInstance().setInt(PreferenceManager.NMBROFBIDS, productList.size());
        holder.buyerNameView.setText(product.getBuyerName());
        holder.bidquantityView.setText(product.getQuantity()+" "+product.getUnit() );
        holder.bidPriceView.setText(product.getBuyerPrice()+" TK");
        holder.totalPriceView.setText(product.getTotalPrice()+" Tk");
        holder.bidStatusView.setText(product.getStatus());
        Log.d("ReceiptStatus",product.getStatus());

        if(product.getStatus().equals(StaticDataManager.MoneyReceiptUploded)||product.getStatus().equals(StaticDataManager.MoneyReceiptverified))
        {
            holder.receiptImageView.setVisibility(View.VISIBLE);
            holder.receiptView.setVisibility(View.VISIBLE);
            Glide.with(mContext)
                    .load(StaticDataManager.MONEY_RECEIPT_IMAGE_PREFIX+product.getImage())
                    .into(holder.receiptImageView);

        }
        if(product.getStatus().equals(StaticDataManager.PENDING)){
            holder.approveBtn.setVisibility(View.VISIBLE);
        }
        holder.approveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(String.valueOf(flag)=="true")
//                {
//                    Toast.makeText(mContext,"Already Approved",Toast.LENGTH_SHORT).show();
//                }else {
//
//                }

                if(product.getQuantity()<=product.getRemainingQuantity()){
                    listener.itemClick(holder.view,StaticDataManager.APPROVED_BUTTON_NAME,product.getBidId(),product.getBuyerId());
                    Log.d("FlagValueApp", String.valueOf(flag));
                    holder.cancelBtn.setVisibility(View.VISIBLE);
                }
                else
                {
                    Toast.makeText(mContext, R.string.excess_max_quantity, Toast.LENGTH_SHORT).show();
                }

            }
        });
        if(product.getStatus().equals(StaticDataManager.APPROVED)) {
            holder.cancelBtn.setVisibility(View.VISIBLE);
            holder.approveBtn.setVisibility(View.GONE);
            approveQuantity=approveQuantity+product.getQuantity();
            Log.d("ApproveQuantity", String.valueOf(approveQuantity));
        }
        holder.cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.itemClick(holder.view,StaticDataManager.CANCEL_BUTTON_NAME,product.getBidId(),product.getBuyerId());
                Log.d("FlagValueCancel", String.valueOf(flag));
                holder.approveBtn.setVisibility(View.VISIBLE);
//                flag=false;

            }
        });
        holder.receiptImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.receiptImageView.setVisibility(View.GONE);
                holder.fullImageView.setVisibility(View.VISIBLE);
                Glide.with(mContext).load(StaticDataManager.MONEY_RECEIPT_IMAGE_PREFIX+product.getImage()).into(holder.fullImageView);
            }
        });


    }



    @Override
    public int getItemCount() {
        return productList.size();
    }

    public interface Listener {
        public void itemClick(View itemView,String name,int bidId,int pos);
        <T> void productClick(View itemView, T item);
    }
}

