package com.example.nasapicturesapp.view;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.nasapicturesapp.R;
import com.example.nasapicturesapp.data.model.DataModel;
import com.example.nasapicturesapp.view.adapter.CustomAdapter;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRvPictures;
    private ArrayList<DataModel> mDataModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadJsonData();
        setInitViews();
        setViewListeners();
    }

    private ArrayList<DataModel> loadJsonData() {
        mDataModelList = new ArrayList<>();
        Gson gson = new Gson();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(getAssets().open("data.json")));
            StringBuilder jsonElement = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonElement.append(line);
            }
            //Create generic type
            Type type = new TypeToken<ArrayList<DataModel>>() {}.getType();
            mDataModelList = gson.fromJson(jsonElement.toString(), type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mDataModelList;
    }

    private void setInitViews() {
        mRvPictures = findViewById(R.id.rv_pictures);
    }

    private void setViewListeners() {
        CustomAdapter customAdapter = new CustomAdapter(this, mDataModelList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mRvPictures.setLayoutManager(layoutManager);
        mRvPictures.setAdapter(customAdapter);
        customAdapter.notifyDataSetChanged();
    }
}
