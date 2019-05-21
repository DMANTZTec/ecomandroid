package com.android.rvdynamically;

import android.app.Application;
import android.os.Build;
import android.os.StrictMode;

public class UserApp extends Application {

    public UserClient userClient;

    @Override
    public void onCreate() {
        super.onCreate();

        /*if (android.os.Build.VERSION.SDK_INT > 25) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }*/

        if(Build.VERSION.SDK_INT > 9){
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        userClient = UserClient.getUserClient();
    }


}
