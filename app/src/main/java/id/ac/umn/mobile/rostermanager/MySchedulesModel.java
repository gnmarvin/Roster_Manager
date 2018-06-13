package id.ac.umn.mobile.rostermanager;

public class MySchedulesModel {
    private String name_event_my_schedule;
    private String date_my_schedule;
    private String time_my_schedule;
    private String position_my_schedule;
    private String cod_my_schedule;

    public MySchedulesModel(String name_event_my_schedule, String date_my_schedule, String time_my_schedule, String position_my_schedule, String cod_my_schedule) {
        this.name_event_my_schedule = name_event_my_schedule;
        this.date_my_schedule = date_my_schedule;
        this.time_my_schedule = time_my_schedule;
        this.position_my_schedule = position_my_schedule;
        this.cod_my_schedule = cod_my_schedule;
    }

    public String getName_event_my_schedule() {
        return name_event_my_schedule;
    }

    public void setName_event_my_schedule(String name_event_my_schedule) {
        this.name_event_my_schedule = name_event_my_schedule;
    }

    public String getDate_my_schedule() {
        return date_my_schedule;
    }

    public void setDate_my_schedule(String date_my_schedule) {
        this.date_my_schedule = date_my_schedule;
    }

    public String getTime_my_schedule() {
        return time_my_schedule;
    }

    public void setTime_my_schedule(String time_my_schedule) {
        this.time_my_schedule = time_my_schedule;
    }

    public String getPosition_my_schedule() {
        return position_my_schedule;
    }

    public void setPosition_my_schedule(String position_my_schedule) {
        this.position_my_schedule = position_my_schedule;
    }

    public String getCod_my_schedule() {
        return cod_my_schedule;
    }

    public void setCod_my_schedule(String cod_my_schedule) {
        this.cod_my_schedule = cod_my_schedule;
    }
}
