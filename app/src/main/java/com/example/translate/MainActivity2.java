package com.example.translate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity2 extends AppCompatActivity {

    private EditText editTextSearch;
//    public String txtSearch;
    Context context;
    private TextView textViewResult;
    TextView textViewFrom,textViewTo;
    ImageButton imageButton_switch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        textViewFrom=findViewById(R.id.textViewFrom);
        textViewTo=findViewById(R.id.textViewTo);
        imageButton_switch=findViewById(R.id.imageButton_switch);

        editTextSearch=findViewById(R.id.editTextSearch);
        textViewResult=findViewById(R.id.textViewResult);
//        getWordData();
        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String txtSearch= editTextSearch.getText().toString();
//                Log.d("test",txtSearch);

                if(textViewFrom.getText().equals("Persian")){
                    getWordData_fa(txtSearch);
                }
                else{
                    getWordData_en(txtSearch);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        imageButton_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(textViewFrom.getText().equals("Persian")){
                    textViewFrom.setText("English");
                    textViewTo.setText("Persian");
                    editTextSearch.setText(textViewResult.getText());
                }
                else{
                    textViewFrom.setText("Persian");
                    textViewTo.setText("English");
                    editTextSearch.setText(textViewResult.getText());
                }
            }
        });
    }

    private void getWordData_en(String txtSearch) {

        String url = "https://one-api.ir/translate/?token=153856:61bba509e8ce37.54039805&action=google&lang=fa&q="+txtSearch;

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String result=response.getString("result");
                    textViewResult.setText(result);

                }
                catch (JSONException e){
                    e.printStackTrace();
//                    Toast.makeText(getApplicationContext(),"fail catch",Toast.LENGTH_SHORT).show();
                    textViewResult.setText("");
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
    private void getWordData_fa(String txtSearch){
        String url = "https://one-api.ir/translate/?token=153856:61bba509e8ce37.54039805&action=google&lang=en&q="+txtSearch;

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String result=response.getString("result");
                    textViewResult.setText(result);

                }
                catch (JSONException e){
                    e.printStackTrace();
//                    Toast.makeText(getApplicationContext(),"fail catch",Toast.LENGTH_SHORT).show();
                    textViewResult.setText("");
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