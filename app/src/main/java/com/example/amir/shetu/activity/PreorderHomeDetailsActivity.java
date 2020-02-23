package com.example.amir.shetu.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.amir.shetu.R;
import com.example.amir.shetu.interfaces.ApiDataManager.PreOrderProductDetailsListener;
import com.example.amir.shetu.interfaces.ApiDataManager.WithdrawBidStatusListener;
import com.example.amir.shetu.manager.ApiDataManager;
import com.example.amir.shetu.manager.PreferenceManager;
import com.example.amir.shetu.manager.StaticDataManager;
import com.example.amir.shetu.model.CommodityPostDetail;

public class PreorderHomeDetailsActivity extends AppCompatActivity implements View.OnClickListener, PreOrderProductDetailsListener, WithdrawBidStatusListener {

    private Button bidBtn, withdrawBtn, modifyBtn;

    private TextView titleView, gradeView, quantityView, priceView, descriptionView, contactNumberView,remainQuantity,buyerName,deliveryPlace,deliveryTime,bidStatus;

    private int productId;

    private int userId,ramainQntty;

    private int bidId;

    private LinearLayout container;

    private CommodityPostDetail product;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preorder_bid_details);
        Bundle bundle = getIntent().getExtras();
        ActionBar actionBar = getSupportActionBar();
        productId = PreferenceManager.getInstance().getInt(PreferenceManager.PREORDER_PRODUCT_ID);
        userId = PreferenceManager.getInstance().getInt(PreferenceManager.USER_ID);
        ramainQntty=PreferenceManager.getInstance().getInt(PreferenceManager.PREORDER_REMAIN_QUANTITY);
        ApiDataManager.getPreorderPostDetail(this,productId,userId);
        actionBar.setDisplayHomeAsUpEnabled(true);
        Log.d("ProductID", String.valueOf(productId));
        Log.d("userID", String.valueOf(userId));

        Log.d("Activity", String.valueOf(userId));
        initializeView();
        progressBar.setVisibility(View.VISIBLE);
    }



    private void initializeView() {
        container=findViewById(R.id.container);

        deliveryPlace=findViewById(R.id.product_delivery_place);

        deliveryTime=findViewById(R.id.delivery_time);

        buyerName=findViewById(R.id.buyer_name);

        remainQuantity=findViewById(R.id.remain_quantity);

        bidBtn = findViewById(R.id.bid_btn);

        withdrawBtn = findViewById(R.id.withdraw_btn);

        titleView = findViewById(R.id.title);

        contactNumberView = findViewById(R.id.phone_no);

        descriptionView = findViewById(R.id.description);

        priceView = findViewById(R.id.price);

        quantityView = findViewById(R.id.quantity);

        gradeView = findViewById(R.id.grade);

        modifyBtn = findViewById(R.id.modify_btn);

        bidStatus=findViewById(R.id.prebid_status);

        progressBar = findViewById(R.id.progressBar);


    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.bid_btn) {
            Intent intent = new Intent(PreorderHomeDetailsActivity.this, PreorderBidandBidEditActivity.class);
            intent.putExtra(PreferenceManager.PREORDERFLAG, StaticDataManager.PREORDER_BID_FLAG);
            intent.putExtra(StaticDataManager.MAX_QUANTITY_PREF,product.getQuantity().toString());
            startActivity(intent);

        } else if (view.getId() == R.id.modify_btn) {

            Intent intent = new Intent(PreorderHomeDetailsActivity.this, PreorderBidandBidEditActivity.class);
            intent.putExtra(PreferenceManager.PREORDERFLAG, StaticDataManager.PREORDER_MODIFY_FLAG);
            intent.putExtra(StaticDataManager.MAX_QUANTITY_PREF,product.getQuantity().toString());
            startActivity(intent);
        } else {
            Log.d("bidID",String.valueOf(bidId));
           ApiDataManager.withdrawPreOrderBidStatus(this,bidId);
        }

        finish();

    }

    private void setView() {
        titleView.setText(product.getName());
        if(ramainQntty == 0)
        {
            remainQuantity.setText("0");
        }else{
            remainQuantity.setText(String.valueOf(ramainQntty));
        }
        buyerName.setText(product.getBuyerName());
        contactNumberView.setText(product.getBuyerPhone());
        deliveryPlace.setText(product.getDeliveryLocation());
        deliveryTime.setText(product.getDeliveryStartDate()+"-"+product.getDeliveryEndDate());
        descriptionView.setText(product.getDescription());
        priceView.setText(product.getPrice().toString()+"  "+"টাকা");
        quantityView.setText(product.getQuantity().toString()+"  "+product.getUnit());
        gradeView.setText(product.getGrade());
        bidStatus.setText(product.getStatus());
//        Log.d("Status",product.getStatus());
    }

    private void showWithdrawBtn() {

        withdrawBtn.setVisibility(View.VISIBLE);

        withdrawBtn.setOnClickListener(this);

    }

    private void hideWithdrawBtn() {

        withdrawBtn.setVisibility(View.GONE);

        withdrawBtn.setOnClickListener(null);

    }

    private void showModifyBtn() {

        modifyBtn.setVisibility(View.VISIBLE);

        modifyBtn.setOnClickListener(this);
    }

    private void hideModifyBtn() {

        modifyBtn.setVisibility(View.GONE);

        modifyBtn.setOnClickListener(null);
    }

    private void showBidBtn() {

        bidBtn.setVisibility(View.VISIBLE);

        bidBtn.setOnClickListener(this);
    }
    private void hideBidBtn() {

        bidBtn.setVisibility(View.GONE);

        bidBtn.setOnClickListener(null);
    }



    @Override
    public void getPreorderPostDetail(CommodityPostDetail product, String errorMessage) {

        this.product= product;

        if (hasBid()) {

            bidId = product.getBid().getId();
            Log.d("BidID",String.valueOf(bidId));
            PreferenceManager.getInstance().setInt(PreferenceManager.PREORDER_BID_ID,bidId);

            if (isBidStatusPending()) {

                showWithdrawBtn();

                showModifyBtn();
            }
            else if(product.getBid().getBidStatus().getStatus().equals(StaticDataManager.Withdrawn))
            {
                showBidBtn();
            }
            else if(product.getBid().getBidStatus().getStatus().equals(StaticDataManager.APPROVED))
            {
                hideBidBtn();
            }

        }else {

            if (notOwnProduct()) {

                showBidBtn();
            }

        }

        setView();

//        initializeImageSlider(product.getImages());

        container.setVisibility(View.VISIBLE);

        progressBar.setVisibility(View.GONE);

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
    private boolean isBidStatusPending() {

        return product.getBid().getBidStatusId() == StaticDataManager.PENDING_ID;
    }

    private boolean notOwnProduct() {

        return !product.getBuyerId().equals(userId);
    }

    private boolean hasBid() {

        return product.getBid() != null;
    }

    @Override
    public void getWithdrawBidStatus(boolean status, String errorMessage) {
        if (status) {

            hideModifyBtn();

            hideWithdrawBtn();
        }
    }
}
