package com.dmantz.ecommerceapp.model;

public class TrackingModel {

    private Integer orderTrackRef;
    private String orderId;
    private String orderTrackUpdateTime;
    private String statusCd;
    private String estimatedTime;
    private Integer statusCdId;
    private Integer orderId2;
    private String orderTracksDesc;

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

    public String getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(String estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public Integer getStatusCdId() {
        return statusCdId;
    }

    public void setStatusCdId(Integer statusCdId) {
        this.statusCdId = statusCdId;
    }

    public Integer getOrderId2() {
        return orderId2;
    }

    public void setOrderId2(Integer orderId2) {
        this.orderId2 = orderId2;
    }

    public String getOrderTracksDesc() {
        return orderTracksDesc;
    }

    public void setOrderTracksDesc(String orderTracksDesc) {
        this.orderTracksDesc = orderTracksDesc;
    }

}
