package com.example.amir.shetu.activity;


import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.amir.shetu.R;
import com.example.amir.shetu.dialog.PurchaseProductDialog;
import com.example.amir.shetu.interfaces.ApiDataManager.PurchasedProductListener;
import com.example.amir.shetu.manager.ApiDataManager;
import com.example.amir.shetu.manager.PreferenceManager;
import com.example.amir.shetu.model.SellHistoryDetail;

import java.util.List;

public class PurchaseProductDetails extends AppCompatActivity implements PurchasedProductListener {

    private Button moreDetailsBtn;

    private TextView titleView, serviceCharge,quantityView, sellingPrice, soldTime, sellerName, buyerName,vatView, shippingCostView,totalView,

    askingPrice, bidTime;

    private int productId;

    private int bidId;

    private ImageView moneyReceiptView;

    private FragmentManager fm;

    private  Bundle bundle;

    private ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_product_details);

        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);

        if(getIntent().getExtras()==null){
            bidId= PreferenceManager.getInstance().getInt(PreferenceManager.BID_ID);

            productId=PreferenceManager.getInstance().getInt(PreferenceManager.PRODUCT_ID);
        }else{
            bidId=getIntent().getExtras().getInt(PreferenceManager.PRODUCT_ID);

            productId=getIntent().getExtras().getInt(PreferenceManager.PRODUCT_ID);

            PreferenceManager.getInstance().setInt(PreferenceManager.BID_ID,bidId);

            PreferenceManager.getInstance().setInt(PreferenceManager.PRODUCT_ID,productId);
        }

        initializeView();

        bundle = new Bundle();

        fm=getFragmentManager();

        fetchPurchaseProducts();

        moreDetailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bundle = new Bundle();

                bundle.putInt(PreferenceManager.PRODUCT_ID, productId);

                PurchaseProductDialog purchaseProductDialog =new PurchaseProductDialog();

                purchaseProductDialog.setArguments(bundle);

                purchaseProductDialog.show(fm,"");

            }
        });
        
    }

    private void initializeView() {
        buyerName =findViewById(R.id.phone);

        titleView=findViewById(R.id.name);

        bidTime =findViewById(R.id.bid_time);

        askingPrice =findViewById(R.id.asking_price);

        sellerName =findViewById(R.id.seller_name);

        soldTime =findViewById(R.id.sold_time);

        sellingPrice =findViewById(R.id.sold_price);

        quantityView=findViewById(R.id.quantity);

        serviceCharge =findViewById(R.id.service_charge);

        shippingCostView =findViewById(R.id.shipping_cost);

        vatView =findViewById(R.id.vat);

        totalView =findViewById(R.id.total);

        progressBar=findViewById(R.id.progressBar);

        moreDetailsBtn=findViewById(R.id.more);


        moneyReceiptView=findViewById(R.id.money_receipt_view);

    }

    private void fetchPurchaseProducts() {

        progressBar.setVisibility(View.GONE);

        int id = PreferenceManager.getInstance().getInt(PreferenceManager.USER_ID);

        ApiDataManager.getPurchasedProduct(this,1,id);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();

        return true;
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }

    @Override
    public void getPurchasedProduct(List<SellHistoryDetail> products, int endPage, String errorMessage) {

        progressBar.setVisibility(View.GONE);

        List<SellHistoryDetail> datas = products;

        for (int i=0;i<products.size();i++){
            if(products.get(i).getId()==productId){
                titleView.setText(products.get(i).getCommodityName());
                buyerName.setText(products.get(i).getBuyerName());
//                bidTime.setText(products.get(i).getProductBidTime());
                askingPrice.setText(products.get(i).getAskingPrice()+" TK");
                sellerName.setText(products.get(i).getSellerName());
                soldTime.setText(products.get(i).getSoldTime());
                sellingPrice.setText(products.get(i).getSellingPrice() + " Tk");
                quantityView.setText(products.get(i).getQuantity()+" "+products.get(i).getUnit());
                serviceCharge.setText(products.get(i).getServiceCharge()+" Tk");

                vatView.setText(products.get(i).getVat()+" Tk");

                shippingCostView.setText(products.get(i).getShippingCost()+" Tk");

                totalView.setText(products.get(i).getTotal()+" Tk");

            }
        }

    }
}
