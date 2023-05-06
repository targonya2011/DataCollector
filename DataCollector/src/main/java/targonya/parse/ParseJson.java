package targonya.parse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.*;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.parser.ParseException;

public class ParseJson {
    public Map<Object, Object> parse(String path) throws IOException, ParseException {

        Object obj = new JSONParser().parse(new FileReader(path));
        JSONArray ja = (JSONArray) obj;
        Map<Object, Object> stations = new HashMap<>();
        for (int i = 0; i < ja.size(); i++) {
            JSONObject jo = (JSONObject) ja.get(i);
            stations.put(jo.get("station_name"), jo.get("depth"));
        }
//        stations.entrySet().forEach(entry ->
//                System.out.println("Название станции: " + entry.getKey() + "\n" +
//                        "Глубина: " + entry.getValue()));
        return stations;
    }




}
