package id.ac.umn.mobile.rostermanager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Marvin on 5/15/2018.
 */

public class RequestHandler {
    //metode untuk mengirim http post request
    public String sendPostRequest(String requestURL, HashMap<String, String> postDataParams) {
        //buat URL
        URL url;

        //objek string builder untuk menyimpan pesan diambil dari server
        StringBuilder stringBuilder = new StringBuilder();
        try {
            //inisialisasi URL
            url = new URL(requestURL);

            //membuat koneksi http url connection
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //konfigurasi koneksi
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            //membuat keluaran stream
            OutputStream outputStream = conn.getOutputStream();

            //menulis paramater untuk permintaan
            //menggunakan metode getPostDataString
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(outputStream, "UTF-8"));
            writer.write(getPostDataString(postDataParams));

            writer.flush();
            writer.close();
            outputStream.close();
            int responseCode = conn.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                stringBuilder = new StringBuilder();
                String response;
                //read server response
                while ((response = bufferedReader.readLine()) != null) {
                    stringBuilder.append(response);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public String sendGetRequest(String requestURL) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            URL url = new URL(requestURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String s;
            while ((s = bufferedReader.readLine()) != null) {
                stringBuilder.append(s+"\n");
            }
        }catch (Exception e) {
        }
        return stringBuilder.toString();
    }

    public String sendGetRequestParam(String requestURL, String id) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            URL url = new URL(requestURL+id);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String s;
            while ((s = bufferedReader.readLine()) != null ) {
                stringBuilder.append(s+"\n");
            }
        }catch (Exception e){
        }
        return stringBuilder.toString();
    }

    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }
}
