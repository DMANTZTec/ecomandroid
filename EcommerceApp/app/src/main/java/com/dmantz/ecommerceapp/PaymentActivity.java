package com.dmantz.ecommerceapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dmantz.ecommerceapp.Activities.PaymentCon;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

public class PaymentActivity extends Activity implements PaymentResultListener {

    public static final String TAG = PaymentActivity.class.getSimpleName();
    ECApplication ECApp;
    Button mPay;
    Long payAmount;
    TextView orderID, productName, finalAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_client);
        ECApp = (ECApplication) getApplicationContext();
        orderID = findViewById(R.id.orderIdTV);
        productName = findViewById(R.id.productNameTV);
        finalAmount = findViewById(R.id.finalAmountTV);
        mPay = findViewById(R.id.btn_pay);

        orderID.setText("Order ID : "+Integer.toString(ECApp.orderClientObj.getOrderId()));
        productName.setText("Product Name :"+"\n" +ECApp.orderClientObj.getCurrentOrder().getOrderItemList().iterator().next().getProductName()+"/n");
        Log.d(TAG, "onCreate: "+finalAmount.getText());

        finalAmount.setText( Integer.toString(ECApp.orderClientObj.getCurrentOrder().getOrderItemList().iterator().next().getCartTotalPrice()));
        payAmount = Long.parseLong(finalAmount.getText().toString());

        mPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPayment(v);
            }
        });

    }



    public void startPayment(View view) {

        Log.d("oprion", "get amount" + payAmount);

        Checkout checkout = new Checkout();
        final Activity activity = this;

        try {

            org.json.JSONObject options = new org.json.JSONObject();
            options.put("Description",orderID.getText());
            options.put("currency", "INR");
            options.put("amount", payAmount *100);

            checkout.open(activity, options);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onPaymentSuccess(String s) {

        Toast.makeText(PaymentActivity.this, "Payment is succeessful", Toast.LENGTH_LONG).show();
        Log.d(TAG, "onPaymentSuccess: "+s);
        ECApp.orderClientObj.payment(s);
        Intent intent = new Intent(PaymentActivity.this,PaymentCon.class);

        startActivity(intent);

    }

    @Override
    public void onPaymentError(int i, String s) {

        Toast.makeText(PaymentActivity.this, "Payment is failed", Toast.LENGTH_LONG).show();

    }

}
