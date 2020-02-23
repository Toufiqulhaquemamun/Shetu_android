package com.example.amir.shetu.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.amir.shetu.R;
import com.example.amir.shetu.interfaces.ApiDataManager.ProfileInformationListener;
import com.example.amir.shetu.manager.ApiDataManager;
import com.example.amir.shetu.manager.PreferenceManager;
import com.example.amir.shetu.model.Address;
import com.example.amir.shetu.model.OrganizationAddress;
import com.example.amir.shetu.model.PresentAddress;
import com.example.amir.shetu.model.ProfileSME;

import static com.example.amir.shetu.manager.StaticDataManager.PROFILE_IMAGE_PREFIX;

public class AgentInformationFragment extends Fragment implements ProfileInformationListener {

    private View rootView;

    private ImageView imageView;

    private TextView nameView, genderView,emailView,phoneView, organizationNameView, businessTypeView,districtView,thanaView,postalCodeView,homeView,organizationDistrictView,organizationThanaView,organizationPostalCodeView,organizationHomeView;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView =inflater.inflate(R.layout.fragment_agent, container, false);

        initializeView();

        int userId= PreferenceManager.getInstance().getInt(PreferenceManager.USER_ID);

        ApiDataManager.getProfileInformation(this,userId);

        return rootView;
    }

    private void initializeView() {

        nameView=rootView.findViewById(R.id.name);

        genderView =rootView.findViewById(R.id.gender);

        emailView=rootView.findViewById(R.id.email);

        phoneView=rootView.findViewById(R.id.phone);

        organizationNameView =rootView.findViewById(R.id.organization_name);

        businessTypeView =rootView.findViewById(R.id.business_type);

        imageView=rootView.findViewById(R.id.profile_pic);

        districtView=rootView.findViewById(R.id.agent_district);

        thanaView=rootView.findViewById(R.id.agent_thana);

        postalCodeView=rootView.findViewById(R.id.agent_postal_code);

        homeView=rootView.findViewById(R.id.agent_house);

        organizationDistrictView=rootView.findViewById(R.id.agent_organization_district);

        organizationThanaView=rootView.findViewById(R.id.agent_organization_thana);

        organizationPostalCodeView=rootView.findViewById(R.id.agent_organization_postal_code);

        organizationHomeView=rootView.findViewById(R.id.agent_organization_house);

    }

    @Override
    public void getProfileInformation(ProfileSME result, String errorMessage) {
        if(errorMessage.equals("")){
           if(result.getAgent().getFirstName()!=null){
               nameView.setText(result.getAgent().getFirstName());
           }else {
               nameView.setText("Empty");
           }

            genderView.setText(result.getAgent().getGender());

            emailView.setText(result.getAgent().getEmail());

            phoneView.setText(result.getAgent().getPhone());

            organizationNameView.setText(result.getAgent().getOrganizationName());

            if(result.getAgent().getTypeofBusiness()!=null){
                businessTypeView.setText(result.getOrganization().getOwnership());
            }

            Glide.with(getContext()).load(PROFILE_IMAGE_PREFIX +""+result.getAgent().getImage()).into(imageView);
            Log.d("d","d");


               PresentAddress presentAddress = result.getAgent().getPresentAddress();

            if(presentAddress!=null) {

                districtView.setText(result.getAgent().getPresentAddress().getDistrict().getBnName());
                thanaView.setText(presentAddress.getThana());
//                postalCodeView.setText((Integer) presentAddress.getPostCode());
                homeView.setText(presentAddress.getStreetAddress());
            }

        OrganizationAddress organizationAddress=result.getAgent().getOrganizationAddress();

            if(organizationAddress!=null){

                organizationDistrictView.setText(result.getAgent().getOrganizationAddress().getDistrict().getBnName());

                organizationThanaView.setText(organizationAddress.getThana());

//                organizationPostalCodeView.setText(organizationAddress.getPostCode().toString());

                organizationHomeView.setText(organizationAddress.getStreetAddress());
            }


        }

    }


}
