package DataAccess;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class DataAccess {
    /**
     * Users, Groups, and Items are stored and modified.
     */

    public void readFile(File jasonFile, Map<String, String> entityMap) throws IOException, ParseException {
        FileReader reader = new FileReader(jasonFile);
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(reader);
        ((JSONArray) obj).forEach(currObj -> parseObject((JSONObject) currObj, entityMap));
    }

    protected void parseObject(JSONObject entityObject, Map<String, String> entityMap) {
        JSONObject entityObjects = (JSONObject) entityObject.get("entity");
        String id = (String) entityObjects.get("id");
        String entityData = (String) entityObjects.get("entityData");

        entityMap.put(id, entityData);
    }

    protected void save(File jasonFile, Map<String, String> entityMap) throws IOException {
        FileWriter writer = new FileWriter(jasonFile);
        JSONArray entityList = new JSONArray();
        entityMap.forEach((key, data) -> {
            JSONObject entityDetails = new JSONObject();
            entityDetails.put("id", key);
            entityDetails.put("entityData", data);
            JSONObject entityObject = new JSONObject();
            entityList.add(entityObject);
        });
        writer.write(entityList.toString());
        writer.flush();
    }
    public void addorUpdateEntity(File jasonFile, Map<String, String> entityMap, String groupId, String groupInfo) throws IOException {
        entityMap.put(groupId, groupInfo);
        save(jasonFile, entityMap);
    }
}
