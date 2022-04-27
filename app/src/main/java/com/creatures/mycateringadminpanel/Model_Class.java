package com.creatures.mycateringadminpanel;

public class Model_Class {

    private int id;
    private String username;
    private String password;
    private String email;
    private String mobile;
    private String login_date;

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
}
