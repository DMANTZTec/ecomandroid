package com.dmantz.ecommerceapp.model;

public class ItemOptions {

    String itemOptionId;
    String itemOptionName;
    String itemOptionValue;


    public  ItemOptions(){

    }

    public ItemOptions(String itemOptionId, String itemOptionName, String itemOptionValue) {
        this.itemOptionId = itemOptionId;
        this.itemOptionName = itemOptionName;
        this.itemOptionValue = itemOptionValue;
    }


    public String getItemOptionId() {
        return itemOptionId;
    }

    public void setItemOptionId(String itemOptionId) {
        this.itemOptionId = itemOptionId;
    }

    public String getItemOptionName() {
        return itemOptionName;
    }

    public void setItemOptionName(String itemOptionName) {
        this.itemOptionName = itemOptionName;
    }

    public String getItemOptionValue() {
        return itemOptionValue;
    }

    public void setItemOptionValue(String itemOptionValue) {
        this.itemOptionValue = itemOptionValue;
    }
}
