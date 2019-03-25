package com.dmantz.ecommerceapp.model;

public class Product {

    public Product(String itemName, String itemSize, double itemPrice, String itemImageUrl, int itemId, String description) {
        this.itemName = itemName;
        this.itemSize = itemSize;
        this.itemPrice = itemPrice;
        this.itemImageUrl = itemImageUrl;
        this.itemId = itemId;
        this.description = description;
    }

    String itemName;
    String itemSize;
    double itemPrice;
    String itemImageUrl;
    int itemId;
    String description;

    public Product() {
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }


    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemSize() {
        return itemSize;
    }

    public void setItemSize(String itemSize) {
        this.itemSize = itemSize;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }


    public String getItemImageUrl() {
        return itemImageUrl;
    }

    public void setItemImage(String itemImageStr) {
        this.itemImageUrl = itemImageStr;
    }


}
