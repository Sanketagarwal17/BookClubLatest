package com.example.android.bookclublatest.Faq.MVP;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.android.bookclublatest.Faq.Model.FAQModel;
import com.example.android.bookclublatest.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FAQActivity extends AppCompatActivity {
    @BindView(R.id.faq_recycler)
    RecyclerView recyclerView;

    FAQAdapter adapter;
    ArrayList<FAQModel> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
        ButterKnife.bind(this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getjson();
    }


    void getjson() {
        String json;

        InputStream inputStream = null;
        try {
            inputStream = getAssets().open("faq.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();

            json = new String(buffer, "utf-8");
            try {
                JSONArray array = new JSONArray(json);
                for (int i = 0; i < array.length(); i++) {
                    JSONObject object = array.getJSONObject(i);
                    String question = object.getString("question");
                    String answer = object.getString("answer");
                    arrayList.add(new FAQModel(question, answer));
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        adapter = new FAQAdapter(arrayList, this);
        recyclerView.setAdapter(adapter);
    }
}