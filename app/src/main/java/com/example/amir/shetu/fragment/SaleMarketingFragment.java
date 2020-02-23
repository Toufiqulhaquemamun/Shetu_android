package com.example.amir.shetu.fragment;

import android.Manifest;
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
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amir.shetu.R;
import com.example.amir.shetu.adapter.HorizontalRecyclerViewAdapter;
import com.example.amir.shetu.model.SMECommodityList;
import com.example.amir.shetu.manager.PreferenceManager;
import com.example.amir.shetu.manager.StaticDataManager;
import com.example.amir.shetu.retorfit.API;
import com.example.amir.shetu.retorfit.RetrofitInstance;
import com.example.amir.shetu.model.Unit;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SaleMarketingFragment extends Fragment implements AdapterView.OnItemSelectedListener{
    private API api=RetrofitInstance.getApi();
    private ProgressBar mProgressBar;
    private Button btnChoose;
    private TextView submitBtn,totalView;
    private EditText quantityView, priceView,featuresView;
    private Spinner spinner;
    private RadioGroup unit;
    private HorizontalRecyclerViewAdapter horizontalAdapter;
    private LinearLayoutManager horizontalLayoutManager;
    private RecyclerView mHorizontalRecyclerView;
    private ArrayList<Uri> images=new ArrayList<>();
    View rootView;
    private int unitId;
    private int productId;
    private ArrayList<MultipartBody.Part> imageParts=new ArrayList<>();
    private RequestBody quantityBody;
    private RequestBody priceBody;
    private RequestBody unitIdBody;
    private RequestBody productIdBody;
    private RequestBody sellerIdBody;
    private RequestBody featuresBody;
    private final String DATE_FORMATE = "yyyyMMddHHmmss";
    private String imageFilePath;
    int imgcount=0;


    public static SaleMarketingFragment newInstance()
    {
        return new SaleMarketingFragment();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView=inflater.inflate(R.layout.fragment_sale_marketing,null);
        int userId= PreferenceManager.getInstance().getInt(PreferenceManager.USER_ID);
        fetchDropdownData(userId);
        initializeView();
        autoCalculate();

        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Display the file chooser dialog
                if (askForPermission()){
                    captureImage();
                }

            }
        });
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tempQuantity=quantityView.getText().toString().trim();
                String tempPrice= priceView.getText().toString().trim();
                String features= featuresView.getText().toString().trim();
                String proId= String.valueOf(productId);
                if(!tempQuantity.equals("") && !tempPrice.equals("") && !features.equals("")&& !(productId ==0)&&!(imgcount==0)){
                    int quantity=Integer.parseInt(tempQuantity);
                    double price=Double.parseDouble(tempPrice);
                    saveToServer(quantity,price,features);
                    submitBtn.setVisibility(View.INVISIBLE);

                }else{
                    Toast.makeText(getContext(),R.string.login_empty,Toast.LENGTH_SHORT).show();
                }


            }
        });

        return rootView;
    }



    private void captureImage() {
        final CharSequence[] options = { "ফোন ক্যামেরা দিয়ে","ফোন থেকে যুক্ত করুন","বাতিল করুন" };
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogCustom));
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
        builder.show();

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



    private void saveToServer(int quantity, double price,String features) {
        mProgressBar.setVisibility(View.VISIBLE);
        int userId=PreferenceManager.getInstance().getInt("id");
        createRequestBody(quantity,price,unitId,userId,productId,images,features);
        api.uploadProduct(quantityBody,priceBody,unitIdBody,productIdBody,sellerIdBody,featuresBody,imageParts).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                mProgressBar.setVisibility(View.GONE);
                if(response.code()==StaticDataManager.OK){
                    quantityView.getText().clear();
                    priceView.getText().clear();
                    images.clear();
                    featuresView.getText().clear();
                    totalView.setText("");
                    totalView.setVisibility(View.GONE);
                    horizontalAdapter.notifyDataSetChanged();
                    Toast.makeText(getContext(),R.string.successfully_uploaded,Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                mProgressBar.setVisibility(View.GONE);
                Toast.makeText(getContext(),getString(R.string.server_error),Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void createRequestBody(int quantity, double price, int unitId, int userId,int productId, ArrayList<Uri> images,String features) {

        quantityBody=createPartFromString(quantity+"");
        priceBody=createPartFromString(price+"");
        unitIdBody=createPartFromString(unitId+"");
        sellerIdBody=createPartFromString(userId+"");
        productIdBody=createPartFromString(productId+"");
        featuresBody =createPartFromString(features);

        for(int i=0;i<images.size();i++){
            if ( images.get(i)!= null) {
                MultipartBody.Part body1 = prepareFilePart("files", images.get(i));
                imageParts.add(body1);
            }
        }

    }

    @NonNull
    private RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create(
                okhttp3.MultipartBody.FORM, descriptionString);
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

    private void initializeView() {
        mHorizontalRecyclerView = (RecyclerView) rootView.findViewById(R.id.horizontalRecyclerView);
        mProgressBar = rootView.findViewById(R.id.progressBar);
        btnChoose = rootView.findViewById(R.id.btnChoose);
        spinner=rootView.findViewById(R.id.product_spinner);
        unit =rootView.findViewById(R.id.unit);
        submitBtn = rootView.findViewById(R.id.submit);
        quantityView = rootView.findViewById(R.id.quantity);
        priceView = rootView.findViewById(R.id.neotiable_price);
        featuresView = rootView.findViewById(R.id.feature);
        totalView = rootView.findViewById(R.id.total);


    }



    public Uri getImageUri(Context inContext, Bitmap inImage) {
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage,"dsdd","sdssd" );
        return Uri.parse(path);
    }

    private void createHorizontalRecyclerView() {
        horizontalAdapter = new HorizontalRecyclerViewAdapter(images, getContext());
        horizontalLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mHorizontalRecyclerView.setLayoutManager(horizontalLayoutManager);
        mHorizontalRecyclerView.setAdapter(horizontalAdapter);
        imgcount++;
    }

    private void fetchDropdownData(int userId) {
//        progressBar.setVisibility(View.VISIBLE);

        RetrofitInstance.getApi()
                .getAssignedCommodities(userId)
                .enqueue(new Callback<List<SMECommodityList>>() {
            @Override
            public void onResponse(Call<List<SMECommodityList>> call, Response<List<SMECommodityList>> response) {
                fetchUnit();
                if(response.code()==StaticDataManager.OK){

                    List<String> commuditiList=new ArrayList<>();
                    for(int i=0;i<response.body().size();i++){
                        commuditiList.add(response.body().get(i).getCommodityName()+",গ্রেড : "+response.body().get(i).getGradeName());
                    }
                    if(commuditiList.size()>0){
                        createProductSpinner(commuditiList,response.body());
                    }

                }

            }

            @Override
            public void onFailure(Call<List<SMECommodityList>> call, Throwable t) {
                Log.d("d","d");
            }
        });
    }

    private void fetchUnit() {
        api.getUnit().enqueue(new Callback<List<Unit>>() {
            @Override
            public void onResponse(Call<List<Unit>> call, Response<List<Unit>> response) {
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


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {


        if(resultCode==getActivity().RESULT_OK && requestCode ==1&&data!=null) {
            Uri selectedImage =  data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.
                    DATA};
            if (selectedImage != null) {
                Cursor cursor = getActivity().getContentResolver().query(selectedImage,
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
        else if(resultCode==getActivity().RESULT_OK && requestCode ==StaticDataManager.REQUEST_CODE)
        {
            Uri imageUri = Uri.parse(imageFilePath);
            images.add(imageUri);
            createHorizontalRecyclerView();
        }


    }



    private boolean askForPermission() {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= Build.VERSION_CODES.M) {
            int hasCallPermission = ContextCompat.checkSelfPermission(getContext(),
                    Manifest.permission.READ_EXTERNAL_STORAGE);
            if (hasCallPermission != PackageManager.PERMISSION_GRANTED) {

                    showDialog();


                if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                        Manifest.permission.READ_EXTERNAL_STORAGE)) {
//                    user permanently disable permission message

                }else {
                    // Request for permission
                    ActivityCompat.requestPermissions(getActivity(),
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
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        StaticDataManager.REQUEST_CODE_ASK_PERMISSIONS);
            }
        };
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case StaticDataManager.REQUEST_CODE_ASK_PERMISSIONS:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted
                    captureImage();
                } else {
                    // Permission Denied
                    Toast.makeText(getContext(), R.string.permission_permanently_denied, Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void showMessageOKCancel(DialogInterface.OnClickListener okListener) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
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

    private void createProductSpinner(List<String> list, final List<SMECommodityList> commudity){
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(rootView.getContext(),android.R.layout.simple_spinner_item,list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                productId=commudity.get(position).id;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Log.d("xxxxxxxxx",""+productId);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public String toString() {
        return "Sale Marketing";
    }




}

