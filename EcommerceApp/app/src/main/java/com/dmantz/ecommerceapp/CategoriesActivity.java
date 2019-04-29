package com.dmantz.ecommerceapp;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.dmantz.ecommerceapp.Adapters.CategoriesListAdapter;
import com.dmantz.ecommerceapp.model.CategoriesChild;
import com.dmantz.ecommerceapp.model.CategoriesParent;
import com.dmantz.ecommerceapp.model.MenuModel;
import com.dmantz.ecommerceapp.model.ProductList;
import com.dmantz.ecommerceapp.model.ProductOld;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class CategoriesActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, NavigationView.OnNavigationItemSelectedListener {

    public static final String TAG = CategoriesActivity.class.getSimpleName();
    private DrawerLayout mDrawerLayout;
    private ProductList mProductList;
    private ProductList mFilteredProductList;

    LinearLayout checkboxLinearLayout;
    CheckBox menuItemCheckBox;
    ECApplication ECApp;

    ImageView img;

    List<String> parentName = new ArrayList<>();
    List<String> childName = new ArrayList<>();

    CategoriesParent catlogDir;
    ExpandableListView categoriesExpandableList;
    CategoriesListAdapter categoriesAdapter;

    private ArrayList<CategoriesParent> parentList = new ArrayList<>();
    private LinkedHashMap<List<String>, CategoriesParent> categories = new LinkedHashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories2);


        ECApp = (ECApplication) getApplicationContext();

        parentList.clear();
        categories.clear();
        loadData();
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.appbar);
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.navigation_icon);
        toolbar.setNavigationIcon(drawable);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();

        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.navigation_icon);

        categoriesExpandableList = (ExpandableListView) findViewById(R.id.categoriesListView);
        categoriesAdapter = new CategoriesListAdapter(this, parentList);

        categoriesExpandableList.setAdapter(categoriesAdapter);

        // expandAll();
        addMenuItemInNavDrawer();


        categoriesExpandableList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                return false;
            }
        });


        categoriesExpandableList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                img = (ImageView) v.findViewById(R.id.ivGroupIndicator);

                //CategoriesParent categoriesP = parentList.get(groupPosition);
                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Toast.makeText(CategoriesActivity.this, "Entered into parent ", Toast.LENGTH_LONG).show();

                    }
                });


                img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        if (parent.isGroupExpanded(groupPosition)) {
                            parent.collapseGroup(groupPosition);
                            img.setImageResource(R.drawable.ic_action_categories);
                        } else {


                            parent.expandGroup(groupPosition);
                            img.setImageResource(R.drawable.ic_action_categories);
                        }

                    }
                });


                return true;
            }
        });
    }


    private void expandAll() {
        int count = categoriesAdapter.getGroupCount();
        for (int i = 0; i < count; i++) {
            categoriesExpandableList.expandGroup(i);
        }
    }


    private void collapseAll() {
        int count = categoriesAdapter.getGroupCount();
        for (int i = 0; i < count; i++) {
            categoriesExpandableList.collapseGroup(i);
        }
    }


    private void loadData() {

        ECApp.catalogClient.check();

        List<CategoriesParent> parent = ECApp.catalogClient.getCategoriesParentList();
        for (CategoriesParent categoriesParent : parent) {

            parentName.add(categoriesParent.getCatalogName());
            Log.d(TAG, "loadData: " + parentName.toString());
        }

//        List<CategoriesChild> childList = ECApp.catalogClient.getCategoriesParentList();

        List<CategoriesChild> categoriesChild = ECApp.catalogClient.categoriesParentList.iterator().next().getChildCatalog();
        for (CategoriesChild categoriesChild1 : categoriesChild) {
            childName.add(categoriesChild1.getCatalogName());
            Log.d(TAG, "loadData: " + childName);
        }
        addProduct(parentName, childName);
    }

    private int addProduct(List<String> department, List<String> product) {

        int groupPosition = 0;

        //check the hash map if the group already exists
        CategoriesParent parent = categories.get(department);

        List<String> parentList = new ArrayList<>();
        for (String s : parentName) {

            Log.d(TAG, "categories activity " + s);
            parentList.add(s);
            categories.put(parentList, parent);
            this.parentList.add(parent);

        }


        List<String> catlogList = new ArrayList<>();
        //size of the children list
        catlogList.add(String.valueOf(childName));

        int listSize = catlogList.size();
        //add to the counter
        listSize++;
        //create a new child and add that to the group
        //   CategoriesChild childObj = categories.get(product);

        //ECApp.catalogClient.getCategoriesParentList().iterator().next().setChildCatalog(catlogList);

        //find the group position inside the list
        groupPosition = catlogList.indexOf(parent);
        return groupPosition;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(TAG, "onCreateOptionsMenu: sucesfully entered into method");
        getMenuInflater().inflate(R.menu.app_bar_menu, menu);
        // MenuItem menuItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(this);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;

            case R.id.carticon:
                cartonclick();
                return true;


        }

        return super.onOptionsItemSelected(item);


    }


    public void cartonclick() {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);


    }


    @Override
    public void onBackPressed() {

        Intent a = new Intent(this, MainActivity.class);

        startActivity(a);

    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        Log.d(TAG, "onQueryTextChange: ");
        newText = newText.toLowerCase();
        ArrayList<ProductOld> newList = new ArrayList<>();

        for (ProductOld productOld : mProductList.getProductsList()) {
            String itemName = productOld.getItemName().toLowerCase();
            if (itemName.contains(newText))
                newList.add(productOld);
        }

        mFilteredProductList = new ProductList();
        mFilteredProductList.setProductsList(newList);
        return true;

    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


    private void addMenuItemInNavDrawer() {


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        mDrawerLayout = findViewById(R.id.navigation_drawer);
        final Menu menu = navigationView.getMenu();

        MenuModel menuModel = new MenuModel();

        // SubMenu subMenu = menu.addSubMenu("Shop for");
        //   SubMenu brands = menu.addSubMenu("BRANDS");


        org.json.JSONObject navMenuOptions = menuModel.menuDetails(this);

        MenuItem mi = null;
        String filterKey;
        Iterator<String> keys = navMenuOptions.keys();
        String[] displayNameArray = new String[3];

        while (keys.hasNext()) {

            filterKey = (String) keys.next();
            final SubMenu subMenu = menu.addSubMenu(filterKey);
            //    mi = subMenu.add(filterKey);
            try {

                org.json.JSONArray categorieData = navMenuOptions.getJSONArray(filterKey);
                Log.d(TAG, "addMenuItemInNavDrawer: " + categorieData);


                for (int i = 0; i < categorieData.length(); i++) {

                    org.json.JSONObject displayNameData = categorieData.getJSONObject(i);
                    String displayName = displayNameData.getString("displayName");


                    checkboxLinearLayout = new LinearLayout(this);
                    menuItemCheckBox = new CheckBox(this);

                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                    checkboxLinearLayout.setLayoutParams(layoutParams);
                    LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    menuItemCheckBox.setLayoutParams(layoutParams2);

                    Intent miIntent = new Intent();
                    Bundle bundle = new Bundle();

                    bundle.putString("filterType", filterKey);
                    bundle.putString("filterData", displayName);

                    checkboxLinearLayout.setOrientation(LinearLayout.VERTICAL);
                    checkboxLinearLayout.addView(menuItemCheckBox);

                    if (menuItemCheckBox.getParent() != null) {
                        ((ViewGroup) menuItemCheckBox.getParent()).removeView(menuItemCheckBox);
                    }

                    mi = subMenu.add(displayName).setActionView(menuItemCheckBox);
                    mi.setIntent(miIntent);

                    menuItemCheckBox.setTag(bundle);
                    menuItemCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                            Bundle bundle = (Bundle) buttonView.getTag();

                            Log.d(TAG, "onCheckedChanged:bundle " + bundle);

                          /*  Intent miIntent = getIntent();
                            String filterEnabled = miIntent.getStringExtra("filterEnabled");
                                String[] filterData = miIntent.getStringArrayExtra() */

                            ECApplication lapp = (ECApplication) getApplication();


                            if (isChecked) {


                                lapp.catalogClient.updateFilter("add", bundle.getString("filterType"), bundle.getString("filterData"));

                                try {

                                    mProductList = lapp.catalogClient.productDisplayList();


                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            } else {


                                lapp.catalogClient.updateFilter("delete", bundle.getString("filterType"), bundle.getString("filterData"));

                                try {
                                    mProductList = lapp.catalogClient.productDisplayList();

                                } catch (Exception e) {

                                    e.printStackTrace();
                                }

                            }

                        }
                    });


                }


            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

        mDrawerLayout.closeDrawers();

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
