package com.creatures.mycateringadminpanel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.util.regex.Pattern;

public class AddUserActivity extends AppCompatActivity {

    TextInputEditText tiet_add_uname,tiet_add_umobile,tiet_add_uemail,tiet_add_upassw0rd;
    Button add_user_data_btn;

    public static final Pattern mobile_Pattern = Pattern.compile("[0-9]{10}");
    public static final Pattern email_Pattern = Pattern.compile("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+");
    public static final Pattern password_Pattern = Pattern.compile("(?=.*[0-9])(?=.*[a-z])(?=\\S+$).{6,15}");

    ProgressBar progress_bar_admin_add_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_add_user);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("   Add User");

        tiet_add_uname=(TextInputEditText) findViewById(R.id.text_input_edit_text_admin_add_user_name);
        tiet_add_uemail=(TextInputEditText) findViewById(R.id.text_input_edit_text_admin_add_user_email);
        tiet_add_umobile=(TextInputEditText) findViewById(R.id.text_input_edit_text_admin_add_user_mobile);
        tiet_add_upassw0rd=(TextInputEditText) findViewById(R.id.text_input_edit_text_admin_add_user_password);

        add_user_data_btn=(Button) findViewById(R.id.add_admin_user_button);

        progress_bar_admin_add_user=(ProgressBar)findViewById(R.id.progress_bar_admin_add_user);

        add_user_data_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String u_name,u_email,u_mobile,u_password;

                u_name=tiet_add_uname.getText().toString().trim();
                u_email=tiet_add_uemail.getText().toString().trim();
                u_mobile=tiet_add_umobile.getText().toString().trim();
                u_password=tiet_add_upassw0rd.getText().toString().trim();

                if (u_name.isEmpty() || u_name.equals(" "))
                {
                    tiet_add_uname.setError("Enter User Name");
                }
                if (u_email.isEmpty() || u_email.equals(" "))
                {
                    tiet_add_uemail.setError("Enter User Email ID");
                }
                if (u_mobile.isEmpty() || u_mobile.equals(" "))
                {
                    tiet_add_uemail.setError("Enter User Mobile Number");
                }
                if (u_password.isEmpty() || u_password.equals(" "))
                {
                    tiet_add_upassw0rd.setError("Enter User Password");
                }
                if (!email_Pattern.matcher(u_email).matches())
                {
                    tiet_add_uemail.setError("Enter Valid User Email IS");
                }
                if (!mobile_Pattern.matcher(u_mobile).matches())
                {
                    tiet_add_umobile.setError("Enter Valid User Mobile Number");
                }
                if (!password_Pattern.matcher(u_password).matches())
                {
                    tiet_add_upassw0rd.setError("Enter Valid User Password");
                }
                else
                {
                    //Start ProgressBar first (Set visibility VISIBLE)
                    progress_bar_admin_add_user.setVisibility(View.VISIBLE);
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[4];
                            field[0] = "username";
                            field[1] = "mobile_no";
                            field[2] = "password";
                            field[3] = "email";
                            //field[3] = "email";

                            //Creating array for data
                            String[] data = new String[4];
                            data[0] = u_name;
                            data[1] = u_mobile;
                            data[2] = u_password;   //192.168.1.101 http://192.168.64.2/test_login/signup.php http://192.168.64.2/ http://192.168.64.2/new_post_test/post_signup.php http://192.168.64.2/test_login/signup.php
                            data[3] = u_email;
                            //PutData putData = new PutData("https://preetojhadatabasetrail.000webhostapp.com/signup_login_test/signup.php", "POST", field, data);
                            PutData putData = new PutData("https://preetojhadatabasetrail.000webhostapp.com/catering_project/sign_up.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    progress_bar_admin_add_user.setVisibility(View.GONE);
                                    String result = putData.getResult();
                                    if (result.equals("Sign Up Success"))
                                    {
                                        Toast.makeText(AddUserActivity.this, "Data Added Successfully", Toast.LENGTH_SHORT).show();
                                    }
                                    else
                                    {
                                        Toast.makeText(AddUserActivity.this, ""+result, Toast.LENGTH_SHORT).show();
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