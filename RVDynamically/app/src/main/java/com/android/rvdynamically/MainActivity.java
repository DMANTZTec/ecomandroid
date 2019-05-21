package com.android.rvdynamically;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.MenuItem;

import com.android.rvdynamically.model.User;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    private DrawerLayout mDrawerLayout;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    //catlog adapter
    private RecyclerViewAdapter adapter;
    private User user;
    UserApp uapp;

    private UserClient client;

    public MainActivity() {

    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // android.support.v7.widget.Toolbar toolbar = findViewById(R.id.)

        uapp = (UserApp) getApplication();
        uapp.userClient.setContext(getApplicationContext());

       ArrayList<User> userList = uapp.userClient.getUserFromDB();


        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(this, 2);

        adapter = new RecyclerViewAdapter(this, userList);

    }
}
