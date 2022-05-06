package com.creatures.mycateringadminpanel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText tiet_admin_email,tiet_admin_passord;
    String str_email,str_pass;
    TextView textview_forgot_pass;
    Button sign_in_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        tiet_admin_email=(TextInputEditText)findViewById(R.id.textinputedittext_admin_mail);
        tiet_admin_passord=(TextInputEditText)findViewById(R.id.textinputedittext_admin_password);

        sign_in_btn=(Button)findViewById(R.id.signin_button);

        textview_forgot_pass=(TextView)findViewById(R.id.text_view_forgot_pass);



        sign_in_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                str_email=tiet_admin_email.getText().toString();
                str_pass=tiet_admin_passord.getText().toString();

                if (str_email.equalsIgnoreCase("admin") && str_pass.equals("P@ssw0rd"))
                {
                    startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                    finish();
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Admin Not Registered "+str_email+" hhh"+str_pass , Toast.LENGTH_SHORT).show();
                }
            }
        });

        textview_forgot_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, "Forgot Password", Toast.LENGTH_SHORT).show();
            }
        });



    }
}