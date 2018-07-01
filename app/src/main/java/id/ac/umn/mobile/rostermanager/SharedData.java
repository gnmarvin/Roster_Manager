package id.ac.umn.mobile.rostermanager;

import android.app.Application;

/**
 * Created by Marvin on 7/1/2018.
 */
//
public class SharedData {
    private static SharedData instance = new SharedData();

    public static SharedData getInstance() {
        return instance;
    }

    public static void setInstance(SharedData instance) {
        SharedData.instance = instance;
    }

    private String token_id;
    private String role;
    private String name;
    private String email;
    private String mobile_no;
    private String mobile_whatsapp;
    private String mobile_line;
    private String contact_id;
    private String code_team;
    private String name_team;

    public String getName_team() {
        return name_team;
    }

    public void setName_team(String name_team) {
        this.name_team = name_team;
    }

    public String getCode_team() {
        return code_team;
    }

    public void setCode_team(String code_team) {
        this.code_team = code_team;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getMobile_whatsapp() {
        return mobile_whatsapp;
    }

    public void setMobile_whatsapp(String mobile_whatsapp) {
        this.mobile_whatsapp = mobile_whatsapp;
    }

    public String getMobile_line() {
        return mobile_line;
    }

    public void setMobile_line(String mobile_line) {
        this.mobile_line = mobile_line;
    }

    public String getContact_id() {
        return contact_id;
    }

    public void setContact_id(String contact_id) {
        this.contact_id = contact_id;
    }

    private SharedData() {}

    public String getToken_id() {
        return token_id;
    }

    public void setToken_id(String token_id) {
        this.token_id = token_id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
