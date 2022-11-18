package DataAccess;
import DataAccessInterface.UserDataInterface;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.*;

public class UserDataAccess implements UserDataInterface {
    private final File jsonFile;
    private final Map<String, String> users = new HashMap<>();

    public UserDataAccess(String jsonPath) throws IOException, ParseException {
        this.jsonFile = new File(jsonPath);
        FileReader reader = new FileReader(jsonFile);
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(reader);
        ((JSONArray) obj).forEach( grpObj -> parseUserObject( (JSONObject) grpObj ) );

    }

    private void parseUserObject(JSONObject userObject) {
        JSONObject userDetails = new JSONObject();
        userDetails.put("username", this.getUsername(userObject));
        userDetails.put("password", this.getPassword(userObject));
        userDetails.put("groups", this.getGroups(userObject));

        users.put("username", String.valueOf(userDetails));
    }

    private String getUsername(JSONObject userObject) {
        return (String) userObject.get("username");
    }

    private String getPassword(JSONObject userObject) {
        return (String) userObject.get("password");
    }

    private String getGroups(JSONObject userObject) {
        return (String) userObject.get("groups");
    }

    public void FileUserRead(String jsonPath) {

        if (jsonFile.length() == 0) {
            this.FileUserSave();

        } else {

        }
    }

    public void FileUserSave(String jsonPath) {

    }

    private void FileUserSave() {
        FileWriter writer = new FileWriter(jsonFile);
        JSONArray userList = new JSONArray();
        users.forEach((username, userData) -> {
            JSONObject userDetails = new JSONObject();
            userDetails.put("username", username);
            userDetails.put("password", password);
            userDetails.put("groups", groups);
            JSONObject userObject = new JSONObject();
            userDetails.put("user")
        })
    }

    public boolean existsByUsername(String username) {
        return true;
    }
}
