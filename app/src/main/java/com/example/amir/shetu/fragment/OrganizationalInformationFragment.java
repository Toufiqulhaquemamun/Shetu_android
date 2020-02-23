package com.example.amir.shetu.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.amir.shetu.R;
import com.example.amir.shetu.interfaces.ApiDataManager.ProfileInformationListener;
import com.example.amir.shetu.model.Organization;
import com.example.amir.shetu.manager.ApiDataManager;
import com.example.amir.shetu.manager.PreferenceManager;
import com.example.amir.shetu.model.ProfileSME;

import static com.example.amir.shetu.manager.StaticDataManager.CERTIFICATE_OF_INCORPORATION_IMAGE_PREFIX;
import static com.example.amir.shetu.manager.StaticDataManager.Memorandoum_OF_UNDERSTANDING_IMAGE_PREFIX;
import static com.example.amir.shetu.manager.StaticDataManager.OTHER_IMAGE_PREFIX;
import static com.example.amir.shetu.manager.StaticDataManager.TIN_IMAGE_PREFIX;
import static com.example.amir.shetu.manager.StaticDataManager.TRADE_LICENSE_IMAGE_PREFIX;
import static com.example.amir.shetu.manager.StaticDataManager.VAT_IMAGE_PREFIX;

public class OrganizationalInformationFragment extends Fragment implements ProfileInformationListener {

    private View rootView;

    private TextView organizationNameView, businessTypeView, companyTypeView, registrationView, startDateView, tradeLicenceNoView, vatNoView,

    tinNoView, employeeNumberView,organizationDistrictView,organizationThanaView,organizationPostalCodeView,organizationHouseView,revenueView,netProfitView,totalAssetView,expenseView,yearlyTurnOverView,permanentAssetView,otherDocumentView,organizationTinNo,organizationVatNo,organizationTradeLicenceNo;

    private ImageView otherDocumentPic,certificateOfIncorporationPic,memorandumOfUnderstandingPic,organizationTinPic,organizationVatPic,organizationTradeLicencePic;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView =inflater.inflate(R.layout.fragment_organizational, container, false);

        initializeView();

        int userId= PreferenceManager.getInstance().getInt("id");

        ApiDataManager.getProfileInformation(this,userId);

        return rootView;
    }

    private void initializeView() {

        organizationNameView =rootView.findViewById(R.id.organization_name);

        businessTypeView =rootView.findViewById(R.id.business_type);

        companyTypeView =rootView.findViewById(R.id.company_type);

        registrationView =rootView.findViewById(R.id.registration);

        startDateView =rootView.findViewById(R.id.start_date);

        tradeLicenceNoView =rootView.findViewById(R.id.trade_licence_no);

        vatNoView =rootView.findViewById(R.id.vat_no);

        tinNoView =rootView.findViewById(R.id.tin_no);

        employeeNumberView=rootView.findViewById(R.id.employee_no);

        organizationDistrictView=rootView.findViewById(R.id.organization_district);

        organizationThanaView =rootView.findViewById(R.id.organization_thana);

        organizationPostalCodeView =rootView.findViewById(R.id.organization_postal_code);

        organizationHouseView =rootView.findViewById(R.id.organization_house);

        revenueView =rootView.findViewById(R.id.revenue);

        netProfitView =rootView.findViewById(R.id.net_profit);

        totalAssetView =rootView.findViewById(R.id.total_asset);

        expenseView =rootView.findViewById(R.id.expense);

        yearlyTurnOverView =rootView.findViewById(R.id.yearly_turn_over);

        permanentAssetView =rootView.findViewById(R.id.permanent_asset);

        otherDocumentPic =rootView.findViewById(R.id.other_document_pic);

        certificateOfIncorporationPic =rootView.findViewById(R.id.certificate_of_incorporation_pic);

        memorandumOfUnderstandingPic =rootView.findViewById(R.id.memorandum_of_understanding_pic);

        organizationTinPic =rootView.findViewById(R.id.organization_tin_pic);

        organizationVatPic =rootView.findViewById(R.id.organization_vat_pic);

        otherDocumentView =rootView.findViewById(R.id.other_document_view);

        organizationTinNo =rootView.findViewById(R.id.organization_tin_no);

        organizationVatNo =rootView.findViewById(R.id.organization_vat_no);

        organizationTradeLicenceNo =rootView.findViewById(R.id.organization_trade_licence_no);

        organizationTradeLicencePic =rootView.findViewById(R.id.organization_trade_licence_pic);



    }

    @Override
    public void getProfileInformation(ProfileSME result, String errorMessage) {
        if(errorMessage.equals("")){
          Organization organization=result.getOrganization();
            if(organization!=null){
                organizationNameView.setText(result.getPersonalInformation().getBnName());

                businessTypeView.setText(result.getOrganization().getOwnership());

                companyTypeView.setText(result.getOrganization().getOwnership());

                registrationView.setText(result.getOrganization().getRegisteredTo());

                startDateView.setText(result.getOrganization().getEstDate());

                tradeLicenceNoView.setText(result.getOrganization().getTradeLicenseNo());

                vatNoView.setText(result.getOrganization().getVatRegistrationNO());

                tinNoView.setText(result.getOrganization().getTinNo());

                employeeNumberView.setText(result.getOrganization().getEmployeeNo().toString());

                if(result.getOrganization().getAddress()!=null)
                {
                    organizationHouseView.setText(result.getOrganization().getAddress().getStreetAddress());

                    organizationPostalCodeView.setText(result.getOrganization().getAddress().getPostCode());

                    organizationThanaView.setText(result.getOrganization().getAddress().getThana());

                    organizationDistrictView.setText(result.getOrganization().getAddress().getDistrict().getBnName());
                }
                else{
                    organizationHouseView.setText("Empty");
                    organizationPostalCodeView.setText("Empty");
                    organizationThanaView.setText("Empty");
                    organizationDistrictView.setText("Empty");
                }


                permanentAssetView.setText(result.getOrganization().getPermanentAsset().toString());

                revenueView.setText(result.getOrganization().getRevenue().toString());

                netProfitView.setText(result.getOrganization().getNetProfit().toString());

                totalAssetView.setText(result.getOrganization().getTotalAsset().toString());

                expenseView.setText(result.getOrganization().getExpense().toString());

                yearlyTurnOverView.setText(result.getOrganization().getYearlyTurnover().toString());


                if(organization.getOtherFile()!=null){
                    Glide.with(getContext()).load(OTHER_IMAGE_PREFIX +""+organization.getOtherFile()).into(otherDocumentPic);
                }

                if(organization.getIncorporateCertificateFile()!=null){
                    Glide.with(getContext()).load(CERTIFICATE_OF_INCORPORATION_IMAGE_PREFIX +""+organization.getIncorporateCertificateFile()).into(certificateOfIncorporationPic);
                }

                if(organization.getMouFile()!=null){
                    Glide.with(getContext()).load(Memorandoum_OF_UNDERSTANDING_IMAGE_PREFIX +""+organization.getMouFile()).into(memorandumOfUnderstandingPic);
                }

                if(organization.getTinFile()!=null){
                    Glide.with(getContext()).load(TIN_IMAGE_PREFIX +""+organization.getTinFile()).into(organizationTinPic);
                }

                if(organization.getVatRegistrationFile()!=null){
                    Glide.with(getContext()).load(VAT_IMAGE_PREFIX+""+organization.getVatRegistrationFile()).into(organizationVatPic);
                }

                if(organization.getTradeLicenseFile()!=null){
                    Glide.with(getContext()).load(TRADE_LICENSE_IMAGE_PREFIX+""+organization.getTradeLicenseFile()).into(organizationTradeLicencePic);
                }

                otherDocumentView.setText(organization.getOther());

                organizationTinNo.setText(organization.getTinNo());

                organizationVatNo.setText(organization.getVatRegistrationNO());

                organizationTradeLicenceNo.setText(organization.getTradeLicenseNo());
            }

        }

    }


}
