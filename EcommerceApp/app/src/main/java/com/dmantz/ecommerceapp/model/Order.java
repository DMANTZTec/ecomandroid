package com.dmantz.ecommerceapp.model;

import android.util.Log;

import com.dmantz.ecommerceapp.ECApplication;

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
            calculateTotal();
            totalQuantity();
                cartTotal();

        } else {
            if (orderItemObj.stream().filter(productFilter -> orderItem.getProductSku().equals(productFilter.getProductSku())).count() > 0) {

                totalQuantity();
                calculateTotal();
                cartTotal();

                Log.d(TAG, "Product is already in Cart");
            } else {
                orderItemObj.add(orderItem);
                calculateTotal();
                totalQuantity();
                cartTotal();

                Log.d(TAG, "addItem:  " + orderItemObj);
            }
        }


    }

    public int totalQuantity() {
        int totalQuantity = 0;
        for (OrderItem orderItem : orderItemObj) {

            int qty = orderItem.getQuantity();
            totalQuantity = totalQuantity + qty;
            orderItem.setTotalQuantity(totalQuantity);
            Log.d(TAG, "totalQuantity: " + totalQuantity);
        }

        return totalQuantity;
    }

    public void calculateTotal() {

        double price;
        int quantity;

        for (OrderItem orderItem : orderItemObj) {


            price = orderItem.getPrice();
            quantity = (orderItem.getQuantity());
            orderItem.setTotalPrice(price * quantity);

            Log.d(TAG, "calculateTotal: " + orderItem.getTotalPrice());


        }


    }


    public int cartTotal() {

        int cartTotal = 0;
        for (OrderItem orderItem : orderItemObj) {

            int cartTot = (int) orderItem.getTotalPrice();
            cartTotal = cartTotal + cartTot;
            orderItem.setCartTotalPrice(cartTotal);
        }
        return cartTotal;
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