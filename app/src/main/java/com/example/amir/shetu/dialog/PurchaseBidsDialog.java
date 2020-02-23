package com.example.amir.shetu.dialog;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.amir.shetu.R;
import com.example.amir.shetu.interfaces.ApiDataManager.PurchaseBidsListener;
import com.example.amir.shetu.manager.ApiDataManager;
import com.example.amir.shetu.manager.PreferenceManager;
import com.example.amir.shetu.model.BiddedCommodityPostList;

import java.util.List;

public class PurchaseBidsDialog extends DialogFragment implements PurchaseBidsListener {

    private View view;

    private int productId;

    private TextView productName, quantity, askingPrice, expireDateTime, sellerName, bidDateTime, status;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.purchase_bids_dialog, container);
        view = rootView;

        int id = PreferenceManager.getInstance().getInt(PreferenceManager.USER_ID);

        getProductId();

        ApiDataManager.getPurchaseBids(this, 1, id);

        initializeView();

        return rootView;
    }

    private void initializeView() {

        productName = view.findViewById(R.id.name);

        quantity = view.findViewById(R.id.quantity);

        askingPrice = view.findViewById(R.id.asking_price);

        expireDateTime = view.findViewById(R.id.expire_date_time);

        sellerName = view.findViewById(R.id.seller_name);

        bidDateTime = view.findViewById(R.id.bid_time);

        status = view.findViewById(R.id.status);

    }


    private void setView(BiddedCommodityPostList product) {

        productName.setText(product.getCommodityName());

        quantity.setText(product.getQuantity()+"");

        askingPrice.setText(product.getPrice()+"");

        String date[] = product.getExpiryTime().split("T", 2);

        expireDateTime.setText(date[0] + "\n" + date[1]);

        sellerName.setText(product.getSellerName());

        date = product.getBiddingTime().split("T", 2);

        String date2[] = date[1].split("\\.", 2);

        bidDateTime.setText(date[0] + "\n" + date2[0]);

        status.setText(product.getBidStatus());

    }


    public void getProductId() {

        if (getArguments() != null) {

            productId = getArguments().getInt(PreferenceManager.PRODUCT_ID, 0);

        }
    }




    @Override
    public void getPurchaseBids(List<BiddedCommodityPostList> bids, int endPage, String errorMessage) {

        for (int i = 0; i < bids.size(); i++) {

            if (bids.get(i).commodityId == productId) {

                setView(bids.get(i));
            }
        }
    }
}
