package com.example.amir.shetu.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.amir.shetu.R;
import com.example.amir.shetu.interfaces.other.ProductClickListener;
import com.example.amir.shetu.manager.StaticDataManager;
import com.example.amir.shetu.model.CommodityPostList;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter {

    private List<CommodityPostList.Data> productsList;
    private ProductClickListener listener;
    private String url;

    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;



    public HomeAdapter(List<CommodityPostList.Data> productList, ProductClickListener listener) {
        this.productsList = productList;
        this.listener = listener;
    }


    public class ProductViewHolder extends RecyclerView.ViewHolder {
        public TextView title, count, price;
        public ImageView thumbnail;
        public LinearLayout container;

        public ProductViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            count = view.findViewById(R.id.count);
            thumbnail = view.findViewById(R.id.thumbnail);
            price = view.findViewById(R.id.price);
            container = view.findViewById(R.id.container);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return productsList.get(position) != null ? VIEW_ITEM : VIEW_PROG;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh=null;
        if (viewType == VIEW_ITEM) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.home_product_list_card, parent, false);

            vh=new ProductViewHolder(v);
        }else {
            View v = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.progress_item, parent, false);

            vh = new ProgressViewHolder(v);
        }

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ProductViewHolder) {
            final CommodityPostList.Data product = productsList.get(position);
//        if(album.getBidId()==userId){
////            holder.container.setBackgroundColor(mContext.getResources().getColor(R.color.own_card_color));
////        }
            ((ProductViewHolder) holder).title.setText(product.getName());

            ((ProductViewHolder) holder).count.setText(product.getQuantity()+" "+product.getUnit());

            ((ProductViewHolder) holder).price.setText(product.getPrice()+StaticDataManager.TAKA_SIGN);

            url = StaticDataManager.COMMODITY_IMAGE_PREFIX + "" + product.getImage();

            Glide.with(((ProductViewHolder) holder).title.getContext())
                    .load(url)
                    .into(((ProductViewHolder) holder).thumbnail);



            ((ProductViewHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.productClick(v,product.getId());
                }
            });
        }else {
            ((ProgressViewHolder) holder).progressBar.setIndeterminate(true);
        }
    }




    @Override
    public int getItemCount() {
        return productsList.size();
    }

    public static class ProgressViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public ProgressViewHolder(View v) {
            super(v);
            progressBar = v.findViewById(R.id.progressBar1);
        }
    }
}
