package com.example.amir.shetu.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.amir.shetu.R;
import com.example.amir.shetu.interfaces.ApiDataManager.ProfileInformationListener;
import com.example.amir.shetu.manager.ApiDataManager;
import com.example.amir.shetu.model.PersonalInformation;
import com.example.amir.shetu.manager.PreferenceManager;
import com.example.amir.shetu.model.ProfileSME;

import static com.example.amir.shetu.manager.StaticDataManager.CHARACTER_CERTIFICATE_IMAGE_PREFIX;
import static com.example.amir.shetu.manager.StaticDataManager.NID_IMAGE_PREFIX;
import static com.example.amir.shetu.manager.StaticDataManager.PROFILE_IMAGE_PREFIX;
import static com.example.amir.shetu.manager.StaticDataManager.SIGN_IMAGE_PREFIX;

public class PersonalInformationFragment extends Fragment implements ProfileInformationListener {


    private View rootView;

    private ImageView profilePicView,signPicView,passportPicView,characterCertificatePicView,nidPicVIew;

    private TextView nameView,fatherNameView,motherNameView,gerderView,dateOfBirthView,experianceView,emailView,phoneView, nidNoView,passportNoView,educationView,presentHomeView,presentDistrictView,presentThanaView,presentPostalCodeView,permanentHomeView,permanentDistrictView,permanentThanaView,permanentPostalCodeView;

    private ProgressBar progressBar;

    private LinearLayout container;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView =inflater.inflate(R.layout.fragment_personal, container, false);

        initializeView();

        int userId= PreferenceManager.getInstance().getInt("id");

        progressBar.setVisibility(View.VISIBLE);

        ApiDataManager.getProfileInformation(this,userId);
        return rootView;
    }

    private void initializeView() {

        container=rootView.findViewById(R.id.container);

        profilePicView = rootView.findViewById(R.id.profile_pic);

        nameView = rootView.findViewById(R.id.name);

        fatherNameView = rootView.findViewById(R.id.father_name);

        motherNameView = rootView.findViewById(R.id.mother_name);

        gerderView = rootView.findViewById(R.id.gender);

        dateOfBirthView = rootView.findViewById(R.id.date_of_birth);

        educationView = rootView.findViewById(R.id.education);

        experianceView=rootView.findViewById(R.id.experience);

        emailView=rootView.findViewById(R.id.email);

        phoneView=rootView.findViewById(R.id.phone);

        nidNoView =rootView.findViewById(R.id.nid_no);

        passportNoView=rootView.findViewById(R.id.passport_no);

        presentDistrictView=rootView.findViewById(R.id.present_district);

        presentThanaView=rootView.findViewById(R.id.present_thana);

        presentPostalCodeView=rootView.findViewById(R.id.present_postal_code);

        presentHomeView=rootView.findViewById(R.id.present_house);;

        permanentDistrictView=rootView.findViewById(R.id.permanent_district);

        permanentThanaView=rootView.findViewById(R.id.permanent_thana);

        permanentPostalCodeView=rootView.findViewById(R.id.permanent_postal_code);

        permanentHomeView=rootView.findViewById(R.id.permanent_house);

        passportPicView=rootView.findViewById(R.id.passport_pic);

        nidPicVIew =rootView.findViewById(R.id.nid_pic);

        signPicView=rootView.findViewById(R.id.sign_pic);

        progressBar=rootView.findViewById(R.id.progressBar);

        characterCertificatePicView=rootView.findViewById(R.id.character_certificate_pic);

        passportPicView=rootView.findViewById(R.id.passport_pic);

    }

    @Override
    public void getProfileInformation(ProfileSME profileSME, String errorMessage) {
        Log.d("User ID", errorMessage);
        if(errorMessage.equals("")){

            setView(profileSME);

            container.setVisibility(View.VISIBLE);
        }

        progressBar.setVisibility(View.GONE);

    }

    private void setView(ProfileSME result) {

        nameView.setText(result.getPersonalInformation().getName());

        fatherNameView.setText(result.getPersonalInformation().getFatherName());

        motherNameView.setText(result.getPersonalInformation().getMotherName());

        gerderView.setText(result.getPersonalInformation().getGender());

        dateOfBirthView.setText(result.getPersonalInformation().getDateOfBirth());

        experianceView.setText(result.getPersonalInformation().getExperience());

        emailView.setText(result.getPersonalInformation().getEmail());

        phoneView.setText(result.getPersonalInformation().getPhone());

        nidNoView.setText(result.getPersonalInformation().getNid());

        passportNoView.setText(result.getPersonalInformation().getPassport());

     PersonalInformation p = result.getPersonalInformation();

        educationView.setText(result.getPersonalInformation().getEducation());
        if(result.getPersonalInformation().getImage()!=null){

            Glide.with(getContext())
                    .load(PROFILE_IMAGE_PREFIX +""+result.getPersonalInformation().getImage())
                    .into(profilePicView);

        }
        else {

        }

        if(result.getPersonalInformation().getPresentAddress() !=null)
        {
            presentDistrictView.setText(result.getPersonalInformation().getPresentAddress().getDistrict().getBnName());
            presentThanaView.setText(result.getPersonalInformation().getPresentAddress().getThana());
            //presentPostalCodeView.setText(result.getPersonalInformation().getPresentAddress().getPostCode().toString());
            presentHomeView.setText(result.getPersonalInformation().getPresentAddress().getStreetAddress());

        }
        else{
            presentDistrictView.setText("Empty");
            presentThanaView.setText("Empty");
            presentPostalCodeView.setText("Empty");
            presentHomeView.setText("Empty");
        }


//        presentThanaView.setText(result.getPersonalInformation().getPresentAddress().getThana());




        if(result.getPersonalInformation().getPermanentAddress()!=null){
            permanentDistrictView.setText(result.getPersonalInformation().getPermanentAddress().getDistrict().getBnName());

            permanentThanaView.setText(result.getPersonalInformation().getPermanentAddress().getThana());

            permanentPostalCodeView.setText(result.getPersonalInformation().getPermanentAddress().getPostCode());

            permanentHomeView.setText(result.getPersonalInformation().getPermanentAddress().getStreetAddress());
        }


        if(result.getPersonalInformation().getSignature()!=null){
            Glide.with(getContext()).load(SIGN_IMAGE_PREFIX +""+result.getPersonalInformation().getSignature()).into(signPicView);
        }
        if(result.getPersonalInformation().getImage()!=null){
            Glide.with(getContext()).load(PROFILE_IMAGE_PREFIX +""+result.getPersonalInformation().getImage()).into(profilePicView);
        }
        if(result.getPersonalInformation().getCharacterCertificate()!=null){
            Glide.with(getContext()).load(CHARACTER_CERTIFICATE_IMAGE_PREFIX +""+result.getPersonalInformation().getCharacterCertificate()).into(characterCertificatePicView);
        }
        if(result.getPersonalInformation().getNidAttachment()!=null){
            Glide.with(getContext()).load(NID_IMAGE_PREFIX +""+result.getPersonalInformation().getNidAttachment()).into(nidPicVIew);
        }
    }


}
