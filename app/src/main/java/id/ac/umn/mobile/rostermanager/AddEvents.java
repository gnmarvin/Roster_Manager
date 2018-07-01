package id.ac.umn.mobile.rostermanager;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddEvents {

    @SerializedName("event_roster")
    @Expose
    private EventRoster eventRoster;
    @SerializedName("event_roster_has_job")
    @Expose
    private List<EventRosterHasJob> eventRosterHasJob = null;

    public EventRoster getEventRoster() {
        return eventRoster;
    }

    public void setEventRoster(EventRoster eventRoster) {
        this.eventRoster = eventRoster;
    }

    public List<EventRosterHasJob> getEventRosterHasJob() {
        return eventRosterHasJob;
    }

    public void setEventRosterHasJob(List<EventRosterHasJob> eventRosterHasJob) {
        this.eventRosterHasJob = eventRosterHasJob;
    }

}