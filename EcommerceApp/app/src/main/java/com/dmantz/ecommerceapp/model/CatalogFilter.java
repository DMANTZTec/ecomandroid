package com.dmantz.ecommerceapp.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class CatalogFilter {


    public String filterEnabled = "false";
    public HashMap<String, ArrayList> filterCriteria = new HashMap<>();
    public  int catalogId ;

    public CatalogFilter() {

    }

    public String getFilterEnabaled() {
        return filterEnabled;
    }


    public void setFilterEnabaled(String filterEnabaled) {
        this.filterEnabled = filterEnabaled;
    }

    public void add(String filterType, String filterData)


    {

        ArrayList newFilterData = null;

        if (filterCriteria.containsKey(filterType)) {
            ArrayList curFilterData = filterCriteria.get(filterType);
            curFilterData.add(filterData);

        } else {

            newFilterData = new ArrayList();

            newFilterData.add(filterData);
            filterCriteria.put(filterType, newFilterData);


        }

        Log.d("filterCriteria", "add: " + filterData);
        Log.d("filterCriteria", "add: " + filterType);


    }

    public void delete(String filterType, String filterData) {

        ArrayList newFilterData = null;

        if (filterCriteria.containsKey(filterType)) {
            ArrayList curFilterData = filterCriteria.get(filterType);
            curFilterData.remove(filterData);

        } else {

            newFilterData = new ArrayList();

            newFilterData.remove(filterData);

        }


    }


    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    @Override
    public String toString() {
        return "CatalogFilter{" +
                "filterEnabled='" + filterEnabled + '\'' +
                ", filterCriteria=" + filterCriteria +
                ", catalogId=" + catalogId +
                '}';
    }
}
