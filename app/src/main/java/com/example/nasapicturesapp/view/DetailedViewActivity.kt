package com.example.nasapicturesapp.view

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.example.nasapicturesapp.R
import com.example.nasapicturesapp.data.model.DataModel

class DetailedViewActivity : AppCompatActivity() {
    private var mDataModel: DataModel? = null
    private var mTvTitle: TextView? = null
    private var mTvExplanation: TextView? = null
    private var mTvCopyright: TextView? = null
    private var mTvDate: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_detailed_view)
        mDataModel = intent.getSerializableExtra("Data") as DataModel
        setInitViews()
        setEventListeners()
    }

    private fun setInitViews() {
        mTvTitle = findViewById(R.id.tv_title)
        mTvExplanation = findViewById(R.id.tv_explanation)
        mTvCopyright = findViewById(R.id.tv_copyright)
        mTvDate = findViewById(R.id.tv_date)
    }

    private fun setEventListeners() {
        mTvTitle?.text = mDataModel?.getTitle()
        mTvExplanation?.text = mDataModel?.getExplanation()
        mTvCopyright?.text = mDataModel?.getCopyright()
        mTvDate?.text = mDataModel?.getDate()
    }
}
