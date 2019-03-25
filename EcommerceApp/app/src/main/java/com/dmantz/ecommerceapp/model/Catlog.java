package com.dmantz.ecommerceapp.model;

import java.util.List;

public class Catlog {

    private List<ProductInfo> products = null;
    private CatalogFilter filterCriteria;


    public Catlog() {
    }


    public List<ProductInfo> getProducts() {
        return products;
    }

    public void setProducts(List<ProductInfo> products) {
        this.products = products;
    }

    public CatalogFilter getFilterCriteria() {
        return filterCriteria;
    }

    public void setFilterCriteria(CatalogFilter filterCriteria) {
        this.filterCriteria = filterCriteria;
    }
}