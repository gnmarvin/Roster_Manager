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

    @SerializedName("organization_id")
    @Expose
    private String organizationId;
    @SerializedName("event_id")
    @Expose
    private String eventId;
    @SerializedName("roster_date")
    @Expose
    private String rosterDate;
    @SerializedName("roster_start_time")
    @Expose
    private String rosterStartTime;
    @SerializedName("roster_end_time")
    @Expose
    private String rosterEndTime;
    @SerializedName("cod_id")
    @Expose
    private String codId;
    @SerializedName("created_by_id")
    @Expose
    private String createdById;

    public String getId(){ return id; }

    public void setId(String id) { this.id = id; }

    public EventRoster withId(String id) {
        this.id = id;
        return this;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getRosterDate() {
        return rosterDate;
    }

    public void setRosterDate(String rosterDate) {
        this.rosterDate = rosterDate;
    }

    public String getRosterStartTime() {
        return rosterStartTime;
    }

    public void setRosterStartTime(String rosterStartTime) {
        this.rosterStartTime = rosterStartTime;
    }

    public String getRosterEndTime() {
        return rosterEndTime;
    }

    public void setRosterEndTime(String rosterEndTime) {
        this.rosterEndTime = rosterEndTime;
    }

    public String getCodId() {
        return codId;
    }

    public void setCodId(String codId) {
        this.codId = codId;
    }

    public String getCreatedById() {
        return createdById;
    }

    public void setCreatedById(String createdById) {
        this.createdById = createdById;
    }

}
