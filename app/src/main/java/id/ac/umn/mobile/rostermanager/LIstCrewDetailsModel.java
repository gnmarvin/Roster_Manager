package id.ac.umn.mobile.rostermanager;

public class LIstCrewDetailsModel {

    private String position;
    private String name_crew;

    public LIstCrewDetailsModel(String position, String name_crew){
        this.position = position;
        this.name_crew = name_crew;
    }

    public  String getPosition(){
        return position;
    }

    public  String getName_crew(){
        return position;
    }
}
