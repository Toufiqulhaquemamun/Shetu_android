package com.example.amir.shetu.dialog;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.amir.shetu.R;
import com.example.amir.shetu.interfaces.ApiDataManager.SoldProductListener;
import com.example.amir.shetu.manager.ApiDataManager;
import com.example.amir.shetu.manager.PreferenceManager;
import com.example.amir.shetu.manager.StaticDataManager;
import com.example.amir.shetu.model.SellHistoryDetail;

import java.util.List;

public class SoldProductDialog extends DialogFragment implements SoldProductListener {

    private View view;
    
    private int productId;
    
    public TextView commodityNameView, askingPriceView, soldPriceView, shippingCostView,servicechargeView,

    vatView,totalView,bidTimeView,soldTimeView,buyerNameView,quantityView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView=inflater.inflate(R.layout.product_dialog,container);

        view=rootView;

        int id = PreferenceManager.getInstance().getInt(PreferenceManager.USER_ID);

        getProductId();

        ApiDataManager.getSoldProduct(this, 1,id);

        initializeView();

        return rootView;
    }

    private void initializeView() {

        commodityNameView = view.findViewById(R.id.name);

        askingPriceView = view.findViewById(R.id.asking_price);

        soldPriceView = view.findViewById(R.id.sold_price);

        shippingCostView = view.findViewById(R.id.shipping_cost);

        servicechargeView = view.findViewById(R.id.service_charge);

        vatView = view.findViewById(R.id.vat);

        totalView = view.findViewById(R.id.total);

        bidTimeView = view.findViewById(R.id.bid_time);

        soldTimeView = view.findViewById(R.id.sold_time);

        buyerNameView = view.findViewById(R.id.phone);

        quantityView = view.findViewById(R.id.quantity);
    }



    private void setView(SellHistoryDetail product) {

        commodityNameView.setText(product.getCommodityName());

        askingPriceView.setText(product.getAskingPrice()+ StaticDataManager.TAKA_SIGN);

        soldPriceView.setText(product.getSellingPrice()+StaticDataManager.TAKA_SIGN);

//        shippingCostView.setText(product.getShippingCost()+StaticDataManager.TAKA_SIGN);

        servicechargeView.setText(product.getServiceCharge()+StaticDataManager.TAKA_SIGN);

        vatView.setText(product.getTotal()+StaticDataManager.TAKA_SIGN);



        soldTimeView.setText(product.getSoldTime());

        buyerNameView.setText(product.getBuyerName());

        totalView.setText((product.getTotal()-product.getServiceCharge())+StaticDataManager.TAKA_SIGN);

        quantityView.setText(product.getQuantity()+"");

    }


    public void getProductId() {

        if (getArguments() != null) {

            productId = getArguments().getInt(PreferenceManager.PRODUCT_ID,0);

        }
    }

    @Override
    public void getSoldProduct(List<SellHistoryDetail> products, int endPage, String errorMessage) {

        for(int i=0;i<products.size();i++){

            if(products.get(i).getId()==productId){

                setView(products.get(i));
            }
        }
    }
}

