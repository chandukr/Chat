package vt.com.cskchat.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import vt.com.cskchat.R;

public class testAdapter extends RecyclerView.Adapter<testAdapter.CustomeViewHolder> {
    Context context;
    ArrayList<String> name;
    String sName;


    public testAdapter(Context context, ArrayList<String> name) {
        this.context = context;
        this.name = name;
    }


    @NonNull
    @Override
    public CustomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.test_item, parent, false);
        return new CustomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CustomeViewHolder holder, final int position) {
        holder.mName.setText(name.get(position));


    }


    @Override
    public int getItemCount() {
        return name.size();
    }

    public class CustomeViewHolder extends RecyclerView.ViewHolder {
        TextView mName,mId,mChat;


        public CustomeViewHolder(View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.name);
            mId = itemView.findViewById(R.id.id);
            mChat = itemView.findViewById(R.id.chat);

        }
    }
}
