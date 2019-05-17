package com.dmantz.ecommerceapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import com.squareup.picasso.Picasso;

public class PaymentClient extends Activity implements PaymentResultListener {


    EditText mAmount;
    Button mPay;
    Long payAmount;

    TextView mTextView;
    ImageView mProductImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_client);
        //   mAdapter = new RecyclerViewAdapter(mProductList , getApplicationContext());
        // mRecyclerView.setAdapter(mAdapter);


        mPay = findViewById(R.id.btn_pay);

        getProductImformation();


    }

    private void getProductImformation() {


        if (getIntent().hasExtra("itemName") && getIntent().hasExtra("itemImage") && getIntent().hasExtra("itemSize") && getIntent().hasExtra("itemPrice")) {

            String itemName = getIntent().getStringExtra("itemName");
            String itemUrl = getIntent().getStringExtra("itemImage");
            String itemId = getIntent().getStringExtra("ProductId");
            String itemPrice = getIntent().getStringExtra("itemPrice");
            String itemSize = getIntent().getStringExtra("itemSize");

            setProductImage(itemName, itemUrl, itemSize, itemPrice);
        }


    }

    private void setProductImage(String itemName, String itemUrl, String itemSize, String itemPrice) {

        mTextView = findViewById(R.id.itemInfo);
        mTextView.setText(itemName);

        mProductImage = findViewById(R.id.productImage);
        Picasso.get().load(itemUrl).fit().into(mProductImage);

        mTextView = findViewById(R.id.itemSize);
        mTextView.setText(itemSize);

        mTextView = findViewById(R.id.itemPrice);
        mTextView.setText(itemPrice);

    }

    public void startPayment(View view) {
        mAmount = findViewById(R.id.enterAmount);

        payAmount = Long.parseLong(mAmount.getText().toString());
        Log.d("oprion", "get amount" + payAmount);
        Checkout checkout = new Checkout();


        final Activity activity = this;

        try {


            org.json.JSONObject options = new org.json.JSONObject();
            options.put("Description", "Order #123456");
            options.put("currency", "INR");

            options.put("amount", payAmount * 100);
            Log.d("oprion", "get amount" + payAmount);


            checkout.open(activity, options);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onPaymentSuccess(String s) {

        Toast.makeText(PaymentClient.this, "Payment is succeessful", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onPaymentError(int i, String s) {

        Toast.makeText(PaymentClient.this, "Payment is failed", Toast.LENGTH_LONG).show();

    }

}
