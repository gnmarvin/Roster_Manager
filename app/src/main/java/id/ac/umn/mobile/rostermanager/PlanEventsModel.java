package id.ac.umn.mobile.rostermanager;

public class PlanEventsModel {
    private String name_event_plan_event ;
    private String date_plan_event;
    private String time_start_plan_event;
    private String time_end_plan_event;
    private String cod_plan_event;
    private String photo_respond_plan_event;
    private String campers_respond_plan_event;
    private String photo_team_plan_event;
    private String campers_team_plan_event;
    private String event_id_plant_event;


    public PlanEventsModel(String name_event_plan_event, String date_plan_event, String time_start_plan_event, String time_end_plan_event, String cod_plan_event,
                           String photo_team_plan_event, String photo_respond_plan_event, String campers_team_plan_event, String campers_respond_plan_event, String event_id_plant_event){
        this.name_event_plan_event = name_event_plan_event;
        this.date_plan_event = date_plan_event;
        this.time_start_plan_event = time_start_plan_event;
        this.time_end_plan_event = time_end_plan_event;
        this.cod_plan_event = cod_plan_event;
        this.photo_team_plan_event = photo_team_plan_event;
        this.photo_respond_plan_event = photo_respond_plan_event;
        this.campers_team_plan_event = campers_team_plan_event;
        this.campers_respond_plan_event = campers_respond_plan_event;
        this.event_id_plant_event = event_id_plant_event;
    }

    public String getName_event_plan_event(){
        return name_event_plan_event;
    }

    public String getDate_plan_event(){
        return date_plan_event;
    }

    public String getTime_start_plan_event(){
        return time_start_plan_event;
    }

    public String getTime_end_plan_event(){
        return time_end_plan_event;
    }

    public String getCod_plan_event(){
        return cod_plan_event;
    }

    public String getPhoto_respond_plan_event() { return photo_respond_plan_event; }

    public String getPhoto_team_plan_event() { return photo_team_plan_event; }

    public String getCampers_respond_plan_event() { return campers_respond_plan_event; }

    public String getCampers_team_plan_event() { return campers_team_plan_event; }

    public String getEvent_id_plant_event() {
        return event_id_plant_event;
    }

    public void setEvent_id_plant_event(String event_id_plant_event) {
        this.event_id_plant_event = event_id_plant_event;
    }

    public void setname_plan_event(String name_plan_event){
        this.name_event_plan_event = name_plan_event;
    }

    public void setdate_plan_event(String date_plan_event){
        this.date_plan_event = date_plan_event;
    }

    public void setTime_start_plan_event(String time_start__plan_event){
        this.time_start_plan_event = time_start_plan_event;
    }

    public void setTime_end_plan_event(String time_end_plan_event){
        this.time_end_plan_event = time_end_plan_event;
    }

    public void setCod_plan_event(String cod_plan_event){
        this.cod_plan_event = cod_plan_event;
    }
}
