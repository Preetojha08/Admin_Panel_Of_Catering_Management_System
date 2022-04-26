package com.creatures.mycateringadminpanel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONArray;
import org.json.JSONObject;

public class UserDetailsActivity extends AppCompatActivity {

    TextInputEditText tiet_username,tiet_useremail,tiet_usernumber,tiet_useraccdate;
    TextView textView_username_heading;
    TextView textview_search,textview_event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_users_details);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("   User Details");

        tiet_username=(TextInputEditText)findViewById(R.id.text_input_edit_text_usersdata_user_name);
        tiet_useremail=(TextInputEditText)findViewById(R.id.text_input_edit_text_usersdata_user_mail);
        tiet_usernumber=(TextInputEditText)findViewById(R.id.text_input_edit_text_usersdata_user_mobile);
        tiet_useraccdate=(TextInputEditText)findViewById(R.id.text_input_edit_text_usersdata_user_createtiondate);

        textView_username_heading=(TextView)findViewById(R.id.text_view_username_profile_title);

        textview_search=(TextView)findViewById(R.id.text_view_searchview);
        textview_event=(TextView)findViewById(R.id.text_view_event_inquiry);

        Intent new_intent = getIntent();
        int id = new_intent.getIntExtra("User_id",0);
        String user_email_id = new_intent.getStringExtra("User_email");
        int s_inquiry = id/2;
        int einquiry = id/3;
        String user_id = String.valueOf(id);
        String search_inquiry = Integer.toString((int)s_inquiry);
        String event_inquiry = String.valueOf(einquiry);

        textview_search.setText(search_inquiry);
        textview_event.setText(event_inquiry);

       /* Toast.makeText(this, "Event :"+search_inquiry+"Kuch bhi: "+event_inquiry, Toast.LENGTH_SHORT).show();
        Log.i("ID wala data"," Main ID:"+id+" Search:"+search_inquiry+" Event: "+event_inquiry);
*/

        //Start ProgressBar first (Set visibility VISIBLE)

        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                //Starting Write and Read data with URL
                //Creating array for parameters
                String[] field = new String[2];
                field[0] = "user_id";
                field[1] = "email";

                //field[3] = "email";

                //Creating array for data
                String[] data = new String[2];
                data[0] = user_id;
                data[1] = user_email_id;

                //PutData putData = new PutData("https://preetojhadatabasetrail.000webhostapp.com/signup_login_test/signup.php", "POST", field, data);
                PutData putData = new PutData("https://preetojhadatabasetrail.000webhostapp.com/catering_project/fetch_single_user_details.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {

                        String result = putData.getResult();
                        if (result.equals("Some Thing is Wrong"))
                        {
                            Toast.makeText(UserDetailsActivity.this, ""+result, Toast.LENGTH_SHORT).show();
                        }
                        else if (result.equals("All fields are required"))
                        {
                            Toast.makeText(UserDetailsActivity.this, ""+result, Toast.LENGTH_SHORT).show();
                        }
                        else if (result.equals("Error:"))
                        {
                            Toast.makeText(UserDetailsActivity.this, ""+result, Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            String user_info = result;

                            try
                            {
                                JSONArray array = new JSONArray(user_info);
                                for(int i=0; i < array.length(); i++)
                                {
                                    JSONObject object = array.getJSONObject(i);
                                    String user_id = object.getString("id");
                                    String uname = object.getString("username");
                                    String password_log = object.getString("password");
                                    String mobile_phone = object.getString("mobile");
                                    String acc_date = object.getString("account_created_date");
                                    String email_id = object.getString("email");

                                    tiet_username.setText(uname);
                                    textView_username_heading.setText(uname);
                                    tiet_useremail.setText(email_id);
                                    tiet_usernumber.setText(mobile_phone);
                                    tiet_useraccdate.setText(acc_date);


                                }
                            }
                            catch (Exception exception)
                            {
                                exception.printStackTrace();
                            }
                        }
                        //End ProgressBar (Set visibility to GONE)
                        Log.i("PutData", result);
                    }
                }
                //End Write and Read data with URL
            }
        });
        //End of handler

    }
}