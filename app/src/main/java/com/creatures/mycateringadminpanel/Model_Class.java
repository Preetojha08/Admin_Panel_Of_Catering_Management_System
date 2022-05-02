package com.creatures.mycateringadminpanel;

public class Model_Class {

    //About Users Deatils
    private int id;
    private String username;
    private String password;
    private String email;
    private String mobile;
    private String login_date;


    //About Event Details

    private int event_id;
    private String event_img_link;
    private String event_name;

    private String event_desc;
    private String event_menu;
    private int event_counter;
    private int event_price;
    private String event_issuing_date;

    public Model_Class(int event_id, String event_name,String event_img_link) {
        this.event_id = event_id;
        this.event_name = event_name;
        this.event_img_link = event_img_link;
    }

    public Model_Class(int id, String username, String email, String login_date) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.login_date = login_date;
    }

    public Model_Class(int id, String username, String password, String email, String mobile) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.mobile = mobile;
    }

    //getter method for users details
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public String getLogin_date() {
        return login_date;
    }


    //getter method for events details


    public int getEvent_id() {
        return event_id;
    }

    public String getEvent_name() {
        return event_name;
    }

    public String getEvent_img_link() {
        return event_img_link;
    }

}

