package com.example.amir.shetu.fragment;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amir.shetu.R;
import com.example.amir.shetu.interfaces.ApiDataManager.PreorderListener;
import com.example.amir.shetu.manager.PreferenceManager;
import com.example.amir.shetu.manager.StaticDataManager;
import com.example.amir.shetu.model.Address;
import com.example.amir.shetu.model.Commodity;
import com.example.amir.shetu.model.CommodityGrade;
import com.example.amir.shetu.model.District;
import com.example.amir.shetu.model.PreOrder;
import com.example.amir.shetu.model.PreOrderCommodityPost;
import com.example.amir.shetu.model.PreorderList;
import com.example.amir.shetu.model.Unit;
import com.example.amir.shetu.model.Upazila;
import com.example.amir.shetu.retorfit.API;
import com.example.amir.shetu.retorfit.RetrofitInstance;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class PreOrderSaleMarketingFragment extends Fragment implements AdapterView.OnItemSelectedListener, PreorderListener {

    private API api=RetrofitInstance.getApi();
    private ProgressBar mProgressBar;
    private TextView submitBtn,totalView;
    private EditText quantityView, priceView,featuresView,startdate,enddate,postalCode,localAddress;
    private Spinner spinner,gradeSpinner;
    private Spinner districtSpinner,upazilaSpinner;
    private TextInputLayout endDateView,startDateview;
    private RadioGroup unit;
     public  View rootView;
    private int unitId;
    private int commodityId;
    private int gradeId,buyerId;
    public int districtId;
    public int upazilaId;
    String postCode;
    String localAdd;
    int mYear ;
    int mMonth;
    int mDay;
    String expectedDeliveryStartDate;
    String expectedDeliveryEndDate;


    public PreOrderSaleMarketingFragment()
    {

    }


    public static PreOrderSaleMarketingFragment newInstance()
    {
        PreOrderSaleMarketingFragment fragment = new PreOrderSaleMarketingFragment();
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView=inflater.inflate(R.layout.fragment_pre_order_sale_marketing,null);
        buyerId= PreferenceManager.getInstance().getInt(PreferenceManager.USER_ID);
        fetchDropdownData();
        initializeView();
        autoCalculate();
        endDateView.setHint( "শেষের তারিখ");
        startDateview.setHint("শুরুর তারিখ");
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tempQuantity=quantityView.getText().toString().trim();
                String tempPrice= priceView.getText().toString().trim();
                String description= featuresView.getText().toString().trim();
                expectedDeliveryStartDate =startdate.getText().toString().trim();
                expectedDeliveryEndDate =enddate.getText().toString().trim();
                postCode = postalCode.getText().toString().trim();
               localAdd =localAddress.getText().toString().trim();
                if(!tempQuantity.equals("") && !tempPrice.equals("") && !description.equals("")&&!postCode.equals("")&&!localAdd.equals("")){
                    int expectedQuantity=Integer.parseInt(tempQuantity);
                    double expectedPrice=Double.parseDouble(tempPrice);
                    Address deliveryLocation= preAdd(districtId,upazilaId,postCode,localAdd);
                    saveToServer(expectedQuantity,expectedPrice,description,expectedDeliveryStartDate,expectedDeliveryEndDate,deliveryLocation);
                    submitBtn.setVisibility(View.INVISIBLE);

                }else{
                    Toast.makeText(getContext(),R.string.login_empty,Toast.LENGTH_SHORT).show();
                }


            }
        });

        startdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentDate = Calendar.getInstance();
                mYear = mcurrentDate.get(Calendar.YEAR);
                mMonth = mcurrentDate.get(Calendar.MONTH);
                mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        Calendar myCalendar = Calendar.getInstance();
                        myCalendar.set(Calendar.YEAR, selectedyear);
                        myCalendar.set(Calendar.MONTH, selectedmonth);
                        myCalendar.set(Calendar.DAY_OF_MONTH, selectedday);
                        String myFormat = "yyyy-MM-dd"; //Change as you need
                        SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
                        startdate.setText(sdf.format(myCalendar.getTime()));
                        mDay = selectedday;
                        mMonth = selectedmonth;
                        mYear = selectedyear;
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Select date");
                mDatePicker.show();
            }
        });

        enddate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentDate = Calendar.getInstance();
                mYear = mcurrentDate.get(Calendar.YEAR);
                mMonth = mcurrentDate.get(Calendar.MONTH);
                mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        Calendar myCalendar = Calendar.getInstance();
                        myCalendar.set(Calendar.YEAR, selectedyear);
                        myCalendar.set(Calendar.MONTH, selectedmonth);
                        myCalendar.set(Calendar.DAY_OF_MONTH, selectedday);
                        String myFormat = "yyyy-MM-dd"; //Change as you need
                        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.getDefault());
                        enddate.setText(sdf.format(myCalendar.getTime()));

                        mDay = selectedday;
                        mMonth = selectedmonth;
                        mYear = selectedyear;
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Select date");
                mDatePicker.show();
            }
        });

        return rootView;
    }

    private void saveToServer(int expectedQuantity, double expectedPrice, String description, String expectedDeliveryStartDate, String expectedDeliveryEndDate, Address deliveryLocation) {
        mProgressBar.setVisibility(View.VISIBLE);
        Log.d("Date",expectedDeliveryStartDate.toString());
        Log.d("Date",expectedDeliveryEndDate.toString());
        PreOrderCommodityPost preOrder =new PreOrderCommodityPost(expectedQuantity,expectedPrice,unitId,gradeId,commodityId,expectedDeliveryStartDate,expectedDeliveryEndDate,description,buyerId,deliveryLocation);
        Call<Boolean> call =api.PostPreorder(preOrder);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                Log.d("ResponseCode", String.valueOf(response.code()));
                mProgressBar.setVisibility(View.GONE);
                quantityView.setText("");
                priceView.setText("");
                featuresView.setText("");
                localAddress.setText("");
                postalCode.setText("");
                Toast.makeText(getContext(),R.string.successfully_uploaded,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.d("Fail",t.getMessage() );
                mProgressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(getContext(),getString(R.string.server_error),Toast.LENGTH_SHORT).show();
                submitBtn.setVisibility(View.VISIBLE);
            }
        });

    }
//
    private Address preAdd(Integer districtId, Integer upazilaId, String postCode, String localAdd) {

        return new Address(districtId,upazilaId,postCode,localAdd);

    }

    private void autoCalculate() {
        quantityView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String tempNegotiationPrice = priceView.getText().toString().trim();
                s = s.toString().trim();
                if (!tempNegotiationPrice.equals("") && !s.equals("")) {
                    double negotiationPrice = Double.parseDouble(tempNegotiationPrice);
                    int quantity = (int)(Double.parseDouble(s.toString()));
                    String total=""+negotiationPrice*quantity;

                    totalView.setText(total);
                }else {
                    totalView.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        priceView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                s = s.toString().trim();
                String tempQuantity = quantityView.getText().toString().trim();
                if (!tempQuantity.equals("") && !s.equals("")) {
                    int quantity = (int)(Double.parseDouble(tempQuantity));
                    double negotiationPrice = Double.parseDouble(s.toString());
                    String total=""+negotiationPrice*quantity;
                    totalView.setVisibility(View.VISIBLE);
                    totalView.setText(total+StaticDataManager.TAKA_SIGN);
                }else {
                    totalView.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void initializeView() {
        mProgressBar = rootView.findViewById(R.id.progressBar);
        spinner=rootView.findViewById(R.id.product_spinner);
        unit =rootView.findViewById(R.id.unit);
        submitBtn = rootView.findViewById(R.id.submit);
        quantityView = rootView.findViewById(R.id.quantity);
        priceView = rootView.findViewById(R.id.neotiable_price);
        featuresView = rootView.findViewById(R.id.feature);
        totalView = rootView.findViewById(R.id.total);
        districtSpinner=rootView.findViewById(R.id.district_spinner);
        upazilaSpinner=rootView.findViewById(R.id.upazila_spinner);
        enddate=rootView.findViewById(R.id.end_date);
        startdate=rootView.findViewById(R.id.start_date);
        startDateview=rootView.findViewById(R.id.startdate_layout);
        endDateView=rootView.findViewById(R.id.enddate_layout);
        postalCode=rootView.findViewById(R.id.postal_code);
        localAddress=rootView.findViewById(R.id.local_address);
        gradeSpinner=rootView.findViewById(R.id.grade_spinner);

    }

    private void fetchDropdownData() {

        RetrofitInstance.getApi().getCommodity().enqueue(new Callback<List<Commodity>>() {
            @Override
            public void onResponse(Call<List<Commodity>> call, Response<List<Commodity>> response) {
                fetchUnit();
                if(response.code()==StaticDataManager.OK)
                {
                    List<String> commuditiList=new ArrayList<>();
                            for(int i=0;i<response.body().size();i++){
                                commuditiList.add(response.body().get(i).bnName);
                            }
                            if(commuditiList.size()>0){
                                createProductSpinner(commuditiList,response.body());
                            }
                }
            }

            @Override
            public void onFailure(Call<List<Commodity>> call, Throwable t) {
                Log.d("Fasil",t.getMessage());
            }
        });
    }

    private void fetchUnit() {
        api.getUnit().enqueue(new Callback<List<Unit>>() {
            @Override
            public void onResponse(Call<List<Unit>> call, Response<List<Unit>> response) {
                fetchDistrict();
                if(response.code()==StaticDataManager.OK){
                    createUnitRadio(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Unit>> call, Throwable t) {
                Log.d("d","d");
            }
        });
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
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(rootView.getContext(),android.R.layout.simple_spinner_item,upazilaList);
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
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(rootView.getContext(),android.R.layout.simple_spinner_item,districtList);
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


    private void createUnitRadio(final List<Unit> unitList){

        for (int i=0;i<unitList.size();i++){
            RadioButton rbn = new RadioButton(getContext());
            rbn.setId(i);
            rbn.setText(unitList.get(i).name);
            unit.addView(rbn);
        }

        unit.check(0);

        if(unitList.size()>0)
            unitId=unitList.get(0).id;
        unit.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                unitId=unitList.get(checkedId).id;


            }
        });


    }

    private void createProductSpinner(List<String> list, final List<Commodity> commudity){
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(rootView.getContext(),android.R.layout.simple_spinner_item,list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                commodityId=commudity.get(position).id;
               fetchGrade(commudity.get(position).categoryId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Log.d("xxxxxxxxx",""+commodityId);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
       // if(parent.getId())
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    private void fetchGrade(int id)
    {
        RetrofitInstance.getApi().getCommodityGradeById(id).enqueue(new Callback<List<CommodityGrade>>() {
            @Override
            public void onResponse(Call<List<CommodityGrade>> call, Response<List<CommodityGrade>> response) {
                if(response.code()==StaticDataManager.OK)
                {
                    List<String> gradeList=new ArrayList<>();
                    for(int i=0;i<response.body().size();i++){
                        gradeList.add(" "+response.body().get(i).getBnGradeName());
                    }
                    if(gradeList.size()>0){
                        createGradeSpinner(gradeList,response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<CommodityGrade>> call, Throwable t) {

            }
        });
    }

    private void createGradeSpinner(List<String> gradeList, final List<CommodityGrade> body) {
        gradeSpinner.setOnItemSelectedListener(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(rootView.getContext(),android.R.layout.simple_spinner_item,gradeList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gradeSpinner.setAdapter(adapter);
        gradeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                gradeId=body.get(i).id;
                Log.d("GardeIDspinner", String.valueOf(gradeId));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void getPreorderList(List<PreOrder> preorderList, int endpage, String errorMessage) {
       // this.preOrder =preorderList;
    }
}
