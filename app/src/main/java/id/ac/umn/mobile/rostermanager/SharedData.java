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
//    private String job_name;
    private String[] job_code_menu = new String[12];
    private String[] job_id_menu = new String[12];
//    private String[] job_name_menu = new String[12];

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

//    public String getJob_name(String code) {
//        for(int i = 0; i < job_name_menu.length ; i++){
//            if(code.equals(this.job_code_menu[i])){
//                this.job_name=job_name_menu[i];
//            }
//            else this.job_name="";
//        }
//        return job_name;
//    }
//
//    public void setJob_name(String job_name, int i) {
//        this.job_name_menu[i] = job_name;
//    }

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
