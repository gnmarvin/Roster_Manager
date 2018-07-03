package id.ac.umn.mobile.rostermanager;

/**
 * Created by Marvin on 7/3/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SendResponse {

    @SerializedName("event_roster_crew")
    @Expose
    private EventRosterCrew eventRosterCrew;

    public EventRosterCrew getEventRosterCrew() {
        return eventRosterCrew;
    }

    public void setEventRosterCrew(EventRosterCrew eventRosterCrew) {
        this.eventRosterCrew = eventRosterCrew;
    }

    public SendResponse withEventRosterCrew(EventRosterCrew eventRosterCrew) {
        this.eventRosterCrew = eventRosterCrew;
        return this;
    }

}
