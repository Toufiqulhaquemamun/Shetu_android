package com.example.amir.shetu.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import com.example.amir.shetu.model.PdffileList;

import java.util.List;

public class ProductGradeAdapter extends RecyclerView.Adapter<ProductGradeAdapter.MyViewHolder> {


    private List<PdffileList> fileList;
    private ProductClickListener listener;
    private Context mctnx;


    public ProductGradeAdapter(List<PdffileList> fileList, ProductClickListener listener,Context context) {

        this.fileList=fileList;
        this.listener=listener;
        this.mctnx=context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.file_list_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final PdffileList list=fileList.get(position);

        holder.txtName.setText(list.getBnName());
        holder.txtBusinessType.setText(list.getCategory().getBnName());
        holder.txtDesc.setText(list.getBnDescription());

        holder.link_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW,Uri.parse("http://docs.google.com/gview?embedded=true&url="+StaticDataManager.GRADE_DOCUMENT+list.getGradeDocument()));
                Log.d("List",Uri.parse(StaticDataManager.GRADE_DOCUMENT+list.getGradeDocument()).toString());
                mctnx.startActivity(browserIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return fileList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtName,txtBusinessType,txtDesc,txtLink;
        Button link_btn;
        View itemView;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.itemView=itemView;
            txtName=itemView.findViewById(R.id.file_name_txt);
            txtBusinessType=itemView.findViewById(R.id.business_type_txt);
            txtDesc=itemView.findViewById(R.id.description_txt);
            link_btn=itemView.findViewById(R.id.attach_file_txt);
        }
    }
}
