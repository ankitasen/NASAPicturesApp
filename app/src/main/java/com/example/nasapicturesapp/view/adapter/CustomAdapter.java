package com.example.nasapicturesapp.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.nasapicturesapp.R;
import com.example.nasapicturesapp.data.model.DataModel;
import com.example.nasapicturesapp.view.DetailedViewActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private LayoutInflater mLayoutInflater;
    private ArrayList<DataModel> mDataModelList;

    public CustomAdapter(Context context, ArrayList<DataModel> dataModelList) {
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mDataModelList = dataModelList;
    }

    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = mLayoutInflater.inflate(R.layout.list_single_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, int i) {
        Picasso.get().load(mDataModelList.get(i).getHdurl()).into(myViewHolder.ivPicture);
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext().startActivity(new Intent(v.getContext(), DetailedViewActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataModelList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView ivPicture;
        MyViewHolder(@NonNull View itemView) {
            super(itemView);

            ivPicture = itemView.findViewById(R.id.iv_picture);
        }
    }
}
