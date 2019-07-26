package com.dmantz.ecommerceapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.dmantz.ecommerceapp.Activities.OrderTracking;
import com.dmantz.ecommerceapp.ECApplication;
import com.dmantz.ecommerceapp.R;
import com.dmantz.ecommerceapp.model.YourOrderModel;

import java.util.ArrayList;

public class YourOrdersAdapter extends BaseAdapter {

    public static final String TAG = YourOrdersAdapter.class.getSimpleName();
    Context context;
    ArrayList<YourOrderModel> orderList;
    ECApplication ECApp;


    public YourOrdersAdapter(Context context, ArrayList<YourOrderModel> orderList) {

        this.context = context;
        this.orderList = orderList;

    }


    @Override
    public int getCount() {
        return orderList.size();
    }

    @Override
    public Object getItem(int position) {
        return orderList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.activity_lv_orders, parent, false);


        }

        Button returnBtn, trackOrderBtn;

        returnBtn = convertView.findViewById(R.id.ReturnBtn);
        trackOrderBtn = convertView.findViewById(R.id.TrackBtn);


        TextView orderId = (TextView) convertView.findViewById(R.id.orderId);
        TextView deliveredStates = (TextView) convertView.findViewById(R.id.deliveredStatus);
        TextView estimatedDate = (TextView) convertView.findViewById(R.id.estimatedDateTV);


        orderId.setText(Integer.toString(orderList.get(position).getOrderId()));
        deliveredStates.setText(orderList.get(position).getOrderStatus());
        estimatedDate.setText(orderList.get(position).getDeliverDate().toString().trim());
        ECApp = (ECApplication) context.getApplicationContext();


        trackOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, OrderTracking.class);
                intent.putExtra("orderId", orderList.get(position).getOrderId());
                intent.putExtra("EstimatedTime", String.valueOf(orderList.get(position).getDeliverDate()));
                intent.putExtra("statusCd", orderList.get(position).getOrderStatus());
                context.startActivity(intent);
                //ECApp.orderClientObj.yourOrders().get(position).setOrderId(orderList.get(position).getOrderId());
                //context.startActivity(new Intent(context, OrderTracking.class));


                Log.d(TAG, "onClick: " + orderList.get(position).getOrderId());
            }
        });

        return convertView;
    }
}
