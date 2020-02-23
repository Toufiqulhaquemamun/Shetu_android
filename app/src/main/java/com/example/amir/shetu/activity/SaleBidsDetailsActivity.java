package com.example.amir.shetu.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.example.amir.shetu.model.SaleableProductBidDetails;
import com.example.amir.shetu.manager.PreferenceManager;
import com.example.amir.shetu.manager.StaticDataManager;
import com.example.amir.shetu.retorfit.RetrofitInstance;
import com.example.amir.shetu.model.Image;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SaleBidsDetailsActivity extends AppCompatActivity {
    private TextView quantityView,priceView,descriptionView,totalPriceView;
    private Button allBidBtn;
    private ViewPager _pager;
    private ImageButton leftBtn,rightBtn;
    private int productId;
    public double quantityTotal;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_bids_details);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        if(getIntent().getExtras()==null){
            productId=PreferenceManager.getInstance().getInt(PreferenceManager.PRODUCT_ID);
        }else {
            productId=getIntent().getExtras().getInt("id");
            PreferenceManager.getInstance().setInt(PreferenceManager.PRODUCT_ID,productId);

        }

        initializeView();
        fetchAllProducts(productId);
        allBidBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SaleBidsDetailsActivity.this,AllBidsActivity.class);
                intent.putExtra("productId",productId);
                intent.putExtra("totalQuantity",quantityTotal);
                startActivity(intent);
            }
        });

    }
    private void initializeView() {
        descriptionView=findViewById(R.id.description);
        priceView=findViewById(R.id.price);
        quantityView=findViewById(R.id.quantity);
        totalPriceView=findViewById(R.id.total_price);
        allBidBtn=findViewById(R.id.all_bid_btn);

        progressBar=findViewById(R.id.progressBar);

    }

    private void fetchAllProducts(int productId) {

        progressBar.setVisibility(View.VISIBLE);

        RetrofitInstance.getApi()
                .getSaleableProductBidDetails(productId)
                .enqueue(new Callback<SaleableProductBidDetails>() {
            @Override
            public void onResponse(Call<SaleableProductBidDetails> call, Response<SaleableProductBidDetails> response) {
                if(response.code()== StaticDataManager.OK){
                    priceView.setText(response.body().getAskingPrice()+" TK");
                    double total=response.body().getAskingPrice()*response.body().getQuantity();
                    totalPriceView.setText(total+" টাকা");
                    quantityTotal=response.body().getQuantity();
                    quantityView.setText(response.body().getQuantity()+"  "+response.body().getUnit());
                    descriptionView.setText(response.body().getDescription());
                    initializeImageSlider(response.body().getImages());
                }

                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<SaleableProductBidDetails> call, Throwable t) {
                Log.d("d","d");
            }
        });
    }


    private void initializeImageSlider(List<Image> imageList) {
        _pager = (ViewPager) findViewById(R.id.pager);
        leftBtn = (ImageButton)findViewById(R.id.left_nav);
        rightBtn =  (ImageButton) findViewById(R.id.right_nav);
        SaleBidsDetailsActivity.GalleryPagerAdapter _adapter = new SaleBidsDetailsActivity.GalleryPagerAdapter( this,imageList);
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

    private class GalleryPagerAdapter extends PagerAdapter {
        Context _context;
        LayoutInflater _inflater;
        List<Image> imageList;

        GalleryPagerAdapter(Context context,List<Image> imageList) {
            _context = context;
            _inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            this.imageList=imageList;
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
            final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(300,260);
            params.setMargins(3,3,3,3);


            final ImageView imageView = itemView.findViewById(R.id.image);
            Glide.with( SaleBidsDetailsActivity.this).load(StaticDataManager.COMMODITY_IMAGE_PREFIX +"" + imageList.get(position).getFile())
                    .apply(new RequestOptions()
                            .placeholder( R.drawable.placeholder)
                            .error(R.drawable.placeholder))
                    .into(imageView);
            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((LinearLayout) object);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        onBackPressed();
      return true;
    }
}


