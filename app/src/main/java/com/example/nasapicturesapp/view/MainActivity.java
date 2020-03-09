package com.example.nasapicturesapp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.nasapicturesapp.R;
import com.example.nasapicturesapp.data.model.DataModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private ListView mListViewPictures;
    private DataModel mDataModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadJsonFile();
        setInitViews();
        setViewListerners();
    }

    private String loadJsonFile() {
        String json = null;
        try {
            InputStream is = getApplicationContext().getAssets().open("/asset/data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    private String loadJsonData(DataModel dataModel) {
        try {
            JSONArray jsonArray = new JSONArray(loadJsonFile());
            ArrayList<HashMap<String, String>> formList = new ArrayList<>();
            HashMap<String, String> m_li;

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jo_inside = jsonArray.getJSONObject(i);
                jo_inside.put("copyright", dataModel.getCopyright());
                jo_inside.put("date", dataModel.getDate());
                jo_inside.put("explanation", dataModel.getExplanation());
                jo_inside.put("hdUrl", dataModel.getHdurl());
                jo_inside.put("mediaType", dataModel.getMediaType());
                jo_inside.put("serviceVersion", dataModel.getServiceVersion());
                jo_inside.put("title", dataModel.getTitle());
                jo_inside.put("url", dataModel.getUrl());
                /*Log.d("Details-->", jo_inside.getString("formule"));
                String formula_value = jo_inside.getString("formule");
                String url_value = jo_inside.getString("url");

                //Add your values in your `ArrayList` as below:
                m_li = new HashMap<String, String>();
                m_li.put("formule", formula_value);
                m_li.put("url", url_value);

                formList.add(m_li);*/
            }
            return jsonArray.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void setInitViews() {
        mListViewPictures = findViewById(R.id.lv_pictures);
//        mImageViewPicture = findViewById(R.id.iv_picture);
    }

    private void setViewListerners() {
        CustomAdapter<ArrayList<String>> customAdapter = new CustomAdapter<ArrayList<String>>(this, R.layout.list_single_item, mDataModel.getHdurl());
        mListViewPictures.setAdapter(customAdapter);
        mListViewPictures.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ImageView picture = (ImageView) parent.getAdapter().getItem(position);

            }
        });
    }
}
