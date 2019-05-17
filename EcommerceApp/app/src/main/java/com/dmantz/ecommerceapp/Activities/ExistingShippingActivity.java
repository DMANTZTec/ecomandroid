package com.dmantz.ecommerceapp.Activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.dmantz.ecommerceapp.Adapters.AddressAdapter;
import com.dmantz.ecommerceapp.ECApplication;
import com.dmantz.ecommerceapp.R;
import com.dmantz.ecommerceapp.ShippingActivity;
import com.dmantz.ecommerceapp.model.Shipping;

import java.util.ArrayList;

public class ExistingShippingActivity extends ShippingActivity {

    public static final String TAG = ExistingShippingActivity.class.getSimpleName();
    public AddressAdapter addressAdapter;
    ArrayList<Shipping> addressList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_existing_shipping);

        ECApplication ECApp = (ECApplication) getApplicationContext();

        ListView listView = (ListView) findViewById(R.id.shippingListview);
        addressList = ECApp.orderClientObj.addressList();

        addressAdapter = new AddressAdapter(ExistingShippingActivity.this, addressList);
        listView.setAdapter(addressAdapter);


        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Log.d(TAG, "entered into item click ");
            }
        });

    }

}
