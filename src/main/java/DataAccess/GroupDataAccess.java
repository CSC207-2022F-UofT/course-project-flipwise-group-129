package DataAccess;

import DataAccessInterface.GroupDataInterface;
//import org.*;
import java.io.FileWriter;
import org.json.simple.JSONObject;
import java.io.File;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.*;
import java.io.*;


public class GroupDataAccess implements GroupDataInterface {
    private final File jasonFile;
    private final Map<String, String> groups = new HashMap<>();
    //gotta replace plannediteminfo with an abstract class or interface bc lots of datatypes need to fulfill
    public GroupDataAccess(String jasonPath) throws IOException, ParseException {
        // this parses the json file that already exists into a hashmap of id and info strings
//        this.jsonFile = new FileWriter(jasonPath);
        this.jasonFile = new File(jasonPath);
        FileReader reader = new FileReader(jasonFile);
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(reader);
        ((JSONArray) obj).forEach( grpObj -> parseGroupObject( (JSONObject) grpObj ) );
    }
    private void parseGroupObject(JSONObject groupObject){
        //Get employee object within list
        JSONObject groupObjects = (JSONObject) groupObject.get("group");

        //Get groupId first name
        String groupId = (String) groupObjects.get("groupId");
        //Get groupInfo last name
        String groupInfo = (String) groupObjects.get("groupData");
        groups.put(groupId, groupInfo);
    }
    private void save() throws IOException {
        //saves all the information once it's been updated
        FileWriter writer = new FileWriter(jasonFile);
        JSONArray groupList = new JSONArray();
        groups.forEach((groupkey, groupData) -> {
            JSONObject groupDetails = new JSONObject();
            groupDetails.put("groupId", groupkey);
            groupDetails.put("groupData", groupData);
            JSONObject groupObject = new JSONObject();
            groupDetails.put("group", groupDetails);
            groupList.add(groupObject);
        });
        writer.write(groupList.toString());
        writer.flush();
    }

    @Override
    public void addorUpdateGroup(String groupId, String groupInfo) throws IOException {
        // needs inputted the new or updated group to string alongside the group's id
        groups.put(groupId, groupInfo);
        save();
    }

    @Override
    public String groupAsString(String groupId) {
        return groups.get(groupId);
    }

//    public static void main(String[] args) throws IOException {
//        File newFile = new File("groups.json");
//
//        FileWriter writer = new FileWriter(newFile);
//        JSONObject groupDetails = new JSONObject();
//        groupDetails.put("groupId", "groupkey");
//        writer.write(groupDetails.toJSONString());
//    }
}
