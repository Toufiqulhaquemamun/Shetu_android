package com.example.amir.shetu.dialog;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.amir.shetu.R;
import com.example.amir.shetu.interfaces.ApiDataManager.AssignedProductListener;
import com.example.amir.shetu.manager.ApiDataManager;
import com.example.amir.shetu.manager.PreferenceManager;
import com.example.amir.shetu.model.SMECommodityList;

import java.util.List;

public class ProductInformationDialog extends DialogFragment implements AssignedProductListener{
    private View v;
    private int productId;
    private TextView communityName, category,grade,serial,communityDes,unitPerCost,criteria,productionProcess,gradeDocument;

    private LinearLayout commodityDesContainer,gradeContainer,certeriaContainer,productionProcessContainer,gradeDocumentContainer;

    private ProgressBar progressBar;

    private LinearLayout container;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.product_infomation_dialog,container);
        v=rootView;

        int id = PreferenceManager.getInstance().getInt(PreferenceManager.USER_ID);

        getProductId();

        initializeView();

        progressBar.setVisibility(View.VISIBLE);

        ApiDataManager.getAssignedProductList(this, id);

        return rootView;
    }

    private void initializeView() {

        container=v.findViewById(R.id.container);

        progressBar=v.findViewById(R.id.progressBar);

        communityName =v.findViewById(R.id.community_name);

        category = v.findViewById(R.id.category);

        grade =v.findViewById(R.id.grade);

        serial =v.findViewById(R.id.serial);

        communityDes =v.findViewById(R.id.commodity_desc);

        unitPerCost =v.findViewById(R.id.per_unit_cost);

        criteria =v.findViewById(R.id.criteria);

        productionProcess =v.findViewById(R.id.production_process);

        gradeDocument = v.findViewById(R.id.grade_document);

        commodityDesContainer = v.findViewById(R.id.commodity_desc_container);

        gradeContainer = v.findViewById(R.id.grade_container);

        certeriaContainer = v.findViewById(R.id.criteria_container);

        productionProcessContainer = v.findViewById(R.id.production_process_container);

        gradeDocumentContainer = v.findViewById(R.id.grade_document_container);
    }


    private void setView(SMECommodityList product) {

        communityName.setText(product.getCommodityName());

        category.setText(product.getCategoryName());

        grade.setText(product.getGradeName());

        serial.setText(product.getId()+"");

        if(product.getDescription()!=null){

            commodityDesContainer.setVisibility(View.VISIBLE);

            communityDes.setText(product.getDescription().toString());
        }

        unitPerCost.setText(product.getPerUnitProductionCost()+"");

        criteria.setText(product.getGradeCriteria().toString());

        if(product.getProductionProcess()!=null){

            productionProcessContainer.setVisibility(View.VISIBLE);

            productionProcess.setText(product.getProductionProcess().toString());
        }

        if(product.getGradeDocument()!=null){

            gradeDocumentContainer.setVisibility(View.VISIBLE);

            gradeDocument.setText(product.getGradeDocument().toString());
        }


    }


    public void getProductId() {

        if (getArguments() != null) {

            productId = getArguments().getInt(PreferenceManager.PRODUCT_ID,0);

        }
    }

    @Override
    public void getAssignedProductList(List<SMECommodityList> products, String errorMessage) {


        for(int i=0;i<products.size();i++){

            if(products.get(i).getId()==productId){

                setView(products.get(i));
            }
        }

        progressBar.setVisibility(View.GONE);

        container.setVisibility(View.VISIBLE);
    }
}

