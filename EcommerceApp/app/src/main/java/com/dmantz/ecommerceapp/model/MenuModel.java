package com.dmantz.ecommerceapp.model;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


public class MenuModel {

    public Context context;

    public org.json.JSONArray categorieArrayObj;
    public org.json.JSONArray brandArrayObj;

    org.json.JSONObject storejsonObj;

    JSONArray filterObj = new JSONArray();

    public JSONObject menuDetails(Context context) {

        String jsonData = null;


        try {


            Log.d("MenuModel class", "ProductInfoJson: entered int to try block");
            InputStream is = context.getAssets().open("navigationInformation.json");
            Log.d("MenuModel class", "ProductInfoJson: entered int to try block" + is);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            jsonData = new String(buffer, "UTF-8");


            storejsonObj = new org.json.JSONObject(jsonData);


            //   categorieArrayObj = storejsonObj.getJSONArray("categorie");
            // brandArrayObj = storejsonObj.getJSONArray("brand");


            //  categorieArrayObj.put(brandArrayObj);

            // filterObj.put(categorieArrayObj);


        } catch (IOException ex) {
            ex.printStackTrace();

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return storejsonObj;


    }


}
