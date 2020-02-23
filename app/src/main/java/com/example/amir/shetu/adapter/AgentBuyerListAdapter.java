package com.example.amir.shetu.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.amir.shetu.R;
import com.example.amir.shetu.fragment.AgentBuyerFragment;
import com.example.amir.shetu.interfaces.other.ProductClickListener;
import com.example.amir.shetu.model.AgentBuyerList;

import java.util.List;

public class AgentBuyerListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private static final int FOOTER_VIEW = 1;
    private Context context;
    public List<AgentBuyerList.Datum> agentbuyList;
    String total;
    public AgentBuyerListAdapter(List<AgentBuyerList.Datum> agentbuyList, AgentBuyerFragment agentBuyerFragment, String total, Context context) {
        this.agentbuyList=agentbuyList;
        this.total=total;
        this.context=context;

    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        if (viewType == FOOTER_VIEW) {
            v = LayoutInflater.from(context).inflate(R.layout.footer_item, parent, false);
            FooterViewHolder vh = new FooterViewHolder(v);
            return vh;
        }

        v = LayoutInflater.from(context).inflate(R.layout.agent_sell_list_item, parent, false);

        NormalViewHolder vh = new NormalViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        try{


            if(holder instanceof NormalViewHolder)
            {
                AgentBuyerList.Datum list = agentbuyList.get(position);
                NormalViewHolder nh = (NormalViewHolder) holder;
                nh.buyername.setText(list.getBnBuyerName());
                nh.sellername.setText(list.getBnSellerName());
                nh.sellDate.setText(list.getSoldDate());
                nh.productName.setText(list.getBnProductName());
                nh.agentShare.setText(list.getAgentAmount().toString());
                nh.sellRate.setText(list.getPrice().toString());
                nh.totalPrice.setText(list.getTotal().toString());
                nh.proamount.setText(list.getQuantity().toString());
                Log.d("MAssage","");

            }
            else if(holder instanceof FooterViewHolder)
            {
                FooterViewHolder fh = (FooterViewHolder) holder;
                fh.amount.setText(total);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {

        // Add extra view to show the footer view
        return agentbuyList.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == agentbuyList.size())
        {
            return FOOTER_VIEW;
        }
        return super.getItemViewType(position);
    }

    public class FooterViewHolder extends ViewHolder {
        TextView amount;
        public FooterViewHolder(View itemView) {
            super(itemView);
            amount=itemView.findViewById(R.id.agent_part);
        }
    }

    public class NormalViewHolder extends ViewHolder {

        TextView buyername,sellername,sellDate,productName,sellRate,proamount,totalPrice,agentShare;
        public NormalViewHolder(View itemView) {
            super(itemView);
            buyername=itemView.findViewById(R.id.buyer_name);
            sellername=itemView.findViewById(R.id.seller_name);
            sellDate=itemView.findViewById(R.id.sale_date_time);
            productName=itemView.findViewById(R.id.product_name);
            proamount=itemView.findViewById(R.id.product_amount);
            sellRate=itemView.findViewById(R.id.product_rate);
            totalPrice=itemView.findViewById(R.id.product_totalprice);
            agentShare=itemView.findViewById(R.id.agent_share);

        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // Define elements of a row here
        TextView amount;
        public ViewHolder(View itemView) {
            super(itemView);


        }

        public void bindView(int position) {
            // bindView() method to implement actions
        }
    }
}
