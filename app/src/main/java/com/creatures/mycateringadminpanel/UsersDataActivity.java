package com.creatures.mycateringadminpanel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UsersDataActivity extends AppCompatActivity {

    RecyclerView recycler_view_usersdata;
    private static final String URL_PRODUCTS ="https://preetojhadatabasetrail.000webhostapp.com/signup_login_test/fetch_data.php";

    List<Model_Class> users_data_list;
    ImageView empty_imageview;

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

        empty_imageview=(ImageView)findViewById(R.id.image_view_users_database);
        empty_imageview.setVisibility(View.GONE);

        users_data_list = new ArrayList<>();

        int a = getItemCount();

        if (a == 0)
        {
            empty_imageview.setVisibility(View.VISIBLE);
            Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
        }

        loadUsersData();
        
    }

    public int getItemCount() {
        return (null != users_data_list ? users_data_list.size() : 0);
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

                    RecyclerviewAdapter recyclerviewAdapter = new RecyclerviewAdapter(UsersDataActivity.this, users_data_list);
                    recycler_view_usersdata.setAdapter(recyclerviewAdapter);
                    empty_imageview.setVisibility(View.GONE);
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