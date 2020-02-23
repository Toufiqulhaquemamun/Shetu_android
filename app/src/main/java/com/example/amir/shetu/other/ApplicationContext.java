package com.example.amir.shetu.other;

import android.app.Application;
import android.content.Context;

public class ApplicationContext extends Application {

    private static ApplicationContext context;

    @Override
    public void onCreate() {
        super.onCreate();

        context=this;
    }

    public static synchronized Context getContext(){

        return context;
    }
}
