package id.ac.umn.mobile.rostermanager;

/**
 * Created by Marvin on 7/1/2018.
 */
//shared data sebagai data public pasti yang dapat digunakan ulang
public class SharedData {
    private static SharedData instance = new SharedData();

    public static SharedData getInstance() {
        return instance;
    }

    public static void setInstance(SharedData instance) {
        SharedData.instance = instance;
    }

    private String token_id;
    private String role;
    private String name;
    private String email;
    private String mobile_no;
    private String mobile_whatsapp;
    private String mobile_line;
    private String contact_id;
    private String code_team;
    private String name_team;
    private String organization_id;
    private String job_id;

    private String event_name_crew_details;
    private String date_event_crew_details;
    private String start_time_event_crew_details;
    private String end_time_event_crew_details;

    public String getEvent_name_crew_details() {
        return event_name_crew_details;
    }

    public void setEvent_name_crew_details(String event_name_crew_details) {
        this.event_name_crew_details = event_name_crew_details;
    }

    public String getDate_event_crew_details() {
        return date_event_crew_details;
    }

    public void setDate_event_crew_details(String date_event_crew_details) {
        this.date_event_crew_details = date_event_crew_details;
    }

    public String getStart_time_event_crew_details() {
        return start_time_event_crew_details;
    }

    public void setStart_time_event_crew_details(String start_time_event_crew_details) {
        this.start_time_event_crew_details = start_time_event_crew_details;
    }

    public String getEnd_time_event_crew_details() {
        return end_time_event_crew_details;
    }

    public void setEnd_time_event_crew_details(String end_time_event_crew_details) {
        this.end_time_event_crew_details = end_time_event_crew_details;
    }

    public String getCod_event_crew_details() {
        return cod_event_crew_details;
    }

    public void setCod_event_crew_details(String cod_event_crew_details) {
        this.cod_event_crew_details = cod_event_crew_details;
    }

    private String cod_event_crew_details;

    public String getEvent_id_plan_crew() {
        return event_id_plan_crew;
    }

    public void setEvent_id_plan_crew(String event_id_plan_crew) {
        this.event_id_plan_crew = event_id_plan_crew;
    }

    private String event_id_plan_crew;
//    private String job_name;
    private String[] job_code_menu = new String[12];
    private String[] job_id_menu = new String[12];
//    private String[] job_name_menu = new String[12];
    private String[] ref_id = new String[20];
    private int size = 0;

    public String getRef_id(int position) {
        return ref_id[position];
    }

    public void setRef_id(String ref_id) {
        this.ref_id[size] = ref_id;
        size++;
    }

    public String getJob_id(String code) {
        for(int i = 0; i < job_id_menu.length ; i++){
            if(code.equals(this.job_code_menu[i])){
                this.job_id=job_id_menu[i];
            }
            else this.job_id="";
        }
        return job_id;
    }

    public void setJob_id(String job_id, int i, String code) {
        this.job_id_menu[i]=job_id;
        this.job_code_menu[i] = code;
    }

    public String getOrganization_id() {
        return organization_id;
    }

    public void setOrganization_id(String organization_id) {
        this.organization_id = organization_id;
    }

    public String getName_team() {
        return name_team;
    }

    public void setName_team(String name_team) {
        this.name_team = name_team;
    }

    public String getCode_team() {
        return code_team;
    }

    public void setCode_team(String code_team) {
        this.code_team = code_team;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getMobile_whatsapp() {
        return mobile_whatsapp;
    }

    public void setMobile_whatsapp(String mobile_whatsapp) {
        this.mobile_whatsapp = mobile_whatsapp;
    }

    public String getMobile_line() {
        return mobile_line;
    }

    public void setMobile_line(String mobile_line) {
        this.mobile_line = mobile_line;
    }

    public String getContact_id() {
        return contact_id;
    }

    public void setContact_id(String contact_id) {
        this.contact_id = contact_id;
    }

    private SharedData() {}

    public String getToken_id() {
        return token_id;
    }

    public void setToken_id(String token_id) {
        this.token_id = token_id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void resetData(){
        token_id = "";
        role = "";
        name = "";
        email = "";
        mobile_no = "";
        mobile_whatsapp = "";
        mobile_line = "";
        contact_id = "";
        code_team ="";
        name_team = "";

    }
}
