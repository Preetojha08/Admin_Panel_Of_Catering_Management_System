package com.creatures.mycateringadminpanel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    CardView usersdatabase_card_view,logindatabase_card_view,eventdatabase_card_view,thalidatabase_card_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_home);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("   Admin Panel");

        usersdatabase_card_view=(CardView)findViewById(R.id.cardview_users_datacollection);
        logindatabase_card_view=(CardView)findViewById(R.id.cardview_users_logindatacollection);
        eventdatabase_card_view=(CardView)findViewById(R.id.cardview_events_datacollection);
        thalidatabase_card_view=(CardView)findViewById(R.id.cardview_thalis_datacollection);


        usersdatabase_card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(HomeActivity.this,UsersDataActivity.class));

            }
        });

        logindatabase_card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(HomeActivity.this,LoginUserDataActivity.class));

            }
        });

        eventdatabase_card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(HomeActivity.this,AddEventActivity.class));

            }
        });

        thalidatabase_card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(HomeActivity.this,AddThaliDetailActivity.class));

            }
        });


    }
}