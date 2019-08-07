package com.dmantz.ecommerceapp.model;

import java.util.List;

public class ProductSku {

    private String productSkuId;
    private List<Option> options = null;
    private String imageUrl;
    private Double price;
    private String productSkuCd;



    public ProductSku() {
    }


    public String getProductSkuId() {
        return productSkuId;
    }

    public void setProductSkuId(String productSkuId) {
        this.productSkuId = productSkuId;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getProductSkuCd() {
        return productSkuCd;
    }

    public void setProductSkuCd(String productSkuCd) {
        this.productSkuCd = productSkuCd;
    }
}
