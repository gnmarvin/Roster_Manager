package id.ac.umn.mobile.rostermanager;

public class PlanCrewsModel {
    private String name_event_plan_crew ;
    private String date_plan_crew;
    private String time_plan_crew;
    private String cod_plan_crew;
    private String respond_plan_crew;
    private String team_plan_crew;


    public PlanCrewsModel(String name_event_plan_crew, String date_plan_crew, String time_plan_crew, String cod_plan_crew,
                          String team_plan_crew, String respond_plan_crew){
        this.name_event_plan_crew = name_event_plan_crew;
        this.date_plan_crew = date_plan_crew;
        this.time_plan_crew = time_plan_crew;
        this.cod_plan_crew = cod_plan_crew;
        this.team_plan_crew = team_plan_crew;
        this.respond_plan_crew = respond_plan_crew;
    }

    public String getName_event_plan_crew(){
        return name_event_plan_crew;
    }

    public String getDate_plan_crew(){
        return date_plan_crew;
    }

    public String getTime_plan_crew(){
        return time_plan_crew;
    }

    public String getCod_plan_crew(){
        return cod_plan_crew;
    }

    public String getTeam_plan_crew() { return team_plan_crew; }

    public String getRespond_plan_crew() { return respond_plan_crew; }

    public void setName_event_plan_crew(String name_plan_crew){
        this.name_event_plan_crew = name_plan_crew;
    }

    public void setdate_plan_crew(String date_plan_crew){
        this.date_plan_crew = date_plan_crew;
    }

    public void setTime_plan_crew(String time_plan_crew){
        this.time_plan_crew = time_plan_crew;
    }

    public void setCod_plan_crew(String cod_plan_crew){
        this.cod_plan_crew = cod_plan_crew;
    }
}
