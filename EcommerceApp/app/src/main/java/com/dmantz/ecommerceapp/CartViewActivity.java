package com.dmantz.ecommerceapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;

import com.dmantz.ecommerceapp.Adapters.CartViewAdapter;
import com.dmantz.ecommerceapp.model.OrderItem;
import com.nex3z.notificationbadge.NotificationBadge;

import java.util.List;

public class CartViewActivity extends AppCompatActivity {

    public static final String TAG = CartViewActivity.class.getSimpleName();

    TextView orderIdText,cartTotalValue;
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

        cartTotalValue = findViewById(R.id.cartSubTotal);
        btnCheckout = findViewById(R.id.btn_checkout);

        orderIdText = (TextView) findViewById(R.id.yourOrderText);
        orderIdText.setText("ORDER ID : "+ Integer.toString(lapp.orderClientObj.getOrderId()));





        List<OrderItem> orderItems = lapp.orderClientObj.getCurrentOrder().getOrderItemList();
        cartAdapter = new CartViewAdapter(getApplicationContext(), orderItems);
        cartViewRecyclerview.setAdapter(cartAdapter);

        lapp.orderClientObj.getCurrentOrder().calculateTotals();
        cartTotalValue.setText(Integer.toString((int) lapp.orderClientObj.getCurrentOrder().cartTotal()));

        NotificationBadge mBadge;
        mBadge = findViewById(R.id.actionbar_notification_textview);


        mBadge.setNumber(lapp.orderClientObj.getCurrentOrder().getTotalQty());




    }



}
