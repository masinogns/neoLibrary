package MySQL;

/**
 * Created by masinogns on 2017. 11. 1..
 */
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.Map;

public class aaa {

    public static void main(String[] args) {

    }

    public Object getJSON(String json, String searchWord, Map<String, Object> resultMap) {  //Json DATA 전 영역 탐사 및 원하는 데이터를 Map 형태로 리턴시킴
        JSONObject jsonObj = null;
        try {
            jsonObj = new JSONObject(json);
            Iterator itr = jsonObj.keys();
            int countJSON = 0;
            while (itr.hasNext()) {

                String key = (String)itr.next();
                Object value = jsonObj.get(key);
                String selecter = (String)value.toString();

                if(key.equals(searchWord)){  // 조건에 걸린 word 값을 찾아서 map 형태로 리턴
                    resultMap.put(key, value);
                }

                if(selecter != null && !selecter.equals("") && !selecter.equals("null") && jsonObj.get(key) instanceof JSONArray){
                    if((JSONArray)jsonObj.get(key) instanceof JSONArray){
                        JSONArray jsonArray = (JSONArray)jsonObj.get(key);
                        //this.logger.debug(jsonArray.toString());
                        for(int i = 0 ; i<jsonArray.length() ; i++){
                            jsonArray.get(i);
                            JSONObject jsonObj2 = new JSONObject(jsonArray.get(i).toString());
                            getJSON(jsonObj2.toString(), searchWord, resultMap);
                        }
                    }
                }
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resultMap;
    }
}
