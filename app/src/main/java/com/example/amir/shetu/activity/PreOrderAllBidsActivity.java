package com.example.amir.shetu.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.amir.shetu.R;
import com.example.amir.shetu.adapter.PreOrderAllBidsAdapter;
import com.example.amir.shetu.manager.PreferenceManager;
import com.example.amir.shetu.manager.StaticDataManager;
import com.example.amir.shetu.model.BidsOfSeller;
import com.example.amir.shetu.retorfit.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PreOrderAllBidsActivity extends AppCompatActivity implements PreOrderAllBidsAdapter.Listener  {
    private RecyclerView recyclerView;
    private int preoOrderproductId;
    private String Status="";
    private static final String TAG = "PreOrderAllBidsActivity";
    private ProgressBar progressBar;
    int productQuantity,totalproductPrice;
    private TextView quantity,totalPrice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_order_all_bids);

        quantity=findViewById(R.id.quantity);
        totalPrice=findViewById(R.id.product_totalprice);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        if(getIntent().getExtras()==null){
            preoOrderproductId= PreferenceManager.getInstance().getInt(PreferenceManager.PREORDER_PRODUCT_ID);
            Log.d("PreProID", String.valueOf(preoOrderproductId));
        }else {
            preoOrderproductId=getIntent().getExtras().getInt(PreferenceManager.PREORDER_PRODUCT_ID);
            Log.d("PreProIDIntent", String.valueOf(preoOrderproductId));
        }
        progressBar=findViewById(R.id.progressbar);
        fetchAllProducts();
        recyclerView=findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void fetchAllProducts() {
        progressBar.setVisibility(View.VISIBLE);

        RetrofitInstance.getApi().getBidsofSeller(preoOrderproductId).enqueue(new Callback<List<BidsOfSeller>>() {
            @Override
            public void onResponse(Call<List<BidsOfSeller>> call, Response<List<BidsOfSeller>> response) {
                if(response.code()== StaticDataManager.OK)
                {
                    Log.d("RESPONSE",response.body().toString());
                    PreOrderAllBidsAdapter adapter = new PreOrderAllBidsAdapter(response.body(),PreOrderAllBidsActivity.this,getBaseContext());
                    recyclerView.setAdapter(adapter);
                    progressBar.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<BidsOfSeller>> call, Throwable t) {
                Log.d("Failure",t.getMessage());
            }
        });
    }
    @Override
    public void itemClick(View itemView, int bidId, int pos) {
        BidsOfSeller model= (BidsOfSeller) itemView.getTag();

        Intent intent = new Intent(this, PreOrderPurchaseBidDetailsActivity.class);

        intent.putExtra(PreferenceManager.PREORDER_BID_ID, bidId);

        intent.putExtra(PreferenceManager.PREORDER_PRODUCT_ID, preoOrderproductId);

        startActivity(intent);


    }


    @Override
    public <T> void productClick(View itemView, T item) {

    }
}
