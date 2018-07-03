package id.ac.umn.mobile.rostermanager;

/**
 * Created by Marvin on 7/4/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CancelCrew {

    @SerializedName("event_roster_crew")
    @Expose
    private EventRosterCrew eventRosterCrew;

    public EventRosterCrew getEventRosterCrew() {
        return eventRosterCrew;
    }

    public void setEventRosterCrew(EventRosterCrew eventRosterCrew) {
        this.eventRosterCrew = eventRosterCrew;
    }

    public CancelCrew withEventRosterCrew(EventRosterCrew eventRosterCrew) {
        this.eventRosterCrew = eventRosterCrew;
        return this;
    }

}
