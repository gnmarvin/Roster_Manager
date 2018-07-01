package id.ac.umn.mobile.rostermanager;

import android.app.Application;

/**
 * Created by Marvin on 7/1/2018.
 */

public class SharedData {
    private static SharedData instance = new SharedData();

    public static SharedData getInstance() {
        return instance;
    }

    public static void setInstance(SharedData instance) {
        SharedData.instance = instance;
    }

    private String token_id, role;

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
}
