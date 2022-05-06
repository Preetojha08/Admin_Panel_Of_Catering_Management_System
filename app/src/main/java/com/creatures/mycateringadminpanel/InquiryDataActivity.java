package com.creatures.mycateringadminpanel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class InquiryDataActivity extends AppCompatActivity {

    RecyclerviewAdapter recyclerviewAdapter;
    RecyclerView recyclerview_inquiry_data;
    List<Model_Class> inquiry_data_list;
    ProgressBar inquiry_data_progressBar;
    SwipeRefreshLayout inquiry_srl;
    private static final String URL_PRODUCTS ="https://preetojhadatabasetrail.000webhostapp.com/catering_project/fetch_all_inquiry.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inquiry_data);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_inquiry_database);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("   Inquiry Information");

        recyclerview_inquiry_data=(RecyclerView) findViewById(R.id.recycler_view_inquiry_data);
        recyclerview_inquiry_data.setHasFixedSize(true);
        recyclerview_inquiry_data.setLayoutManager(new LinearLayoutManager(this));

        inquiry_data_progressBar=(ProgressBar)findViewById(R.id.spin_kit_progress_bar_inquiry_data);
        Sprite wave = new DoubleBounce();
        inquiry_data_progressBar.setIndeterminateDrawable(wave);
        inquiry_data_progressBar.setVisibility(View.VISIBLE);

        inquiry_data_list = new ArrayList<>();

        inquiry_srl=(SwipeRefreshLayout)findViewById(R.id.inquiry_database_swipe_refresh_layout);
        inquiry_srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                inquiry_srl.setRefreshing(true);
                LoadInquiryData();
                inquiry_srl.setRefreshing(false);
            }
        });


        LoadInquiryData();

    }

    private void LoadInquiryData()
    {
        inquiry_data_progressBar.setVisibility(View.VISIBLE);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_PRODUCTS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);

                    for (int i = 0; i < array.length(); i++) {

                        //getting product object from json array
                        JSONObject inquiry_data_obj = array.getJSONObject(i);

                        //adding the product to product list
                        inquiry_data_list.add(new Model_Class(
                                inquiry_data_obj.getInt("user_id"),
                                inquiry_data_obj.getInt("inquiry_id"),
                                inquiry_data_obj.getString("inquiry_user_name"),
                                inquiry_data_obj.getString("inquiry_thalis"),
                                inquiry_data_obj.getString("inquiry_category_food"),
                                inquiry_data_obj.getString("inquiry_user_mail")

                        ));
                    }
                    /*ProductsAdapter adapter = new ProductsAdapter(MainActivity.this, productList);
                    recyclerView.setAdapter(adapter);*/

                    recyclerviewAdapter = new RecyclerviewAdapter(InquiryDataActivity.this, inquiry_data_list,50);
                    recyclerview_inquiry_data.setAdapter(recyclerviewAdapter);
                    inquiry_data_progressBar.setVisibility(View.GONE);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        Volley.newRequestQueue(this).add(stringRequest);
    }
}