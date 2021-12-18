package com.example.translate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    private EditText editTextSearch;
    private RecyclerView recyclerView;
    private ArrayList<WordsModel> wordsModelArrayList;
    private WordAdapter wordAdapter;
    public String txtSearch;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        editTextSearch=findViewById(R.id.editTextSearch);
        wordsModelArrayList=new ArrayList<>();
        wordAdapter = new WordAdapter(wordsModelArrayList,this);
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(wordAdapter);
        getWordData();
        //txtSearch= editTextSearch.getText().toString();
        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filterWords(s.toString());
            }
        });

    }



    private void filterWords(String word) {
        ArrayList<WordsModel> filterList = new ArrayList<>();
        for (WordsModel item : wordsModelArrayList){
            if (item.getWord().toLowerCase().contains(word.toLowerCase())){
                filterList.add(item);
            }
        }
        if(filterList.isEmpty()){
            Toast.makeText(getApplicationContext(),"fail is Empty",Toast.LENGTH_SHORT).show();
        }
        else
        {
            wordAdapter.filterList(filterList);
        }
    }


    private void getWordData() {
        txtSearch= editTextSearch.getText().toString();
        //txtSearch="hi";
        String url = "https://one-api.ir/translate/?token=153856:61bba509e8ce37.54039805&action=google&lang=fa&q="+txtSearch;

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String dataArray=response.getString("result");
                    editTextSearch.setText(dataArray);
//                    for (int i = 0; i<dataArray.length(); i++) {
//                        JSONObject dataObject = dataArray.getJSONObject(i);
////                        String q=dataObject.getString("result");
//                        wordsModelArrayList.add(new WordsModel("q"));
//                    }
                    wordAdapter.notifyDataSetChanged();
                }
                catch (JSONException e){
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"fail catch",Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"fail",Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);

    }
}