package com.dmantz.ecommerceapp.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.dmantz.ecommerceapp.Adapters.YourOrdersAdapter;
import com.dmantz.ecommerceapp.ECApplication;
import com.dmantz.ecommerceapp.R;
import com.dmantz.ecommerceapp.model.YourOrderModel;

import java.util.ArrayList;

public class YourOrdersActivity extends AppCompatActivity {
    public static final String TAG = YourOrdersActivity.class.getSimpleName();
    ECApplication ECApp;
    ListView yoursOrderLV;
    YourOrdersAdapter yourOrdersAdapter;
    ArrayList<YourOrderModel> yourOrderModelArrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_orders);
        ECApp = (ECApplication) getApplication();


        yoursOrderLV = (ListView) findViewById(R.id.yourOrderLV);
        yourOrderModelArrayList = ECApp.orderClientObj.yourOrders();

        yourOrdersAdapter = new YourOrdersAdapter(getApplicationContext(), yourOrderModelArrayList);
        Log.d(TAG, "onCreate: "+yourOrdersAdapter.toString());
        yoursOrderLV.setAdapter(yourOrdersAdapter);


    }
}
