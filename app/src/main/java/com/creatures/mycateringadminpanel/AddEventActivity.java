package com.creatures.mycateringadminpanel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class AddEventActivity extends AppCompatActivity {

    TextInputEditText tiet_event_name,tiet_event_desc,tiet_event_menu,tiet_event_counter,tiet_event_price,tiet_event_link;
    Button btn_add_event;
    ProgressBar add_event_data_progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_add_event_detail);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("   Add Thali Details");

        tiet_event_name=(TextInputEditText) findViewById(R.id.text_input_edit_text_admin_add_event_name);
        tiet_event_desc=(TextInputEditText) findViewById(R.id.text_input_edit_text_admin_add_event_desc);
        tiet_event_menu=(TextInputEditText) findViewById(R.id.text_input_edit_text_admin_add_event_menu); 
        tiet_event_counter=(TextInputEditText) findViewById(R.id.text_input_edit_text_admin_add_event_counter);
        tiet_event_price=(TextInputEditText) findViewById(R.id.text_input_edit_text_admin_add_event_price);
        tiet_event_link=(TextInputEditText) findViewById(R.id.text_input_edit_text_admin_add_event_img_link);

        add_event_data_progressBar=(ProgressBar)findViewById(R.id.spin_kit_progress_bar_add_event_data);
        Sprite wave = new DoubleBounce();
        add_event_data_progressBar.setIndeterminateDrawable(wave);
        add_event_data_progressBar.setVisibility(View.GONE);
        
        btn_add_event=(Button) findViewById(R.id.add_admin_event_button);

        btn_add_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name,desc,menu,counter,price,link;

                name=tiet_event_name.getText().toString().trim();
                desc=tiet_event_desc.getText().toString().trim();
                menu=tiet_event_menu.getText().toString().trim();
                counter=tiet_event_counter.getText().toString().trim();
                price=tiet_event_price.getText().toString().trim();
                link=tiet_event_link.getText().toString().trim();

                if (name.isEmpty() || desc.isEmpty() || menu.isEmpty() || counter.isEmpty() || price.isEmpty() || link.isEmpty())
                {
                    if (name.isEmpty() || name.equals(" "))
                    {
                        tiet_event_name.setError("Enter Event Name");
                    }
                    if (desc.isEmpty() || desc.equals(" "))
                    {
                        tiet_event_desc.setError("Enter Event Description");
                    }
                    if (menu.isEmpty() || menu.equals(" "))
                    {
                        tiet_event_menu.setError("Enter Event Description");
                    }
                    if (counter.isEmpty() || counter.equals(" "))
                    {
                        tiet_event_counter.setError("Enter Event Counter");
                    }
                    if (price.isEmpty() || price.equals(" "))
                    {
                        tiet_event_price.setError("Enter Event Price");
                    }
                    if (link.isEmpty() || link.equals(" "))
                    {
                        tiet_event_link.setError("Enter Event Link");
                    }
                }
                else
                {
                    add_event_data_progressBar.setVisibility(View.VISIBLE);
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[6];
                            field[0] = "event_name";
                            field[1] = "event_desc";
                            field[2] = "event_menu";
                            field[3] = "event_counter";
                            field[4] = "event_price";
                            field[5] = "event_link";
                            //field[3] = "email";

                            //Creating array for data
                            String[] data = new String[6];
                            data[0] = name;
                            data[1] = desc;
                            data[2] = menu;
                            data[3] = counter;
                            data[4] = price;
                            data[5] = link;
                            //PutData putData = new PutData("https://preetojhadatabasetrail.000webhostapp.com/signup_login_test/signup.php", "POST", field, data);
                            PutData putData = new PutData("https://preetojhadatabasetrail.000webhostapp.com/catering_project/add_event_details.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    if (result.equals("Sign Up Success"))
                                    {
                                        add_event_data_progressBar.setVisibility(View.GONE);
                                        Toast.makeText(AddEventActivity.this, "Data Added Successfully", Toast.LENGTH_SHORT).show();
                                    }
                                    else
                                    {
                                        add_event_data_progressBar.setVisibility(View.GONE);
                                        Toast.makeText(AddEventActivity.this, ""+result, Toast.LENGTH_SHORT).show();
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
        });
    }
}