package com.dmantz.ecommerceapp;

import android.app.Application;
import android.os.StrictMode;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class ECApplication extends Application {


    public LoginClient loginClient2;
    public CatalogClient catalogClient;
    public OrderClient orderClientObj;

    private RequestQueue requestQueue;
    private static ECApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;


        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        loginClient2 = LoginClient.getLoginClient2();
        catalogClient = CatalogClient.getCatalogClient();
        orderClientObj = OrderClient.getOrderClient();


    }

    public static synchronized ECApplication getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(getApplicationContext());

        }
        return requestQueue;
    }
    public void addToRequestQueue(Request request, String tag) {
        request.setTag(tag);
        getRequestQueue().add(request);
    }

    public void cancelAllRequests(String tag) {
        getRequestQueue().cancelAll(tag);
    }
}
