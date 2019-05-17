package com.dmantz.ecommerceapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.dmantz.ecommerceapp.ECApplication;
import com.dmantz.ecommerceapp.R;
import com.dmantz.ecommerceapp.model.OrderItem;

import java.util.List;

public class CartViewAdapter extends RecyclerView.Adapter<CartViewAdapter.ViewHolder> {


    public static final String TAG = CartViewAdapter.class.getSimpleName();
    Context context;
    ECApplication ECApp;
    private List<OrderItem> orderItemList;


    public CartViewAdapter(Context context, List<OrderItem> porderItemList) {
        this.context = context;
        this.orderItemList = porderItemList;

    }

    @NonNull
    @Override
    public CartViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_cartview_recyclerview, parent, false);
        ViewHolder itemHolderObj = new ViewHolder(v);

        return itemHolderObj;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.itemName.setText(orderItemList.get(position).getProductName());
        holder.itemPrice.setText(Double.toString(orderItemList.get(position).getPrice()));

        //holder.itemPrice.setText(productInfoObj.getProductSkus().get(position).getPrice());
        //holder.itemName.setText(productInfoObj.getProductName());
        //holder.itemName.setText(orderObj.getOrderItemList().get(position).getMrpPrice());
        //holder.itemPrice.setText(orderObj.getOrderItemList().get(position).getProductSku());
        //Picasso.get().load(orderObj.getOrderArrayList().get(position).getProductImage()).fit().into(holder.itemImage)

        ECApp = (ECApplication) context.getApplicationContext();
        ECApp.orderClientObj.getCurrentOrder().calculateTotals();

        holder.elegantNumberButton.setNumber(Integer.toString(orderItemList.get(position).getQuantity()));
        holder.totalPrice.setText(Integer.toString((int) ECApp.orderClientObj.getCurrentOrder().getOrderItemList().get(position).getTotalPrice()));


        holder.elegantNumberButton.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
            @Override
            public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {


                for (OrderItem orderitem : orderItemList) {
                    if (orderitem.equals(orderItemList)) {
                        ECApp.orderClientObj.updateQuantityBE(ECApp.orderClientObj.getOrderId(), orderItemList.get(position).getProductSku(), newValue);
                    }
                }
                holder.totalPrice.setText(Integer.toString(ECApp.orderClientObj.getCurrentOrder().getOrderItemList().iterator().next().getCartTotalPrice()));
            }
        });



        /*

            holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @
            public void onClick(View view) {Override



                delete(position);

                Toast.makeText(context, "Deleted succesfully",
                        Toast.LENGTH_LONG).show();


            }
        }); */

    }


    @Override
    public int getItemCount() {
        return orderItemList.size();
    }

    /*   public void delete(int position) {

           orderItemList.remove(position);
           notifyItemRemoved(position);
           notifyItemRangeChanged(position,orderItemList.size());

           Log.d(TAG, "delete: "+orderItemList.size());
       }
   */
    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView itemName;
        public TextView itemPrice;
        public ImageView itemImage;
        public ElegantNumberButton elegantNumberButton;
        public Button deleteBtn;
        public TextView totalPrice;
        public TextView cartTotal;


        public ViewHolder(View itemView) {
            super(itemView);

            itemName = itemView.findViewById(R.id.cartView_itemName);
            itemPrice = itemView.findViewById(R.id.cartView_itemPrice);
            itemImage = itemView.findViewById(R.id.cartView_image);
            elegantNumberButton = itemView.findViewById(R.id.cardView_elegentBtn);
            //   deleteBtn = itemView.findViewById(R.id.cart_delete_btn);

            totalPrice = itemView.findViewById(R.id.total);


        }
    }


}
