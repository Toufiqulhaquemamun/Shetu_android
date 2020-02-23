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
import com.example.amir.shetu.fragment.AgentSellFragment;
import com.example.amir.shetu.model.AgentSellerList;

import java.util.List;

public class AgentSellerListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int FOOTER_VIEW = 1;
    private Context context;
    public List<AgentSellerList.Datum> agentsellList;
    String totalPrice;


    public AgentSellerListAdapter(List<AgentSellerList.Datum> agentsellList, AgentSellFragment agentSellFragment, Context context, String totalPrice) {
        this.agentsellList=agentsellList;
        this.context=context;
        this.totalPrice=totalPrice;

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
                NormalViewHolder nh = (NormalViewHolder) holder;
                AgentSellerList.Datum list = agentsellList.get(position);
                nh.buyername.setText(list.getBnBuyerName());
                nh.sellername.setText(list.getBnSellerName());
                nh.sellDate.setText(list.getSoldDate());
                nh.productName.setText(list.getBnProductName());
                nh.agentShare.setText(list.getAgentAmount().toString());
                nh.sellRate.setText(list.getPrice().toString());
                nh.totalPrice.setText(list.getTotal().toString());
                nh.amount.setText(list.getQuantity().toString());
//                count=count+list.getAgentAmount();
//                Log.d("Amount", String.valueOf(count));

            }
            else if(holder instanceof FooterViewHolder)
            {
                FooterViewHolder fh = (FooterViewHolder) holder;
                fh.amount.setText(String.valueOf(totalPrice));

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
        return agentsellList.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == agentsellList.size())
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

        TextView buyername,sellername,sellDate,productName,sellRate,amount,totalPrice,agentShare;
        public NormalViewHolder(View itemView) {
            super(itemView);
            buyername=itemView.findViewById(R.id.buyer_name);
            sellername=itemView.findViewById(R.id.seller_name);
            sellDate=itemView.findViewById(R.id.sale_date_time);
            productName=itemView.findViewById(R.id.product_name);
            amount=itemView.findViewById(R.id.product_amount);
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
