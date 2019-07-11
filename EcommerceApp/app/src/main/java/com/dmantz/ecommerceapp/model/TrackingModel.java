package com.dmantz.ecommerceapp.model;

public class TrackingModel {

    private Integer orderTrackRef;
    private String orderId;
    private String orderTrackUpdateTime;
    private String statusCd;
    private String orderTrackDesc;
    private String estimatedTime;

    public Integer getOrderTrackRef() {
        return orderTrackRef;
    }

    public void setOrderTrackRef(Integer orderTrackRef) {
        this.orderTrackRef = orderTrackRef;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderTrackUpdateTime() {
        return orderTrackUpdateTime;
    }

    public void setOrderTrackUpdateTime(String orderTrackUpdateTime) {
        this.orderTrackUpdateTime = orderTrackUpdateTime;
    }

    public String getStatusCd() {
        return statusCd;
    }

    public void setStatusCd(String statusCd) {
        this.statusCd = statusCd;
    }

    public String getOrderTrackDesc() {
        return orderTrackDesc;
    }

    public void setOrderTrackDesc(String orderTrackDesc) {
        this.orderTrackDesc = orderTrackDesc;
    }

    public String getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(String estimatedTime) {
        this.estimatedTime = estimatedTime;
    }


}
