package com.example.amir.shetu.activity;

import android.Manifest;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.amir.shetu.R;
import com.example.amir.shetu.dialog.PurchaseBidsDialog;
import com.example.amir.shetu.manager.ApiDataManager;
import com.example.amir.shetu.manager.PreferenceManager;
import com.example.amir.shetu.manager.StaticDataManager;
import com.example.amir.shetu.model.BidImage;
import com.example.amir.shetu.model.BidStatus;
import com.example.amir.shetu.model.BidsOfSeller;
import com.example.amir.shetu.model.CommodityPostDetail;
import com.example.amir.shetu.model.DeviceList;
import com.example.amir.shetu.model.Image;
import com.example.amir.shetu.retorfit.API;
import com.example.amir.shetu.retorfit.RetrofitInstance;
import com.msoftworks.easynotify.EasyNotify;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PreOrderPurchaseBidDetailsActivity extends AppCompatActivity implements View.OnClickListener,ApiDataManager.UploadReceipt{

    private API api= RetrofitInstance.getApi();
    private ViewPager _pager;

    private ImageButton leftBtn,rightBtn;

    private Button approveBtn,withdrawBtn,shippingBtn,moreDetailsBtn, captureBtn,submitBtn,tokenBtn;

    private TextView titleView,gradeView,quantityView,priceView,descriptionView,companyNameView,companyLocationView,

    emailView,contactNumberView,bidStatus,bidPrice,bidQuantity,receipt_title;

    private EditText accountInfo;

    private int productId;

    int tokenCount=0;

    private int bidId,sellerId;

    private ImageView moneyReceiptView;

    private FragmentManager fm;

    private  Bundle bundle;
    String picturePath;

    private ProgressBar progressBar;
    private final String DATE_FORMATE = "yyyyMMddHHmmss";
    private String imageFilePath;
    String mediaPath;
    private LinearLayout container,accont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_order_purchase_bid_details);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        if(getIntent().getExtras()==null) {
            bidId = PreferenceManager.getInstance().getInt(PreferenceManager.PREORDER_BID_ID);
            productId = PreferenceManager.getInstance().getInt(PreferenceManager.PREORDER_PRODUCT_ID);
            Log.d("Null", String.valueOf(productId));
        }
        else{
            bidId=getIntent().getExtras().getInt(PreferenceManager.PREORDER_BID_ID);

            productId=getIntent().getExtras().getInt(PreferenceManager.PREORDER_PRODUCT_ID);
            Log.d("Vales", String.valueOf(bidId));
        }

        initializeView();

        fetchDetails();

    }


    private void initializeView() {

        container=findViewById(R.id.container);

        approveBtn=findViewById(R.id.approve);

        withdrawBtn=findViewById(R.id.cancel_btn);

        titleView=findViewById(R.id.title);

        contactNumberView=findViewById(R.id.contact_number);

        companyNameView=findViewById(R.id.company_name);

        descriptionView=findViewById(R.id.description);

        priceView=findViewById(R.id.price);

        quantityView=findViewById(R.id.quantity);

        gradeView=findViewById(R.id.grade);

        bidStatus=findViewById(R.id.bid_status);

        bidPrice=findViewById(R.id.bid_price);

        bidQuantity=findViewById(R.id.bid_quantity);

        shippingBtn=findViewById(R.id.shipping_btn);

        moreDetailsBtn=findViewById(R.id.more);

        captureBtn =findViewById(R.id.btn_capture);

        moneyReceiptView=findViewById(R.id.money_receipt_view);

        submitBtn=findViewById(R.id.submit);

        accountInfo=findViewById(R.id.account_details);

        receipt_title=findViewById(R.id.receipt_title);

        progressBar=findViewById(R.id.progressBar);
        accont=findViewById(R.id.accounts_layout);
        tokenBtn=findViewById(R.id.sendToken);


    }


    private void fetchDetails()
    {
        progressBar.setVisibility(View.VISIBLE);
        api.getPreorderBidDetails(bidId).enqueue(new Callback<BidsOfSeller>() {
                                                                @Override
                                                                public void onResponse(Call<BidsOfSeller> call, Response<BidsOfSeller> response) {
                                                                    if(response.code()== StaticDataManager.OK){
                                                                        Log.d("Success", response.body().getCommodity().getBnDescription());
                                                                        BidsOfSeller commodityPostDetail=response.body();
                                                                        sellerId=commodityPostDetail.getSellerId();
                                                                        Log.d("Seller Id", commodityPostDetail.getBidStatus().getStatus());
                                                                        titleView.setText(commodityPostDetail.getCommodity().getBnName());

                                                                        descriptionView.setText(commodityPostDetail.getPreOrder().getDescription());

                                                                        priceView.setText(commodityPostDetail.getPreOrder().getExpectedPrice()+" টাকা");

                                                                        quantityView.setText(commodityPostDetail.getPreOrder().getExpectedQuantity()+" "+commodityPostDetail.getUnit().getName());

                                                                        bidStatus.setText(commodityPostDetail.getBidStatus().getDisplayBnName());


                                                                        gradeView.setText(commodityPostDetail.getGrade().getBnGradeName());
                                                                        bidQuantity.setText(commodityPostDetail.getQuantity()+" "+commodityPostDetail.getUnit().getName());

                                                                        bidPrice.setText(String.valueOf(commodityPostDetail.getPrice())+" টাকা");

                                                                        if(commodityPostDetail.getMoneyReciept()!=null) {

                                                                            String moneyReceipt = commodityPostDetail.getMoneyReciept();

                                                                            if (moneyReceipt != null) {

                                                                                String fullPath = StaticDataManager.MONEY_RECEIPT_IMAGE_PREFIX + moneyReceipt;
                                                                                PreferenceManager manager = PreferenceManager.getInstance();
                                                                                String acc = manager.getString(PreferenceManager.ACC_INFO);
                                                                                showMoneyReceipt(fullPath, acc);
                                                                            }

                                                                        }
                                                                            BidStatus bid=response.body().getBidStatus();

                                                                            if(commodityPostDetail.getBidStatus().getStatus().equals(StaticDataManager.PENDING)){
                                                                                showApproveButton();
                                                                            }
                                                                            else if(commodityPostDetail.getBidStatus().getStatus().equals(StaticDataManager.APPROVED)){
                                                                                showCancelButton();
                                                                            }
                                                                            else if(commodityPostDetail.getBidStatus().getStatus().equals(StaticDataManager.SHIPPING)){

                                                                                shippingBtn.setVisibility(View.VISIBLE);
                                                                                accountInfo.setVisibility(View.INVISIBLE);
                                                                                shippingBtn.setOnClickListener(new View.OnClickListener() {
                                                                                    @Override
                                                                                    public void onClick(View v) {

                                                                                        shippingStatusToServer();
                                                                                    }
                                                                                });

                                                                            }else if(commodityPostDetail.getBidStatus().getStatus().equals(StaticDataManager.Locked)){
                                                                                accont.setVisibility(View.VISIBLE);
                                                                                captureBtn.setVisibility(View.VISIBLE);
                                                                                captureBtn.setOnClickListener(new View.OnClickListener() {
                                                                                    @Override
                                                                                    public void onClick(View v) {
                                                                                        if(Build.VERSION.SDK_INT>Build.VERSION_CODES.LOLLIPOP){
                                                                                            checkPermission();
                                                                                        }else {
                                                                                            captureImage();
                                                                                        }


                                                                                    }
                                                                                });
                                                                            }
                                                                            else if(commodityPostDetail.getBidStatus().getStatus().equals(StaticDataManager.BuyerRecvStatus))
                                                                            {
                                                                                tokenBtn.setVisibility(View.VISIBLE);

                                                                               moneyReceiptView.setVisibility(View.VISIBLE);
                                                                                tokenBtn.setOnClickListener(new View.OnClickListener() {
                                                                                    @Override
                                                                                    public void onClick(View view) {
                                                                                        progressBar.setVisibility(View.VISIBLE);
                                                                                            sendToken();
                                                                                            tokenBtn.setText("পুনরায় টোকেন পাঠান");
                                                                                    }
                                                                                });
                                                                            }

                                                                        initializeImageSlider(commodityPostDetail.getBidImages());
                                                                        }



                                                                        progressBar.setVisibility(View.GONE);

                                                                        container.setVisibility(View.VISIBLE);
                                                                    }

                                                                @Override
                                                                public void onFailure(Call<BidsOfSeller> call, Throwable t) {
                                                                    Log.d("###############",t.getMessage());
                                                                }
                                                            }
        );


    }

    private void initializeImageSlider(List<BidImage> imageList) {
        _pager = (ViewPager) findViewById(R.id.pager);
        leftBtn = (ImageButton) findViewById(R.id.left_nav);
        rightBtn = (ImageButton) findViewById(R.id.right_nav);
        GalleryPagerAdapter _adapter = new GalleryPagerAdapter(PreOrderPurchaseBidDetailsActivity.this, imageList);
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
        List<BidImage> imageList;

        GalleryPagerAdapter(Context context, List<BidImage> imageList) {
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
            Glide.with(PreOrderPurchaseBidDetailsActivity.this).load(StaticDataManager.COMMODITY_IMAGE_PREFIX + "" + imageList.get(position).getImage())
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
    private void sendToken() {
        Log.d("BidID", String.valueOf(bidId));
        api.sendSmsToken(bidId).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if(response.code()==StaticDataManager.OK)
                {
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext(),"SuccessFully send Token",Toast.LENGTH_SHORT).show();

                }
                else {
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext(),"Error"+response.code(),Toast.LENGTH_SHORT).show();
                }

        }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void checkPermission() {
        if (isPermissionNotGranted()) {

            if (isPermissionNotPermanentlyDenied()) {
                showExplanationDialog();
            } else {
                askPermission();

            }
        } else {
            captureImage();
        }
    }

    private void showExplanationDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.dialog_message)
                .setTitle(R.string.dialog_title)
                .setIcon(R.drawable.info)
                .setPositiveButton("ঠিক আছে", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        askPermission();
                    }
                });

        builder.create().show();
    }

    private void askPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                StaticDataManager.REQUEST_CODE_ASK_PERMISSIONS);
    }

    private boolean isPermissionNotPermanentlyDenied() {
        return ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    private boolean isPermissionNotGranted() {
        return ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(grantResults.length>0 && requestCode==StaticDataManager.REQUEST_CODE_ASK_PERMISSIONS){
            if(isPermissionNotGranted() && !isPermissionNotPermanentlyDenied()){
                Toast.makeText(this,getString(R.string.permission_permanently_denied),Toast.LENGTH_LONG).show();

            }else if(!isPermissionNotGranted()){
                captureImage();
            }
        }
    }


    private void showMoneyReceipt(String moneyReceipt,String acc) {
        receipt_title.setVisibility(View.VISIBLE);
        moneyReceiptView.setVisibility(View.VISIBLE);


        Glide.with(this).load(moneyReceipt).into(moneyReceiptView);
    }



    private void captureImage() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        startActivityForResult(cameraIntent, StaticDataManager.CAMERA_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == StaticDataManager.CAMERA_REQUEST && resultCode == Activity.RESULT_OK&&data!=null) {

            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            assert cursor != null;
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            mediaPath = cursor.getString(columnIndex);
            showMoneyReciept(Uri.parse(mediaPath));


            submitBtn.setVisibility(View.VISIBLE);

            submitBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String s = accountInfo.getText().toString();
                    PreferenceManager.getInstance().setString(PreferenceManager.ACC_INFO,s);
                    if(TextUtils.isEmpty(s))
                    {
                        Toast.makeText(getApplicationContext(), "আপনার একাউন্টের তথ্য প্রদান করুন", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    else{
                        ApiDataManager.uploadMoneyReceipt(PreOrderPurchaseBidDetailsActivity.this,PreOrderPurchaseBidDetailsActivity.this,bidId,s,Uri.parse(mediaPath));
                        submitBtn.setActivated(false);
                        Log.d("Seller Id", String.valueOf(sellerId));
         //               deviceList(sellerId);
                        progressBar.setVisibility(View.VISIBLE);
                    }


                }
            });


        }
    }

//    public void deviceList(int buyerId)
//    {
//        RetrofitInstance.getApi().getDeviceList(buyerId).enqueue(new Callback<List<DeviceList>>() {
//            @Override
//            public void onResponse(Call<List<DeviceList>> call, Response<List<DeviceList>> response) {
//                if(response.code()==StaticDataManager.OK)
//                {
//                    for (DeviceList list : response.body()){
//                        notification(list.getToken());
//                        Log.i("Tokens", list.getToken());
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<DeviceList>> call, Throwable t) {
//
//            }
//        });
//    }

//    public void notification(String token)
//    {
//        PreferenceManager manager = PreferenceManager.getInstance();
//        String fcmtoken=manager.getString("FCM_token");
//
//        EasyNotify easyNotify = new EasyNotify(StaticDataManager.API_KEY);
//        easyNotify.setSendBy(EasyNotify.TOKEN);
//        easyNotify.setToken(token);
//        easyNotify.setTitle("Money Recipt Status");
//        easyNotify.setBody("Money recipt Uploaded");
//        easyNotify.setClickAction("");
//        easyNotify.nPush();
//        easyNotify.setEasyNotifyListener(new EasyNotify.EasyNotifyListener() {
//            @Override
//            public void onNotifySuccess(String s) {
//                //Toast.makeText(AllBidsActivity.this, s, Toast.LENGTH_SHORT).show();
//                Log.d("Success",s);
//            }
//
//            @Override
//            public void onNotifyError(String s) {
//                //Toast.makeText(AllBidsActivity.this, s, Toast.LENGTH_SHORT).show();
//                Log.d("Fail",s);
//            }
//
//        });
//    }

    private void showMoneyReciept(Uri uri) {
        moneyReceiptView.setVisibility(View.VISIBLE);
        moneyReceiptView.setImageURI(uri);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }

    private void showApproveButton() {
//        withdrawBtn.setVisibility(View.VISIBLE);
//        withdrawBtn.setOnClickListener(this);
        approveBtn.setVisibility(View.VISIBLE);
        approveBtn.setOnClickListener(this);
    }
    private void showCancelButton() {
        withdrawBtn.setVisibility(View.VISIBLE);
        withdrawBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        if(id==R.id.approve){
         changeStatus(bidId);
        }else if(id==R.id.cancel_btn) {
            changeStatus(bidId);
        }
    }

    public void changeStatus(int bidId )
    {
        Log.d("Status", String.valueOf(bidId));
        //progressBar.setVisibility(View.VISIBLE);
        RetrofitInstance.getApi().changeStatus(bidId)
                .enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        if(response.code()==StaticDataManager.OK){
                            finish();
                            startActivity(getIntent());
                        }
                    }

                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {
                        Log.d("error",t.getMessage());
                    }
                });
    }


    private void shippingStatusToServer() {
//        api.shippingChange(bidId,StaticDataManager.DELIVERED).enqueue(new Callback<Boolean>() {
//            @Override
//            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
//                if(response.code()==StaticDataManager.OK){
//                    endActivity();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Boolean> call, Throwable t) {
//                Log.d("d","d");
//            }
//        });
    }
    private void endActivity() {
        Intent returnIntent = new Intent();

        setResult(Activity.RESULT_OK,returnIntent);

        finish();
    }

    @Override
    public void getUploadReceipt(Response<ResponseBody> response) {
        if(response.code()==StaticDataManager.OK){
            Toast.makeText(getApplicationContext(),getText(R.string.receipt_upload_successful),Toast.LENGTH_SHORT).show();

            endActivity();
        }
    }
}
