import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class JsonParser {

    private static JsonParser instance;

    private JsonParser() {
    }

    public static JsonParser get() {
        if (instance == null) {
            instance = new JsonParser();
        }
        return instance;
    }

    protected String readString(String path) {
        JSONParser jsonParser = new JSONParser();
        try {
            JSONArray array = (JSONArray) jsonParser.parse(new FileReader(path));
            return String.valueOf(array);

        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return null;
        }

    }

    protected List<Employee> jsonToList(String json) {
        List<Employee> staff = new ArrayList<>();
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        JSONParser jsonParser = new JSONParser();
        try {
            JSONArray array = (JSONArray) jsonParser.parse(json);
            for (Object o : array) {
                JSONObject jsonObject = (JSONObject) o;
                Employee e = gson.fromJson(String.valueOf(jsonObject), Employee.class);
                staff.add(e);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return staff;
    }

    public List<Employee> JsonFileToList(String path) {
        String json = readString(path);
        return jsonToList(json);
    }

}
