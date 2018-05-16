package id.ac.umn.mobile.rostermanager;

/**
 * Created by Marvin on 5/15/2018.
 */

public class PhpConn {

    //untuk test ini alamat tertuju ke IP address dimana file PHP disimpan
    //jangan lupa ganti alamatnya sesuai alamat server kalo file PHP sudah di server
    public static final String URL_ADD = "http://172.16.14.50/Android/php/add_event.php";

    //kunci yang digunakan untuk mengirim permintaan ke php
    public static final String KEY_EVENT_ID = "event_id";
    public static final String KEY_EVENT_NAME = "event_name";
    public static final String KEY_COD_ID = "cod_id";
    public static final String KEY_TEAM_ID = "team_id";

    //JSON tags
    public static final String TAG_JSON_ARRAY = "result";
    public static final String TAG_EVENT_ID = "event_id";
    public static final String TAG_EVENT_NAME = "event_name";
    public static final String TAG_COD_ID = "cod_id";
    public static final String TAG_TEAM_ID = "team_id";

}
