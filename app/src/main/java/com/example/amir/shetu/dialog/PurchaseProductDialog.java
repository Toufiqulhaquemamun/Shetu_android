package com.example.amir.shetu.dialog;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.amir.shetu.R;
import com.example.amir.shetu.interfaces.ApiDataManager.PurchasedProductListener;
import com.example.amir.shetu.manager.ApiDataManager;
import com.example.amir.shetu.manager.PreferenceManager;
import com.example.amir.shetu.manager.StaticDataManager;
import com.example.amir.shetu.model.SellHistoryDetail;

import java.util.List;

public class PurchaseProductDialog extends DialogFragment implements PurchasedProductListener {

    private View view;

    private int productId;

    private TextView titleView, serviceCharge,quantityView, sellingPrice, soldTime, sellerName, buyerName,

    vatView, shippingCostView,totalView,

    askingPrice, bidTime;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView=inflater.inflate(R.layout.purchase_product_dialog,container);

        view=rootView;

        int id = PreferenceManager.getInstance().getInt(PreferenceManager.USER_ID);

        getProductId();

        ApiDataManager.getPurchasedProduct(this, 1,id);

        initializeView();

        return rootView;
    }

    private void initializeView() {

//        buyerName =view.findViewById(R.id.buyer_name_per);

        titleView=view.findViewById(R.id.name);

        bidTime =view.findViewById(R.id.bid_time);

        askingPrice =view.findViewById(R.id.asking_price);

        sellerName =view.findViewById(R.id.seller_name);

        soldTime =view.findViewById(R.id.sell_time);

        sellingPrice =view.findViewById(R.id.sold_price);

        quantityView=view.findViewById(R.id.quantity);

        serviceCharge =view.findViewById(R.id.service_charge);


        shippingCostView =view.findViewById(R.id.shipping_cost);

        vatView =view.findViewById(R.id.vat);

        totalView =view.findViewById(R.id.total);
    }



    private void setView(SellHistoryDetail product) {

        titleView.setText(product.getCommodityName());

//        buyerName.setText(product.getBuyerName());

        askingPrice.setText(product.getAskingPrice()+ StaticDataManager.TAKA_SIGN);

        sellerName.setText(product.getSellerName());

        soldTime.setText(product.getSoldTime());

        sellingPrice.setText(product.getSellingPrice() +StaticDataManager.TAKA_SIGN);

        quantityView.setText(product.getQuantity()+" "+product.getUnit());

        serviceCharge.setText(product.getServiceCharge()+StaticDataManager.TAKA_SIGN);

        vatView.setText(product.getVat()+StaticDataManager.TAKA_SIGN);

        shippingCostView.setText(product.getShippingCost()+StaticDataManager.TAKA_SIGN);

        totalView.setText(product.getTotal()+StaticDataManager.TAKA_SIGN);
    }


    public void getProductId() {

        if (getArguments() != null) {

            productId = getArguments().getInt(PreferenceManager.PRODUCT_ID,0);

        }
    }


    @Override
    public void getPurchasedProduct(List<SellHistoryDetail> products, int endPage, String errorMessage) {

        for(int i=0;i<products.size();i++){

            if(products.get(i).getId()==productId){

                setView(products.get(i));
            }
        }

    }
}


