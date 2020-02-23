package com.example.amir.shetu.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amir.shetu.R;
import com.example.amir.shetu.interfaces.ApiDataManager.BidInformationListener;
import com.example.amir.shetu.interfaces.ApiDataManager.PostBidListener;
import com.example.amir.shetu.interfaces.ApiDataManager.PostModifiedBidListener;
import com.example.amir.shetu.manager.ApiDataManager;
import com.example.amir.shetu.manager.PreferenceManager;
import com.example.amir.shetu.manager.StaticDataManager;
import com.example.amir.shetu.model.Address;
import com.example.amir.shetu.model.BidCalculationModel;
import com.example.amir.shetu.model.Bid;
import com.example.amir.shetu.model.District;
import com.example.amir.shetu.model.PackCalculation;
import com.example.amir.shetu.model.PackageList;
import com.example.amir.shetu.model.ShippingCostByCommodityId;
import com.example.amir.shetu.model.Upazila;
import com.example.amir.shetu.retorfit.API;
import com.example.amir.shetu.retorfit.RetrofitInstance;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.amir.shetu.other.ApplicationContext.getContext;

public class BidAndBidEditActivity extends AppCompatActivity implements View.OnClickListener, PostBidListener, BidInformationListener, PostModifiedBidListener, AdapterView.OnItemSelectedListener {

    private ProgressBar progressBar;

    private EditText negotiatedPriceView, quantityView,postalCode,localAddress,quantityAmount;

    private API api=RetrofitInstance.getApi();

    private TextView totalPriceView, totalPackCost, serviceChargeView, netBalanceView, bidBtn, cancelBtn,bagAmount,priceAmount;

    private int buyerId;

    private int commodityPostId ;

    private double totalPrice;

    private double vat;

    private double tempVat;

    private String tempPrice;

    private double price;

    private String tempQuantity;

    public int districtId;
    public int upazilaId;
    String postCode;
    String localAdd;

    private int quantity;

    private Spinner districtSpinner,upazilaSpinner;

    private double tempServiceCharge;

    private double tempShipCharge;

    private double shipCharge;

    private double serviceCharge;

    private String flag;


    private int bidId,commodityId;

    private Bid bid;

    int costperunit;

    private TextInputLayout quantityInputLayout;

    private String maxQuantity;

    private DecimalFormat decimalFormat = new DecimalFormat("#");
    private LinearLayout linearLayout;
    private LinearLayout linearLayout1;

    private List<EditText> allEds = new ArrayList<EditText>();
    private List<TextView> allTvbag = new ArrayList<TextView>();
    private List<TextView> allTvprice = new ArrayList<TextView>();
    private double[] data = new double[10];
    private int buyquantity,quantityamount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_bid);

        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);

        commodityPostId = PreferenceManager.getInstance().getInt(PreferenceManager.PRODUCT_ID);

        commodityId= getIntent().getExtras().getInt(PreferenceManager.COMMODITY_ID);

        buyerId = PreferenceManager.getInstance().getInt(PreferenceManager.USER_ID);

        flag = getIntent().getExtras().getString(PreferenceManager.FLAG);

        bidId = PreferenceManager.getInstance().getInt(PreferenceManager.BID_ID);

        maxQuantity = getIntent().getExtras().getString(StaticDataManager.MAX_QUANTITY_PREF);

        Log.d("Bid Details","buyerID:"+buyerId+"CommoID:"+commodityId+"bidID:"+bidId);

        progressBar = findViewById(R.id.progressbar);

        fetchDistrict();
        getShippingCost();
        getPackaging();
        initializeView();

        if (flag.equals(StaticDataManager.MODIFY_FLAG)) {

            ApiDataManager.getBidInformation(this,bidId);

            bidBtn.setText(getString(R.string.edit));

        }else {
            autoCalculate();
        }

        bidBtn.setOnClickListener(BidAndBidEditActivity.this);

        cancelBtn.setOnClickListener(BidAndBidEditActivity.this);

        quantityInputLayout.setHint( "( "+getString(R.string.maxQuantity)+" "+maxQuantity+" )");




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

    public void getPackaging()
    {
        api.getPackageList(commodityId).enqueue(new Callback<List<PackageList>>() {
            @Override
            public void onResponse(Call<List<PackageList>> call, Response<List<PackageList>> response) {
                if(response.code()==StaticDataManager.OK){
                    createPackageView(response.body());


                }
            }

            @Override
            public void onFailure(Call<List<PackageList>> call, Throwable t) {

            }
        });
    }

    public void createPackageView(final List<PackageList> body)
    {
        for ( int j=0;j<body.size();j++){
            LinearLayout linearLayout1 = new LinearLayout(getContext());
            linearLayout1.setWeightSum(1);
            linearLayout.addView(linearLayout1);

            bagAmount= new TextView(getContext());
            bagAmount.setText(body.get(j).getBnName());
            allTvbag.add(bagAmount);

            priceAmount= new TextView(getContext());
            priceAmount.setText(body.get(j).getCostPerUnit().toString());
            allTvprice.add(priceAmount);

            quantityAmount= new EditText(getContext());
            quantityAmount.setHint("Enter Quantity");
            quantityAmount.setHintTextColor(Color.GRAY);
            quantityAmount.setInputType(InputType.TYPE_CLASS_NUMBER);
            allEds.add(quantityAmount);
                setTextViewAttributes(bagAmount);
                setTextViewAttributes(priceAmount);
                setEditTextAttributes(quantityAmount);
                linearLayout1.addView(bagAmount);
                linearLayout1.addView(priceAmount);
                linearLayout1.addView(quantityAmount);

            final int finalJ = j;
            quantityAmount.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                       // totalPackCost.setText(String.valueOf(intQuantity(charToString(charSequence))*doubleNegotiationPrice(charToString(allTvprice.get(finalJ).getText()))));

                        if (!charToString(charSequence).equals("")) {

                            PackCalculation calculate = calculatePack(finalJ, intQuantity(charToString(charSequence)));
                            showPackView(calculate);
                        }else {
                            clearView();
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
//                        totalPackCost.setText(String.valueOf(intQuantity(charToString(editable))*doubleNegotiationPrice(charToString(allTvprice.get(finalJ).getText()))));
                    }
                });
        }
    }

    private void showPackView(PackCalculation calculate) {
        totalPackCost.setText(String.valueOf(calculate.getPricetotal()));
    }

//    public void autocalculate(int i)
//    {
//        Toast.makeText(this,"position:"+i,Toast.LENGTH_SHORT).show();
//    }

    private PackCalculation calculatePack(int position, int bagamount) {


        double tempunitPrice= doubleNegotiationPrice(charToString(allTvprice.get(position).getText()));
        double tempEditText= doubleNegotiationPrice(charToString(allEds.get(position).getText()));
        double temptotal = tempunitPrice*tempEditText;

        for(int k=0;k<=allEds.size();k++)
        {
            data[position]=temptotal;
            Log.d("Value", String.valueOf(data[k]));
        }
        double pricetotal =0.0;

        for(double num :data)
        {
            pricetotal=pricetotal+num;
        }



        pricetotal=BigDecimal.valueOf(pricetotal).doubleValue();

        return new PackCalculation(position,buyquantity , costperunit, bagamount, quantityamount, pricetotal);
    }

    private BidCalculationModel calculate(int quantity, double price) {

        totalPrice = BigDecimal.valueOf(quantity * price).doubleValue();

        if(bid==null){
            bid=new Bid();
            bid.setVat(0);
            bid.setServiceCharge(10);
            bid.setShippingCharge(tempShipCharge);
        }

        tempVat = BigDecimal.valueOf(bid.getVat() / 100.0f).doubleValue();

        vat = BigDecimal.valueOf(totalPrice * tempVat).doubleValue();

        tempServiceCharge =BigDecimal.valueOf(bid.getServiceCharge() / 100.0f).doubleValue();

        serviceCharge = (int) (totalPrice * tempServiceCharge);

        serviceCharge=BigDecimal.valueOf(serviceCharge).doubleValue();

        shipCharge= (int)(tempShipCharge*quantity);

        shipCharge=BigDecimal.valueOf(shipCharge).doubleValue();

        double netBalance = totalPrice+shipCharge;

        netBalance=BigDecimal.valueOf(netBalance).doubleValue();

        return new BidCalculationModel(totalPrice, vat, serviceCharge, quantity, shipCharge, netBalance);

    }


    private void setTextViewAttributes(TextView textView) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                0.33f);


        textView.setTextColor(Color.BLACK);
        textView.setLayoutParams(params);
    }
    private void setEditTextAttributes(EditText editText) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                0.33f);


        editText.setTextColor(Color.BLACK);
        editText.setLayoutParams(params);
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
        totalPriceView.setText("");
//        vatView.setText("" );
        totalPackCost.setText("");
        serviceChargeView.setText("");
        netBalanceView.setText("");
    }

    private void showView(BidCalculationModel calculation) {

        totalPriceView.setText("" + decimalFormat.format(calculation.getTotalPrice())+StaticDataManager.TAKA_SIGN);

//        vatView.setText("" + calculation.getVat()+StaticDataManager.TAKA_SIGN);

        serviceChargeView.setText("" + decimalFormat.format(calculation.getShipCharge())+StaticDataManager.TAKA_SIGN);

        netBalanceView.setText("" + decimalFormat.format(calculation.getNetBalance())+StaticDataManager.TAKA_SIGN);

    }
//    private void showPackView(PackCalculation calculation) {
//
//        totalPackCost.setText("" + decimalFormat.format(calculation.getTotalPrice())+StaticDataManager.TAKA_SIGN);
//
//
//    }

    private void saveNewBidToServer(int quantity, double price, BidCalculationModel calculationModel, Address deliveryLocation) {

        Bid bid = new Bid(quantity,price,calculationModel.getVat(),calculationModel.getServiceCharge(),calculationModel.getShipCharge(),commodityPostId,buyerId,deliveryLocation);
        ApiDataManager.postBid(BidAndBidEditActivity.this, bid);

    }

    private void getShippingCost()
    {
        api.getShippingCost(commodityId).enqueue(new Callback<ShippingCostByCommodityId>() {
            @Override
            public void onResponse(Call<ShippingCostByCommodityId> call, Response<ShippingCostByCommodityId> response) {
                if(response.code()==StaticDataManager.OK)
                {
                    tempShipCharge=response.body().getCostPerUnit();
                    Log.d("Charge", String.valueOf(tempShipCharge));
                }
            }

            @Override
            public void onFailure(Call<ShippingCostByCommodityId> call, Throwable t) {

            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }

    private void initializeView() {

        negotiatedPriceView = findViewById(R.id.negociation_price);
        quantityView = findViewById(R.id.quantity);
        //quantityView.setText(maxQuantity);
        totalPriceView = findViewById(R.id.total_price);
        totalPackCost=findViewById(R.id.total_packcost);
        linearLayout=findViewById(R.id.packmainLayout);
        linearLayout1=findViewById(R.id.packLayout);
        serviceChargeView = findViewById(R.id.service_charge);
        netBalanceView = findViewById(R.id.net_balance);
        bidBtn = findViewById(R.id.bid_btn);
        cancelBtn = findViewById(R.id.cancel_btn);
        quantityInputLayout =findViewById(R.id.quantity_layout);
        postalCode=findViewById(R.id.postal_code);
        localAddress=findViewById(R.id.local_address);
        districtSpinner=findViewById(R.id.district_spinner);
        upazilaSpinner=findViewById(R.id.upazila_spinner);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.bid_btn) {
            tempQuantity = stringQuantity();
            tempPrice = stringNegotiatedPrice();
            postCode = postalCode.getText().toString().trim();
            localAdd =localAddress.getText().toString().trim();
            if (!tempQuantity.equals("") && !tempPrice.equals("")&& !postCode.equals("")&& !localAdd.equals("")) {

                if(stringToDouble(tempQuantity)>stringToDouble(maxQuantity)){

                    Toast.makeText(this,getString(R.string.excess_max_quantity),Toast.LENGTH_SHORT).show();

                }else {

                    quantity = (int)Double.parseDouble(tempQuantity);
                    price = Double.parseDouble(tempPrice);
                    Address deliveryLocation= preAdd(districtId,upazilaId,postCode,localAdd);
                    BidCalculationModel calculatedModel = calculate(quantity, price);
                    if (flag.equals(StaticDataManager.MODIFY_FLAG)) {
                        saveModifiedBidToServer(calculatedModel,deliveryLocation);
                    } else {
                        saveNewBidToServer(quantity, price, calculatedModel,deliveryLocation);

                    }

                    finish();

                }


            }
            else {
                Toast.makeText(BidAndBidEditActivity.this, R.string.login_empty, Toast.LENGTH_SHORT).show();
            }
        } else {

           finish();

        }
    }

    private Address preAdd(Integer districtId, Integer upazilaId, String postCode, String localAdd) {

        return new Address(districtId,upazilaId,postCode,localAdd);

    }
    private void fetchDistrict()
    {
        RetrofitInstance.getApi().DistrictList().enqueue(new Callback<List<District>>() {
            @Override
            public void onResponse(Call<List<District>> call, Response<List<District>> response) {
                if(response.code()==StaticDataManager.OK)
                {
                    List<String> districtList=new ArrayList<>();
                    for(int i=0;i<response.body().size();i++){
                        districtList.add(" জেলাঃ "+response.body().get(i).getBnName());
                    }
                    if(districtList.size()>0){
                        createDistrictSpinner(districtList,response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<District>> call, Throwable t) {
                Log.d("DistrictFsilure",t.getMessage());
            }
        });
    }

    private void fetchUpazila(int id)
    {
        RetrofitInstance.getApi().getUpazilasByDistrictId(id).enqueue(new Callback<List<Upazila>>() {
            @Override
            public void onResponse(Call<List<Upazila>> call, Response<List<Upazila>> response) {

                Log.d("District",response.body().toString());
                List<String> upazilaList=new ArrayList<>();
                for(int i=0;i<response.body().size();i++){
                    upazilaList.add(" থানাঃ "+response.body().get(i).getBnName());
                }
                if(upazilaList.size()>0){
                    createUpazilaSpinner(upazilaList,response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Upazila>> call, Throwable t) {

            }
        });
    }

    private void createUpazilaSpinner(List<String> upazilaList, final List<Upazila> body) {
        upazilaSpinner.setOnItemSelectedListener(this);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,upazilaList);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        upazilaSpinner.setAdapter(adapter2);
        upazilaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                upazilaId=body.get(i).getId();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void createDistrictSpinner(List<String> districtList, final List<District> body) {
        districtSpinner.setOnItemSelectedListener(this);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,districtList);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        districtSpinner.setAdapter(adapter1);
        districtSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                districtId=body.get(i).getId();
                Log.d("Id", String.valueOf(body.get(i).getId()));
                fetchUpazila(body.get(i).getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    private double stringToDouble(String value){
        return Double.parseDouble(value);
    }

    private void saveModifiedBidToServer(BidCalculationModel calculatedModel, Address deliveryLocation) {

        Bid bid=new Bid(bidId,quantity,price,calculatedModel.getVat(),calculatedModel.getServiceCharge(),calculatedModel.getShipCharge(),deliveryLocation);

        ApiDataManager.postModifiedBid(this,bid);

    }



    @Override
    public void getPostBidInformation(Boolean result, String errorMessage) {
        if(errorMessage.equals("")){
            Toast.makeText(BidAndBidEditActivity.this,getString(R.string.bid_successful),Toast.LENGTH_SHORT).show();
            finish();
        }else {
            Toast.makeText(BidAndBidEditActivity.this,errorMessage,Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getBidInformation(Bid bid, String errorMessage) {

        this.bid = bid;

        if(flag.equals(StaticDataManager.MODIFY_FLAG)){

            BidCalculationModel calculation = calculate(bid.getQuantity(),bid.getPrice());

            quantityView.setText(decimalFormat.format(bid.getQuantity())+"");

            negotiatedPriceView.setText(decimalFormat.format(bid.getPrice())+"");

            showView(calculation);

            autoCalculate();

        }



    }

    @Override
    public void modifiedBidInformation(boolean result, String errorMessage) {

        if(result){

            Toast.makeText(this,getString(R.string.modified_bid_successful),Toast.LENGTH_SHORT).show();

        }

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
