package com.dmantz.ecommerceapp.model;

import java.util.Date;

public class YourOrderModel {

    int orderId;
    String orderStatus;
    Date deliverDate;


    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getDeliverDate() {
        return deliverDate;
    }

    public void setDeliverDate(Date deliverDate) {
        this.deliverDate = deliverDate;
    }

    @Override
    public String toString() {
        return "YourOrderModel{" +
                "orderId=" + orderId +
                ", orderStatus='" + orderStatus + '\'' +
                ", deliverDate='" + deliverDate + '\'' +
                '}';
    }
}
