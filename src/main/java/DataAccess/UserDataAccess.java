package DataAccess;
import DataAccessInterface.UserDataInterface;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.*;

public class UserDataAccess implements UserDataInterface {
    private final File jsonFile;
    private final Map<String, String> userMap = new HashMap<>();

    public UserDataAccess(String jsonPath) throws IOException, ParseException {
        this.jsonFile = new File(jsonPath);
        FileReader reader = new FileReader(jsonFile);
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(reader);
        JSONObject userjson = (JSONObject) obj;
        userjson.keySet().forEach(keyValue ->
        {
            parseUserArray(userjson, (String) keyValue);
        });

    }

    private void parseUserArray(JSONObject userjson, String keyValue) {
        String username = (String) userjson.get(keyValue);
        JSONObject userDetails = (JSONObject) userjson.get(username);
//        String password = (String) userDetails.get("password");
//        ArrayList<String> groups = getGroups(userDetails);

        userMap.put(username, String.valueOf(userDetails));
    }

    private ArrayList<String> getGroups(JSONObject userDetails) {
        JSONArray groups = (JSONArray) userDetails.get("groups");
        ArrayList<String> listOfGroups = null;
        if (groups != null) {
            listOfGroups = new ArrayList<String>(groups);
        }
        return (listOfGroups);
    }

    private void FileUserSave() throws IOException {
        FileWriter writer = new FileWriter(jsonFile);
        JSONObject userList = new JSONObject();
        userMap.forEach((username, userData) -> {
            JSONObject userDetails = new JSONObject();
            userDetails.put("password", userData.get("password"));
            userDetails.put("groups", groups);
            userList.put(username, userDetails);
        });
        writer.write(userList.toString());
        writer.flush();
    }

    @Override
    public void addUser(String username, String password) {
        HashMap<String, Object> userDetails = new HashMap<String, Object>();
        ArrayList<String> groups = new ArrayList<String>();
        userDetails.put("password", password);
        userDetails.put("groups", groups);
        this.userMap.put(username, String.valueOf(userDetails));
    }

    @Override
    public boolean existsByUsername(String username) {
        return this.userMap.containsKey(username);
    }
}
