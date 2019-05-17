package com.dmantz.ecommerceapp.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private static final String TAG = Order.class.getSimpleName();

    String customerId;
    int OrderId;

    double totalAmt;
    int totalQty;


    List<OrderItem> orderItemList = new ArrayList<>();


    public Order() {

    }


    public boolean doesItemExists(OrderItem orderItem) {

        if (orderItemList.stream().filter(productFilter -> orderItem.getProductSku().equals(productFilter.getProductSku())).count() > 0) {

            return true;
        } else return false;
    }

    public void addItem(OrderItem orderItem) {

        if (orderItemList.isEmpty()) {
            orderItemList.add(orderItem);
            Log.d(TAG, "Inside addItem method: orderItem array list " + orderItemList);
            calculateTotals();
            totalQuantity();
            cartTotal();

        } else {
            orderItemList.add(orderItem);
            calculateTotals();
            totalQuantity();
            cartTotal();
            Log.d(TAG, "addItem:  " + orderItemList);
        }

    }

    public void calculateTotals() {

        double price;
        int quantity;
        totalAmt = 0.0;
        totalQty = 0;
        for (OrderItem orderItem : orderItemList) {

            totalAmt = totalAmt + orderItem.getPrice();
            totalQty = totalQty + orderItem.getQuantity();
            Log.d(TAG, "order total amt: " + totalAmt + ";" + "order total qty " + totalQty);
            Log.d(TAG, "calculateTotals: " + orderItem.getPrice() * orderItem.getQuantity());


        }
    }

    public int totalQuantity() {
        int totalQuantity = 0;
        for (OrderItem orderItem : orderItemList) {

            int qty = orderItem.getQuantity();
            totalQuantity = totalQuantity + qty;
            orderItem.setTotalQuantity(totalQuantity);
            Log.d(TAG, "totalQuantity: " + totalQuantity);
        }

        return totalQuantity;
    }

    public int cartTotal() {

        int cartTotal = 0;
        for (OrderItem orderItem : orderItemList) {

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

    public List<OrderItem> getOrderItemList() {

        return orderItemList;
    }

    public void setOrderItemList(ArrayList<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }


    public double getTotalAmt() {
        return totalAmt;
    }

    public void setTotalAmt(double totalAmt) {
        this.totalAmt = totalAmt;
    }

    public int getTotalQty() {
        return totalQty;
    }

    public void setTotalQty(int totalQty) {
        this.totalQty = totalQty;
    }

}