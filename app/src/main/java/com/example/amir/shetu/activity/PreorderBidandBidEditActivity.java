package com.example.amir.shetu.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amir.shetu.R;
import com.example.amir.shetu.adapter.HorizontalRecyclerViewAdapter;
import com.example.amir.shetu.interfaces.ApiDataManager.BidInformationListener;
import com.example.amir.shetu.interfaces.ApiDataManager.PostModifiedPreOrderbidListener;
import com.example.amir.shetu.interfaces.ApiDataManager.PreorderPostBidListener;
import com.example.amir.shetu.manager.ApiDataManager;
import com.example.amir.shetu.manager.PreferenceManager;
import com.example.amir.shetu.manager.StaticDataManager;
import com.example.amir.shetu.model.Bid;
import com.example.amir.shetu.model.BidCalculationModel;
import com.example.amir.shetu.model.BidImage;
import com.example.amir.shetu.retorfit.API;
import com.example.amir.shetu.retorfit.RetrofitInstance;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okio.ByteString;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.amir.shetu.other.ApplicationContext.getContext;

public class PreorderBidandBidEditActivity extends AppCompatActivity implements View.OnClickListener, PreorderPostBidListener, PostModifiedPreOrderbidListener,BidInformationListener {
    private ProgressBar progressBar;

    private EditText negotiatedPriceView, quantityView;

    private Button captureBtn;

    private TextView  vatView, serviceChargeView, netBalanceView, bidBtn, cancelBtn;

    private int sellerId,productId;

    private int preOrderId;

    private double totalPrice;

    private double vat;

    private API api= RetrofitInstance.getApi();
    private double tempVat;

    private String tempPrice;

    private double price;

    private String tempQuantity;

    private int quantity;

    private double tempServiceCharge;

    private double serveCharge;

    private String flag;

    private int bidId;

    private Bid bid;

    private TextInputLayout quantityInputLayout;

    private String maxQuantity;

    private List<BidImage> bidImages=null;
    private RequestBody bids;

    private final String DATE_FORMATE = "yyyyMMddHHmmss";
    private String imageFilePath;
    int imgcount=0;
    private HorizontalRecyclerViewAdapter horizontalAdapter;
    private LinearLayoutManager horizontalLayoutManager;
    private RecyclerView mHorizontalRecyclerView;
    private ArrayList<Uri> images=new ArrayList<>();
    private ArrayList<MultipartBody.Part> imageParts=new ArrayList<>();



    private DecimalFormat decimalFormat = new DecimalFormat("#");
    private RequestBody quantityBody;
    private RequestBody priceBody;
    private RequestBody vatBody;
    private RequestBody serviceChargeBody;
    private RequestBody preOrderIdBody;
    private RequestBody sellerIdBody;
    private RequestBody shipmentChageBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preorder_bidand_bid_edit);
        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);

        productId = PreferenceManager.getInstance().getInt(PreferenceManager.PRODUCT_ID);
        preOrderId = PreferenceManager.getInstance().getInt(PreferenceManager.PREORDER_PRODUCT_ID);
        Log.d("preorderId", String.valueOf(preOrderId));

        sellerId = PreferenceManager.getInstance().getInt(PreferenceManager.USER_ID);

        flag = getIntent().getExtras().getString(PreferenceManager.PREORDERFLAG);

        bidId = PreferenceManager.getInstance().getInt(PreferenceManager.PREORDER_BID_ID);

        maxQuantity = getIntent().getExtras().getString(StaticDataManager.MAX_QUANTITY_PREF);

        progressBar = findViewById(R.id.progressbar);

        initializeView();



        if (flag.equals(StaticDataManager.PREORDER_MODIFY_FLAG)) {

            ApiDataManager.getBidInformation(this,bidId);

            bidBtn.setText(getString(R.string.edit));

        }else {
            autoCalculate();
        }
        bidBtn.setOnClickListener(PreorderBidandBidEditActivity.this);

        cancelBtn.setOnClickListener(PreorderBidandBidEditActivity.this);

        captureBtn.setOnClickListener(PreorderBidandBidEditActivity.this);

        quantityInputLayout.setHint( "( "+getString(R.string.maxQuantity)+" "+maxQuantity+" )");


    }


    private void initializeView() {

        negotiatedPriceView = findViewById(R.id.negociation_price);
        quantityView = findViewById(R.id.quantity);
        quantityView.setText(maxQuantity);
        //totalPriceView = findViewById(R.id.total_price);
        captureBtn = findViewById(R.id.btnChoose1);
        serviceChargeView = findViewById(R.id.service_charge);
        netBalanceView = findViewById(R.id.net_balance);
        bidBtn = findViewById(R.id.bid_btn);
        cancelBtn = findViewById(R.id.cancel_btn);
        quantityInputLayout =findViewById(R.id.quantity_layout);

        mHorizontalRecyclerView = findViewById(R.id.horizontalRecyclerView);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();
        if (id == R.id.bid_btn) {
            tempQuantity = stringQuantity();
            tempPrice = stringNegotiatedPrice();

            if (!tempQuantity.equals("") && !tempPrice.equals("")) {

//                if(stringToDouble(tempQuantity)>stringToDouble(maxQuantity)){
//
//                    Toast.makeText(this,getString(R.string.excess_max_quantity),Toast.LENGTH_SHORT).show();
//
//                }else {
//
//                }
                quantity = (int)Double.parseDouble(tempQuantity);
                price = Double.parseDouble(tempPrice);
                BidCalculationModel calculatedModel = calculate(quantity, price);
                if (flag.equals(StaticDataManager.MODIFY_FLAG)) {
                    saveModifiedBidToServer(calculatedModel);
                } else {
                    saveNewBidToServer(quantity, price, calculatedModel);

                }

                finish();

            } else {
                Toast.makeText(PreorderBidandBidEditActivity.this, R.string.login_empty, Toast.LENGTH_SHORT).show();
            }
        }
        else if(id == R.id.btnChoose1)
        {
            if (askForPermission()){
                captureImage();
            }
        }
            else {

            finish();

        }

    }
    private void captureImage() {
        final CharSequence[] options = { "ফোন ক্যামেরা দিয়ে","ফোন থেকে যুক্ত করুন","বাতিল করুন" };
        AlertDialog.Builder builder = new AlertDialog.Builder(PreorderBidandBidEditActivity.this);
        builder.setTitle("ছবি তুলুন");
        builder.setIcon(R.drawable.arrow_left);


        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("ফোন ক্যামেরা দিয়ে")) {
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
                } else if (options[item].equals("ফোন থেকে যুক্ত করুন")) {
                    Intent pickPhoto = new Intent(Intent.ACTION_GET_CONTENT);
                    pickPhoto.setType("image/*");
                    startActivityForResult(pickPhoto,1);
                } else if (options[item].equals("বাতিল করুন")) {
                    dialog.dismiss();
                }
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
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
    private boolean askForPermission() {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= Build.VERSION_CODES.M) {
            int hasCallPermission = ContextCompat.checkSelfPermission(PreorderBidandBidEditActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE);
            if (hasCallPermission != PackageManager.PERMISSION_GRANTED) {

                showDialog();


                if (ActivityCompat.shouldShowRequestPermissionRationale(PreorderBidandBidEditActivity.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE)) {
//                    user permanently disable permission message

                }else {
                    // Request for permission
                    ActivityCompat.requestPermissions(PreorderBidandBidEditActivity.this,
                            new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                            StaticDataManager.REQUEST_CODE_ASK_PERMISSIONS);
                }

                return false;
            } else {
                // permission granted and calling function working
                return true;
            }
        } else {
            return true;
        }
    }
    private void showDialog() {
        DialogInterface.OnClickListener listener=createListener();
        showMessageOKCancel(listener);
    }
    @NonNull
    private DialogInterface.OnClickListener createListener() {
        return new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ActivityCompat.requestPermissions(PreorderBidandBidEditActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        StaticDataManager.REQUEST_CODE_ASK_PERMISSIONS);
            }
        };
    }
    private void showMessageOKCancel(DialogInterface.OnClickListener okListener) {

        AlertDialog.Builder builder = new AlertDialog.Builder(PreorderBidandBidEditActivity.this);
        final AlertDialog dialog = builder.setMessage(getString(R.string.file_access_message))
                .setPositiveButton(getString(R.string.ok), okListener)
                .setCancelable(false)
                .create();

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface arg0) {
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(
                        ContextCompat.getColor(getContext(), android.R.color.holo_blue_light));
            }
        });

        dialog.show();

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {


        if(resultCode == RESULT_OK && requestCode == 1&&data!=null) {
            Uri selectedImage =  data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.
                    DATA};
            if (selectedImage != null) {
                Cursor cursor = PreorderBidandBidEditActivity.this.getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                if (cursor != null) {
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String picturePath = cursor.getString(columnIndex);
                    images.add(Uri.parse(picturePath));
                    cursor.close();
                    createHorizontalRecyclerView();
                }
            }
        }
        else if(resultCode == RESULT_OK && requestCode == StaticDataManager.REQUEST_CODE)
        {
            Uri imageUri = Uri.parse(imageFilePath);
            images.add(imageUri);
            createHorizontalRecyclerView();
        }


    }
    private void createHorizontalRecyclerView() {
        horizontalAdapter = new HorizontalRecyclerViewAdapter(images, PreorderBidandBidEditActivity.this);
        horizontalLayoutManager = new LinearLayoutManager(PreorderBidandBidEditActivity.this, LinearLayoutManager.HORIZONTAL, false);
        mHorizontalRecyclerView.setLayoutManager(horizontalLayoutManager);
        mHorizontalRecyclerView.setAdapter(horizontalAdapter);
        imgcount++;
    }
    private void saveNewBidToServer(int quantity, double price, BidCalculationModel calculationModel) {

        createRequestBody(quantity,price,vat,serveCharge,preOrderId,sellerId,calculationModel.getShipCharge(),images);
        api.PreorderPostBid(quantityBody,priceBody,vatBody,serviceChargeBody,preOrderIdBody,sellerIdBody,shipmentChageBody,imageParts).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.code() == StaticDataManager.OK) {
                            Log.d("Success",response.body().toString());
                    quantityView.getText().clear();
                    images.clear();
                    horizontalAdapter.notifyDataSetChanged();
                    Toast.makeText(getContext(),R.string.successfully_uploaded,Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Log.d("else########postBid", String.valueOf(response.code()));
                            Log.d("else########postBid", String.valueOf(response.message()));
                        }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.d("Failure", t.getMessage());
                //progressBar.setVisibility(View.GONE);
                Toast.makeText(getContext(),getString(R.string.server_error),Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void createRequestBody(int quantity, double price, double vat, double serveCharge, int preOrderId, int sellerId, double sipmentCharge, ArrayList<Uri> images) {
        quantityBody=createPartFromString(quantity+"");
        priceBody=createPartFromString(price+"");
        vatBody=createPartFromString(vat+"");
        sellerIdBody=createPartFromString(sellerId+"");
        serviceChargeBody=createPartFromString(serveCharge+"");
        shipmentChageBody=createPartFromString(sipmentCharge+"");
        preOrderIdBody=createPartFromString(preOrderId+"");
        for(int i=0;i<images.size();i++){
            if ( images.get(i)!= null) {
                MultipartBody.Part body1 = prepareFilePart("images", images.get(i));
                imageParts.add(body1);
            }
        }
    }


    @NonNull
    private MultipartBody.Part prepareFilePart(String partName, Uri fileUri) {
        File file=new File(fileUri.getPath());

        // create RequestBody instance from file
        RequestBody requestFile = RequestBody.create(
                MediaType.parse("image/jpeg"),
                file
        );

        // MultipartBody.Part is used to send also the actual file name
        return MultipartBody.Part.createFormData(partName, file.getName(), requestFile);
    }


    @NonNull
    private RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create(
                okhttp3.MultipartBody.FORM, descriptionString);
    }


    private void saveModifiedBidToServer(BidCalculationModel calculatedModel) {

        Bid bid=new Bid(calculatedModel.getVat(),calculatedModel.getServiceCharge(),calculatedModel.getShipCharge(),bidId,quantity,price);

        ApiDataManager.PostModifiedPreOrderBid(this,bid);

    }

    private void autoCalculate() {

        quantityView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (!stringNegotiatedPrice().equals("") && !charToString(s).equals("")) {

                    BidCalculationModel calculation = calculate(intQuantity(charToString(s)), doubleNegotiationPrice(stringNegotiatedPrice()));

                    showView(calculation);

                }else {

                    clearView();

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        negotiatedPriceView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (!stringQuantity().equals("") && !charToString(s).equals("")) {

                    BidCalculationModel calculation = calculate(intQuantity(stringQuantity()), doubleNegotiationPrice(charToString(s)));
                    showView(calculation);
                }else {
                    clearView();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    @NonNull
    private String charToString(CharSequence s) {
        return s.toString().trim();
    }

    @NonNull
    private String stringNegotiatedPrice() {
        return negotiatedPriceView.getText().toString().trim();
    }

    private int intQuantity(String quantity){
        return (int)(Double.parseDouble(quantity));
    }

    private double doubleNegotiationPrice(String price){

        return Double.parseDouble(price);
    }

    @NonNull
    private String stringQuantity() {
        return quantityView.getText().toString().trim();
    }

    private void clearView() {

        netBalanceView.setText("");
    }

    private void showView(BidCalculationModel calculation) {

//        totalPriceView.setText("" + decimalFormat.format(calculation.getTotalPrice())+ StaticDataManager.TAKA_SIGN);

//        vatView.setText("" + calculation.getVat()+StaticDataManager.TAKA_SIGN);

//        serviceChargeView.setText("" + decimalFormat.format(calculation.getServiceCarage())+StaticDataManager.TAKA_SIGN);

        netBalanceView.setText("" + decimalFormat.format(calculation.getTotalPrice())+StaticDataManager.TAKA_SIGN);

    }
    private BidCalculationModel calculate(int quantity, double price) {

        totalPrice = BigDecimal.valueOf(quantity * price).doubleValue();

        if(bid==null){
            bid=new Bid();
            bid.setVat(0);
            bid.setServiceCharge(10);
            bid.setShippingCharge(0);
        }

        tempVat = BigDecimal.valueOf(bid.getVat() / 100.0f).doubleValue();

        vat = BigDecimal.valueOf(totalPrice * tempVat).doubleValue();

        tempServiceCharge =BigDecimal.valueOf(bid.getServiceCharge() / 100.0f).doubleValue();

        serveCharge = (int) (totalPrice * tempServiceCharge);

        serveCharge=BigDecimal.valueOf(serveCharge).doubleValue();

        double netBalance = totalPrice + serveCharge;

         netBalance=BigDecimal.valueOf(netBalance).doubleValue();

        return new BidCalculationModel(totalPrice, vat, serveCharge, quantity, bid.getShippingCharge(), netBalance);

    }





    @Override
    public void getPreorderPostBidInformation(Boolean result, String errorMessage) {

        if(errorMessage.equals("")){
            Toast.makeText(PreorderBidandBidEditActivity.this,getString(R.string.bid_successful),Toast.LENGTH_SHORT).show();
            finish();
        }else {
            Toast.makeText(PreorderBidandBidEditActivity.this,errorMessage,Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void getBidInformation(Bid bid, String errorMessage) {
        this.bid = bid;
        Log.d("quantity", String.valueOf(bid.getQuantity()));

        if(flag.equals(StaticDataManager.PREORDER_MODIFY_FLAG)){

            BidCalculationModel calculation = calculate(bid.getQuantity(),bid.getPrice());

            quantityView.setText(decimalFormat.format(bid.getQuantity())+"");

            negotiatedPriceView.setText(decimalFormat.format(bid.getPrice())+"");

            showView(calculation);

            autoCalculate();

        }
    }

    @Override
    public void getPostModifiedPreOrderbidInfo(boolean result, String errorMessage) {
        if(result){

            Toast.makeText(this,getString(R.string.modified_bid_successful),Toast.LENGTH_SHORT).show();

        }
    }
//    private double stringToDouble(String value){
//        return Double.parseDouble(value);
//    }
}
