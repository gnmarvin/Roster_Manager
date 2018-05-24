package id.ac.umn.mobile.rostermanager;

public class PlanEventsModel {
    private String name_event_plan_event ;
    private String date_plan_event;
    private String time_plan_event;
    private String cod_plan_event;
    private String photo_respond_plan_event;
    private String campers_respond_plan_event;
    private String photo_team_plan_event;
    private String campers_team_plan_event;


    public PlanEventsModel(String name_event_plan_event, String date_plan_event, String time_plan_event, String cod_plan_event,
                           String photo_team_plan_event, String photo_respond_plan_event, String campers_team_plan_event, String campers_respond_plan_event){
        this.name_event_plan_event = name_event_plan_event;
        this.date_plan_event = date_plan_event;
        this.time_plan_event = time_plan_event;
        this.cod_plan_event = cod_plan_event;
        this.photo_team_plan_event = photo_team_plan_event;
        this.photo_respond_plan_event = photo_respond_plan_event;
        this.campers_team_plan_event = campers_team_plan_event;
        this.campers_respond_plan_event = campers_respond_plan_event;
    }

    public String getName_event_plan_event(){
        return name_event_plan_event;
    }

    public String getDate_plan_event(){
        return date_plan_event;
    }

    public String getTime_plan_event(){
        return time_plan_event;
    }

    public String getCod_plan_event(){
        return cod_plan_event;
    }

    public String getPhoto_respond_plan_event() { return photo_respond_plan_event; }

    public String getPhoto_team_plan_event() { return photo_team_plan_event; }

    public String getCampers_respond_plan_event() { return campers_respond_plan_event; }

    public String getCampers_team_plan_event() { return campers_team_plan_event; }

    public void setname_plan_event(String name_plan_event){
        this.name_event_plan_event = name_plan_event;
    }

    public void setdate_plan_event(String date_plan_event){
        this.date_plan_event = date_plan_event;
    }

    public void setTime_plan_event(String time_plan_event){
        this.time_plan_event = time_plan_event;
    }

    public void setCod_plan_event(String cod_plan_event){
        this.cod_plan_event = cod_plan_event;
    }
}
