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

import com.dmantz.ecommerceapp.Activities.ItemActivity;
import com.dmantz.ecommerceapp.R;
import com.dmantz.ecommerceapp.model.Catlog;
import com.dmantz.ecommerceapp.model.Product;
import com.dmantz.ecommerceapp.model.ProductList;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    public static final String TAG = RecyclerViewAdapter.class.getSimpleName();
    private ProductList mproductList;
    private Context mcontext;
    private List<Product> productList;
    private Catlog mCatlog;


    public RecyclerViewAdapter(ProductList productList, Context context) {
        mproductList = productList;
        mcontext = context;

        Log.d("TAG", "entered into add method");
    }


    public RecyclerViewAdapter(List<Product> mproducts, Context context) {

        productList = mproducts;
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

            holder.mProductName.setText(productList.get(position).getProductName());
            //  holder.mProductName.setText(mproductList.getProductsList().get(position).getItemName());
            // holder.mProductSize.setText(mproductList.getProductsList().get(position).getItemSize());
            // holder.mProductSize.setText(mCatlog.getProducts().get(position).getProductManufacturerName());
            holder.mProductPrice.setText(String.valueOf(productList.get(position).getProductSkus().get(0).getPrice()));
            // holder.mProductPrice.setText(Double.toString(mCatlog.getProducts().get(position).getProductSkus().get(position).getPrice()));
            Picasso.get().load(productList.get(position).getProductSkus().get(0).getImage()).fit().into(holder.mProductImage);
            //  Log.d("picasso", mproductList.getProductsList().get(position).getItemImageUrl());
            //holder.mProductImage.setImageURI(mproductList.getProductsList().get(position).getItemImage());


        } catch (Exception e) {
            e.printStackTrace();
        }


        //  Uri uri = Uri.parse(String.valueOf(mproductList.getProductsList().get(position).getItemImage()));
        //  holder.mProductImage.setImageURI(uri);


        holder.cv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d("recyclicareview onlick ", "onClick: entered into onclick ");
                Intent intent = new Intent(mcontext, ItemActivity.class);
                intent.putExtra("productId", productList.get(position).getProductId());



            /*    intent.putExtra("productName", mproductList.getProductsList().get(position).getItemName());
                intent.putExtra("productPrice", mproductList.getProductsList().get(position).getItemPrice());
                intent.putExtra("productSize", mproductList.getProductsList().get(position).getItemSize());
                intent.putExtra("productId", mproductList.getProductsList().get(position).getItemId());
                intent.putExtra("productDescription", mproductList.getProductsList().get(position).getDescription());
                intent.putExtra("productUrl", mproductList.getProductsList().get(position).getItemImageUrl());

            */
                // Log.d("on clicke recyclerview", "onClick: " + mCatlog.getProducts().get(position).getProductId());


                mcontext.startActivity(intent);
            }
        });


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {


        return productList.size();
        //mproductList.getProductsList().size();
    }

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


}

