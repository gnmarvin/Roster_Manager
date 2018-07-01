package id.ac.umn.mobile.rostermanager;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EventRosterHasJob {

    @SerializedName("roster_job_id")
    @Expose
    private String rosterJobId;
    @SerializedName("roster_job_quota")
    @Expose
    private String rosterJobQuota;
    @SerializedName("organization_unit_id")
    @Expose
    private String organizationUnitId;

    public String getRosterJobId() {
        return rosterJobId;
    }

    public void setRosterJobId(String rosterJobId) {
        this.rosterJobId = rosterJobId;
    }

    public String getRosterJobQuota() {
        return rosterJobQuota;
    }

    public void setRosterJobQuota(String rosterJobQuota) {
        this.rosterJobQuota = rosterJobQuota;
    }

    public String getOrganizationUnitId() {
        return organizationUnitId;
    }

    public void setOrganizationUnitId(String organizationUnitId) {
        this.organizationUnitId = organizationUnitId;
    }

}