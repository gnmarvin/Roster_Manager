package id.ac.umn.mobile.rostermanager;

public class Event_model {
    private String nama_event;
    private String tanggal_event;
    private String shift_event;
    private String cod_event;

    public Event_model(String nama_event, String tanggal_event, String shift_event, String cod_event){
        this.nama_event = nama_event;
        this.tanggal_event = tanggal_event;
        this.shift_event = shift_event;
        this.cod_event = cod_event;
    }

    public String getNama_event(){
        return nama_event;
    }

    public void setNama_event(String nama_event){
        this.nama_event = nama_event;
    }

    public String getTanggal_event(){
        return tanggal_event;
    }

    public void setTanggal_event(String tanggal_event){
        this.tanggal_event = tanggal_event;
    }

    public String getShift_event(){
        return shift_event;
    }

    public void setShift_event(String shift_event){
        this.shift_event = shift_event;
    }

    public String getCod_event(){
        return cod_event;
    }

    public void setCod_event(String cod_event){
        this.cod_event = cod_event;
    }
}
