package id.ac.umn.mobile.rostermanager;

public class PlanCrewsModel {
    private String name_event_plan_crew ;
    private String date_plan_crew;
    private String time_start_plan_crew;
    private String time_end_plan_crew;
    private String cod_plan_crew;
    private String respond_plan_crew;
    private String team_plan_crew;
    private String event_id;


    public PlanCrewsModel(String name_event_plan_crew, String date_plan_crew, String time_start_plan_crew, String time_end_plan_crew, String cod_plan_crew,
                          String team_plan_crew, String respond_plan_crew, String event_id){
        this.name_event_plan_crew = name_event_plan_crew;
        this.date_plan_crew = date_plan_crew;
        this.time_start_plan_crew = time_start_plan_crew;
        this.time_end_plan_crew = time_end_plan_crew;
        this.cod_plan_crew = cod_plan_crew;
        this.team_plan_crew = team_plan_crew;
        this.respond_plan_crew = respond_plan_crew;
        this.event_id = event_id;
    }

    public String getName_event_plan_crew() {
        return name_event_plan_crew;
    }

    public void setName_event_plan_crew(String name_event_plan_crew) {
        this.name_event_plan_crew = name_event_plan_crew;
    }

    public String getDate_plan_crew() {
        return date_plan_crew;
    }

    public void setDate_plan_crew(String date_plan_crew) {
        this.date_plan_crew = date_plan_crew;
    }

    public String getTime_start_plan_crew() {
        return time_start_plan_crew;
    }

    public void setTime_start_plan_crew(String time_start_plan_crew) {
        this.time_start_plan_crew = time_start_plan_crew;
    }

    public String getTime_end_plan_crew() {
        return time_end_plan_crew;
    }

    public void setTime_end_plan_crew(String time_end_plan_crew) {
        this.time_end_plan_crew = time_end_plan_crew;
    }

    public String getCod_plan_crew() {
        return cod_plan_crew;
    }

    public void setCod_plan_crew(String cod_plan_crew) {
        this.cod_plan_crew = cod_plan_crew;
    }

    public String getRespond_plan_crew() {
        return respond_plan_crew;
    }

    public void setRespond_plan_crew(String respond_plan_crew) {
        this.respond_plan_crew = respond_plan_crew;
    }

    public String getTeam_plan_crew() {
        return team_plan_crew;
    }

    public void setTeam_plan_crew(String team_plan_crew) {
        this.team_plan_crew = team_plan_crew;
    }


    public String getEvent_id() {
        return event_id;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }

}
