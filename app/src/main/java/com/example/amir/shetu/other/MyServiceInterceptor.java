package com.example.amir.shetu.other;

import com.example.amir.shetu.manager.PreferenceManager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class MyServiceInterceptor implements Interceptor {


    private String HEADER_NAME="Authorization";

    private String OBJECT_NAME="Bearer";

//    will not work without space

    private String SPACE="  ";

    @Override public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        Request.Builder requestBuilder = request.newBuilder();

        String token= PreferenceManager.getInstance().getString(PreferenceManager.TOKEN);
            if (token != null) { {

//                will not work without space

                requestBuilder.addHeader(HEADER_NAME, OBJECT_NAME+SPACE+ token);
            }
    }

        return chain.proceed(requestBuilder.build());
}
}
