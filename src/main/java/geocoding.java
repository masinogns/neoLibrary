/**
 * Created by masinogns on 2017. 11. 1..
 */

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;


import com.oracle.javafx.jmx.json.JSONException;
import org.apache.commons.io.IOUtils;
//
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

//(For this you need to add "commons-io-1.3.1.jar" in your project.)

public class geocoding {

/*Geocode request URL. Here see we are passing "json" it means we will get the output in JSON format.
* You can also pass "xml" instead of "json" for XML output.
* For XML output URL will be "http://maps.googleapis.com/maps/api/geocode/xml";
*/

private static final String URL = "http://maps.googleapis.com/maps/api/geocode/json";
private double lng;
private double lat;

        public double getLng() {
                return lng;
        }

        public void setLng(double lng) {
                this.lng = lng;
        }

        public double getLat() {
                return lat;
        }

        public void setLat(double lat) {
                this.lat = lat;
        }

        /*
        * Here the fullAddress String is in format like "address,city,state,zipcode". Here address means "street number + route" .
        *
        */
        public static void main(String[] args) throws IOException, ParseException {
//                geocoding bb = new geocoding();
//                String path = "\"제주특별자치도 제주시 거로중길 28\"";
//                ArrayList<Double> ret = bb.run(path);
//
//                System.out.println("result location");
//                System.out.println("lng : "+ret.get(0));
//                System.out.println("lat : "+ret.get(1));
        }

        public void run(String path) throws IOException, ParseException {
                String ret = getJSONByGoogle(path);
//                System.out.println(ret);
                JSONObject json = stringToJson(ret);
//                System.out.println(json);
                Iterator it = json.keySet().iterator();
//                while (it.hasNext())
//                        System.out.println(it.next());

//                System.out.println(json.get("results"));
                JSONArray jsonArray = (JSONArray) json.get("results");

                JSONObject object = (JSONObject) jsonArray.get(0);
                Iterator bb = object.keySet().iterator();
//                while (bb.hasNext())
//                        System.out.println(bb.next());

                JSONObject object1 = (JSONObject) object.get("geometry");
                JSONObject object2 = (JSONObject) object1.get("location");
                Iterator itt = object2.values().iterator();

                ArrayList<Double> result = new ArrayList();

                while (itt.hasNext()){
//                        System.out.println(itt.next());
                        result.add((Double) itt.next());
                }


//                System.out.println("lng : "+result.get(0));
                setLng(result.get(0));

//                System.out.println("lat : "+result.get(1));
                setLat(result.get(1));
        }



        public JSONObject stringToJson(String string) throws ParseException {
                JSONParser parser = new JSONParser();
                Object obj = parser.parse(string);
                JSONObject jsonObj = (JSONObject) obj;

                return jsonObj;
        }

        public String getJSONByGoogle(String fullAddress) throws IOException {

        /*
        * Create an java.net.URL object by passing the request URL in constructor.
        * Here you can see I am converting the fullAddress String in UTF-8 format.
        * You will get Exception if you don't convert your address in UTF-8 format. Perhaps google loves UTF-8 format. :)
        * In parameter we also need to pass "sensor" parameter.
        * sensor (required parameter) — Indicates whether or not the geocoding request comes from a device with a location sensor. This value must be either true or false.
        */
                URL url = new URL(URL + "?address=" + URLEncoder.encode(fullAddress, "UTF-8"));

        // Open the Connection
                URLConnection conn = url.openConnection();

        //This is Simple a byte array output stream that we will use to keep the output data from google.
                ByteArrayOutputStream output = new ByteArrayOutputStream(1024);

        // copying the output data from Google which will be either in JSON or XML depending on your request URL that in which format you have requested.
                IOUtils.copy(conn.getInputStream(), output);

        //close the byte array output stream now.
                output.close();

                return output.toString(); // This returned String is JSON string from which you can retrieve all key value pair and can save it in POJO.
                }
}

