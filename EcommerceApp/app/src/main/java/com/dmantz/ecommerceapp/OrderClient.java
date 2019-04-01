package com.dmantz.ecommerceapp;

import android.util.Log;

import com.dmantz.ecommerceapp.model.Order;
import com.dmantz.ecommerceapp.model.OrderItem;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

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

public class OrderClient {

    public static final String TAG = OrderClient.class.getSimpleName();

    static OrderClient orderClientObj;
    private Order currentOrder;
    private String orderUrl = "http://192.168.0.174:8080/EcommerceApp/createOrder2";
    private String updateUrl = "http://192.168.0.174:8080/EcommerceApp/updateOrder";

    int orderId;



    public static OrderClient getOrderClient() {

        if (orderClientObj == null) {
            orderClientObj = new OrderClient();
        }
        return orderClientObj;
    }


    public void addItem(OrderItem orderItemObj) {


        if (currentOrder == null) {


            createOrder("102", orderItemObj);
           // Log.d(TAG, "addItem: order id " + orderId);
            // currentOrder.setOrderId(orderId);

            currentOrder = new Order();
            currentOrder.addItem(orderItemObj);
            currentOrder.setOrderId(orderId);
            currentOrder.calculateTotal();
          //  currentOrder.totalQuantity(orderItemObj.getQuantity());


        } else {
            //check if orderItem already exists in currentOrder
            //then call updateItemInOrder(Integer newQuantity,String OrderId)
            //else do the below steps


            addItemToOrder(orderItemObj, "102");
            currentOrder.addItem(orderItemObj);
            currentOrder.calculateTotal();
        }
    }


    public void addItemToOrder(OrderItem orderItemObj, String customerId) {


        try {
            URL url = new URL(updateUrl );
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("content-Type", "application/json");
            // connection.setDoInput(true);
            //connection.setDoOutput(true);



            Gson gson = new Gson();

            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("orderId",currentOrder.getOrderId());

            JsonObject additemObjJson = new JsonObject();


           // JsonObject updateOrderROJson = new JsonObject();
            additemObjJson.addProperty("productSku", orderItemObj.getProductSku());
            additemObjJson.addProperty("quantity", currentOrder.getOrderItemObj().iterator().next().getQuantity());
            Log.d(TAG, "updateQuantity: " + additemObjJson);

            jsonObject.add("addItem", additemObjJson);

            String updateOrderROStr = gson.toJson(jsonObject);
            Log.d(TAG, "updateQuantity  " + updateOrderROStr);
            //Log.d("json convert order item", "productDisplayList: converted jso


            DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
            dataOutputStream.write(updateOrderROStr.getBytes());
            //dataOutputStream.write(orderId.getBytes());
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
            //JSONObject jsonObj = storejsonObj.getJSONObject("orderItem");

            //Log.d("jsonarray", "products" + jsonObj);


        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();

        }

    }


    public void createOrder(String customerId, OrderItem orderItemObj) {


        try {
            URL url = new URL(orderUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("content-Type", "application/json");
            // connection.setDoInput(true);
            //connection.setDoOutput(true);

            List<OrderItem> orderlistArray = new ArrayList<>();
            orderlistArray.add(orderItemObj);


            Gson gson = new Gson();


            JsonObject createOrderROJson = new JsonObject();

            createOrderROJson.addProperty("customerId", customerId);
            createOrderROJson.add("orderItemObj", new Gson().toJsonTree(orderlistArray));



            String createOrderROStr = gson.toJson(createOrderROJson);


            Log.d(TAG, "createOrder: " + createOrderROStr);
            //Log.d("json convert order item", "productDisplayList: converted json" + createOrderROStr);


            //updateFilter(catalogFilterObj.getFilterEnabaled(),filterNewObj.getFilterType(),filterNewObj.getFilterData());


            DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
            dataOutputStream.write(createOrderROStr.getBytes());
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
            orderId = storejsonObj.getInt("orderId");


               //currentOrder.setOrderId(orderId);

            Log.d(TAG, "list obj" + storejsonObj);
            //Log.d(TAG, "orderidfromJson: " + currentOrder.getOrderId());
            //JSONObject jsonObj = storejsonObj.getJSONObject("orderItem");

            //Log.d("jsonarray", "products" + jsonObj);


        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();

        }

        // connection.setDoInput(true);
        //connection.setDoOutput(true);

    }

    //below method is  for updating the quantity
    public void updateQuantity(int orderId, String productSku, String quantity)

    {


        try {
            URL url = new URL(updateUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("content-Type", "application/json");
            // connection.setDoInput(true);
            //connection.setDoOutput(true);

          /*  List<OrderItem> orderlistArray = new ArrayList<>();
            orderlistArray.iterator().next().setQuantity(quantity);
            orderlistArray.add(orderItem);
*/


            Gson gson = new Gson();

            JsonObject jsonObject = new JsonObject();

            JsonObject updateOrderROJson = new JsonObject();

            updateOrderROJson.addProperty("orderId", orderId);
            updateOrderROJson.addProperty("newQuantity", quantity);
            updateOrderROJson.addProperty("productSku", productSku);
            Log.d(TAG, "updateQuantity: " + updateOrderROJson);

            jsonObject.add("updateQuantity", updateOrderROJson);

            String updateOrderROStr = gson.toJson(jsonObject);
            Log.d(TAG, "updateQuantity  " + updateOrderROStr);


            DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
            dataOutputStream.write(updateOrderROStr.getBytes());
            dataOutputStream.flush();

            BufferedReader bufferedresponse = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = bufferedresponse.readLine()) != null) {
                response.append(line);
                response.append("/r");
            }
            bufferedresponse.close();


            currentOrder.getOrderItemObj().iterator().next().setQuantity(quantity);

            Log.d(TAG, "json Response Obj" + currentOrder.getOrderItemObj().iterator().next().getQuantity());


        } catch (IOException e) {
            e.printStackTrace();
        }
        /*catch (JSONException e) {
            e.printStackTrace();

        }
*/

    }

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(Order currentOrder) {
        this.currentOrder = currentOrder;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
