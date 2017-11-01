/**
 * Created by masinogns on 2017. 10. 31..
 */
/* Java 샘플 코드 */


import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;

public class api {
    public static void main(String[] args) throws IOException {
        String Key = "=Po2YR9LbJ7x7fLoAe7AzzcB1ysApHG6L6Ey6mD53qRkcKXK9lxr2pTlqEjCLepuOvChhsgrlY%2FjOgzHQKW9FjA%3D%3D";
        StringBuilder urlBuilder = new StringBuilder("http://openapi.jejutour.go.kr:8080/openapi/service/TourMapService/getTourMapRelSpot"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + Key); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("SEQ","UTF-8") + "=" + URLEncoder.encode("50058", "UTF-8")); /*관광지번호*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println(sb.toString());
    }
}