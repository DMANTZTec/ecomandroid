package com.dmantz.ecommerceapp.model;

import java.util.ArrayList;

public class ProductList {
    String itemName;
    String itemSize;
    double itemPrice;


    ArrayList<ProductOld> productsList = new ArrayList<>();

    public ProductList() {

    }

    public ArrayList<ProductOld> getProductsList() {
        return productsList;
    }

    public void setProductsList(ArrayList<ProductOld> productsList) {
        this.productsList = productsList;
    }

    public ProductList(String itemName, String itemSize, double itemPrice) {
        this.itemName = itemName;
        this.itemSize = itemSize;
        this.itemPrice = itemPrice;

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


    @Override
    public String toString() {
        return "ProductList [itemName=" + itemName + ", itemSize=" + itemSize + ", itemPrice=" + itemPrice + "]";
    }


}
