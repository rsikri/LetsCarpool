package com.sarthakmeh.shareyourride.Models;

/**
 * Created by sarthakmeh on 7/6/16.
 */
public class User {

    public String username;
    public String img_url;
    public String email;
    public String gender;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getemail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getImg_url() {
        return img_url;
    }
    public void setImg_url(String url) {
        this.img_url = url;
    }
}
