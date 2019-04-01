package com.dmantz.ecommerceapp.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private static final String TAG = Order.class.getSimpleName();

    String customerId;
    int OrderId;
    double totalPrice;


    List<OrderItem> orderItemObj = new ArrayList<>();


    public Order() {

    }


    public void addItem(OrderItem orderItem) {

        if (orderItemObj.isEmpty()) {
            orderItemObj.add(orderItem);
            Log.d(TAG, "Inside addItem method: orderItem array list " + orderItemObj);

        } else {
            if (orderItemObj.stream().filter(productFilter -> orderItem.getProductSku().equals(productFilter.getProductSku())).count() > 0) {

                Log.d(TAG, "Product is already in Cart");
            } else {
                orderItemObj.add(orderItem);
                Log.d(TAG, "addItem:  " + orderItemObj);
            }
        }


    }

    public void calculateTotal() {

        int price;
        int quantity;



        for (OrderItem orderItem : orderItemObj) {


            price = Integer.parseInt(orderItem.getPrice());
            quantity = Integer.parseInt(orderItem.getQuantity());
            orderItem.setTotalPrice(price *quantity);

            String sku =orderItem.getProductSku();

            totalPrice = orderItem.getTotalPrice();

            Log.d(TAG, "calculateTotal: "+totalPrice);
            Log.d(TAG, "calculateTotal: "+orderItem.toString());

        }
        Log.d(TAG, "calculateTotal: "+orderItemObj.toString());



    }


    public String getcustomerId() {
        return customerId;
    }

    public void setcustomerId(String customerId) {
        customerId = customerId;
    }

    public int getOrderId() {
        return OrderId;
    }

    public void setOrderId(int orderId) {
        OrderId = orderId;
    }

    public List<OrderItem> getOrderItemObj() {
        return orderItemObj;
    }

    public void setOrderItemObj(ArrayList<OrderItem> orderItemObj) {
        this.orderItemObj = orderItemObj;
    }

    public double getTotalPrice() {
        Log.d(TAG, "getTotalPrice: "+totalPrice);
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}