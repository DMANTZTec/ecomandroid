package com.dmantz.ecommerceapp.model;

public class CouponRes {


    private String couponStatus;
    private String reason;
    private Double discountToApply;
    private Double totalAmount;
    private Double finalAmount;

    public String getCouponStatus() {
        return couponStatus;
    }

    public void setCouponStatus(String couponStatus) {
        this.couponStatus = couponStatus;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Double getDiscountToApply() {
        return discountToApply;
    }

    public void setDiscountToApply(Double discountToApply) {
        this.discountToApply = discountToApply;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getFinalAmount() {
        return finalAmount;
    }

    public void setFinalAmount(Double finalAmount) {
        this.finalAmount = finalAmount;
    }


    @Override
    public String toString() {
        return "CouponRes{" +
                "couponStatus='" + couponStatus + '\'' +
                ", reason='" + reason + '\'' +
                ", discountToApply=" + discountToApply +
                ", totalAmount=" + totalAmount +
                ", finalAmount=" + finalAmount +
                '}';
    }
}


