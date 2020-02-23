package com.example.amir.shetu.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.amir.shetu.R;
import com.example.amir.shetu.adapter.AllBidsAdapter;
import com.example.amir.shetu.interfaces.ApiDataManager.HomeProductDetailsListener;
import com.example.amir.shetu.interfaces.ApiDataManager.WithdrawBidStatusListener;
import com.example.amir.shetu.manager.ApiDataManager;
import com.example.amir.shetu.manager.PreferenceManager;
import com.example.amir.shetu.manager.StaticDataManager;
import com.example.amir.shetu.model.AllBidPrice;
import com.example.amir.shetu.model.CommodityPostDetail;
import com.example.amir.shetu.model.Image;
import com.example.amir.shetu.retorfit.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeProductDetailsActivity extends AppCompatActivity implements View.OnClickListener,HomeProductDetailsListener,WithdrawBidStatusListener,AllBidsAdapter.Listener {

    private static final String TAG = "HomeProDesActivity";
    private ViewPager _pager;

    private ImageButton leftBtn, rightBtn;


    private Button bidBtn, withdrawBtn, modifyBtn;

    private TextView titleView, gradeView, quantityView, priceView, descriptionView, companyNameView, companyLocationView, emailView, contactNumberView,bidnmbr;

    private int productId;

    private int userId,nmbrofBids,commodityId;

    private int id;

    private LinearLayout container;

    private CommodityPostDetail product;

    private ProgressBar progressBar;

    private RecyclerView recyclerView;
    private AllBidsAdapter adapter;
    double quantityTotal;





    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home_details);

        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);

        productId = PreferenceManager.getInstance().getInt(PreferenceManager.PRODUCT_ID);

        userId = PreferenceManager.getInstance().getInt(PreferenceManager.USER_ID);

        Log.d("ProductID", String.valueOf(productId)+String.valueOf(userId));

        initializeView();

        progressBar.setVisibility(View.VISIBLE);

        ApiDataManager.getHomeProductDetails(this,productId,userId);

        fetchAllProducts();

    }

    private void initializeView() {

        container=findViewById(R.id.container);

        companyLocationView = findViewById(R.id.company_location);

        bidBtn = findViewById(R.id.bid_btn);

        withdrawBtn = findViewById(R.id.withdraw_btn);

        titleView = findViewById(R.id.title);

        contactNumberView = findViewById(R.id.contact_number);

        emailView = findViewById(R.id.email);

        companyNameView = findViewById(R.id.company_name);

        descriptionView = findViewById(R.id.description);

        priceView = findViewById(R.id.price);

        quantityView = findViewById(R.id.quantity);

        gradeView = findViewById(R.id.grade);

        modifyBtn = findViewById(R.id.modify_btn);

        progressBar = findViewById(R.id.progressBar);

        bidnmbr=findViewById(R.id.bid_nmber);

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


    private void initializeImageSlider(List<Image> imageList) {
        _pager = (ViewPager) findViewById(R.id.pager);
        leftBtn = (ImageButton) findViewById(R.id.left_nav);
        rightBtn = (ImageButton) findViewById(R.id.right_nav);
        GalleryPagerAdapter _adapter = new GalleryPagerAdapter(HomeProductDetailsActivity.this, imageList);
        _pager.setAdapter(_adapter);

        _pager.setOffscreenPageLimit(4);
        _adapter.notifyDataSetChanged();
        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tab = _pager.getCurrentItem();
                if (tab > 0) {
                    tab--;
                    _pager.setCurrentItem(tab);
                } else if (tab == 0) {
                    _pager.setCurrentItem(tab);
                }
            }
        });

        rightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tab = _pager.getCurrentItem();
                tab++;
                _pager.setCurrentItem(tab);
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bid_btn) {
            Intent intent = new Intent(HomeProductDetailsActivity.this, BidAndBidEditActivity.class);
            intent.putExtra(PreferenceManager.FLAG, StaticDataManager.BID_FLAG);
            intent.putExtra(StaticDataManager.MAX_QUANTITY_PREF,product.getQuantity().toString());
            intent.putExtra(PreferenceManager.COMMODITY_ID, commodityId);
            startActivity(intent);

        } else if (v.getId() == R.id.modify_btn) {

            Intent intent = new Intent(HomeProductDetailsActivity.this, BidAndBidEditActivity.class);
            intent.putExtra(PreferenceManager.FLAG, StaticDataManager.MODIFY_FLAG);
            PreferenceManager.getInstance().setInt(PreferenceManager.BID_ID,id);
            intent.putExtra(StaticDataManager.MAX_QUANTITY_PREF,product.getQuantity().toString());
            intent.putExtra(PreferenceManager.COMMODITY_ID, commodityId);
            startActivity(intent);


        } else {

            ApiDataManager.getWithdrawBidStatus(this,id);
        }

        finish();

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

    private void fetchAllProducts() {
        progressBar.setVisibility(View.VISIBLE);

        RetrofitInstance.getApi().getAllBid(productId).enqueue(new Callback<List<AllBidPrice>>() {
            @Override
            public void onResponse(Call<List<AllBidPrice>> call, Response<List<AllBidPrice>> response) {
                if(response.code()== StaticDataManager.OK){
                    adapter=new AllBidsAdapter(HomeProductDetailsActivity.this,response.body(),HomeProductDetailsActivity.this, quantityTotal);
                    Log.d("Response",response.body().toString());
                    nmbrofBids=response.body().size();
                    Log.d("bids", String.valueOf(nmbrofBids));

                }
            }

            @Override
            public void onFailure(Call<List<AllBidPrice>> call, Throwable t) {
                Log.d("d","s");
            }
        });
    }


    @Override
    public void getHomeProductDetailsListener(CommodityPostDetail product, String errorMessage) {

        String token= getToken();

        this.product= product;
        commodityId=product.getCommodityId();

        if (hasBid()) {

            id = product.getBid().getId();

            if (isBidStatusPending()) {

                showWithdrawBtn();

                showModifyBtn();
            }
            else if(product.getBid().getBidStatus().getStatus().equals(StaticDataManager.Withdrawn))
            {
                showBidBtn();
            }

        }else {

            if (notOwnProduct()) {

                showBidBtn();
            }

        }

        setView();

        initializeImageSlider(product.getImages());

        container.setVisibility(View.VISIBLE);

        progressBar.setVisibility(View.GONE);

    }

    private void setView() {
        titleView.setText(product.getName());
        companyLocationView.setText(product.getOrganizationLocation());
        contactNumberView.setText(product.getSellerPhone());
        emailView.setText(product.getSellerEmail());
        bidnmbr.setText(String.valueOf(nmbrofBids));
        companyNameView.setText(product.getSellerName());
        descriptionView.setText(product.getFeatures());
        priceView.setText(product.getPrice().toString()+"  "+"টাকা");
        quantityView.setText(product.getQuantity().toString()+"  "+product.getUnit());
        gradeView.setText(product.getGrade());
    }

    private boolean isBidStatusPending() {

        return product.getBid().getBidStatusId() == StaticDataManager.PENDING_ID;
    }

    private boolean notOwnProduct() {

        return !product.getSellerId().equals(userId);
    }

    private boolean hasBid() {

        return product.getBid() != null;
    }

    private String getToken() {

        return PreferenceManager.getInstance().getString(PreferenceManager.TOKEN);
    }

    @Override
    public void getWithdrawBidStatus(boolean status, String errorMessage) {

        if (status) {

            hideModifyBtn();

            hideWithdrawBtn();
        }

    }

    @Override
    public void itemClick(View itemView, String name, int bidId,int pos) {

    }

    @Override
    public <T> void productClick(View itemView, T item) {

    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


    private class GalleryPagerAdapter extends PagerAdapter {
        Context _context;
        LayoutInflater _inflater;
        List<Image> imageList;

        GalleryPagerAdapter(Context context, List<Image> imageList) {
            _context = context;
            _inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            this.imageList = imageList;
        }

        @Override
        public int getCount() {
            return imageList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == (object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            View itemView = _inflater.inflate(R.layout.pager_gallery_item, container, false);
            container.addView(itemView);
            final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(300, 230);
            params.setMargins(3, 3, 3, 3);


            final ImageView imageView = itemView.findViewById(R.id.image);
            Glide.with(HomeProductDetailsActivity.this).load(StaticDataManager.COMMODITY_IMAGE_PREFIX + "" + imageList.get(position).getFile())
                    .apply(new RequestOptions()
                            .placeholder(R.drawable.placeholder)
                            .error(R.drawable.placeholder))
                    .into(imageView);
            return itemView;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((LinearLayout) object);
        }


    }
}
