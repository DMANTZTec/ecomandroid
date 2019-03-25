package com.dmantz.ecommerceapp;


import android.content.Context;
import android.util.Log;

import com.dmantz.ecommerceapp.model.CatalogFilter;
import com.dmantz.ecommerceapp.model.Catlog;
import com.dmantz.ecommerceapp.model.Product;
import com.dmantz.ecommerceapp.model.ProductInfo;
import com.dmantz.ecommerceapp.model.ProductList;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;


public class CatalogClient extends ProductList {

    public static final String TAG = CatalogClient.class.getSimpleName();

    static CatalogClient catalogclientObj;
    private Catlog catlog;
    private ProductList productList;
    private CatalogFilter catalogFilterObj;

    Context mContext;
    String json = null;
    String catalogURL = "http://192.168.0.123:8080/UserApp/catalog";


    public CatalogClient() {


        if (catalogFilterObj == null) {
            catalogFilterObj = new CatalogFilter();
            catalogFilterObj.setFilterEnabaled("False");


        }


    }


    public static CatalogClient getCatalogClient() {


        if (catalogclientObj == null) {
            catalogclientObj = new CatalogClient();
        }
        return catalogclientObj;
    }

    // This method is used to get catlog form backend
    private Context context;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    private void getCatalogFromBE() {

        ProductInfoJson();
        Gson gson = new Gson();
        catlog = gson.fromJson(jsonData, Catlog.class);
        Log.d(TAG, "getCatalogFromBE: " + catlog);


    }


    public ProductInfo getProduct(String productId) {

        List<ProductInfo> filteredProducts = catlog.getProducts().stream().filter(product -> product.getProductId().equals(productId)).collect(Collectors.toList());
        return filteredProducts.get(0);

        // catlog.getProducts().get( i).getProductSkus().g+et(i).getOptions().get(i).getOptionName();
        //Log.d("Catlog Client", "getCatlog: "+catlog.getProducts().get(0));
    }


    public ProductList productDisplayList() throws Exception {

        URL url = new URL(catalogURL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("content-Type", "application/json");
        // connection.setDoInput(true);
        //connection.setDoOutput(true);


        Gson gson = new Gson();
        String catalogFilterJson = gson.toJson(catalogFilterObj);


        Log.d(TAG, "productDisplayList: converted json" + catalogFilterJson);


        //updateFilter(catalogFilterObj.getFilterEnabaled(),filterNewObj.getFilterType(),filterNewObj.getFilterData());


        DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
        dataOutputStream.write(catalogFilterJson.getBytes());
        dataOutputStream.flush();

        BufferedReader bufferedresponse = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        StringBuffer response = new StringBuffer();
        while ((line = bufferedresponse.readLine()) != null) {
            response.append(line);
            response.append("/r");
        }
        bufferedresponse.close();


        JSONObject storejsonObj = new JSONObject(response.toString());
        //List listObj = new ArrayList();

        Log.d(TAG, "list obj" + storejsonObj);
        JSONArray jsonarrayObj = storejsonObj.getJSONArray("Catogery");

        Log.d(TAG, "products" + jsonarrayObj);

        productList = new ProductList();
        ArrayList<Product> products = new ArrayList<>();

        //ProductList productlistObj = new ProductList();
        for (int i = 0; i < jsonarrayObj.length(); i++) {

            //System.out.println("itemName: "+jsonarrayObj.getJSONObject(i).get("item_name"));
            Product currentProduct = new Product();

            currentProduct.setItemName((String) jsonarrayObj.getJSONObject(i).get("item_name"));
            currentProduct.setItemSize((String) jsonarrayObj.getJSONObject(i).get("size"));
            currentProduct.setItemPrice((double) jsonarrayObj.getJSONObject(i).get("item_price"));
            currentProduct.setItemId((int) jsonarrayObj.getJSONObject(i).get("id"));
            currentProduct.setDescription((String) jsonarrayObj.getJSONObject(i).get("description"));
            currentProduct.setItemImage((String) jsonarrayObj.getJSONObject(i).get("url"));


            Log.d(TAG, "item-name" + currentProduct.getItemName());
            Log.d(TAG, "productDisplayList: " + currentProduct.getItemId());
            Log.d(TAG, "productDisplayList: " + currentProduct.getItemImageUrl());
            products.add(currentProduct);

            // Log.d("full ","fjdhkgr"+listObj.get);
        }


        productList.setProductList(products);

        //ProductList productObj = (ProductList) listObj.get(1);
        // Log.d("Result", "size of " + productObj.getItemName());
        return productList;

      /*  for(Object Obj:listObj) {
            ProductList productObj=(ProductList)Obj;
           // System.out.println("itemName is: "+productObj.getItemName() + " itemSize is: "+productObj.getItemSize() + " itemPrice is: "+productObj.getItemPrice());
          Log.d("full","name"+.getItemName());



    }
     */
    }


    public void updateFilter(String filterOperation, String filterType, String filterData) {

        if (filterOperation.equals("add")) {
            catalogFilterObj.setFilterEnabaled("True");
            catalogFilterObj.add(filterType, filterData);


        } else
            catalogFilterObj.delete(filterType, filterData);


    }


    public Catlog getCatlog(Boolean refresh) {

/*
    if callog is null or if refresh is true then get catalog data from backend
    else just return catalog.
 */

        if (catlog == null || refresh == true) {
            getCatalogFromBE();
        }

        return catlog;


    }

    public void setCatlog(Catlog catlog) {
        this.catlog = catlog;
    }


    JSONObject storejsonObj;
    String jsonData = null;

    public JSONObject ProductInfoJson() {


        try {


            Log.d(TAG, "ProductInfoJson: entered int to try block");

            InputStream is = context.getAssets().open("ProductInfo.json");
            Log.d(TAG, "ProductInfoJson: entered int to try block" + is);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            jsonData = new String(buffer, "UTF-8");


            storejsonObj = new org.json.JSONObject(jsonData);


        } catch (IOException ex) {
            ex.printStackTrace();

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return storejsonObj;


    }


}

