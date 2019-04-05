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
import com.dmantz.ecommerceapp.model.Order;
import com.dmantz.ecommerceapp.model.OrderItem;
import com.dmantz.ecommerceapp.model.ProductInfo;
import com.dmantz.ecommerceapp.model.ProductSkus;
import com.nex3z.notificationbadge.NotificationBadge;

import java.util.Iterator;
import java.util.List;

public class ItemActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final String TAG = ItemActivity.class.getSimpleName();

    Button buyNow;
    ImageView cartIconView;
    NotificationBadge mBadge;
    String totalQuantity;


    // Firebase url, url2;
    //  DatabaseReference mFirebase, rootRef;


    OrderItem orderItemObj = new OrderItem();

    int count = 0;
    Spinner productColor, productSize;
    String itemName, itemPrice, itemSize, ProductId, itemDescription, itemImage;

    ECApplication ecApp;

    ProductInfo productInfoObj = new ProductInfo();
    OrderItem orderItem;
    Order order = new Order();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ecApp = (ECApplication) getApplication();
        setContentView(R.layout.activity_item_client);

        //  getItemInformation();
        //    Firebase.setAndroidContext(this);
        getItemInfo();

        cartIconView = findViewById(R.id.cart_item_notification);
        buyNow = findViewById(R.id.buyNow);
        mBadge = findViewById(R.id.actionbar_notification_textview);


        RelativeLayout ll = (RelativeLayout) findViewById(R.id.activity_item_details);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.BELOW, R.id.buyNow);

        //Elegent number button for increasing quantity of products


        Button addToCartBtn = new Button(getApplicationContext());
        addToCartBtn.setText("Add to cart");
        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //update quantity in orderItem

                ecApp.orderClientObj.addItem(orderItem);

                mBadge.setNumber(ecApp.orderClientObj.getCurrentOrder().totalQuantity());
                Log.d(TAG, "onClick: count " + ecApp.orderClientObj.getCurrentOrder());
                Toast toast = Toast.makeText(getApplicationContext(), "Item added to cart", Toast.LENGTH_SHORT);
                toast.show();

            }
        });


        //AddItemButton addItemButton = new AddItemButton(getApplicationContext());
        //addItemButton.setOrderItem(orderItem);
        //addItemButton.addtocart();
        //add this button to Layout

        //  url = new Firebase("https://ecapplication-7c0cf.firebaseio.com/name/-LOw1zFfkeJ5tC1E4uA8");
        // url2 = new Firebase("https://ecapplication-7c0cf.firebaseio.com/name/-LOwEbY70jY3l6hy26Vg");


        //   ecApp.orderClientObj.addItem((AddItemButton)v.getOrderItem());
//        ecApp.orderClientObj.addItem(addItemButton.getOrderItem());

        ll.addView(addToCartBtn, lp);

                /*  String itemName = getIntent().getStringExtra("productName");
                String itemId = String.valueOf(getIntent().getIntExtra("productId", 0));
                String itemPrice = String.valueOf(getIntent().getDoubleExtra("productPrice", 0.00));

                String itemColor = productColor.getSelectedItem().toString();
                String itemSize = productSize.getSelectedItem().toString();
                String itemImage = getIntent().getStringExtra("productUrl");
                /*

                   Firebase firebase = url.child("name");
                   Map newUserData = new HashMap();
                   firebase.push().setValue(itemName);
                   firebase.updateChildren(newUserData);




                orderItemObj.setItemName(itemName);
                orderItemObj.setItemPrice(itemPrice);
                //orderItemObj.setProductImage(itemImage);


                itemOptionsObj.setItemOptionId(itemId);
                itemOptionsObj.setItemOptionName(itemSize);
                itemOptionsObj.setItemOptionValue(itemColor);
                */

                /*
                 orderItemObj.setItemOptionsObj(itemOptionsObj);
                  orderItemObj.setOrderItemObj(orderItemObj);
                  order.setOrderItemObj(orderItemObj);
                Log.d(TAG, "onClick: " + orderItemObj.getProductName() + orderItemObj.getProductId() + orderItemObj.getOption1Value() + orderItemObj.getOption2Value());
                */

        //ECApplication lapp = (ECApplication) getApplication();
        //lapp.orderClientObj.createOrder(orderItemObj);

        //  Log.d(TAG, "onClick: " + lapp.toString());
        //count = orderObj.getOrderArrayList().size();

        //count = lapp.orderClientObj.getCurrentOrder().getOrderItemObj().size();


        // after clicking on cart icon and it shows how many items available in cart
        cartIconView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent onClickCartIcon = new Intent(ItemActivity.this, CartViewActivity.class);

                //onClickCartIcon.putExtra("itemOfCart", orderItemObj.getProductSku());
                //onClickCartIcon.putExtra("itemOfCart", orderItemObj.getItemName());
                startActivity(onClickCartIcon);

            }
        });


        // after clicking buy now button it will go to payment gatway
        buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent buyNow = new Intent(ItemActivity.this, PaymentClient.class);
                startActivity(buyNow);
            }
        });

    /*    try {
            Log.d(TAG, "onCreate: entered into try block");
            InputStream is = getAssets().open("itemInformation.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            jsonData = new String(buffer, "UTF-8");
            Log.d(TAG, "got information" + jsonData);


        } catch (IOException ex) {
            ex.printStackTrace();

        }


        try {
            org.json.JSONObject storejsonObj = new org.json.JSONObject(jsonData.toString());

            org.json.JSONArray jsonarrayObj = storejsonObj.getJSONArray("productInformation");
            Log.d(TAG, "got product object with json data" + jsonarrayObj);


            //    ArrayList<ItemDetailPoJoClass> itemDetail = new ArrayList<>();

            for (int i = 0; i < jsonarrayObj.length(); i++) {

                Log.d(TAG, "entered into for loop");
                ItemDetailPoJoClass obj = new ItemDetailPoJoClass();

                obj.setItemName((String) jsonarrayObj.getJSONObject(i).get("itemName"));
                obj.setItemDescription((String) jsonarrayObj.getJSONObject(i).get("itemDescription"));
                obj.setItemId((String) jsonarrayObj.getJSONObject(i).get("ProductId"));
                obj.setItemSize((String) jsonarrayObj.getJSONObject(i).get("itemSize"));
                obj.setItemPrice((String) jsonarrayObj.getJSONObject(i).get("itemPrice"));
                Log.d(TAG, "itemname" + obj.getItemName());
                Log.d(TAG, "itemdescription" + obj.getItemDescription());
                //  itemDetail.add(obj);
                String name = obj.getItemName();
                mName.setText(name);
                String itemDesc = obj.getItemDescription();
                mDescription.setText(itemDesc);
                String itemsize = obj.getItemSize();
                mSize.setText(itemsize);
                String itemid = obj.getItemId();
                mId.setText(itemid);
                String itemprice = obj.getItemPrice();
                mPrice.setText(itemprice);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

*/
    }


    // All the information will get from backend and displays to the itemActivity
    private void getItemInfo() {
        ProductId = getIntent().getStringExtra("productId");
        productInfoObj = ecApp.catalogClient.getProduct(ProductId);


        ProductSkus productSku = productInfoObj.getProductSkus().get(0);


        orderItem = new OrderItem();
        orderItem.setProductName(productInfoObj.getProductName());
        orderItem.setPrice(productSku.getPrice());
        orderItem.setProductSku(productSku.getSku());
        orderItem.setProductId("102");


        ElegantNumberButton quantityIncrementBtn = (ElegantNumberButton) findViewById(R.id.elegentBtn);
        quantityIncrementBtn.setBackgroundColor(Color.BLUE);


        quantityIncrementBtn.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
            @Override
            public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {

                //  orderItem.setQuantity(productInfoObj.getProductSkus().iterator().next().);

                    int qty = Integer.parseInt(quantityIncrementBtn.getNumber());
                    orderItem.setQuantity(qty);
                    Log.d(TAG, "onValueChange: get quantity from orderitem" + quantityIncrementBtn.getNumber());


                //mBadge.setNumber(Integer.parseInt(ecApp.orderClientObj.getCurrentOrder().getOrderItemObj().iterator().next().getQuantity()));

            }
        });



       /* quantityIncrementBtn.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
            @Override
            public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
             // mBadge.setNumber(ecApp.orderClientObj.getCurrentOrder().getOrderItemObj().size());


                orderItem.setQuantity(String.valueOf(newValue));
                Log.d(TAG, "onValueChange: "+orderItem.getQuantity());
            }
        });
        */

        List<Option> options = productSku.getOptions();
        Iterator i = options.iterator();
        LinearLayout optionsLayout = null;

        while (i.hasNext()) {
            //for every option create TextViews
            Option option = (Option) i.next();
            Log.d(TAG, option.getOptionName());
            Log.d(TAG, option.getOptionValue());

            optionsLayout = findViewById(R.id.options_layout);


            //  optionsLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));


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

        priceText.setText(Double.toString(productInfoObj.getProductSkus().get(0).getPrice()));
        priceText.setPadding(10, 10, 10, 10);
        priceText.setTextSize(30);
        priceText.setTextColor(Color.BLUE);

        optionsLayout.addView(priceText);

        //images to display
        //price
        //discount

        Log.d(TAG, "getItemInfo: " + totalQuantity);

        Log.d(TAG, "onClick: count " + ecApp.orderClientObj.getCurrentOrder());

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {


    }



}



