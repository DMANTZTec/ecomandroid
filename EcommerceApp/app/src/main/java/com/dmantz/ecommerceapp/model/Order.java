package com.dmantz.ecommerceapp.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private static final String TAG = Order.class.getSimpleName();

    String customerId;
    String OrderId;
    List<OrderItem> orderItemObj = new ArrayList<>();


    public Order() {

    }


    public void addItem(OrderItem orderItem) {

        if (orderItemObj.isEmpty()) {
            orderItemObj.add(orderItem);
            Log.d(TAG, "Inside addItem me thod: orderItem array list " + orderItemObj);

        } else {
            if (orderItemObj.stream().filter(productFilter -> orderItem.getProductSku().equals(productFilter.getProductSku())).count() > 0) {

                Log.d(TAG, "Product is already in Cart");
            } else {
                orderItemObj.add(orderItem);
                Log.d(TAG, "addItem:  " + orderItemObj);
            }
        }


    }


    public String getcustomerId() {
        return customerId;
    }

    public void setcustomerId(String customerId) {
        customerId = customerId;
    }

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }

    public List<OrderItem> getOrderItemObj() {
        return orderItemObj;
    }

    public void setOrderItemObj(ArrayList<OrderItem> orderItemObj) {
        this.orderItemObj = orderItemObj;
    }
}