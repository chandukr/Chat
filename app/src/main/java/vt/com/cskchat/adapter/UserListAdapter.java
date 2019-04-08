package vt.com.cskchat.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import vt.com.cskchat.R;
import vt.com.cskchat.model.UserModel;
import vt.com.cskchat.view.CustomItemClickListener;
import vt.com.cskchat.view.UserData;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.CustomeViewHolder> {
    Context context;
    ArrayList<UserModel> name;

    CustomItemClickListener listenerChatFirebase;

    public UserListAdapter(Context context, ArrayList<UserModel> name, CustomItemClickListener listenerChatFirebase) {
        this.context = context;
        this.name = name;
        this.listenerChatFirebase = listenerChatFirebase;
    }


    @NonNull
    @Override
    public CustomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.userlist, parent, false);
        final CustomeViewHolder mCustomeViewHolder = new CustomeViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenerChatFirebase.onItemClick(v, mCustomeViewHolder.getPosition());
            }
        });
        // return new CustomeViewHolder(view);
        return mCustomeViewHolder;


    }

    @Override
    public void onBindViewHolder(@NonNull final CustomeViewHolder holder, final int position) {

        holder.mUserName.setText(name.get(position).getName());

        Log.e("aa", "onBindViewHolder: "+ name.get(position).getName());
        holder.mEmail.setText(name.get(position).getPhoto_profile());
        holder.mPass.setText(name.get(position).getId());


    }


    @Override
    public int getItemCount() {
        return name.size();
    }

    public class CustomeViewHolder extends RecyclerView.ViewHolder {
        TextView mUserName,mEmail,mPass;


        public CustomeViewHolder(View itemView) {
            super(itemView);
            mUserName = itemView.findViewById(R.id.tvUserName1);
            mEmail = itemView.findViewById(R.id.tvEmail2);
            mPass = itemView.findViewById(R.id.tvPass1);

        }
    }
}
