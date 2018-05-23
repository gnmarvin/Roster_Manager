package id.ac.umn.mobile.rostermanager;

public class Event_model {
    private String name_event ;
    private String date_event;
    private String time_event;
    private String cod_event;

    public Event_model(String name_event, String date_event, String time_event, String cod_event){
        this.name_event = name_event;
        this.date_event = date_event;
        this.time_event = time_event;
        this.cod_event = cod_event;
    }

    public String getName_event(){
        return name_event;
    }

    public void setname_event(String name_event){
        this.name_event = name_event;
    }

    public String getDate_event(){
        return date_event;
    }

    public void setdate_event(String date_event){
        this.date_event = date_event;
    }

    public String getTime_event(){
        return time_event;
    }

    public void setTime_event(String time_event){
        this.time_event = time_event;
    }

    public String getCod_event(){
        return cod_event;
    }

    public void setCod_event(String cod_event){
        this.cod_event = cod_event;
    }
}
