package com.dmantz.ecommerceapp.Activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.baoyachi.stepview.VerticalStepView;
import com.dmantz.ecommerceapp.ECApplication;
import com.dmantz.ecommerceapp.R;
import com.dmantz.ecommerceapp.model.TrackingModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderTracking extends AppCompatActivity {

    VerticalStepView verticalStepView;
    ECApplication ECApp;
    TextView EstimatedTime, TrackingNo;
    TrackingModel trackingModel;
    int tracking,i;
    int tot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_tracking);

        ECApp = (ECApplication) getApplicationContext();
        ECApp.orderClientObj.orderTracking();


        EstimatedTime = findViewById(R.id.estimatedTime);
        TrackingNo = findViewById(R.id.trackingNo);

        EstimatedTime.setText(ECApp.orderClientObj.getTrackingModel().getEstimatedTime().toString());
        TrackingNo.setText(ECApp.orderClientObj.getTrackingModel().getOrderId());
        verticalStepView = (VerticalStepView) findViewById(R.id.stepView);
      List<String> trackingStatus = new ArrayList<>();
       // trackingStatus.add(ECApp.orderClientObj.getStatuscd().toString());

        trackingStatus.add("PAID");
        trackingStatus.add("Shipped");
        trackingStatus.add("In transit");
        trackingStatus.add("Out for delivery");
        trackingStatus.add("delivery completed");


        verticalStepView.setStepViewTexts(trackingStatus);

        String s = ECApp.orderClientObj.getTrackingModel().getStatusCd();
        for(String track : trackingStatus){

            if(track.contains(s)){

                 tracking  = trackingStatus.size();
                 i = trackingStatus.indexOf(track);
                 tot = tracking+i;

                Log.d("Order tracking", "entered into for each loop: "+i+tracking);
              //  Log.d("Order tracking", "entered into for each loop: "+tracking);
            }

        }

        Log.d("OrderTRacking", "onCreate: "+tot);

        verticalStepView.setStepsViewIndicatorComplectingPosition(tot- trackingStatus.size())
                .reverseDraw(false)

                .setLinePaddingProportion(1.90f)
                .setStepsViewIndicatorCompletedLineColor(Color.parseColor("#008000"))
                .setStepViewComplectedTextColor(Color.parseColor("#008000"))
                .setStepViewUnComplectedTextColor(Color.parseColor("#000000"))
                .setStepsViewIndicatorUnCompletedLineColor(Color.parseColor("#000000"))
                .setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(this, R.drawable.complete_icon))
                .setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(this, R.drawable.attention_icon))
                .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(this, R.drawable.ic_radio_button_checked_black_24dp));

    }
}
