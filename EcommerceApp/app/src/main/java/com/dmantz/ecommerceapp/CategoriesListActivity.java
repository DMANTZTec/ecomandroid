package com.dmantz.ecommerceapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class CategoriesListActivity extends Activity {

    TextView text;
    ListView categoriesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        Toolbar mCategoryToolbar = (Toolbar) findViewById(R.id.categories_toolbar);
        mCategoryToolbar.setTitle("Categories");
        mCategoryToolbar.setNavigationIcon(R.drawable.back_arrow);

        mCategoryToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mBack = new Intent(CategoriesListActivity.this, MainActivity.class);
                startActivity(mBack);
            }
        });


        categoriesListView = findViewById(R.id.categories_list_view);

        String[] categoriesNames = new String[]{"Men", "Women", "Kids"};

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_textview_adapter, R.id.text_categories_list, categoriesNames);
        categoriesListView.setAdapter(arrayAdapter);


    }

}
