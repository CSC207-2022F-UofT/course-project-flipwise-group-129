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
    /*
     * Parent class of Users, Groups, and Items data access classes that facilitates
     * redundant code of reading and modifying data stored in the entity's respective json file.
     */

    /**
     * This function will be called to read the file from the filepath and store it in a given map
     * @param jasonFile the filepath storing entity data to read
     * @param entityMap the map to store all read data from filepath, this should be mutated
     */
    public void readFile(File jasonFile, Map<String, String> entityMap) throws IOException, ParseException {
        if(jasonFile.length() != 0){
            FileReader reader = new FileReader(jasonFile);
            JSONParser jsonParser = new JSONParser();
            Object obj = jsonParser.parse(reader);
            ((JSONArray) obj).forEach(currObj -> parseObject((JSONObject) currObj, entityMap));
        }
    }

    /**
     * This function will be called to parse a specific JSONObject and insert into the map
     * @param entityObject the current object to parse and put into map
     * @param entityMap the map to store each object parsed from filepath, this should be mutated
     */
    protected void parseObject(JSONObject entityObject, Map<String, String> entityMap) {
        String id = (String) entityObject.get("entityId");
        String entityData = (String) entityObject.get("entityInfo");
        entityMap.put(id, entityData);
    }

    /**
     * This function saves all information currently in the map and writes it onto the filepath
     * @param jasonFile the filepath storing entity data to read
     * @param entityMap the map that stores all entity objects parsed from filepath
     */
    protected void save(File jasonFile, Map<String, String> entityMap) throws IOException {
        FileWriter writer = new FileWriter(jasonFile);
        JSONArray entityList = new JSONArray();
        entityMap.forEach((key, data) -> {
            JSONObject entityDetails = new JSONObject();
            entityDetails.put("id", key);
            entityDetails.put("entityData", data);
            entityList.add(entityDetails);
        });
        writer.write(entityList.toString());
        writer.flush();
    }

    /**
     * This function updates the map and saves all information onto the filepath
     * @param jasonFile the filepath storing entity data to read
     * @param entityMap the map that stores all entity objects parsed from filepath
     * @param entityId the current entityId to be updated or added to the map
     * @param entityInfo the information in string form associated with the current entity to be updated or added
     */
    public void addorUpdateEntity(File jasonFile, Map<String, String> entityMap, String entityId, String entityInfo) throws IOException {
        entityMap.put(entityId, entityInfo);
        save(jasonFile, entityMap);
    }
}