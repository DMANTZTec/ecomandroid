package com.dmantz.ecommerceapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dmantz.ecommerceapp.Activities.ExistingShippingActivity;
import com.dmantz.ecommerceapp.model.Shipping;

public class UpdateShippingAddress extends AppCompatActivity {

    public static final String TAG = UpdateShippingAddress.class.getSimpleName();

    EditText firstName, middleName, lastName, mobileNo, flatNo, area, landmark, city, state, pincode;
    ECApplication ecApp;
    Button updateBtn;
    Shipping shipping = new Shipping();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_shipping_address);

        ecApp = (ECApplication) getApplicationContext();
        updateBtn = findViewById(R.id.updateBtn);

        firstName = findViewById(R.id.firstNameET);
        middleName = findViewById(R.id.middleNameET);
        lastName = findViewById(R.id.lastNameET);
        mobileNo = findViewById(R.id.mobileNoET);
        flatNo = findViewById(R.id.flatNoET);
        area = findViewById(R.id.areaET);
        landmark = findViewById(R.id.landmarkET);
        city = findViewById(R.id.cityET);
        state = findViewById(R.id.stateET);
        pincode = findViewById(R.id.pincodeET);

        shipping.setCustomerId("102");


        for (Shipping shipping : ecApp.orderClientObj.getCurrentOrder().getShippingArrayList()) {

            firstName.setText(shipping.getFirstName());
            shipping.setFirstName(firstName.getText().toString());
            firstName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {
                        shipping.setFirstName(firstName.getText().toString());
                    }
                }
            });
            middleName.setText(shipping.getMiddleName());
            shipping.setMiddleName(middleName.getText().toString());
            middleName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {
                        shipping.setMiddleName(middleName.getText().toString());
                    }
                }
            });
            lastName.setText(shipping.getLastName());
            shipping.setLastName(lastName.getText().toString());
            lastName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {
                        shipping.setLastName(lastName.getText().toString());
                    }
                }
            });
            mobileNo.setText(Long.toString(shipping.getMobileNo()));
            shipping.setMobileNo(Long.parseLong(mobileNo.getText().toString()));
            mobileNo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {
                        shipping.setMobileNo(Long.parseLong(mobileNo.getText().toString()));
                    }
                }
            });

            flatNo.setText(shipping.getFlatNo());
            shipping.setFlatNo(flatNo.getText().toString());
            flatNo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {
                        shipping.setFlatNo(flatNo.getText().toString());
                    }
                }
            });
            area.setText(shipping.getArea());
            shipping.setArea(area.getText().toString());
            area.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {
                        shipping.setArea(area.getText().toString());
                    }
                }
            });
            landmark.setText(shipping.getLandmark());
            shipping.setLandmark(landmark.getText().toString());
            landmark.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {
                        shipping.setLandmark(landmark.getText().toString());
                    }
                }
            });
            city.setText(shipping.getCity());
            shipping.setCity(city.getText().toString());
            city.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {
                        shipping.setCity(city.getText().toString());
                    }
                }
            });
            state.setText(shipping.getState());
            shipping.setState(state.getText().toString());
            state.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {
                        shipping.setState(state.getText().toString());
                    }
                }
            });


            updateBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ecApp.orderClientObj.addAddress(shipping);
                    Toast toast = Toast.makeText(getApplicationContext(), "Updated Successful", Toast.LENGTH_LONG);
                    toast.show();
                    Log.d(TAG, "onClick: " + shipping);
                    Intent intent = new Intent(UpdateShippingAddress.this, ExistingShippingActivity.class);
                    startActivity(intent);
                }
            });

        }
    }
}
