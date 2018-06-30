package id.ac.umn.mobile.rostermanager;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Marvin on 6/30/2018.
 */

class EventRoster {
    @SerializedName("id")
    @Expose
    private String id;

    public String getId(){ return id; }

    public void setId(String id) { this.id = id; }

    public EventRoster withId(String id) {
        this.id = id;
        return this;
    }
}
