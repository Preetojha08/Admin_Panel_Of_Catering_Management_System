package com.creatures.mycateringadminpanel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
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
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ThaliDataActivity extends AppCompatActivity {

    RecyclerviewAdapter recyclerviewAdapter;
    RecyclerView recyclerview_thali_db;

    FloatingActionButton fab_thalis_db;
    SwipeRefreshLayout thali_srl;
    ProgressBar progressbar_thali_db;

    private static final String URL_PRODUCTS ="https://preetojhadatabasetrail.000webhostapp.com/catering_project/fetch_thali_data.php";
    List<Model_Class> thalis_data_list;
    int layout_no = 40;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thali_data);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_thalis_database);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("   Thalis Information");

        recyclerview_thali_db=(RecyclerView) findViewById(R.id.recycler_view_thalis_data);
        recyclerview_thali_db.setHasFixedSize(true);
        recyclerview_thali_db.setLayoutManager(new LinearLayoutManager(this));

        fab_thalis_db=(FloatingActionButton)findViewById(R.id.floating_action_button_thalis_data);
        fab_thalis_db.setImageTintMode(null);

        fab_thalis_db.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ThaliDataActivity.this,AddThaliDetailActivity.class));
            }
        });

        thalis_data_list = new ArrayList<>();

        progressbar_thali_db=(ProgressBar)findViewById(R.id.spin_kit_progress_bar_thali_data);
        Sprite wave = new DoubleBounce();
        progressbar_thali_db.setIndeterminateDrawable(wave);
        progressbar_thali_db.setVisibility(View.VISIBLE);

        thali_srl=(SwipeRefreshLayout)findViewById(R.id.thalis_database_swipe_refresh_layout);
        thali_srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                thali_srl.setRefreshing(true);
                loadThalisData();
                thali_srl.setRefreshing(false);
            }
        });

        loadThalisData();

    }

    private void loadThalisData()
    {
        progressbar_thali_db.setVisibility(View.VISIBLE);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_PRODUCTS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);

                    for (int i = 0; i < array.length(); i++) {

                        //getting product object from json array
                        JSONObject thalis_data_obj = array.getJSONObject(i);

                        //adding the product to product list
                        thalis_data_list.add(new Model_Class(
                                thalis_data_obj.getInt("thali_id"),
                                thalis_data_obj.getString("thali_name"),
                                thalis_data_obj.getString("thali_desc"),
                                thalis_data_obj.getString("thali_img_link")

                        ));
                    }
                    /*ProductsAdapter adapter = new ProductsAdapter(MainActivity.this, productList);
                    recyclerView.setAdapter(adapter);*/

                    recyclerviewAdapter = new RecyclerviewAdapter(ThaliDataActivity.this, thalis_data_list,layout_no);
                    recyclerview_thali_db.setAdapter(recyclerviewAdapter);
                    progressbar_thali_db.setVisibility(View.GONE);

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