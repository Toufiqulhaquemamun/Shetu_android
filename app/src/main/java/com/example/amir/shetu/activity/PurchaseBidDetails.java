package com.example.amir.shetu.activity;

import android.Manifest;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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
import com.example.amir.shetu.model.BidStatus;
import com.example.amir.shetu.model.DeviceList;
import com.example.amir.shetu.retorfit.API;
import com.example.amir.shetu.retorfit.RetrofitInstance;
import com.example.amir.shetu.model.CommodityPostDetail;
import com.example.amir.shetu.model.Image;
import com.msoftworks.easynotify.EasyNotify;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.amir.shetu.other.ApplicationContext.getContext;

public class PurchaseBidDetails extends AppCompatActivity implements View.OnClickListener,ApiDataManager.UploadReceipt {

    private API api=RetrofitInstance.getApi();
    private ViewPager _pager;

    private ImageButton leftBtn,rightBtn;

    private Button modifyBtn,withdrawBtn,shippingBtn,moreDetailsBtn, captureBtn,submitBtn,tokenBtn;

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
    private LinearLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_bid_details);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        if(getIntent().getExtras()==null){
            bidId=PreferenceManager.getInstance().getInt("bidId");

            productId=PreferenceManager.getInstance().getInt("productId");
        }else{
            bidId=getIntent().getExtras().getInt("bidId");

            productId=getIntent().getExtras().getInt("productId");

            PreferenceManager.getInstance().setInt("bidId",bidId);

            PreferenceManager.getInstance().setInt("productId",productId);
        }

        initializeView();

        bundle = new Bundle();

        fm=getFragmentManager();

        fetchDetails();

        moreDetailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bundle = new Bundle();

                bundle.putInt("productId", productId);

                PurchaseBidsDialog purchaseBidsDialog =new PurchaseBidsDialog();

                purchaseBidsDialog.setArguments(bundle);

                purchaseBidsDialog.show(fm,"");

            }
        });
    }

    private void initializeView() {

        container=findViewById(R.id.container);

        companyLocationView=findViewById(R.id.company_location);

        modifyBtn=findViewById(R.id.modify_btn);

        withdrawBtn=findViewById(R.id.withdraw_btn);

        titleView=findViewById(R.id.title);

        contactNumberView=findViewById(R.id.contact_number);

        emailView=findViewById(R.id.email);

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
        accountInfo.setVisibility(View.INVISIBLE);
        tokenBtn=findViewById(R.id.sendToken);


    }

    private void fetchDetails() {

        progressBar.setVisibility(View.VISIBLE);

        final int userId= PreferenceManager.getInstance().getInt("id");
        api.getPurchaseBidDetails(productId,userId).enqueue(new Callback<CommodityPostDetail>() {
                         @Override
                         public void onResponse(Call<CommodityPostDetail> call, Response<CommodityPostDetail> response) {
                             if(response.code()== StaticDataManager.OK){
                                 Log.d("Success", response.body().getDescription());
                                 CommodityPostDetail commodityPostDetail=response.body();
                                 sellerId=commodityPostDetail.getSellerId();
                                 Log.d("Seller Id", String.valueOf(sellerId));
                                 titleView.setText(commodityPostDetail.getName());

                                 companyLocationView.setText(commodityPostDetail.getOrganizationLocation());

                                 contactNumberView.setText(commodityPostDetail.getSellerPhone());

                                 emailView.setText(commodityPostDetail.getSellerEmail());

                                 companyNameView.setText(commodityPostDetail.getOrganizationName());

                                 descriptionView.setText(commodityPostDetail.getDescription());

                                 priceView.setText(commodityPostDetail.getPrice() + " টাকা");

                                 quantityView.setText(commodityPostDetail.getQuantity()+" "+commodityPostDetail.getUnit());

                                 bidStatus.setText(commodityPostDetail.getStatus());


                                 gradeView.setText(commodityPostDetail.getGrade().toString());

                                 if(commodityPostDetail.getBid()!=null){

                                     String moneyReceipt=commodityPostDetail.getBid().getMoneyReciept();

                                     if(moneyReceipt!=null){

                                         String fullPath=StaticDataManager.MONEY_RECEIPT_IMAGE_PREFIX +moneyReceipt;
                                         PreferenceManager manager= PreferenceManager.getInstance();
                                         String acc=manager.getString(PreferenceManager.ACC_INFO);
                                         showMoneyReceipt(fullPath,acc);
                                     }

                                     bidQuantity.setText(commodityPostDetail.getBid().getQuantity()+" "+commodityPostDetail.getUnit());

                                     bidPrice.setText(commodityPostDetail.getBid().getPrice() + " টাকা");

                                     bidStatus.setText(commodityPostDetail.getBid().getBidStatus().getStatus());

                                     BidStatus bid=response.body().getBid().getBidStatus();

                                     if(bid.getStatus().equals(StaticDataManager.PENDING)){

                                         showButton();
                                     }else if(bid.getStatus().equals(StaticDataManager.SHIPPING)){

                                         shippingBtn.setVisibility(View.VISIBLE);
                                         accountInfo.setVisibility(View.INVISIBLE);
                                         shippingBtn.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View v) {

                                                 shippingStatusToServer();
                                             }
                                         });

                                     }else if(bid.getStatus().equals(StaticDataManager.Locked)){

                                         captureBtn.setVisibility(View.VISIBLE);
                                         accountInfo.setVisibility(View.VISIBLE);
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
                                     else if(bid.getStatus().equals(StaticDataManager.BuyerRecvStatus))
                                     {
                                        tokenBtn.setVisibility(View.VISIBLE);


                                        moneyReceiptView.setVisibility(View.INVISIBLE);
                                        tokenBtn.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                if(tokenCount>0&& tokenCount<3)
                                                {
                                                    tokenBtn.setText("পুনরায় টোকেন পাঠান");
                                                    Toast.makeText(getApplicationContext(),"বাকি আছে :"+(3-tokenCount),Toast.LENGTH_SHORT).show();
                                                }
                                                else if(tokenCount>=3)
                                                {
                                                    tokenBtn.setVisibility(View.INVISIBLE);
                                                }

                                                tokenCount++;
                                            }
                                        });
                                     }

                                 }

                                 initializeImageSlider(commodityPostDetail.getImages());


                             }
                             progressBar.setVisibility(View.GONE);

                             container.setVisibility(View.VISIBLE);

                         }

                         @Override
                         public void onFailure(Call<CommodityPostDetail> call, Throwable t) {
                             Log.d("###############",t.getMessage());
                         }
                     }
        );
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

// 2. Chain together various setter methods to set the dialog characteristics
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

        Intent cameraIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(cameraIntent.resolveActivity(getContext().getPackageManager())!=null){

            File photoFile=null;
            try {

                photoFile= createImageFile();

            }catch (IOException io){
                return;
            }

            Uri photoUri= FileProvider.getUriForFile(getContext(),getContext().getPackageName()+".provider",photoFile);

            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,photoUri);

            startActivityForResult(cameraIntent,StaticDataManager.REQUEST_CODE);
        }

    }
    private File createImageFile() throws IOException {

        String imageFileName = generateImageFileName();

        File storageDir=getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        File image=File.createTempFile(imageFileName,".jpg",storageDir);

        imageFilePath = image.getAbsolutePath();

        return image;
    }

    @NonNull
    private String generateImageFileName() {
        String timeStamp=new SimpleDateFormat(DATE_FORMATE, Locale.getDefault()).format(new Date());

        return "IMG_"+timeStamp+"_";
    }

    private void shippingStatusToServer() {
        api.shippingChange(bidId,StaticDataManager.DELIVERED).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if(response.code()==StaticDataManager.OK){
                    endActivity();
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.d("d","d");
            }
        });
    }

    private void initializeImageSlider(List<Image> imageList) {
        _pager = (ViewPager) findViewById(R.id.pager);
        leftBtn = (ImageButton)findViewById(R.id.left_nav);
        rightBtn =  (ImageButton) findViewById(R.id.right_nav);
        PurchaseBidDetails.GalleryPagerAdapter _adapter = new PurchaseBidDetails.GalleryPagerAdapter( this,imageList);
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

            if(resultCode==RESULT_OK && requestCode ==StaticDataManager.REQUEST_CODE) {

                final Uri selectedImage = Uri.parse(imageFilePath);
                Log.d("InageUri", selectedImage.toString());
                showMoneyReciept(Uri.parse(String.valueOf(selectedImage)));
                submitBtn.setVisibility(View.VISIBLE);
                submitBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String s = accountInfo.getText().toString();
                        PreferenceManager.getInstance().setString(PreferenceManager.ACC_INFO, s);
                        if (TextUtils.isEmpty(s)) {
                            Toast.makeText(getApplicationContext(), "আপনার একাউন্টের তথ্য প্রদান করুন", Toast.LENGTH_SHORT).show();
                            return;
                        } else {
                            ApiDataManager.uploadMoneyReceipt(PurchaseBidDetails.this, PurchaseBidDetails.this, bidId, s, selectedImage);
                            submitBtn.setActivated(false);
                            Log.d("Seller Id", String.valueOf(sellerId));
                            //deviceList(sellerId);
                            progressBar.setVisibility(View.VISIBLE);
                        }


                    }
                });

            } else {
                Log.d("ErrorResult", String.valueOf(resultCode));
            }

    }

    public void deviceList(int buyerId)
    {
        RetrofitInstance.getApi().getDeviceList(buyerId).enqueue(new Callback<List<DeviceList>>() {
            @Override
            public void onResponse(Call<List<DeviceList>> call, Response<List<DeviceList>> response) {
                if(response.code()==StaticDataManager.OK)
                {
                    for (DeviceList list : response.body()){
                        notification(list.getToken());
                        Log.i("Tokens", list.getToken());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<DeviceList>> call, Throwable t) {

            }
        });
    }

    public void notification(String token)
    {
        PreferenceManager manager = PreferenceManager.getInstance();
        String fcmtoken=manager.getString("FCM_token");

        EasyNotify easyNotify = new EasyNotify(StaticDataManager.API_KEY);
        easyNotify.setSendBy(EasyNotify.TOKEN);
        easyNotify.setToken(token);
        easyNotify.setTitle("Money Recipt Status");
        easyNotify.setBody("Money recipt Uploaded");
        easyNotify.setClickAction("");
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

    private void showMoneyReciept(Uri uri) {
        moneyReceiptView.setVisibility(View.VISIBLE);
        moneyReceiptView.setImageURI(uri);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }

    private void showButton() {
        withdrawBtn.setVisibility(View.VISIBLE);
        withdrawBtn.setOnClickListener(this);
        modifyBtn.setVisibility(View.VISIBLE);
        modifyBtn.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        int id=v.getId();
        if(id==R.id.withdraw_btn){
            inFormServer();
        }else {
            Intent intent=new Intent(PurchaseBidDetails.this,BidAndBidEditActivity.class);
            intent.putExtra("flag",StaticDataManager.MODIFY_FLAG);
            intent.putExtra("bidId",bidId);
            startActivity(intent);
            finish();
        }
    }

    private void inFormServer() {
        api.withdrawBid(bidId).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if(response.code()==StaticDataManager.OK) {
                    if (response.body() == true) {
                        PreferenceManager.getInstance().setInt(PreferenceManager.BID_ID,bidId);
                        PreferenceManager.getInstance().setInt(PreferenceManager.PRODUCT_ID,productId);
                        finish();
                        startActivity(getIntent());
                    }
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {

            }
        });
    }

    @Override
    public void getUploadReceipt(Response<ResponseBody> response) {
        if(response.code()==StaticDataManager.OK){

            Toast.makeText(getApplicationContext(),getText(R.string.receipt_upload_successful),Toast.LENGTH_SHORT).show();

            endActivity();
        }
    }

    private void endActivity() {
        Intent returnIntent = new Intent();

        setResult(Activity.RESULT_OK,returnIntent);

        finish();
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
            Glide.with( PurchaseBidDetails.this).load(StaticDataManager.COMMODITY_IMAGE_PREFIX +"" + imageList.get(position).getFile())
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

}