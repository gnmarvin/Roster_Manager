package id.ac.umn.mobile.rostermanager;

/**
 * Created by Marvin on 7/3/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EventRosterCrew {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("crew_id")
    @Expose
    private String crewId;
    @SerializedName("response")
    @Expose
    private String response;
    @SerializedName("remarks")
    @Expose
    private String remarks;

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public EventRosterCrew withRemarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public EventRosterCrew withId(String id) {
        this.id = id;
        return this;
    }

    public String getCrewId() {
        return crewId;
    }

    public void setCrewId(String crewId) {
        this.crewId = crewId;
    }

    public EventRosterCrew withCrewId(String crewId) {
        this.crewId = crewId;
        return this;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public EventRosterCrew withResponse(String response) {
        this.response = response;
        return this;
    }

}
