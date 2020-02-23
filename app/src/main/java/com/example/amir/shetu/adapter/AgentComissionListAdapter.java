package com.example.amir.shetu.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.amir.shetu.R;
import com.example.amir.shetu.fragment.AgentComissionFragment;
import com.example.amir.shetu.model.AgentComission;

import java.util.List;

public class AgentComissionListAdapter extends RecyclerView.Adapter<AgentComissionListAdapter.ViewHolder> {

    private static final int FOOTER_VIEW = 1;
    private Context context;
    public List<AgentComission> agentComissionList;


    public AgentComissionListAdapter(List<AgentComission> agentComissionList, AgentComissionFragment agentComissionFragment, Context context) {
        this.agentComissionList=agentComissionList;
        this.context=context;
    }

    @NonNull
    @Override
    public AgentComissionListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.agent_comission_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AgentComissionListAdapter.ViewHolder holder, int position) {

        AgentComission comission = agentComissionList.get(position);
        holder.productName.setText(comission.getBnCommodityName());
        holder.serviceCharge.setText(comission.getServiceCharge());
        holder.agentShare.setText(comission.getAgentAmount().toString());
        holder.recvDate.setText(comission.getSoldTime());
        holder.totalAmount.setText(comission.getTotal().toString());
        holder.sellerName.setText(comission.getSellerName());
    }

    @Override
    public int getItemCount() {
        return agentComissionList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView productName,recvDate,sellerName,totalAmount,agentShare,serviceCharge;
        public ViewHolder(View itemView) {
            super(itemView);
            productName=itemView.findViewById(R.id.product_name);
            recvDate=itemView.findViewById(R.id.sale_date_time);
            sellerName=itemView.findViewById(R.id.seller_name);
            totalAmount=itemView.findViewById(R.id.product_totalprice);
            agentShare=itemView.findViewById(R.id.agent_share);
            serviceCharge=itemView.findViewById(R.id.service_charge);
        }
    }
}
