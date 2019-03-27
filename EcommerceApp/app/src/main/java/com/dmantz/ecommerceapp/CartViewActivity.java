package com.dmantz.ecommerceapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.dmantz.ecommerceapp.Adapters.CartViewAdapter;
import com.dmantz.ecommerceapp.model.Order;
import com.dmantz.ecommerceapp.model.OrderItem;
import com.dmantz.ecommerceapp.model.ProductInfo;
import com.nex3z.notificationbadge.NotificationBadge;

import java.util.ArrayList;
import java.util.List;

public class CartViewActivity extends AppCompatActivity {

    public static final String TAG = CartViewActivity.class.getSimpleName();

    TextView orderIdText;
    Button btnCheckout;


    private RecyclerView.Adapter cartAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_view);


        RecyclerView cartViewRecyclerview = (RecyclerView) findViewById(R.id.cartView_RecyclerView);
        GridLayoutManager linearLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        cartViewRecyclerview.setLayoutManager(linearLayoutManager);


        ECApplication lapp = (ECApplication) getApplication();

        btnCheckout = findViewById(R.id.btn_checkout);

        orderIdText = (TextView) findViewById(R.id.yourOrderText);
        orderIdText.setText(lapp.orderClientObj.getOrderId());


        // productInfoObj = lapp.catalogClient.getProduct("103");
        List<OrderItem> orderItems = lapp.orderClientObj.getCurrentOrder().getOrderItemObj();
        cartAdapter = new CartViewAdapter(getApplicationContext(), orderItems);
        cartViewRecyclerview.setAdapter(cartAdapter);


        NotificationBadge mBadge;
        mBadge = findViewById(R.id.actionbar_notification_textview);
        mBadge.setNumber(Integer.parseInt(orderItems.iterator().next().getQuantity()));


        //mBadge.setNumber(lapp.orderClientObj.getCurrentOrder().getOrderItemObj().size());

        Log.d(TAG, "onCreate: " + lapp.orderClientObj.getCurrentOrder().getOrderItemObj().size());
    }


}
