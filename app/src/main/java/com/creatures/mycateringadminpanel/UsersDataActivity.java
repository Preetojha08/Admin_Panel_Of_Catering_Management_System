package com.creatures.mycateringadminpanel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

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

public class UsersDataActivity extends AppCompatActivity {

    RecyclerView recycler_view_usersdata;
    private static final String URL_PRODUCTS ="https://preetojhadatabasetrail.000webhostapp.com/signup_login_test/fetch_data.php";
    SwipeRefreshLayout users_data_swipeRefreshLayout;
    List<Model_Class> users_data_list;
    ImageView empty_imageview;

    int layout_no = 10;

    FloatingActionButton users_data_fab;
    ProgressBar users_data_progressBar;
    RecyclerviewAdapter recyclerviewAdapter;

    int new_a = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_data);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_users_database);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("   Users Information");

        recycler_view_usersdata=(RecyclerView) findViewById(R.id.recycler_view_user_data);
        recycler_view_usersdata.setHasFixedSize(true);
        recycler_view_usersdata.setLayoutManager(new LinearLayoutManager(this));

        users_data_progressBar=(ProgressBar)findViewById(R.id.spin_kit_progress_bar_users_data);
        Sprite wave = new DoubleBounce();
        users_data_progressBar.setIndeterminateDrawable(wave);
        users_data_progressBar.setVisibility(View.VISIBLE);

        users_data_swipeRefreshLayout=(SwipeRefreshLayout)findViewById(R.id.users_database_swipe_refresh_layout);

        users_data_fab=(FloatingActionButton)findViewById(R.id.floating_action_button_users_data);
        users_data_fab.setImageTintMode(null);

        users_data_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UsersDataActivity.this,AddUserActivity.class);
                startActivity(i);
            }
        });

        empty_imageview=(ImageView)findViewById(R.id.image_view_users_database);
        empty_imageview.setVisibility(View.GONE);

        users_data_list = new ArrayList<>();

        users_data_swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                clearData();
                users_data_progressBar.setVisibility(View.VISIBLE);
                recyclerviewAdapter.notifyDataSetChanged();
                users_data_swipeRefreshLayout.setRefreshing(false);
                loadUsersData();
                new_a++;
            }
        });

        if (new_a == 0)
        {
            loadUsersData();
        }

        
    }


    public void clearData() {
        users_data_list.clear(); // clear list
        recyclerviewAdapter.notifyDataSetChanged(); // let your adapter know about the changes and reload view.
    }

    private void loadUsersData()
    {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_PRODUCTS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);

                    for (int i = 0; i < array.length(); i++) {

                        //getting product object from json array
                        JSONObject user_data_obj = array.getJSONObject(i);

                        //adding the product to product list
                        users_data_list.add(new Model_Class(
                                user_data_obj.getInt("id"),
                                user_data_obj.getString("username"),
                                user_data_obj.getString("password"),
                                user_data_obj.getString("email"),
                                user_data_obj.getString("mobile")

                        ));
                    }
                    /*ProductsAdapter adapter = new ProductsAdapter(MainActivity.this, productList);
                    recyclerView.setAdapter(adapter);*/

                    recyclerviewAdapter = new RecyclerviewAdapter(UsersDataActivity.this, users_data_list,layout_no);
                    recycler_view_usersdata.setAdapter(recyclerviewAdapter);
                    users_data_progressBar.setVisibility(View.GONE);
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