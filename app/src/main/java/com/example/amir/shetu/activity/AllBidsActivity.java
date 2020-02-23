package com.example.amir.shetu.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.amir.shetu.R;
import com.example.amir.shetu.adapter.AllBidsAdapter;
import com.example.amir.shetu.model.BidsProduct;
import com.example.amir.shetu.manager.PreferenceManager;
import com.example.amir.shetu.manager.StaticDataManager;
import com.example.amir.shetu.model.DeviceList;
import com.example.amir.shetu.retorfit.RetrofitInstance;
import com.example.amir.shetu.model.AllBidPrice;
import com.msoftworks.easynotify.EasyNotify;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllBidsActivity extends AppCompatActivity implements AllBidsAdapter.Listener {
    private RecyclerView recyclerView;
    private AllBidsAdapter adapter;
    private int productId;
    private String Status="";

    public double quantityTotal;
    private static final String TAG = "AllBidsActivity";

    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_bids);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        if(getIntent().getExtras()==null){
            productId=PreferenceManager.getInstance().getInt("productId");
        }else {
            productId=getIntent().getExtras().getInt("productId");
            quantityTotal=getIntent().getExtras().getDouble("totalQuantity");
        }


        progressBar=findViewById(R.id.progressBar);


        fetchAllProducts();

        recyclerView=findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }




    @Override
    public void itemClick(View itemView,String buttonName,int bidId,int buyerId) {
        AllBidPrice model= (AllBidPrice) itemView.getTag();
        if(buttonName.equals(StaticDataManager.APPROVED_BUTTON_NAME)){
            changeStatus(bidId);
            deviceList(buyerId,"Approved");

        }
        else if(buttonName.equals(StaticDataManager.CANCEL_BUTTON_NAME))
        {
            changeStatus(bidId);
            deviceList(buyerId,"Canceled");

        }
        else {
            changeStatus(bidId);
        }
    }

    @Override
    public <T> void productClick(View itemView, T item) {
        AllBidPrice model= (AllBidPrice) item;
    }


    private void changeStatus(int bidId) {
        progressBar.setVisibility(View.VISIBLE);
        RetrofitInstance.getApi().changeStatus(bidId)
        .enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if(response.code()==StaticDataManager.OK){
                    PreferenceManager.getInstance().setInt("productId",productId);
                    finish();
                    startActivity(getIntent());
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.d("d","d");
            }
        });
    }

    private void fetchAllProducts() {
        progressBar.setVisibility(View.VISIBLE);
        Log.d("ProductID", String.valueOf(productId));
        RetrofitInstance.getApi().getAllBid(productId).enqueue(new Callback<List<AllBidPrice>>() {
            @Override
            public void onResponse(Call<List<AllBidPrice>> call, Response<List<AllBidPrice>> response) {
                if(response.code()== StaticDataManager.OK){
                    boolean flag = false;
                    adapter=new AllBidsAdapter(AllBidsActivity.this,response.body(),AllBidsActivity.this,quantityTotal);
                    Log.d("Response",response.body().toString());
                    recyclerView.setAdapter(adapter);
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<List<AllBidPrice>> call, Throwable t) {
                Log.d("d","s");
            }
        });
    }




    public void deviceList(int buyerId, final String s)
    {
        RetrofitInstance.getApi().getDeviceList(buyerId).enqueue(new Callback<List<DeviceList>>() {
            @Override
            public void onResponse(Call<List<DeviceList>> call, Response<List<DeviceList>> response) {
                if(response.code()==StaticDataManager.OK)
                {
                    for (DeviceList list : response.body()){
                        notification(list.getToken(),s);
                        Log.i("Tokens", list.getToken());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<DeviceList>> call, Throwable t) {

            }
        });
    }

    public void notification(String token,String s)
    {
        PreferenceManager manager = PreferenceManager.getInstance();
        String fcmtoken=manager.getString("FCM_token");

        EasyNotify easyNotify = new EasyNotify(StaticDataManager.API_KEY);
        easyNotify.setSendBy(EasyNotify.TOKEN);
        easyNotify.setToken(token);
        easyNotify.setTitle("Bid Status");
        easyNotify.setBody("Your Bid is"+" "+s);
//        easyNotify.setClickAction(click_action.getText().toString());
        easyNotify.nPush();
        easyNotify.setEasyNotifyListener(new EasyNotify.EasyNotifyListener() {
            @Override
            public void onNotifySuccess(String s) {
                //Toast.makeText(AllBidsActivity.this, s, Toast.LENGTH_SHORT).show();
                Log.d("Success",s);
            }

            @Override
            public void onNotifyError(String s) {
                //Toast.makeText(AllBidsActivity.this, s, Toast.LENGTH_SHORT).show();
                Log.d("Fail",s);
            }

        });
    }
}
