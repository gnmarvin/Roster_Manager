package id.ac.umn.mobile.rostermanager;

public class BlockoutDatesModel {
    private String dates;
    private String reason;

    public  BlockoutDatesModel(String dates, String reason){
        this.dates = dates;


        this.reason = reason;
    }

    public String getDates() {
        return dates;
    }

    public String getReason() {
        return reason;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
