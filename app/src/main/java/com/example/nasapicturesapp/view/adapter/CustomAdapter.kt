package com.example.nasapicturesapp.view.adapter

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.nasapicturesapp.R
import com.example.nasapicturesapp.data.model.DataModel
import com.example.nasapicturesapp.view.adapter.CustomAdapter.MyViewHolder
import com.squareup.picasso.Picasso
import java.util.*

class CustomAdapter(private val mContext: Activity, private val mDataModelList: ArrayList<DataModel>?,
                    private val mListener: OnItemClickListener) : RecyclerView.Adapter<MyViewHolder?>() {

    interface OnItemClickListener {
        fun onItemClick(dataModel: DataModel)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.list_single_item, viewGroup, false))
    }

    override fun onBindViewHolder(myViewHolder: MyViewHolder, i: Int) {
        Picasso.get().load(mDataModelList!![i].getHdurl())
                .error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher)
                .into(myViewHolder.ivPicture)
        myViewHolder.attachListener(mDataModelList[i], mListener)
    }

    override fun getItemCount(): Int {
        return mDataModelList!!.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ivPicture: ImageView? = itemView.findViewById(R.id.iv_picture)

        fun attachListener(dataModel: DataModel, listener: OnItemClickListener) {
            itemView.setOnClickListener { listener.onItemClick(dataModel) }
        }
    }
}