package com.dmantz.ecommerceapp.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private static final String TAG = Order.class.getSimpleName();

    String customerId;
    int OrderId;


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

        double price;
        int quantity;


        for (OrderItem orderItem : orderItemObj) {


            price = orderItem.getPrice();
            quantity = Integer.parseInt(orderItem.getQuantity());
            orderItem.setTotalPrice(price * quantity);



            Log.d(TAG, "calculateTotal: " + orderItem.toString());

        }
        Log.d(TAG, "calculateTotal: " + orderItemObj.toString());


    }

    public int totalQuantity(){
        int totalQuantity = 0;
        for (OrderItem orderItem : orderItemObj){

            int qty = Integer.parseInt(orderItem.getQuantity());
            totalQuantity = totalQuantity+qty;
            orderItem.setTotalQuantity(totalQuantity);
            Log.d(TAG, "totalQuantity: "+totalQuantity);
        }

        return totalQuantity;
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


}