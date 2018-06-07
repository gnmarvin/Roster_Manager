package id.ac.umn.mobile.rostermanager;

import java.util.List;

public class ListCrewDetailsModel {

    private String position;
    private String name_crew;
    private String team;
    private String respond;

    public ListCrewDetailsModel(String name_crew, String position, String team, String respond){
        this.position = position;
        this.name_crew = name_crew;
        this.team = team;
        this.respond = respond;
    }

    public String getPosition() {
        return position;
    }

    public String getName_crew(){
        return name_crew;
    }

    public String getRespond() {
        return respond;
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

    public void setRespond(String respond) {
        this.respond = respond;
    }

    public void setTeam(String team) {
        this.team = team;
    }
}
