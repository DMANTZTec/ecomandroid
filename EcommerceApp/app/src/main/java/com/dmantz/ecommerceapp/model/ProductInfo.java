package com.dmantz.ecommerceapp.model;

import java.util.List;

public class ProductInfo {

    private String productId;
    private String productManufacturerName;
    private String productName;

    private List<ProductSkus> productSkus = null;


    public ProductInfo() {
    }


    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductManufacturerName() {
        return productManufacturerName;
    }

    public void setProductManufacturerName(String productManufacturerName) {
        this.productManufacturerName = productManufacturerName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public List<ProductSkus> getProductSkus() {
        return productSkus;
    }

    public void setProductSkus(List<ProductSkus> productSkus) {
        this.productSkus = productSkus;
    }

}
