package com.dmantz.ecommerceapp.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.dmantz.ecommerceapp.ECApplication;
import com.dmantz.ecommerceapp.PaymentActivity;
import com.dmantz.ecommerceapp.R;
import com.dmantz.ecommerceapp.model.Option;
import com.dmantz.ecommerceapp.model.OrderItem;
import com.dmantz.ecommerceapp.model.Product;
import com.dmantz.ecommerceapp.model.ProductSku;
import com.nex3z.notificationbadge.NotificationBadge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ItemActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final String TAG = ItemActivity.class.getSimpleName();

    ECApplication ecApp;

    ElegantNumberButton quantityIncrementBtn;
    Button buyNow;

    ImageView cartIconView;
    NotificationBadge mBadge;
    String itemName;
    String itemPrice;
    String itemSize;
    String ProductId;
    String itemDescription;
    String itemImage;

    Product productObj;
    OrderItem orderItem;

    HashMap<List<String>, List<String>> optionValueMap = new HashMap<>();
    String size, color;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ecApp = (ECApplication) getApplication();
        setContentView(R.layout.activity_item);

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
                orderItem.setProductSku(productSku.getProductSkuId());

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

        List<ProductSku> productSkus = productObj.getProductSkus();
        Log.d(TAG, "list of product sku " + productSkus);

        quantityIncrementBtn.setBackgroundColor(Color.BLUE);
        quantityIncrementBtn.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
            @Override
            public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
                Log.d(TAG, "onValueChange: get quantity from orderitem" + quantityIncrementBtn.getNumber());


            }
        });


        List<String> productSkuList = productSkus.stream()
                .flatMap(a -> a.getOptions().stream())
                .map(b -> b.getOptionName()).collect(Collectors.toList());

        List<String> optionNames = productSkuList.stream().distinct().collect(Collectors.toList());
        Log.d(TAG, "option name " + optionNames);


        List<String> optionValues = productSkus.stream()
                .flatMap(y -> y.getOptions().stream())
                .filter(x -> x.getOptionName().equals("color"))
                .map(z -> z.getOptionValue()).collect(Collectors.toList());
        Log.d(TAG, "getItemInfo: " + optionValues);

        List<String> optionValuesSize = productSkus.stream()
                .flatMap(y -> y.getOptions().stream())
                .filter(x -> x.getOptionName().equals("size"))
                .map(z -> z.getOptionValue()).collect(Collectors.toList());
        Log.d(TAG, "getItemInfo: " + optionValues);

        // hashmap concatinat to the productsku list

        List<String> productId = new ArrayList<>();
        List<String> Size = new ArrayList<>();
        List<String> colorList = new ArrayList<>();
        List<String> concatinateDemo = new ArrayList<>();

        HashMap<List<String>, List<String>> hm = new HashMap<>();

        // for loop to get productskuid
        for (ProductSku productSku : productSkus) {

            String s = productSku.getProductSkuId();

            productId.add(s);
            Log.d(TAG, "product id " + productId);

            //for loop to get options values
            for (Option option : productSku.getOptions()) {

                String optionDetails = option.getOptionValue();
                Log.d(TAG, "option details " + optionDetails);

                if (optionDetails.length() <= 1) {
                    size = optionDetails;

                    Size.add(size);
                    Log.d(TAG, "Size " + Size);


                } else {
                    color = optionDetails;
                    colorList.add(color);
                    Log.d(TAG, "getItemInfo: " + colorList);

                }


            }

            concatinateDemo.add(color + ":" + size);


        }
        hm.put(productId, concatinateDemo);


        Log.d(TAG, "concatinatedemo " + hm);






/*
        for (int i = 0; i < productSku.size(); i++) {

            List<Option> option = productSku.get(i).getOptions();


            for (int k = 0; k < option.size(); k++) {


                if (option.get(k).getOptionName().equals("size")) {
                    sizeList.add(option.get(k).getOptionValue());
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.activity_spin1_layout, sizeList);

                    spinSize.setAdapter(dataAdapter);
                    spinSize.setPadding(10, 10, 10, 10);
                    dataAdapter.setDropDownViewResource(R.layout.activity_spin1_layout);
                    spinSize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            Log.d(TAG, "onItemSelected: " + position);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });


                } else if (option.get(k).getOptionName().equals("color")) {
                    concatinateDemo.add(option.get(k).getOptionValue());

                    ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this, R.layout.activity_color_layout, concatinateDemo);

                    spinColor.setAdapter(dataAdapter1);
                    spinColor.setPadding(10, 10, 10, 10);
                    dataAdapter1.setDropDownViewResource(R.layout.activity_color_layout);

                }


            }


            //sizeSpinner.setTextColor(Color.BLUE);
            //sizeSpinner.setTextSize(30);

        }
        */


        List<Option> options = productSkus.iterator().next().getOptions();

        Iterator i = options.iterator();
        LinearLayout optionsLayout = null;

        while (i.hasNext()) {
            //for every option create TextViews
            Option option = (Option) i.next();
            Log.d(TAG, option.getOptionName());
            Log.d(TAG, option.getOptionValue());

            optionsLayout = findViewById(R.id.options_layout);

            TextView optionNameText = new TextView(this);
            Spinner optionValueText = new Spinner(this);


            optionNameText.setText(option.getOptionName() + ":  ");
            optionNameText.layout(5, 5, 5, 5);
            optionNameText.setPadding(10, 10, 10, 10);
            optionNameText.setTextColor(Color.BLACK);
            optionNameText.setTextSize(30);


            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.activity_spin1_layout, optionValues);


            optionValueText.setAdapter(dataAdapter);
            optionValueText.setPadding(10, 10, 10, 10);
            //optionValueText.setTextColor(Color.BLUE);
            //optionValueText.setTextSize(30);

            //  dataAdapter.setDropDownViewResource(R.layout.activity_spin1_layout);


            optionsLayout.addView(optionNameText);
            optionsLayout.addView(optionValueText);


        }


        TextView priceText = new TextView(this);

        // priceText.setText(String.valueOf(productSku.getPrice()));
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



