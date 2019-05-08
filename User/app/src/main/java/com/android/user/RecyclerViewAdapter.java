package com.android.user;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.UserViewHolder> {

    //a menu item view type
    private static final int MENU_ITEM_VIEW_TYPE = 0;

    // the native express ad view type
    private static final int NATIVE_EXPRESS_AD_VIEW_TYPE = 1;

    //An activity context
    private final Context mContext;

    private final List<User> mRecyclerViewUsers;

    public RecyclerViewAdapter(Context context, List<User> recyclerViewUsers) {
        this.mContext = context;
        this.mRecyclerViewUsers = recyclerViewUsers;
    }

    /*provides a reference to each view in the menu item view*/
    public class UserViewHolder extends RecyclerView.ViewHolder {

        private TextView user_Id;
        private TextView user_Name;
        private TextView user_EmailId;

        UserViewHolder(View view) {

            super(view);
            user_Id = view.findViewById(R.id.user_id);
            user_Name = view.findViewById(R.id.user_name);
            user_EmailId = view.findViewById(R.id.user_email_id);
        }
    }


    @NonNull
    @Override
    public RecyclerViewAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.menu_item_container, viewGroup, false);

        UserViewHolder userViewHolder = new UserViewHolder(v);
        return userViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder viewHolder, int position) {

        //add the user details to the user view
        viewHolder.user_Id.setText(mRecyclerViewUsers.get(position).getUserId());
        viewHolder.user_EmailId.setText(mRecyclerViewUsers.get(position).getUserEmailId());
        viewHolder.user_Name.setText(mRecyclerViewUsers.get(position).getUserName());

        //  userViewHolder.user_Name.setText(userItem.getUserName());
        //userViewHolder.user_EmailId.setText(userItem.getUserEmailId());


    }

    @Override
    public int getItemCount() {
        return mRecyclerViewUsers.size();
    }
}
