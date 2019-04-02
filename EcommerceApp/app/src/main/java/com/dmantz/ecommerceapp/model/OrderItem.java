package com.dmantz.ecommerceapp.model;

import java.util.ArrayList;

public class OrderItem {


    private String productSku;
    private String mrpPrice;
    private Double price;
    private String discountApplied;
    private String quantity;
    private String giftWrapped;
    private String productId;
    private String productName;
    private double totalPrice;
    private int totalQuantity;


    public OrderItem() {
    }

    public String getProductSku() {
        return productSku;
    }

    public void setProductSku(String productSku) {
        this.productSku = productSku;
    }

    public String getMrpPrice() {
        return mrpPrice;
    }

    public void setMrpPrice(String mrpPrice) {
        this.mrpPrice = mrpPrice;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDiscountApplied() {
        return discountApplied;
    }

    public void setDiscountApplied(String discountApplied) {
        this.discountApplied = discountApplied;
    }

    public String getQuantity() {

        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getGiftWrapped() {
        return giftWrapped;
    }

    public void setGiftWrapped(String giftWrapped) {
        this.giftWrapped = giftWrapped;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "productSku='" + productSku + '\'' +
                ", mrpPrice='" + mrpPrice + '\'' +
                ", price='" + price + '\'' +
                ", discountApplied='" + discountApplied + '\'' +
                ", quantity='" + quantity + '\'' +
                ", giftWrapped='" + giftWrapped + '\'' +
                ", productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", totalPrice=" + totalPrice +
                '}';
    }
}