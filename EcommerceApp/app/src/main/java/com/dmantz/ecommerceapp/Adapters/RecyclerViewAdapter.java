package com.dmantz.ecommerceapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dmantz.ecommerceapp.ItemActivity;
import com.dmantz.ecommerceapp.model.Catlog;
import com.dmantz.ecommerceapp.model.Product;
import com.dmantz.ecommerceapp.model.ProductList;
import com.dmantz.ecommerceapp.R;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {


    private ProductList mproductList;
    private Context mcontext;
    private ArrayList<Product> mArrayList;
    private Catlog mCatlog;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mProductName;

        public TextView mProductSize;
        public TextView mProductPrice;
        public ImageView mProductImage;
        CardView cv;

        public ViewHolder(View itemView) {
            super(itemView);

            mProductName = itemView.findViewById(R.id.productName);
            mProductSize = itemView.findViewById(R.id.productSize);
            mProductPrice = itemView.findViewById(R.id.productPrice);
            mProductImage = itemView.findViewById(R.id.productImage);
            cv = itemView.findViewById(R.id.card_view);
        }
    }


    public RecyclerViewAdapter(ProductList productList, Context context) {
        mproductList = productList;
        mcontext = context;

        Log.d("TAG", "entered into add method");
    }

    public RecyclerViewAdapter(Catlog catlog, Context context) {

        mCatlog = catlog;
        mcontext = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_activity, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        try {


            holder.mProductName.setText(mCatlog.getProducts().get(position).getProductName());
            //  holder.mProductName.setText(mproductList.getProductList().get(position).getItemName());
            // holder.mProductSize.setText(mproductList.getProductList().get(position).getItemSize());
            holder.mProductSize.setText(mCatlog.getProducts().get(position).getProductManufacturerName());
            // holder.mProductPrice.setText(String.valueOf(mproductList.getProductList().get(position).getItemPrice()));
            holder.mProductPrice.setText(mCatlog.getProducts().get(position).getProductSkus().get(position).getPrice());
            //Picasso.get().load(mproductList.getProductList().get(position).getItemImageUrl()).fit().into(holder.mProductImage);
            Log.d("picasso", mproductList.getProductList().get(position).getItemImageUrl());
            //holder.mProductImage.setImageURI(mproductList.getProductList().get(position).getItemImage());


        } catch (Exception e) {
            e.printStackTrace();
        }


        //  Uri uri = Uri.parse(String.valueOf(mproductList.getProductList().get(position).getItemImage()));
        //  holder.mProductImage.setImageURI(uri);


        holder.cv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d("recyclicareview onlick ", "onClick: entered into onclick ");
                Intent intent = new Intent(mcontext, ItemActivity.class);
                intent.putExtra("productId", mCatlog.getProducts().get(position).getProductId());
            /*    intent.putExtra("productName", mproductList.getProductList().get(position).getItemName());
                intent.putExtra("productPrice", mproductList.getProductList().get(position).getItemPrice());
                intent.putExtra("productSize", mproductList.getProductList().get(position).getItemSize());
                intent.putExtra("productId", mproductList.getProductList().get(position).getItemId());
                intent.putExtra("productDescription", mproductList.getProductList().get(position).getDescription());
                intent.putExtra("productUrl", mproductList.getProductList().get(position).getItemImageUrl());

            */
                Log.d("on clicke recyclerview", "onClick: " + mCatlog.getProducts().get(position).getProductId());


                mcontext.startActivity(intent);
            }
        });


    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mCatlog.getProducts().size();
        //mproductList.getProductList().size();
    }


}

