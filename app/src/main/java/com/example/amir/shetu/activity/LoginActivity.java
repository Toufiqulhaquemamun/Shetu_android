package com.example.amir.shetu.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amir.shetu.R;
import com.example.amir.shetu.manager.ApiDataManager;
import com.example.amir.shetu.manager.PreferenceManager;
import com.example.amir.shetu.model.Authenticate;
import com.example.amir.shetu.model.UserLogin;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

public class LoginActivity extends AppCompatActivity implements ApiDataManager.LoginInformation{


    private static final String TAG = "LoginActivity";
    private EditText nameView,passwordView;
    private TextView registertxt;
    private CheckBox rememberBox;
    private boolean remember=false;
    private Button loginBtn;
    private LinearLayout layout;

    private ProgressBar progressBar;

    private UserLogin userLogin;

    String name,password,token;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Token();
        getSupportActionBar().hide();

        initializeView();

        restorePassword();


        registertxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(i);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                name=nameView.getText().toString().trim();

                password=passwordView.getText().toString().trim();

                token=PreferenceManager.getInstance().getString("FCM_token");

                if(!name.equals("") && !password.equals("")){

                    userLogin =new UserLogin(name,password,token);

                    progressBar.setVisibility(View.VISIBLE);

                    ApiDataManager.fetchLoginInformation(LoginActivity.this,LoginActivity.this, userLogin);

                }else{

                    Toast.makeText(LoginActivity.this,R.string.login_empty,Toast.LENGTH_SHORT).show();
                }
            }
        });

        rememberBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    remember=true;
                }
            }
        });
    }

    private void restorePassword() {
        PreferenceManager manager=PreferenceManager.getInstance();
        name=manager.getString(PreferenceManager.USER_NAME);
        password=manager.getString(PreferenceManager.PASSWORD);
        nameView.setText(name);
        passwordView.setText(password);
    }




    private void saveResponse(Authenticate authenticate) {

        PreferenceManager.getInstance().setInt(PreferenceManager.USER_ID, authenticate.getId());

        PreferenceManager.getInstance().setString(PreferenceManager.TYPE, authenticate.getType());

        PreferenceManager.getInstance().setString(PreferenceManager.TOKEN, authenticate.getToken());

        PreferenceManager.getInstance().setString(PreferenceManager.USER_IMAGE, authenticate.getUsername());
    }

    private void initializeView() {
        nameView=findViewById(R.id.user_name);
        passwordView=findViewById(R.id.password);
        rememberBox=findViewById(R.id.remember);
        loginBtn=findViewById(R.id.login);
        progressBar=findViewById(R.id.progressBar);
        registertxt=findViewById(R.id.textViewRegister);
    }

    public void Token()
    {
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG , "getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        token = task.getResult().getToken();
                        PreferenceManager manager = PreferenceManager.getInstance();
                        manager.setString("FCM_token",token);
                        Log.d("Token", token);
                    }
                });
    }

    @Override
    public void getLoginInformation(Authenticate response, String errorMessage) {
        if(errorMessage.equals("")){

            Authenticate authenticate =response;
            saveResponse(authenticate);
            Log.d("Type",response.getType());
            Log.d("UserID", String.valueOf(response.getId()));
            Log.d("Token",response.getToken());
            if(remember){

                PreferenceManager.getInstance().setString(PreferenceManager.USER_NAME, userLogin.getUsername());

                PreferenceManager.getInstance().setString(PreferenceManager.PASSWORD, userLogin.getPassword());
            }

            startActivity(new Intent(LoginActivity.this,MainActivity.class));
        }else {

            Toast.makeText(LoginActivity.this,errorMessage,Toast.LENGTH_SHORT).show();
        }

        progressBar.setVisibility(View.GONE);

    }

    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Exit")
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        android.os.Process.killProcess(android.os.Process.myPid());
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
}
