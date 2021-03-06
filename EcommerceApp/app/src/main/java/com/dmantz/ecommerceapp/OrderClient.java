package com.dmantz.ecommerceapp;

import android.util.Log;

import com.dmantz.ecommerceapp.model.CouponInfo;
import com.dmantz.ecommerceapp.model.CouponRes;
import com.dmantz.ecommerceapp.model.Order;
import com.dmantz.ecommerceapp.model.OrderItem;
import com.dmantz.ecommerceapp.model.Shipping;
import com.dmantz.ecommerceapp.model.TrackingModel;
import com.dmantz.ecommerceapp.model.YourOrderModel;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderClient {

    public static final String TAG = OrderClient.class.getSimpleName();
    static OrderClient orderClientObj;
    CouponInfo couponInfo;
    TrackingModel trackingModel;
    int orderId;
    ArrayList<Shipping> addressList = new ArrayList<>();
    ArrayList<YourOrderModel> yourOrderList = new ArrayList<YourOrderModel>();
    CouponRes couponRes = new CouponRes();
    private Order currentOrder;


    private String orderUrl = "http://192.168.100.3:8080/EcommerceApp/createOrder2";
    private String updateUrl = "http://192.168.100.3:8080/EcommerceApp/updateOrder";
    private String shippingUrl = "http://192.168.100.3:8080/EcommerceApp/addOrUpdateShippingAddress";
    private String addressListUrl = "http://192.168.100.3:8080/EcommerceApp/viewShippingAddresses?customerId=";
    private String couponUrl = "http://192.168.100.3:8080/EcommerceApp/applyCouponCode";
    private String paymentUrl = "http://192.168.100.3:8080/EcommerceApp/getPayment?paymentId=";
    private String OrderTrackingUrl = "http://192.168.100.3:8080/EcommerceApp/getOrderStatus?orderId=";
    private String yourOrdersUrl = "http://192.168.100.3:8080/EcommerceApp/getCustomerOrders?customerId=";

    public static OrderClient getOrderClient() {
        if (orderClientObj == null) {
            orderClientObj = new OrderClient();
        }
        return orderClientObj;
    }

    public void applyCoupon(String coupon) {

        if (coupon != null) {
            couponInfo = new CouponInfo();
            couponInfo.setCouponCode(coupon);
            applyCouponCode(coupon);
            currentOrder.finalAmt(couponRes);
            currentOrder.disountedAmt(couponRes);
            currentOrder.CouponInfo(getCouponInfo());
        }

    }

    //Apply coupon service call
    public void applyCouponCode(String coupon) {

        JSONObject couponJson = new JSONObject();
        try {
            couponJson.put("couponCode", coupon);
            couponJson.put("orderId", getOrderId());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {

            URL url = new URL(couponUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("content-Type", "application/json");

            DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
            dataOutputStream.write(couponJson.toString().getBytes());
            dataOutputStream.flush();
            BufferedReader bufferedresponse = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = bufferedresponse.readLine()) != null) {
                response.append(line);
                response.append("/r");
            }
            bufferedresponse.close();

            Gson gson = new Gson();
            JsonReader reader = new JsonReader(new StringReader(response.toString()));
            reader.setLenient(true);
            couponRes = gson.fromJson(reader, CouponRes.class);
            Log.d(TAG, "applyCouponCode: " + couponRes.getCouponStatus());
            getCurrentOrder().finalAmt(couponRes);


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public CouponInfo getCouponInfo() {
        return couponInfo;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
      /*  JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, couponUrl, couponJson, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, "onResponse: " + response.toString());
                Gson gson = new Gson();

                couponRes = gson.fromJson(String.valueOf(response), CouponRes.class);



                String s = getCouponRes().getCouponStatus();
                getCurrentOrder().setCouponStatus(s);

                    for(OrderItem orderItem : getCurrentOrder().getOrderItemList()){

                        orderItem.setCartTotalPrice((int) Double.parseDouble(String.valueOf((getCouponRes().getFinalAmount()))));
                        orderItem.getTotalPrice();
                    }


                //   Log.d(TAG, "onResponse: " + getCouponRes().getFinalAmount());


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d(TAG, "onErrorResponse: "+error.toString());
            }
        });

        ECApplication.getInstance().addToRequestQueue(jsonObjectRequest, "getRequest");
*/

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(Order currentOrder) {
        this.currentOrder = currentOrder;
    }

    public void setCouponInfo(CouponInfo couponInfo) {
        this.couponInfo = couponInfo;
    }

    // add item to cart api call
    public void addItem(OrderItem orderItemObj) {

        if (currentOrder == null) {

            createOrderBE("102", orderItemObj);
            currentOrder = new Order();
            currentOrder.addItem(orderItemObj);
            currentOrder.setOrderId(orderId);

            OrderItem existingItem = currentOrder.getOrderItemList().iterator().next();

            existingItem.setQuantity(orderItemObj.getQuantity());
            Log.d(TAG, "addItem: " + ":" + existingItem.getQuantity() + ":");
            existingItem.setTotalPrice(existingItem.getQuantity() * orderItemObj.getPrice());
            Log.d(TAG, "addItem: " + ":" + existingItem.getPrice() + ":");

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
                Log.d(TAG, "addItem: " + ":" + existingItem.getQuantity() + ":");
                existingItem.setTotalPrice(existingItem.getQuantity() * orderItemObj.getPrice());
                Log.d(TAG, "addItem: " + ":" + existingItem.getPrice() + ":");
                currentOrder.calculateTotals();
            } else {

                //  TODO need to process status response from Backend
                addItemToOrderBE(orderItemObj, "102");
                orderItemObj.setTotalPrice(orderItemObj.getQuantity() * orderItemObj.getPrice());
                currentOrder.addItem(orderItemObj);
                currentOrder.calculateTotals();
            }
        }
    }

    // Create order api call
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
    public void updateQuantityBE(int orderId, String productSku, int quantity) {

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

    //Adding new item to the existing order api call
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

    //method to add shipping address to BE
    public void addAddress(Shipping shippingAddress) {

        try {

            URL url = new URL(shippingUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("content-Type", "application/json");
            Log.d(TAG, "addAddress: " + shippingAddress);
            Gson gson = new Gson();
            String newShippingAddressStr = gson.toJson(shippingAddress);
            Log.d(TAG, "createOrderBE: " + newShippingAddressStr);
            DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
            dataOutputStream.write(newShippingAddressStr.getBytes());
            dataOutputStream.flush();
            BufferedReader bufferedresponse = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = bufferedresponse.readLine()) != null) {
                response.append(line);
                response.append("/r");
            }
            bufferedresponse.close();
            Log.d(TAG, "addAddress: " + response);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //List of address api call
    public ArrayList<Shipping> addressList() {


        try {

            URL url = new URL(addressListUrl + "102");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("content-Type", "application/json");


            BufferedReader bufferedresponse = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = bufferedresponse.readLine()) != null) {
                response.append(line);
                response.append("/r");
            }
            bufferedresponse.close();


            JSONArray jsonArray = new JSONArray(response.toString());
            Gson gson = new Gson();

            Type collectionType = new TypeToken<ArrayList<Shipping>>() {
            }.getType();
            addressList = gson.fromJson(String.valueOf(jsonArray), collectionType);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return addressList;

    }

    //payment api call of razor pay
    public void payment(String paymentkey) {

        try {

            URL url = new URL(paymentUrl + paymentkey);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("content-Type", "application/json");


            BufferedReader bufferedresponse = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = bufferedresponse.readLine()) != null) {
                response.append(line);
                response.append("/r");
            }
            bufferedresponse.close();
            Log.d(TAG, "payment: " + response);
            String jsonstring = response.toString();


           /* paymentRes = new JSONObject();
            try {
                paymentRes.put("paymentRes ", response);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Gson gson = new Gson();
            paymentResponse = gson.fromJson(jsonstring, PaymentResponse.class);
            Log.d(TAG, "payment: " + paymentResponse.getStatus());
*/

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Ordertracking api call
    public void orderTracking(int orderId) {

        try {

            Log.d(TAG, "Entered into order tracking method from OrderClient");
            URL url = new URL(OrderTrackingUrl +orderId);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("content-Type", "application/json");


            BufferedReader bufferedresponse = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = bufferedresponse.readLine()) != null) {
                response.append(line);
                response.append("/r");
            }
            bufferedresponse.close();
            Log.d(TAG, "payment: " + response);
            String jsonstring = response.toString();


            Gson gson = new Gson();
            JsonReader reader = new JsonReader(new StringReader(jsonstring));
            reader.setLenient(true);

            trackingModel = gson.fromJson(reader, TrackingModel.class);
            Log.d(TAG, "tracking model " + trackingModel.getStatusCd());

           setTrackingModel(trackingModel);


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    // list of orders of the customers api call
    public ArrayList<YourOrderModel> yourOrders() {

        try {
            URL url = new URL(yourOrdersUrl + "102");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("content-Type", "application/json");

            BufferedReader bufferedresponse = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = bufferedresponse.readLine()) != null) {
                response.append(line);
                response.append("/r");
            }
            bufferedresponse.close();
            JSONArray jsonArray = new JSONArray(response.toString());
            Gson gson = new Gson();

            Type collectionType = new TypeToken<ArrayList<YourOrderModel>>() {
            }.getType();
            yourOrderList = gson.fromJson(String.valueOf(jsonArray), collectionType);
            Log.d(TAG, "yourOrders: " + response);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return yourOrderList;
    }

    public ArrayList<Shipping> address(Shipping address) {

        addressList.add(address);
        return addressList;
    }

    public ArrayList<Shipping> getAddressList() {
        return addressList;
    }

    public void setAddressList(ArrayList<Shipping> addressList) {
        this.addressList = addressList;
    }

    public CouponRes getCouponRes() {
        return couponRes;
    }

    public void setCouponRes(CouponRes couponRes) {
        this.couponRes = couponRes;
    }

    public TrackingModel getTrackingModel() {
        return trackingModel;
    }

    public void setTrackingModel(TrackingModel trackingModel) {
        this.trackingModel = trackingModel;
    }


    public ArrayList<YourOrderModel> getYourOrderList() {
        return yourOrderList;
    }

    public void setYourOrderList(ArrayList<YourOrderModel> yourOrderList) {
        this.yourOrderList = yourOrderList;
    }
}
