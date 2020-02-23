package com.example.amir.shetu.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.amir.shetu.R;
import com.example.amir.shetu.interfaces.other.ProductClickListener;
import com.example.amir.shetu.model.SMEList;

import java.util.List;

public class SMEListAdapter extends RecyclerView.Adapter<SMEListAdapter.VH> {
    private List<SMEList.SME> smeList;
    private ProductClickListener listener;

    public SMEListAdapter(List<SMEList.SME> smeList,ProductClickListener listener) {
        this.smeList = smeList;
        this.listener=listener;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.sme_list_itme,parent,false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final VH holder, int position) {
        final SMEList.SME sme=this.smeList.get(position);
        holder.name.setText(sme.getFirstName()+" "+sme.getLastName());
        holder.phone.setText(sme.getPhone());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.itemClick(holder.itemView,sme);
            }
        });
    }


    @Override
    public int getItemCount() {
        return smeList.size();
    }

    class VH extends RecyclerView.ViewHolder{
        private TextView name,phone;
        private View itemView;

        public VH(final View itemView) {
            super(itemView);
            this.itemView=itemView;
            name=itemView.findViewById(R.id.name);
            phone=itemView.findViewById(R.id.phone);

        }
    }


}
