package com.dmantz.ecommerceapp.model;

import java.util.List;

public class ProductSkus {

    private String sku;
    private List<Option> options = null;
    private String image;
    private Double price;



    public ProductSkus() {
    }


    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

}
