package com.creatures.mycateringadminpanel;

public class Model_Class {

    private int id;
    private String username;
    private String password;
    private String email;
    private String mobile;

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
}
