package com.creatures.mycateringadminpanel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

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

        users_data_list = new ArrayList<>();

        loadUsersData();

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