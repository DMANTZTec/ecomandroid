package com.dmantz.ecommerceapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dmantz.ecommerceapp.Adapters.CartViewAdapter;
import com.dmantz.ecommerceapp.ECApplication;
import com.dmantz.ecommerceapp.PaymentActivity;
import com.dmantz.ecommerceapp.R;
import com.dmantz.ecommerceapp.ShippingActivity;
import com.dmantz.ecommerceapp.model.OrderItem;
import com.dmantz.ecommerceapp.model.Shipping;
import com.nex3z.notificationbadge.NotificationBadge;

import java.util.List;

public class CartViewActivity extends AppCompatActivity {

    public static final String TAG = CartViewActivity.class.getSimpleName();

    TextView orderIdText, cartTotalValue, finalAmt, disountApplied, couponstatus;
    Button btnCheckout, applyCoupon, addAddress;
    EditText couponCodeET;
    private RecyclerView.Adapter cartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_view);

        //Handle to recyclerview and setting adapter
        RecyclerView cartViewRecyclerview = (RecyclerView) findViewById(R.id.cartView_RecyclerView);
        GridLayoutManager linearLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        cartViewRecyclerview.setLayoutManager(linearLayoutManager);

        ECApplication lapp = (ECApplication) getApplication();

        cartTotalValue = findViewById(R.id.cartSubTotal);
        btnCheckout = findViewById(R.id.btn_checkout);
        applyCoupon = findViewById(R.id.applyBtn);
        couponCodeET = findViewById(R.id.couponET);
        disountApplied = findViewById(R.id.disountApplied);
        finalAmt = findViewById(R.id.finalAmt);
        orderIdText = (TextView) findViewById(R.id.yourOrderText);
        couponstatus = findViewById(R.id.couponStatus);
        addAddress = findViewById(R.id.editAddress);

        TextView name = (TextView) findViewById(R.id.name);
        TextView hno = (TextView) findViewById(R.id.hno);
        TextView street = (TextView) findViewById(R.id.street);
        TextView city = (TextView) findViewById(R.id.city);
        TextView state = (TextView) findViewById(R.id.state);
        TextView pincode = (TextView) findViewById(R.id.pinCode);


        // looping through the shipping to set selected shipping address in cartview or orderview
        for (Shipping shipping : lapp.orderClientObj.getCurrentOrder().getShippingArrayList()) {

            name.setText(shipping.getFirstName());
            hno.setText(shipping.getFlatNo());
            street.setText(shipping.getArea());
            city.setText(shipping.getCity());
            state.setText(shipping.getState());
            //  pincode.setText(shipping.getPincode());

        }

        orderIdText.setText("ORDER ID : " + Integer.toString(lapp.orderClientObj.getOrderId()));

        // condition to check coupon code is applied or not in cartview

        if (lapp.orderClientObj.getCurrentOrder().getCouponCode() == null) {
            couponCodeET.setEnabled(true);

        } else
            couponCodeET.setEnabled(true);
        couponCodeET.setText(lapp.orderClientObj.getCurrentOrder().getCouponCode());


        // apply coupon button on click send code to BE
        applyCoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String couponcode = couponCodeET.getText().toString();
                lapp.orderClientObj.applyCoupon(couponcode);

                couponstatus.setText(lapp.orderClientObj.getCouponRes().getCouponStatus());
                Toast toast = Toast.makeText(getApplicationContext(), lapp.orderClientObj.getCouponRes().getCouponStatus(), Toast.LENGTH_LONG);
                toast.show();
                finalAmt.setText(Double.toString(lapp.orderClientObj.getCurrentOrder().getFinalAmount()));
                disountApplied.setText("-" + lapp.orderClientObj.getCouponRes().getDiscountToApply().toString());
                //cartTotalValue.setText(Double.toString( lapp.orderClientObj.getCouponRes().getFinalAmount()));
            }


        });

        //finalAmt.setText(Double.toString(lapp.orderClientObj.getCurrentOrder().finalAmt()));
        List<OrderItem> orderItems = lapp.orderClientObj.getCurrentOrder().getOrderItemList();
        cartAdapter = new CartViewAdapter(getApplicationContext(), orderItems);
        cartViewRecyclerview.setAdapter(cartAdapter);

        finalAmt.setText(Double.toString(lapp.orderClientObj.getCurrentOrder().getFinalAmount()));
        disountApplied.setText("-" + lapp.orderClientObj.getCurrentOrder().getDiscountedAmount());
        lapp.orderClientObj.getCurrentOrder().calculateTotals();
        cartTotalValue.setText(Integer.toString((int) lapp.orderClientObj.getCurrentOrder().cartTotal()));

        NotificationBadge mBadge;
        mBadge = findViewById(R.id.actionbar_notification_textview);
        mBadge.setNumber(lapp.orderClientObj.getCurrentOrder().getTotalQty());


        addAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                String s = "";
                for (Shipping shipping : lapp.orderClientObj.addressList()) {
                    s = shipping.getCustomerId();
                }

                if (s.equals("102")) {
                    intent = new Intent(CartViewActivity.this, ExistingShippingActivity.class);
                } else {
                    intent = new Intent(CartViewActivity.this, ShippingActivity.class);
                }
                startActivity(intent);
            }
        });


        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CartViewActivity.this, PaymentActivity.class);
                startActivity(intent);

            }
        });
    }


}
