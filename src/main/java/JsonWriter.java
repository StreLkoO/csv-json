import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class JsonWriter {
    private static JsonWriter instance;

    private JsonWriter() {
    }

    public static JsonWriter get() {
        if (instance == null) {
            instance = new JsonWriter();
        }
        return instance;
    }

    public void writeListInJson(List<Employee> list, String path) {
        String json = listToJSON(list);
        writeString(json, path);
    }

    private String listToJSON(List<Employee> list) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        Type listType = new TypeToken<List<Employee>>() {
        }.getType();
        return gson.toJson(list, listType);
    }

    private void writeString(String json, String path) {
        try (FileWriter fileWriter = new FileWriter(path)) {
            fileWriter.write(json);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
