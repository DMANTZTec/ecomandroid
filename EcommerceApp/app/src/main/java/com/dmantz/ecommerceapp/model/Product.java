package com.dmantz.ecommerceapp.model;

import java.util.List;

public class Product {

    private String productId;
    private String productManufacturerName;
    private String productName;
    private String brandName;

    private List<ProductSku> productSkus = null;


    public Product() {
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

    public List<ProductSku> getProductSkus() {
        return productSkus;
    }

    public void setProductSkus(List<ProductSku> productSkus) {
        this.productSkus = productSkus;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", productManufacturerName='" + productManufacturerName + '\'' +
                ", productName='" + productName + '\'' +
                ", productSkus=" + productSkus +
                '}';
    }
}
