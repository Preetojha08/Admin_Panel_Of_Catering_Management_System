package com.creatures.mycateringadminpanel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.github.ybq.android.spinkit.style.Wave;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LoginUserDataActivity extends AppCompatActivity {

    RecyclerView login_users_recycler_view;
    private static final String URL_PRODUCTS ="https://preetojhadatabasetrail.000webhostapp.com/catering_project/login_usersdata.php";

    List<Model_Class> users_login_data_list;
    ImageView login_empty_imageview;

    SwipeRefreshLayout srfl_login_userdatabase;
    RecyclerviewAdapter recyclerviewAdapter;
    int a = 0;

    int layoutno = 20;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user_data);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_login_users_database);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("   Users Login Information");

        progressBar = (ProgressBar)findViewById(R.id.spin_kit_progress_bar);
        Sprite wave = new DoubleBounce();
        progressBar.setIndeterminateDrawable(wave);

        progressBar.setVisibility(View.VISIBLE);

        login_empty_imageview=(ImageView)findViewById(R.id.image_view_login_users_database);
        login_empty_imageview.setVisibility(View.GONE);

        login_users_recycler_view=(RecyclerView) findViewById(R.id.recycler_view_login_user_data);
        login_users_recycler_view.setHasFixedSize(true);
        login_users_recycler_view.setLayoutManager(new LinearLayoutManager(this));
        users_login_data_list = new ArrayList<>();

        srfl_login_userdatabase=(SwipeRefreshLayout)findViewById(R.id.login_users_database_swipe_refresh_layout);

        srfl_login_userdatabase.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                clearData();
                progressBar.setVisibility(View.VISIBLE);
                recyclerviewAdapter.notifyDataSetChanged();
                srfl_login_userdatabase.setRefreshing(false);
                loadUsersLoginData();
                a++;
            }
        });

        if (a==0)
        {
            loadUsersLoginData();
        }


    }

    public void clearData() {
        users_login_data_list.clear(); // clear list
        recyclerviewAdapter.notifyDataSetChanged(); // let your adapter know about the changes and reload view.
    }

    private void loadUsersLoginData()
    {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_PRODUCTS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    progressBar.setVisibility(View.GONE);
                    JSONArray array = new JSONArray(response);

                    for (int i = 0; i < array.length(); i++) {

                        //getting product object from json array
                        JSONObject user_data_obj = array.getJSONObject(i);

                        //adding the product to product list
                        users_login_data_list.add(new Model_Class(
                                user_data_obj.getInt("id"),
                                user_data_obj.getString("email"),
                                user_data_obj.getString("username"),
                                user_data_obj.getString("login_date")

                        ));
                    }
                    /*ProductsAdapter adapter = new ProductsAdapter(MainActivity.this, productList);
                    recyclerView.setAdapter(adapter);*/

                    recyclerviewAdapter = new RecyclerviewAdapter(LoginUserDataActivity.this, users_login_data_list,layoutno);
                    login_users_recycler_view.setAdapter(recyclerviewAdapter);
                    login_empty_imageview.setVisibility(View.GONE);

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