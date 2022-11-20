//package DataAccess;
//import DataAccessInterface.*;
//import org.json.simple.*;
//import org.json.simple.parser.ParseException;
//
//import java.io.*;
//import java.util.*;
//
//public class UserDataAccess extends DataAccess implements UserDataInterface {
//
//    public UserDataAccess(String jsonPath) throws IOException, ParseException {
//        super(jsonPath);
//    }
//
//    @Override
//    protected void parseObject(JSONObject userjson) {
//        JSONObject userObjects = (JSONObject) userjson.get("user");
//        String username = (String) userObjects.get("username");
//        String userDetails = (String) userObjects.get("userDetails");
//
//        userMap.put(username, userDetails);
//    }
//
//    @Override
//    protected void save() throws IOException {
//        FileWriter writer = new FileWriter(jasonFile);
//        JSONArray userList = new JSONArray();
//        userMap.forEach((username, userData) -> {
//            JSONObject userDetails = new JSONObject();
//            userDetails.put("username", username);
//            userDetails.put("userDetails", userData);
//            JSONObject userObject = new JSONObject();
//            userDetails.put("user", userDetails);
//
//            super.entityMap.add(userObject);
//        });
//        writer.write(userList.toString());
//        writer.flush();
//    }
//
//    @Override
//    public void addUser(String username, String password) {
//        HashMap<String, Object> userDetails = new HashMap<String, Object>();
//        ArrayList<String> groups = new ArrayList<String>();
//        userDetails.put("password", password);
//        userDetails.put("groups", groups);
//        this.userMap.put(username, String.valueOf(userDetails));
//    }
//
//    @Override
//    public boolean existsByUsername(String username) {
//        return this.userMap.containsKey(username);
//    }
//}
