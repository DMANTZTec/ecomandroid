package com.dmantz.ecommerceapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.dmantz.ecommerceapp.Activities.ExistingShippingActivity;
import com.dmantz.ecommerceapp.model.Shipping;

import java.util.ArrayList;
import java.util.List;

//this activity is for the add new address and send address to the backend
public class ShippingActivity extends AppCompatActivity {

    public static final String TAG = ShippingActivity.class.getSimpleName();

    List<EditText> editTextList = new ArrayList<EditText>();
    Button addNewAddressBtn;
    ArrayList<String> hintNames = new ArrayList<>();
    Shipping shipping = new Shipping();
    ECApplication ECApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipping);


        ECApp = (ECApplication) getApplicationContext();
        LinearLayout shippingLayout = (LinearLayout) findViewById(R.id.shippingLayout);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        addNewAddressBtn = (Button) findViewById(R.id.proceedToPaymentbtn);

        shipping.setCustomerId("102");
        hintNames.add("firstName");
        hintNames.add("lastName");
        hintNames.add("middleName");
        hintNames.add("mobileNo");
        hintNames.add("pincode");
        hintNames.add("city");
        hintNames.add("state");
        hintNames.add("flatNo");
        hintNames.add("area");
        hintNames.add("landmark");


        //loop to add Edit text UI Component as per the hint names
        for (int i = 0; i < hintNames.size(); i++) {

            EditText addressFieldText = new EditText(this);

            editTextList.add(addressFieldText);
            shippingLayout.addView(addressFieldText);
            String names = hintNames.get(i);
            addressFieldText.setHint(names);

            if (addressFieldText.getText().length() == 0) {
                addNewAddressBtn.setEnabled(false);
            } else
                addNewAddressBtn.setEnabled(true);


            addressFieldText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {

                        if (addressFieldText.getHint().equals("firstName")) {
                            String firstName = addressFieldText.getText().toString();

                            if (!firstName.matches("^[A-Za-z]+$") && firstName.isEmpty()) {

                                addressFieldText.getText().clear();
                                addressFieldText.setError("Enter valid first name");
                                addNewAddressBtn.setEnabled(false);
                            } else

                                shipping.setFirstName(addressFieldText.getText().toString());



                            Log.d(TAG, "getting first name  " + shipping.getFirstName());

                        } else if (addressFieldText.getHint().equals("lastName")) {
                            String firstName = addressFieldText.getText().toString();
                            if (!firstName.matches("^[A-Za-z]+$") && firstName.isEmpty()) {
                                addressFieldText.getText().clear();
                                addressFieldText.setError("Enter valid last name");
                                addNewAddressBtn.setEnabled(false);

                            } else
                                shipping.setLastName(addressFieldText.getText().toString());
                            Log.d(TAG, "getting last name " + shipping.getLastName());


                        } else if (addressFieldText.getHint().equals("middleName")) {
                            String Name = addressFieldText.getText().toString();
                            if (!Name.matches("^[A-Za-z]+$") && Name.isEmpty()) {
                                addressFieldText.getText().clear();
                                addressFieldText.setError("Enter valid middle name");
                                addNewAddressBtn.setEnabled(false);

                            } else
                                shipping.setMiddleName(addressFieldText.getText().toString());
                            Log.d(TAG, "getting middle name " + shipping.getMiddleName());


                        } else if (addressFieldText.getHint().equals("city")) {
                            String Name = addressFieldText.getText().toString();
                            if (!Name.matches("^[A-Za-z]+$") && Name.isEmpty()) {
                                addressFieldText.getText().clear();
                                addressFieldText.setError("Enter valid city");
                                addNewAddressBtn.setEnabled(false);

                            } else
                                shipping.setCity(addressFieldText.getText().toString());
                            Log.d(TAG, "getting city " + shipping.getCity());


                        } else if (addressFieldText.getHint().equals("state")) {
                            String Name = addressFieldText.getText().toString();
                            if (!Name.matches("^[A-Za-z]+$") && Name.isEmpty()) {
                                addressFieldText.getText().clear();
                                addressFieldText.setError("Enter valid state");
                                addNewAddressBtn.setEnabled(false);

                            } else
                                shipping.setState(addressFieldText.getText().toString());
                            Log.d(TAG, "getting state " + shipping.getState());


                        } else if (addressFieldText.getHint().equals("area")) {
                            String Name = addressFieldText.getText().toString();
                            if (!Name.matches("^[A-Za-z]+$") && Name.isEmpty()) {
                                addressFieldText.getText().clear();
                                addressFieldText.setError("Enter valid area");
                                addNewAddressBtn.setEnabled(false);

                            } else
                                shipping.setArea(addressFieldText.getText().toString());
                            Log.d(TAG, "getting area " + shipping.getArea());
                            addNewAddressBtn.setEnabled(true);


                        } else if (addressFieldText.getHint().equals("flatNo")) {
                            String Name = addressFieldText.getText().toString();
                            if (!Name.matches("^[A-Za-z]+$") && Name.isEmpty()) {
                                addressFieldText.getText().clear();
                                addressFieldText.setError("Enter valid flat no");
                                addNewAddressBtn.setEnabled(false);

                            } else
                                shipping.setFlatNo(addressFieldText.getText().toString());
                            Log.d(TAG, "getting flat no " + shipping.getFlatNo());


                        } else if (addressFieldText.getHint().equals("landmark")) {
                            String Name = addressFieldText.getText().toString();
                            if (!Name.matches("^[A-Za-z]+$") && Name.isEmpty()) {
                                addressFieldText.getText().clear();
                                addressFieldText.setError("Enter valid landmark");

                            } else
                                shipping.setLandmark((addressFieldText.getText().toString()));
                            Log.d(TAG, "getting landmark " + shipping.getLandmark());
                            addNewAddressBtn.setEnabled(true);
                            addressFieldText.addTextChangedListener(new TextWatcher() {
                                @Override
                                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                                    addNewAddressBtn.setEnabled(false);
                                }

                                @Override
                                public void onTextChanged(CharSequence s, int start, int before, int count) {

                                    addNewAddressBtn.setEnabled(true);
                                }

                                @Override
                                public void afterTextChanged(Editable s) {
                                    addNewAddressBtn.setEnabled(true);
                                }
                            });


                        } else if (addressFieldText.getHint().equals("mobileNo")) {

                            String mobile = addressFieldText.getText().toString();
                            if (mobile.isEmpty() || !isValidMobile(mobile) || addressFieldText.getText().toString().toString().length() < 10 || mobile.length() > 13)

                            {
                                addressFieldText.setError("enter valid mobile number");

                                addNewAddressBtn.setEnabled(false);

                            } else {

                                shipping.setMobileNo(Long.parseLong(mobile));
                                Log.d(TAG, "mobile number " + shipping.getMobileNo());
                            }


                        } else if (addressFieldText.getHint().equals("pincode")) {

                            String mobile = addressFieldText.getText().toString();
                            if (mobile.isEmpty() || !isValidMobile(mobile) || addressFieldText.getText().toString().toString().length() != 6)

                            {
                                addressFieldText.setError("enter valid pincode");
                                addNewAddressBtn.setEnabled(false);

                            } else {

                                shipping.setPincode(Integer.parseInt(mobile));
                                Log.d(TAG, "mobile number " + shipping.getPincode());
                            }


                        }


                    }
                }
            });

        }

        //after clicking add new address button get user entered data in list
        addNewAddressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ECApp.orderClientObj.addAddress(shipping);
                Toast.makeText(getApplicationContext(), "Added Sucessfully", Toast.LENGTH_SHORT).show();

                ECApp.orderClientObj.address(shipping);

                Intent intent = new Intent(getApplicationContext(),ExistingShippingActivity.class);
                startActivity(intent);




            }
        });


    }


    private boolean isValidMobile(String s) {
        return android.util.Patterns.PHONE.matcher(s).matches();
    }


    public Shipping getShipping() {
        return shipping;
    }

    public void setShipping(Shipping shipping) {
        this.shipping = shipping;
    }
}
