package com.dmantz.ecommerceapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.dmantz.ecommerceapp.Activities.CartViewActivity;
import com.dmantz.ecommerceapp.R;
import com.dmantz.ecommerceapp.model.Shipping;

import java.util.ArrayList;

// this adapter will show the list of address user added in the list
public class AddressAdapter extends BaseAdapter {

    public static final String TAG = AddressAdapter.class.getSimpleName();
    Context context;
    ArrayList<Shipping> addressList;

    public AddressAdapter(Context context, ArrayList<Shipping> addressList) {

        this.context = context;
        this.addressList = addressList;

    }


    @Override
    public int getCount() {
        return addressList.size();
    }

    @Override
    public Object getItem(int position) {
        return addressList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.activity_address_list, parent, false);


        }

        Shipping address = (Shipping) getItem(position);
        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView hno = (TextView) convertView.findViewById(R.id.hno);
        TextView street = (TextView) convertView.findViewById(R.id.street);
        TextView city = (TextView) convertView.findViewById(R.id.city);
        TextView state = (TextView) convertView.findViewById(R.id.state);
        TextView pincode = (TextView) convertView.findViewById(R.id.pinCode);
        Button editaddress = convertView.findViewById(R.id.editAddressBtn);
        Button sendToThisAddressBtn = convertView.findViewById(R.id.sendAddressBtn);

        convertView.setBackground(context.getDrawable(R.drawable.card_style));
        convertView.setPadding(5, 5, 5, 5);


        name.setText(address.getFirstName());
        hno.setText(address.getFlatNo());
        street.setText(address.getArea());
        city.setText(address.getCity());
        state.setText(address.getState());

        sendToThisAddressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, CartViewActivity.class));
            }
        });

        pincode.setText(String.valueOf(address.getPincode()));


        return convertView;
    }
}
