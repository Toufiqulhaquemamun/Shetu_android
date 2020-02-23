package com.example.amir.shetu.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amir.shetu.R;
import com.example.amir.shetu.manager.StaticDataManager;
import com.example.amir.shetu.model.Registration;
import com.example.amir.shetu.retorfit.API;
import com.example.amir.shetu.retorfit.RetrofitInstance;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private TextView logintxt;

    private API api= RetrofitInstance.getApi();

    private EditText userName,userPhone,userEmail,passWord,confirmPassword;

    String name,email,phone,password,retypepass,emailPattern,passwordPattern;

    private Button registerbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
        initializeView();

        logintxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RegisterActivity.this,LoginActivity.class);

                startActivity(i);
            }
        });

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name=userName.getText().toString().trim();
                email=userEmail.getText().toString().trim();
                phone=userPhone.getText().toString().trim();
                password=passWord.getText().toString().trim();
                retypepass=confirmPassword.getText().toString().trim();
                if(!name.isEmpty()&&isValidEmail(email))
                {
                    if((phone.length()==11))
                    {
                        if(password.equals(retypepass))
                        {
                            if(isValidPassword(passWord.getText().toString().trim()))
                            {
                                Log.d("Detail",name+email+phone+password+retypepass);
                                Registration reg = new Registration(name,phone,email,password,retypepass);
                                api.guestRegister(reg).enqueue(new Callback<Boolean>() {
                                    @Override
                                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                                        if(response.code()==StaticDataManager.OK)
                                        {
                                            Toast.makeText(RegisterActivity.this,"Successfully Registered",Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(RegisterActivity.this,MainActivity.class));
                                        }
                                        else{
                                            Log.d("ResponseCode", String.valueOf(response.code()));
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Boolean> call, Throwable t) {
                                        Log.d("OnFailure", t.getMessage());
                                    }
                                });
                            }
                            else{
                                Toast.makeText(RegisterActivity.this,"Invalid password format. Password should contain an upper case, a lower case, a special character and at least eight character",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(RegisterActivity.this,"PassWord not matched",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(RegisterActivity.this,"Must be 11 digit",Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(RegisterActivity.this,"Email Not Valid",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    public boolean isValidPassword(String password) {
        Pattern pattern;
        Matcher matcher;
        String PASSWORD_PATTERN = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();

    }
    private void initializeView(){
        logintxt=findViewById(R.id.textViewLogin);
        registerbtn=findViewById(R.id.register);
        userName=findViewById(R.id.user_name);
        userPhone=findViewById(R.id.phone_no);
        userEmail=findViewById(R.id.user_email);
        passWord=findViewById(R.id.password);
        confirmPassword=findViewById(R.id.confirm_password);
        registerbtn=findViewById(R.id.register);
    }


}
