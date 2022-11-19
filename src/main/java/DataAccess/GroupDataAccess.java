package DataAccess;

import DataAccessInterface.GroupDataInterface;
//import org.*;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.*;
import java.io.*;


public class GroupDataAccess implements GroupDataInterface {
    private final File jasonFile;
    private final Map<String, String> groups = new HashMap<>();
    public GroupDataAccess(String jasonPath) throws IOException, ParseException {
        // this parses the json file into a hashmap of id and info strings
        this.jasonFile = new File(jasonPath);
        FileReader reader = new FileReader(jasonFile);
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(reader);
        ((JSONArray) obj).forEach( grpObj -> parseGroupObject( (JSONObject) grpObj ) );
    }
    private void parseGroupObject(JSONObject groupObject){
        //Get group object within list
        JSONObject groupObjects = (JSONObject) groupObject.get("group");
        //Get groupId
        String groupId = (String) groupObjects.get("groupId");
        //Get groupInfo
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
        // takes groupId and groupInfo string and adds it to the group hashmap
        // if the key already exists, this overrides the information automatically
        groups.put(groupId, groupInfo);
        save();
    }

    @Override
    public String groupAsString(String groupId) {
        return groups.get(groupId);
    }
}
