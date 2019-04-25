package com.dmantz.ecommerceapp.model;

import java.util.ArrayList;
import java.util.List;

public class CategoriesParent {


    private String catalogName;
    private int catalogId;
    private  String catalogDesc;
    private int catalogLevel;
    private int parentCatalogId;
    List<CategoriesChild> childCatalog ;
    private  int storeId;


    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogDesc() {
        return catalogDesc;
    }

    public void setCatalogDesc(String catalogDesc) {
        this.catalogDesc = catalogDesc;
    }

    public int getCatalogLevel() {
        return catalogLevel;
    }

    public void setCatalogLevel(int catalogLevel) {
        this.catalogLevel = catalogLevel;
    }

    public int getParentCatalogId() {
        return parentCatalogId;
    }

    public void setParentCatalogId(int parentCatalogId) {
        this.parentCatalogId = parentCatalogId;
    }

    public List<CategoriesChild> getChildCatalog() {
        return childCatalog;
    }

    public void setChildCatalog(List<CategoriesChild> childCatalog) {
        this.childCatalog = childCatalog;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }


    @Override
    public String toString() {
        return "CategoriesParent{" +
                "catalogName='" + catalogName + '\'' +
                ", catalogId=" + catalogId +
                ", catalogDesc='" + catalogDesc + '\'' +
                ", catalogLevel=" + catalogLevel +
                ", parentCatalogId=" + parentCatalogId +
                ", childCatalog=" + childCatalog +
                ", storeId=" + storeId +
                '}';
    }
}
