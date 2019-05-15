package com.android.rvdynamically;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.rvdynamically.model.User;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.UserViewHolder>{

    //a menu item view type
    private static final int MENU_ITEM_VIEW_TYPE = 0;

    // the native express ad view type
    private static final int NATIVE_EXPRESS_AD_VIEW_TYPE = 1;

    //An activity context
    private final Context mContext;
    private final ArrayList<User> mRecyclerViewUsers;

    public RecyclerViewAdapter(Context context, ArrayList<User> recyclerViewUsers) {
        this.mRecyclerViewUsers = recyclerViewUsers;
        this.mContext = context;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{

        private TextView user_id;
        private TextView user_name;
        private TextView user_email_id;

        public UserViewHolder(View view) {
            super(view);
            user_id = view.findViewById(R.id.user_id);
            user_name = view.findViewById(R.id.user_name);
            user_email_id = view.findViewById(R.id.user_email_id);
        }
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_container,viewGroup,false);

        UserViewHolder viewHolder = new UserViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.UserViewHolder viewHolder, int position) {

        viewHolder.user_id.setText(mRecyclerViewUsers.get(position).getUserId());
        viewHolder.user_name.setText(mRecyclerViewUsers.get(position).getUserName());
        viewHolder.user_email_id.setText(mRecyclerViewUsers.get(position).getUserEmailId());
    }

    @Override
    public int getItemCount() {
        return mRecyclerViewUsers.size();
    }
}
