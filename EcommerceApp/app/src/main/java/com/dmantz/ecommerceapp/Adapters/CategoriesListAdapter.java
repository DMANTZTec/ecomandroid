package com.dmantz.ecommerceapp.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.dmantz.ecommerceapp.ECApplication;
import com.dmantz.ecommerceapp.R;
import com.dmantz.ecommerceapp.model.CategoriesChild;
import com.dmantz.ecommerceapp.model.CategoriesParent;

import java.util.List;

public class CategoriesListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<CategoriesParent> parentList;
    public static final String TAG = CategoriesListAdapter.class.getSimpleName();
    ECApplication ECApp;


    public CategoriesListAdapter(Context mContext, List<CategoriesParent> mParentList) {
        this.context = mContext;
        this.parentList = mParentList;
        Log.d(TAG, "CategoriesListAdapter: " + parentList);
    }


    @Override
    public int getGroupCount() {
        return parentList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        int productList = ECApp.catalogClient.getCategoriesParentList().get(groupPosition).getChildCatalog().size();
        return productList;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return parentList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        List<CategoriesChild> productList = ECApp.catalogClient.getCategoriesParentList().get(childPosition).getChildCatalog();
        return productList.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        TextView heading;

        ECApp = (ECApplication) context.getApplicationContext();

        CategoriesParent parentCatalog = (CategoriesParent) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inf.inflate(R.layout.activity_categories_parent, null);
        }

        heading = (TextView) convertView.findViewById(R.id.categoriesParentText);
        heading.setText(ECApp.catalogClient.getCategoriesParentList().get(groupPosition).getCatalogName());

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        CategoriesChild childCatalog = (CategoriesChild) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.activity_categories_child, null);
        }


        TextView childItem = (TextView) convertView.findViewById(R.id.categoriesChildText);
        childItem.setText(ECApp.catalogClient.getCategoriesParentList().get(groupPosition).getChildCatalog().get(childPosition).getCatalogDesc());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
