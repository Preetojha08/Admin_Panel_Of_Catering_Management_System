package com.creatures.mycateringadminpanel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONArray;
import org.json.JSONObject;

public class EventDetailsActivity extends AppCompatActivity {

    EditText event_det_textview_heading,event_det_textview_items,event_det_textview_counters,event_det_textview_money;
    TextView event_desc;
    ImageView singleeventdetails_imageview;
    int id;
    String event_id;
    String event_name;
    String Event_Name,Event_Desc,Event_menu,Event_Counter,Event_Budget,Event_ImgLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_single_event_deatils);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("   Event Details");

        event_desc=(EditText)findViewById(R.id.textview_items_single_event_description);
        event_det_textview_heading=(EditText) findViewById(R.id.textview_heading_single_eventdeatils);
        event_det_textview_items=(EditText) findViewById(R.id.textview_items_single_eventdeatils);
        event_det_textview_counters=(EditText) findViewById(R.id.textview_counters_single_eventdeatils);
        event_det_textview_money=(EditText) findViewById(R.id.textview_money_single_eventdeatils);

        singleeventdetails_imageview=(ImageView) findViewById(R.id.image_view_single_event_details);

        id = getIntent().getExtras().getInt("event_id");
        event_name= getIntent().getExtras().getString("event_name");

        event_id = String.valueOf(id);



        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                //Starting Write and Read data with URL
                //Creating array for parameters
                String[] field = new String[2];
                field[0] = "event_id";
                field[1] = "event_name";

                //field[3] = "email";

                //Creating array for data
                String[] data = new String[2];
                data[0] = event_id;
                data[1] = event_name;

                //PutData putData = new PutData("https://preetojhadatabasetrail.000webhostapp.com/signup_login_test/signup.php", "POST", field, data);
                PutData putData = new PutData("https://preetojhadatabasetrail.000webhostapp.com/catering_project/fectch_single_event_details.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {

                        String result = putData.getResult();
                        if (result.equals("Some Thing is Wrong"))
                        {
                            Toast.makeText(EventDetailsActivity.this, ""+result, Toast.LENGTH_SHORT).show();
                        }
                        else if (result.equals("All fields are required"))
                        {
                            Toast.makeText(EventDetailsActivity.this, ""+result, Toast.LENGTH_SHORT).show();
                        }
                        else if (result.equals("Error:"))
                        {
                            Toast.makeText(EventDetailsActivity.this, ""+result, Toast.LENGTH_SHORT).show();
                        }
                        else
                        {

                            try
                            {
                                JSONArray array = new JSONArray(result);
                                for(int i=0; i < array.length(); i++)
                                {
                                    JSONObject object = array.getJSONObject(i);
                                    String Event_Id = object.getString("event_id");
                                    Event_Name = object.getString("event_name");
                                    Event_Desc = object.getString("event_desc");
                                    Event_menu = object.getString("event_menu");
                                    Event_Counter = object.getString("event_counter");
                                    Event_Budget = object.getString("event_price");
                                    Event_ImgLink = object.getString("event_img_link");
                                    String Event_Date = object.getString("event_issuing_datee");

                                }

                                event_det_textview_heading.setText(Event_Name);
                                event_desc.setText(Event_Desc);
                                event_det_textview_items.setText(Event_menu);
                                String front_counters = "Upto "+Event_Counter+" Counters";
                                event_det_textview_counters.setText(front_counters);
                                String front_money = "Approx Budget "+Event_Budget+" ₹";
                                event_det_textview_money.setText(front_money);

                                try
                                {
                                    Picasso.get().load(Event_ImgLink).into(singleeventdetails_imageview);
                                }
                                catch (Exception e)
                                {
                                    e.printStackTrace();
                                }

                                Log.i("Lootdata", "Information "+Event_Name+", "+Event_Desc+", "+Event_menu+", "+Event_Counter+", "+Event_Budget);

                            }
                            catch (Exception exception)
                            {
                                exception.printStackTrace();
                                event_det_textview_heading.setText(Event_Name);
                                event_desc.setText(Event_Desc);
                                event_det_textview_items.setText(Event_menu);
                                event_det_textview_money.setText(Event_Budget);
                                String front_counters = "Upto "+Event_Counter+" Counters";
                                event_det_textview_counters.setText(front_counters);
                                String front_money = "Approx Budget "+Event_Budget+" ₹";
                                event_det_textview_money.setText(front_money);

                                try
                                {
                                    Picasso.get().load(Event_ImgLink).into(singleeventdetails_imageview);
                                }
                                catch (Exception e)
                                {
                                    e.printStackTrace();
                                }

                                Log.i("Lootdata", "Information "+Event_Name+", "+Event_Desc+", "+Event_menu+", "+Event_Counter+", "+Event_Budget);
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