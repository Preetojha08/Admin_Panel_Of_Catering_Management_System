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

public class AddThaliDetailActivity extends AppCompatActivity {

    TextInputEditText tiet_thali_name,tiet_thali_desc,tiet_thali_counter,tiet_thali_price,tiet_thali_link;
    Button btn_add_thali;
    ProgressBar add_thali_data_progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_thali_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_add_thali_detail);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("   Add Thali Details");

        tiet_thali_name=(TextInputEditText) findViewById(R.id.text_input_edit_text_admin_add_thali_name);
        tiet_thali_desc=(TextInputEditText) findViewById(R.id.text_input_edit_text_admin_add_thali_desc);
        tiet_thali_counter=(TextInputEditText) findViewById(R.id.text_input_edit_text_admin_add_thali_counter);
        tiet_thali_price=(TextInputEditText) findViewById(R.id.text_input_edit_text_admin_add_thali_price);
        tiet_thali_link=(TextInputEditText) findViewById(R.id.text_input_edit_text_admin_add_thali_img_link);

        add_thali_data_progressBar=(ProgressBar)findViewById(R.id.spin_kit_progress_bar_add_thali_data);
        Sprite wave = new DoubleBounce();
        add_thali_data_progressBar.setIndeterminateDrawable(wave);
        add_thali_data_progressBar.setVisibility(View.GONE);

        btn_add_thali=(Button) findViewById(R.id.add_admin_thali_button);

        btn_add_thali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name,desc,counter,price,link;

                name=tiet_thali_name.getText().toString().trim();
                desc=tiet_thali_desc.getText().toString().trim();
                counter=tiet_thali_counter.getText().toString().trim();
                price=tiet_thali_price.getText().toString().trim();
                link=tiet_thali_link.getText().toString().trim();

                if (name.isEmpty() || desc.isEmpty() || counter.isEmpty() || price.isEmpty() || link.isEmpty())
                {
                    if (name.isEmpty() || name.equals(" "))
                    {
                       tiet_thali_name.setError("Enter Thali Name");
                    }
                    if (desc.isEmpty() || desc.equals(" "))
                    {
                        tiet_thali_desc.setError("Enter Thali Description");
                    }
                    if (counter.isEmpty() || counter.equals(" "))
                    {
                        tiet_thali_counter.setError("Enter Thali Counter");
                    }
                    if (price.isEmpty() || price.equals(" "))
                    {
                        tiet_thali_price.setError("Enter Thali Price");
                    }
                    if (link.isEmpty() || link.equals(" "))
                    {
                        tiet_thali_link.setError("Enter Thali Link");
                    }
                }
                else
                {
                    add_thali_data_progressBar.setVisibility(View.VISIBLE);
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[5];
                            field[0] = "thali_name";
                            field[1] = "thali_desc";
                            field[2] = "thali_counter";
                            field[3] = "thali_price";
                            field[4] = "thali_link";
                            //field[3] = "email";

                            //Creating array for data
                            String[] data = new String[5];
                            data[0] = name;
                            data[1] = desc;
                            data[2] = counter;   //192.168.1.101 http://192.168.64.2/test_login/signup.php http://192.168.64.2/ http://192.168.64.2/new_post_test/post_signup.php http://192.168.64.2/test_login/signup.php
                            data[3] = price;
                            data[4] = link;
                            //PutData putData = new PutData("https://preetojhadatabasetrail.000webhostapp.com/signup_login_test/signup.php", "POST", field, data);
                            PutData putData = new PutData("https://preetojhadatabasetrail.000webhostapp.com/catering_project/add_thali_details.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    if (result.equals("Sign Up Success"))
                                    {
                                        Toast.makeText(AddThaliDetailActivity.this, "Data Added Successfully", Toast.LENGTH_SHORT).show();
                                        add_thali_data_progressBar.setVisibility(View.GONE);
                                    }
                                    else
                                    {
                                        Toast.makeText(AddThaliDetailActivity.this, ""+result, Toast.LENGTH_SHORT).show();
                                        add_thali_data_progressBar.setVisibility(View.GONE);
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