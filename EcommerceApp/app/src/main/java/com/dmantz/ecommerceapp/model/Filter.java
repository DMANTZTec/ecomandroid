package com.dmantz.ecommerceapp.model;

import java.util.HashMap;

public class Filter {

    private String filterType;
    private String filterData;

    private HashMap<String, String> filterCriteria = new HashMap<>();


    public HashMap<String, String> getHashmap() {
        return filterCriteria;
    }


    public void setHashmap(HashMap<String, String> hashmap) {
        this.filterCriteria = hashmap;
    }


    public String getFilterType() {
        return filterType;
    }

    public void setFilterType(String filterType) {
        this.filterType = filterType;
    }

    public String getFilterData() {
        return filterData;
    }

    public void setFilterData(String filterData) {
        this.filterData = filterData;
    }


}
