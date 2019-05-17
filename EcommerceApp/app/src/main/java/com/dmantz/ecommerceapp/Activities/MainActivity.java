package com.dmantz.ecommerceapp.Activities;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import com.dmantz.ecommerceapp.Adapters.RecyclerViewAdapter;
import com.dmantz.ecommerceapp.ECApplication;
import com.dmantz.ecommerceapp.Fragments.OneFragment;
import com.dmantz.ecommerceapp.R;
import com.dmantz.ecommerceapp.model.Catlog;
import com.dmantz.ecommerceapp.model.MenuModel;
import com.dmantz.ecommerceapp.model.ProductList;
import com.dmantz.ecommerceapp.model.ProductOld;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, NavigationView.OnNavigationItemSelectedListener {


    public static final String TAG = MainActivity.class.getSimpleName();
    LinearLayout checkboxLinearLayout;
    CheckBox menuItemCheckBox;
    private DrawerLayout mDrawerLayout;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    //catlog adapter
    private RecyclerViewAdapter adapter;
    private Catlog catlog;
    private ProductList mProductList;
    private ProductList mFilteredProductList;

    public MainActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.appbar);
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.navigation_icon);
        toolbar.setNavigationIcon(drawable);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();

        ECApplication lapp = (ECApplication) getApplication();
        lapp.catalogClient.setContext(getApplicationContext());

        catlog = lapp.catalogClient.getCatlog(true);


        //      viewPager = findViewById(R.id.viewpager);
        //      setupViewPager(viewPager);
        //     tabLayout = findViewById(R.id.tabs);
        //     tabLayout.setupWithViewPager(viewPager);

        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.navigation_icon);

        addMenuItemInNavDrawer();

        mRecyclerView = findViewById(R.id.recyclerviewone);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));


        try {

            //productList.add((Product) catlog.getProducts());
            adapter = new RecyclerViewAdapter(catlog.getProducts(), this);
            //ECApplication)getApplication());
            mRecyclerView.setAdapter(adapter);
            //productList.add(mCatlog.getProducts().iterator().next().getProductName());
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                                    mAdapter = new RecyclerViewAdapter(mProductList, getApplicationContext());
                                    //ECApplication)getApplication());
                                    mRecyclerView.setAdapter(mAdapter);
                                    // miIntent.putExtra("filterEnabled", "true");

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            } else {


                                lapp.catalogClient.updateFilter("delete", bundle.getString("filterType"), bundle.getString("filterData"));

                                try {
                                    mProductList = lapp.catalogClient.productDisplayList();
                                    mAdapter = new RecyclerViewAdapter(mProductList, getApplicationContext());
                                    //ECApplication)getApplication());
                                    mRecyclerView.setAdapter(mAdapter);
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


            case R.id.categories:
                onCategoriesClick();
                return true;

        }

        return super.onOptionsItemSelected(item);


    }

    public void cartonclick() {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);


    }

    public void onCategoriesClick() {
        Intent OnClick = new Intent(this, CategoriesActivity.class);
        startActivity(OnClick);
    }

    @Override
    public void onBackPressed() {

        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new OneFragment(), "ONE");
        viewPager.setAdapter(adapter);
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

        adapter = new RecyclerViewAdapter(mFilteredProductList, getApplicationContext());
        mRecyclerView.setAdapter(adapter);
        // mRecyclerView.updateList(newList);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }
    }


}




