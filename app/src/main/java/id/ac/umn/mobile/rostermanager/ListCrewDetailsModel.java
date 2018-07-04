package id.ac.umn.mobile.rostermanager;

import java.util.List;

public class ListCrewDetailsModel {

    private String position;
    private String name_crew;
    private String team;
    private String roster_id;

    public ListCrewDetailsModel(String crew_name, String name_crew, String position, String team){
        this.position = position;
        this.name_crew = name_crew;
        this.team = team;
    }

    public String getPosition() {
        return position;
    }

    public String getName_crew(){
        return name_crew;
    }

    public String getRoster_id() {
        return roster_id;
    }

    public String getTeam() {
        return team;
    }

    public void setName_crew(String name_crew) {
        this.name_crew = name_crew;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setRoster_id(String roster_id) {
        this.roster_id = roster_id;
    }

    public void setTeam(String team) {
        this.team = team;
    }
}
