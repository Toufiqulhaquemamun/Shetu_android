package com.example.amir.shetu.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
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
import com.example.amir.shetu.model.BidsOfSeller;

import java.util.List;

public class PreOrderAllBidsAdapter extends RecyclerView.Adapter<PreOrderAllBidsAdapter.MyviewHolder> {


    private List<BidsOfSeller> productList;
    boolean flag;
    private ImageView receiptImageView;
    Context mContext;
    PreOrderAllBidsAdapter.Listener listener;

    public PreOrderAllBidsAdapter(List<BidsOfSeller> productList,PreOrderAllBidsAdapter.Listener listener,Context context) {
        this.productList = productList;
        this.mContext = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.all_bids_card, parent, false);
        return new MyviewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyviewHolder holder, final int position) {
        final BidsOfSeller product =productList.get(position);
        Log.d("FlagValue", String.valueOf(flag));
        //PreferenceManager.getInstance().setInt(PreferenceManager.NMBROFBIDS, productList.size());
        holder.buyerNameView.setText(product.getSeller().getPersonalInformation().getBnName());
        holder.bidquantityView.setText(product.getQuantity()+" "+product.getUnit().name );
        holder.bidPriceView.setText(product.getPrice()+" TK");
        holder.bidStatusView.setText(product.getBidStatus().getDisplayBnName());
        holder.totalPriceView.setText(product.getQuantity()*product.getPrice()+" TK");
        Log.d("ReceiptStatus",product.getBidStatus().getDisplayBnName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.itemClick(view,product.getId(),product.getCommodityId());
            }
        });


    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        private TextView buyerNameView,bidPriceView,bidquantityView,totalPriceView,bidStatusView,receiptView;
        public ImageView receiptImageView,fullImageView;
        private Button approveBtn,cancelBtn,uploadmoney_recipt;
        private View view;
        public MyviewHolder(View itemView) {
            super(itemView);
            this.view=itemView;
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
            uploadmoney_recipt=view.findViewById(R.id.upload_moneyrecipt);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

    public interface Listener {
        public void itemClick(View itemView,int bidId,int pos);
        <T> void productClick(View itemView, T item);
    }
}
