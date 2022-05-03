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
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class EventDataActivity extends AppCompatActivity {

    RecyclerView recycler_view_events_db;
    SwipeRefreshLayout srfl_events_db;
    RecyclerviewAdapter recyclerviewAdapter;
    private static final String URL_PRODUCTS ="https://preetojhadatabasetrail.000webhostapp.com/catering_project/fetch_event_data.php";
    List<Model_Class> events_data_list;
    int layout_no = 30;

    ProgressBar progressbar_event_db;

    FloatingActionButton fab_events_db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_data);
        
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_events_database);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("   Events Information");
        
        recycler_view_events_db=(RecyclerView) findViewById(R.id.recycler_view_events_data);
        recycler_view_events_db.setHasFixedSize(true);
        recycler_view_events_db.setLayoutManager(new LinearLayoutManager(this));

        fab_events_db=(FloatingActionButton)findViewById(R.id.floating_action_button_events_data);
        fab_events_db.setImageTintMode(null);

        progressbar_event_db = (ProgressBar)findViewById(R.id.spin_kit_progress_bar_events_data);
        Sprite wave = new DoubleBounce();
        progressbar_event_db.setIndeterminateDrawable(wave);
        progressbar_event_db.setVisibility(View.VISIBLE);

        srfl_events_db=(SwipeRefreshLayout) findViewById(R.id.events_database_swipe_refresh_layout);

        srfl_events_db.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //yaha par refresh hone ke baad ka code aaye ga
                srfl_events_db.setRefreshing(false);
            }
        });

        events_data_list = new ArrayList<>();

        loadEventdata();

    }

    private void loadEventdata()
    {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_PRODUCTS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);

                    for (int i = 0; i < array.length(); i++) {

                        //getting product object from json array
                        JSONObject events_data_obj = array.getJSONObject(i);

                        //adding the product to product list
                        events_data_list.add(new Model_Class(
                                events_data_obj.getInt("event_id"),
                                events_data_obj.getString("event_name"),
                                events_data_obj.getString("event_img_link")

                        ));
                    }
                    /*ProductsAdapter adapter = new ProductsAdapter(MainActivity.this, productList);
                    recyclerView.setAdapter(adapter);*/

                    recyclerviewAdapter = new RecyclerviewAdapter(EventDataActivity.this, events_data_list,layout_no);
                    recycler_view_events_db.setAdapter(recyclerviewAdapter);
                    progressbar_event_db.setVisibility(View.GONE);

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