package com.dmantz.ecommerceapp.model;

import java.util.List;

public class Catlog {

    private List<Product> products =null;
   // private CatalogFilter filterCriteria;


    public Catlog() {
    }


    public List<Product> getProducts() {

        return products;

    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

   /* public CatalogFilter getFilterCriteria() {
        return filterCriteria;
    }

    public void setFilterCriteria(CatalogFilter filterCriteria) {
        this.filterCriteria = filterCriteria;
    }
    */

    @Override
    public String toString() {
        return "Catlog{" +
                "products=" + products +
                '}';
    }
}