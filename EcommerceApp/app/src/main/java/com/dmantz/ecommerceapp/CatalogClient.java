package com.dmantz.ecommerceapp;


import android.content.Context;
import android.util.Log;

import com.dmantz.ecommerceapp.model.CatalogFilter;
import com.dmantz.ecommerceapp.model.CategoriesParent;
import com.dmantz.ecommerceapp.model.Catlog;
import com.dmantz.ecommerceapp.model.Product;
import com.dmantz.ecommerceapp.model.ProductList;
import com.dmantz.ecommerceapp.model.ProductOld;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class CatalogClient extends ProductList {

    public static final String TAG = CatalogClient.class.getSimpleName();

    static CatalogClient catalogClientObj;
    public List<CategoriesParent> categoriesParentList = new ArrayList<>();
    public Catlog catlog;
    CategoriesParent categories;

    String catalogURL = "http://192.168.100.3:8080/ec/catalog";
    String categoriesUrl = "http://192.168.100.3:8080/catalog_dir";

    private ProductList productList;
    private CatalogFilter catalogFilterObj;
    private Context context;


    public CatalogClient() {


        if (catalogFilterObj == null) {
            catalogFilterObj = new CatalogFilter();
            catalogFilterObj.setFilterEnabaled("False");
            int id = catalogFilterObj.getCatalogId();

        }

    }

    public static CatalogClient getCatalogClient() {


        if (catalogClientObj == null) {
            catalogClientObj = new CatalogClient();
        }
        return catalogClientObj;
    }

    public void check() {
        if (categoriesParentList.isEmpty()) {
            getCatlogDir();
        }

    }

    public void getCatlogDir() {


        try {

            URL url = new URL(categoriesUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("content-Type", "application/json");
            // connection.setDoInput(true);
            //connection.setDoOutput(true);

            Gson gson = new Gson();


            JsonObject categoriesJsonObj = new JsonObject();
            categoriesJsonObj.addProperty("startLevel", 1);
            categoriesJsonObj.addProperty("endLevel", 2);
            categoriesJsonObj.addProperty("storeId", 1);
            categoriesJsonObj.addProperty("productCatlogId", 0);


            String categoriesJson = gson.toJson(categoriesJsonObj);

            Log.d(TAG, "list of CategoriesDir" + categoriesJson);

            DataOutputStream dataOutputStream = null;
            dataOutputStream = new DataOutputStream(connection.getOutputStream());
            dataOutputStream.write(categoriesJson.getBytes());
            dataOutputStream.flush();

            BufferedReader bufferedresponse = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = bufferedresponse.readLine()) != null) {
                response.append(line);
                response.append("/r");
            }
            bufferedresponse.close();


            JSONArray responseJsonObj = new JSONArray(response.toString());
            // JSONArray responseArray = responseJsonObj.getJSONArray("produ cts");

            for (int i = 0; i < responseJsonObj.length(); i++) {
                JSONObject js = responseJsonObj.getJSONObject(i);
                String responseBody = js.toString();
                categories = gson.fromJson(responseBody, CategoriesParent.class);

                categoriesParentList.add(categories);
            }


            Log.d(TAG, "inside loop of response: " + getCategories());


        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public CategoriesParent getCategories() {
        return categories;
    }

    public void setCategories(CategoriesParent categories) {
        this.categories = categories;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Product getProduct(String productId) {

        List<Product> filteredProducts = catlog.getProducts().stream().filter(product -> product.getProductId().equals(productId)).collect(Collectors.toList());
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

        Log.d(TAG, "productOlds" + jsonarrayObj);

        productList = new ProductList();
        ArrayList<ProductOld> productOlds = new ArrayList<>();

        //ProductList productlistObj = new ProductList();
        for (int i = 0; i < jsonarrayObj.length(); i++) {

            //System.out.println("itemName: "+jsonarrayObj.getJSONObject(i).get("item_name"));
            ProductOld currentProductOld = new ProductOld();

            currentProductOld.setItemName((String) jsonarrayObj.getJSONObject(i).get("item_name"));
            currentProductOld.setItemSize((String) jsonarrayObj.getJSONObject(i).get("size"));
            currentProductOld.setItemPrice((double) jsonarrayObj.getJSONObject(i).get("item_price"));
            currentProductOld.setItemId((int) jsonarrayObj.getJSONObject(i).get("id"));
            currentProductOld.setDescription((String) jsonarrayObj.getJSONObject(i).get("description"));
            currentProductOld.setItemImage((String) jsonarrayObj.getJSONObject(i).get("url"));


            Log.d(TAG, "item-name" + currentProductOld.getItemName());
            Log.d(TAG, "productDisplayList: " + currentProductOld.getItemId());
            Log.d(TAG, "productDisplayList: " + currentProductOld.getItemImageUrl());
            productOlds.add(currentProductOld);

            // Log.d("full ","fjdhkgr"+listObj.get);
        }


        productList.setProductsList(productOlds);

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

    public void getCatalogFromBE() {


        try {

            URL url = new URL(catalogURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("content-Type", "application/json");
            // connection.setDoInput(true);
            //connection.setDoOutput(true);


            Gson gson = new Gson();

            JsonObject filterCriteriaJsonObj = new JsonObject();

            JsonObject filterEnabledJsonObj = new JsonObject();
            filterCriteriaJsonObj.add("filterCriteria", filterEnabledJsonObj);

            filterEnabledJsonObj.addProperty("catalogId", catalogFilterObj.getCatalogId());
            filterEnabledJsonObj.addProperty("filterEnabled", catalogFilterObj.getFilterEnabaled());
            String catalogFilterJson = gson.toJson(filterCriteriaJsonObj);


            Log.d(TAG, "productDisplayList: converted json" + filterCriteriaJsonObj);


            //updateFilter(catalogFilterObj.getFilterEnabaled(),filterNewObj.getFilterType(),filterNewObj.getFilterData());

            DataOutputStream dataOutputStream = null;
            dataOutputStream = new DataOutputStream(connection.getOutputStream());
            dataOutputStream.write(catalogFilterJson.getBytes());
            dataOutputStream.flush();

            BufferedReader bufferedresponse = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String res;
            StringBuffer response = new StringBuffer();
            while ((res = bufferedresponse.readLine()) != null) {
                response.append(res);
                response.append("/r");
            }
            bufferedresponse.close();

            JSONObject responseJsonObj = new JSONObject(response.toString());
            // JSONArray responseArray = responseJsonObj.getJSONArray("products");
            String responseBody = responseJsonObj.toString();
            catlog = gson.fromJson(responseBody, Catlog.class);

            // catlog.setProducts(catlog.getProducts());


            for (Product product : catlog.getProducts()) {

                //    catlog.setProducts(Collections.singletonList(product));
                //  catlog.getProducts().size();
                //   catlog.setProducts(Collections.singletonList(product));
                String s1 = String.valueOf(product.getProductSkus().iterator().next().getPrice());
                Log.d(TAG, "getCatalogFromBE: " + s1);
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void setCatlog(Catlog catlog) {
        this.catlog = catlog;
    }

    public List<CategoriesParent> getCategoriesParentList() {
        return categoriesParentList;
    }

    public void setCategoriesParentList(List<CategoriesParent> categoriesParentList) {
        this.categoriesParentList = categoriesParentList;
    }

    /*

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
*/


    public CatalogFilter getCatalogFilterObj() {
        return catalogFilterObj;
    }

    public void setCatalogFilterObj(CatalogFilter catalogFilterObj) {
        this.catalogFilterObj = catalogFilterObj;
    }


}

/*the end*/