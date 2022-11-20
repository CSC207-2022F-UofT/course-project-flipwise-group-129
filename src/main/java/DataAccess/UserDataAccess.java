package DataAccess;
import DataAccessInterface.*;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.*;

public class UserDataAccess extends DataAccess implements UserDataInterface {
    public final File userFile = new File("users.json");;
    private final Map<String, String> userMap = new HashMap<>();
    public UserDataAccess() throws IOException, ParseException {
        super.readFile(groupFile, userMap);
    }
    @Override
    public void addorUpdateUser(String username, String password) throws IOException {
        super.addorUpdateEntity(groupFile, userMap, username, password);
    }

    @Override
    public boolean userIdExists(String username) {
        return userMap.containsKey(username);
    }

    @Override
    public String userAsString(String username) {
        return userMap.get(username);
    }
}
