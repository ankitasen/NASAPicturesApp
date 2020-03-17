package com.example.nasapicturesapp.view

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.example.nasapicturesapp.R
import com.example.nasapicturesapp.data.model.DataModel
import com.example.nasapicturesapp.view.adapter.CustomAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), AsyncResponse {
    private var mRvPictures: RecyclerView? = null
    private var mDataModelList: ArrayList<DataModel>? = null
    private var mProgressBar: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DownloadTask(fileName = this.assets.open("data.json"), delegate = this).execute()
        setInitViews()
//        loadJsonData()
//        setEventListeners()
    }

    override fun processFinish(dataModelList: ArrayList<DataModel>?) {
        mDataModelList = dataModelList
        setEventListeners()
    }

    /*private fun loadJsonData(): ArrayList<DataModel>? {
        mDataModelList = ArrayList()
        val gson = Gson()
        try {
            val reader = BufferedReader(InputStreamReader(assets.open("data.json")))
            val jsonElement = StringBuilder()
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                jsonElement.append(line)
            }
            //Create generic type
            val type = object : TypeToken<ArrayList<DataModel?>?>() {}.type
            mDataModelList = gson.fromJson(jsonElement.toString(), type)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return mDataModelList
    }*/

    private fun setInitViews() {
        mRvPictures = findViewById(R.id.rv_pictures)
        mProgressBar = findViewById(R.id.progress_bar)
        mProgressBar?.visibility = View.VISIBLE
        mRvPictures?.visibility = View.INVISIBLE
    }

    private fun setEventListeners() {
//        val mDataModelListSorted = mDataModelList?.sortedWith(compareBy { it.getDate() }) as ArrayList<DataModel>?
        mProgressBar?.visibility = View.INVISIBLE
        mRvPictures?.visibility = View.VISIBLE
        val layoutManager = GridLayoutManager(this, 2)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        mRvPictures?.layoutManager = layoutManager
        val customAdapter = CustomAdapter(this, mDataModelList, object : CustomAdapter.OnItemClickListener {
            override fun onItemClick(dataModel: DataModel) {
                Toast.makeText(applicationContext, dataModel.getTitle(), Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@MainActivity, DetailedViewActivity::class.java)
                        .putExtra("Data", dataModel))
//                finish()
            }
        })
        mRvPictures?.adapter = customAdapter
        customAdapter.notifyDataSetChanged()
    }
}

interface AsyncResponse {
    fun processFinish(dataModelList: ArrayList<DataModel>?)
}

class DownloadTask(private val fileName: InputStream, delegate: AsyncResponse): AsyncTask<String, Void, StringBuilder>() {
    private var mDataModelList: ArrayList<DataModel>? = null
    private val gson = Gson()
    val mDelegate: AsyncResponse = delegate

    override fun doInBackground(vararg params: String?): StringBuilder {
        mDataModelList = ArrayList()
        val jsonElement = StringBuilder()
        try {
            val reader = BufferedReader(InputStreamReader(fileName))
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                jsonElement.append(line)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return jsonElement
    }

    override fun onPostExecute(result: StringBuilder) {
        super.onPostExecute(result)

        //Create generic type
        val type = object : TypeToken<ArrayList<DataModel>?>() {}.type
        mDataModelList = gson.fromJson(result.toString(), type)
        mDelegate.processFinish(mDataModelList)
    }
}