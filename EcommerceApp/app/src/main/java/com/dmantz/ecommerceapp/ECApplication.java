package com.dmantz.ecommerceapp;

import android.app.Application;
import android.os.StrictMode;

public class ECApplication extends Application {


    public LoginClient loginClient2;
    public CatalogClient catalogClient;
    public OrderClient orderClientObj;



    @Override
    public void onCreate() {
        super.onCreate();


        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        loginClient2 = LoginClient.getLoginClient2();
        catalogClient = CatalogClient.getCatalogClient();
        orderClientObj = OrderClient.getOrderClient();

    }
}
