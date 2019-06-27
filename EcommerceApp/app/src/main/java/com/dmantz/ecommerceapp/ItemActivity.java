package com.dmantz.ecommerceapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.dmantz.ecommerceapp.model.Option;
import com.dmantz.ecommerceapp.model.OrderItem;
import com.dmantz.ecommerceapp.model.ProductSku;
import com.dmantz.ecommerceapp.model.Product;
import com.nex3z.notificationbadge.NotificationBadge;

import java.util.Iterator;
import java.util.List;

public class ItemActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final String TAG = ItemActivity.class.getSimpleName();

    ECApplication ecApp;

    ElegantNumberButton quantityIncrementBtn;
    Button buyNow;

    ImageView cartIconView;
    NotificationBadge mBadge;
    Spinner productColor, productSize;
    String itemName;
    String itemPrice;
    String itemSize;
    String ProductId;
    String itemDescription;
    String itemImage;

    Product productObj;
    OrderItem orderItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ecApp = (ECApplication) getApplication();
        setContentView(R.layout.activity_item_client);

        cartIconView = findViewById(R.id.cart_item_notification);
        buyNow = findViewById(R.id.buyNow);
        mBadge = findViewById(R.id.actionbar_notification_textview);
        quantityIncrementBtn = (ElegantNumberButton) findViewById(R.id.elegentBtn);

        getItemInfo();

        RelativeLayout ll = (RelativeLayout) findViewById(R.id.activity_item_details);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.BELOW, R.id.buyNow);


        Button addToCartBtn = new Button(getApplicationContext());
        addToCartBtn.setText("Add to cart");
        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                ProductSku productSku = productObj.getProductSkus().get(0);

                orderItem = new OrderItem();
                orderItem.setProductName(productObj.getProductName());
                orderItem.setPrice(productSku.getPrice());
                orderItem.setProductSku(productSku.getSku());

                int qty = Integer.parseInt(quantityIncrementBtn.getNumber());
                orderItem.setQuantity(qty);
                ecApp.orderClientObj.addItem(orderItem);

                mBadge.setNumber(ecApp.orderClientObj.getCurrentOrder().getTotalQty());
                Log.d(TAG, "onClick: count " + ecApp.orderClientObj.getCurrentOrder());
                Toast toast = Toast.makeText(getApplicationContext(), "Item added to cart", Toast.LENGTH_SHORT);
                toast.show();

            }
        });


        ll.addView(addToCartBtn, lp);

        cartIconView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent onClickCartIcon = new Intent(ItemActivity.this, CartViewActivity.class);
                startActivity(onClickCartIcon);

            }
        });


        // after clicking buy now button it will go to payment gatway
        buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent buyNow = new Intent(ItemActivity.this, PaymentActivity.class);
                startActivity(buyNow);
            }
        });


    }


    // All the information will get from backend and displays to the itemActivity
    private void getItemInfo() {
        ProductId = getIntent().getStringExtra("productId");
        productObj = ecApp.catalogClient.getProduct(ProductId);

        ProductSku productSku = productObj.getProductSkus().get(0);

        quantityIncrementBtn.setBackgroundColor(Color.BLUE);
        quantityIncrementBtn.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
            @Override
            public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
                Log.d(TAG, "onValueChange: get quantity from orderitem" + quantityIncrementBtn.getNumber());


            }
        });


        List<Option> options = productSku.getOptions();
        Iterator i = options.iterator();
        LinearLayout optionsLayout = null;

        while (i.hasNext()) {
            //for every option create TextViews
            Option option = (Option) i.next();
            Log.d(TAG, option.getOptionName());
            Log.d(TAG, option.getOptionValue());

            optionsLayout = findViewById(R.id.options_layout);


            TextView optionNameText = new TextView(this);
            TextView optionValueText = new TextView(this);


            optionNameText.setText(option.getOptionName() + ":  ");
            optionNameText.layout(5, 5, 5, 5);
            optionNameText.setPadding(10, 10, 10, 10);
            optionNameText.setTextColor(Color.BLACK);
            optionNameText.setTextSize(30);


            optionValueText.setText(option.getOptionValue());
            optionValueText.setPadding(10, 10, 10, 10);
            optionValueText.setTextColor(Color.BLUE);
            optionValueText.setTextSize(30);


            optionsLayout.addView(optionNameText);
            optionsLayout.addView(optionValueText);


        }


        TextView priceText = new TextView(this);

        priceText.setText(String.valueOf(productSku.getPrice()));
        priceText.setPadding(10, 10, 10, 10);
        priceText.setTextSize(30);
        priceText.setTextColor(Color.BLUE);

        optionsLayout.addView(priceText);

        //images to display
        //price
        //discount


        Log.d(TAG, "onClick: count " + ecApp.orderClientObj.getCurrentOrder());

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {


    }


}



