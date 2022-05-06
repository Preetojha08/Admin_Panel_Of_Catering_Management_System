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

    //About Thalis Details

    private int thali_id;
    private String thali_name;
    private String thali_img_link;
    private String thali_desc;


    private int thali_counter;
    private String thali_price;
    private String thali_issuing_date;


    private int userid;
    private int inquiryId;
    private String inq_1;
    private String inq_2;


    public Model_Class(int userid, int inquiryId, String username,String inq_1, String inq_2,String email) {
        this.userid = userid;
        this.inquiryId = inquiryId;
        this.username = username;
        this.inq_1 = inq_1;
        this.inq_2 = inq_2;
        this.email = email;
    }

    public Model_Class(int event_id, String event_name, String event_img_link) {
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

    //About method for Thali details


    public int getThali_id() {
        return thali_id;
    }

    public String getThali_name() {
        return thali_name;
    }

    public String getThali_img_link() {
        return thali_img_link;
    }

    public int getUserid() {
        return userid;
    }

    public int getInquiryId() {
        return inquiryId;
    }

    public String getInq_1() {
        return inq_1;
    }

    public String getInq_2() {
        return inq_2;
    }


}

