package DataAccess;

import DataAccessInterface.EntityDataInterface;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DataAccess implements EntityDataInterface {
    /**
     * Users, Groups, and Items are stored and modified.
     */
    public final File jasonFile;
    public final Map<String, String> entityMap = new HashMap<>();

    public DataAccess(String jasonPath) throws IOException, ParseException {
        this.jasonFile = new File(jasonPath);
        FileReader reader = new FileReader(jasonFile);
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(reader);
        ((JSONArray) obj).forEach(currObj -> parseObject((JSONObject) currObj));
    }

    protected void parseObject(JSONObject entityObject) {
        JSONObject entityObjects = (JSONObject) entityObject.get("entity");
        String id = (String) entityObjects.get("id");
        String entityData = (String) entityObjects.get("entityData");

        entityMap.put(id, entityData);
    }

    protected void save() throws IOException {
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

    @Override
    public void addorUpdateEntity(String groupId, String groupInfo) throws IOException {
        entityMap.put(groupId, groupInfo);
        save();
    }

    @Override
    public String entityAsString(String groupId) {
        return entityMap.get(groupId);
    }

    @Override
    public boolean entityIdExists(String id) {
        return entityMap.containsKey(id);
    }
}
