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
import java.util.Optional;

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


            createOrderBE("102", orderItemObj);
            currentOrder = new Order();
            currentOrder.addItem(orderItemObj);
            currentOrder.setOrderId(orderId);

            OrderItem existingItem = currentOrder.getOrderItemList().iterator().next();

            existingItem.setQuantity(orderItemObj.getQuantity());
            Log.d(TAG, "addItem: "+":"+existingItem.getQuantity()+":");
            existingItem.setTotalPrice(existingItem.getQuantity() * orderItemObj.getPrice());
            Log.d(TAG, "addItem: "+":"+existingItem.getPrice()+":");


            currentOrder.calculateTotals();
            currentOrder.totalQuantity();
            currentOrder.cartTotal();


        } else {
            //check if orderItem already exists in currentOrder
            //then call updateItemInOrder(Integer newQuantity,String OrderId)
            //else do the below steps


            if (currentOrder.doesItemExists(orderItemObj)) {

                Optional<OrderItem> existingItemOpt = currentOrder.getOrderItemList().stream().filter(productFilter -> orderItemObj.getProductSku().equals(productFilter.getProductSku())).findFirst();
                //OrderItem existingItem = currentOrder.getOrderItemList().iterator().next();
                OrderItem existingItem = existingItemOpt.get();
                //TODO Check for success response before updating quantity on existing order item.
                updateQuantityBE(currentOrder.getOrderId(), existingItem.getProductSku(), existingItem.getQuantity() + orderItemObj.getQuantity());
                existingItem.setQuantity(existingItem.getQuantity() + orderItemObj.getQuantity());
                Log.d(TAG, "addItem: "+":"+existingItem.getQuantity()+":");
                existingItem.setTotalPrice(existingItem.getQuantity() * orderItemObj.getPrice());
                Log.d(TAG, "addItem: "+":"+existingItem.getPrice()+":");
                currentOrder.calculateTotals();
            } else {

                //  TODO need to process status response from Backend
                addItemToOrderBE(orderItemObj, "102");
                orderItemObj.setTotalPrice(orderItemObj.getQuantity()*orderItemObj.getPrice());
                currentOrder.addItem(orderItemObj);
                currentOrder.calculateTotals();
            }
        }
    }

    public void addItemToOrderBE(OrderItem orderItemObj, String customerId) {


        try {
            URL url = new URL(updateUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("content-Type", "application/json");
            Gson gson = new Gson();


            JsonObject updateOrder = new JsonObject();
            updateOrder.addProperty("orderId", getOrderId());


            JsonObject createOrderROJson = new JsonObject();
            updateOrder.add("addItem", createOrderROJson);
            createOrderROJson.add("orderItem", new Gson().toJsonTree(orderItemObj));


            String updateOrderROStr = gson.toJson(updateOrder);
            Log.d(TAG, "createOrderBE: " + updateOrderROStr);

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


            JSONObject storejsonObj = new JSONObject(response.toString());
            Log.d(TAG, "list obj" + storejsonObj);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();

        }

    }


    public void createOrderBE(String customerId, OrderItem orderItemObj) {


        try {
            URL url = new URL(orderUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("content-Type", "application/json");

            List<OrderItem> orderlistArray = new ArrayList<>();
            orderlistArray.add(orderItemObj);

            Gson gson = new Gson();

            JsonObject createOrderROJson = new JsonObject();

            createOrderROJson.addProperty("customerId", customerId);
            createOrderROJson.add("orderItemObj", new Gson().toJsonTree(orderlistArray));

            String createOrderROStr = gson.toJson(createOrderROJson);


            Log.d(TAG, "createOrderBE: " + createOrderROStr);


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
            orderId = storejsonObj.getInt("orderId");


            Log.d(TAG, "list obj" + storejsonObj);


        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();

        }


    }

    //below method is  for updating the quantity
    public void updateQuantityBE(int orderId, String productSku, int quantity)

    {

        try {
            URL url = new URL(updateUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("content-Type", "application/json");

            Gson gson = new Gson();

            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("orderId", orderId);

            JsonObject additemObjJson = new JsonObject();

            additemObjJson.addProperty("productSku", productSku);
            additemObjJson.addProperty("newQuantity", quantity);
            Log.d(TAG, "updateQuantity: " + additemObjJson);

            jsonObject.add("updateQuantity", additemObjJson);

            String updateOrderROStr = gson.toJson(jsonObject);
            Log.d(TAG, "updateQuantityBE  " + updateOrderROStr);


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


            Log.d(TAG, "json Response Obj" + currentOrder.getOrderItemList().iterator().next().getQuantity());


        } catch (IOException e) {
            e.printStackTrace();
        }

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
