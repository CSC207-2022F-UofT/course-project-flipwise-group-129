package DataAccess;
import DataAccessInterface.*;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.*;

public class UserDataAccess extends DataAccess implements UserDataInterface {
    /**
     * Implements UserDataInterface-
     * This is an UserDataAccess which facilitates data reading and writing from users.json to update
     */
    public File userFile = new File("src/main/users.json");
    private final Map<String, String> userMap = new HashMap<>();

    /**
     * Creates a user data access instance by first reading the users.json file and storing all information locally
     */
    public UserDataAccess() throws IOException, ParseException {
        super.readFile(userFile, userMap);
    }

    /**
     * Creates a test user data access instance by first reading and storing the testusers.json file locally
     * @param testString a string confirming database to access is test
     */
    public UserDataAccess(String testString) throws IOException, ParseException {
        this.userFile = new File("././src/main/testusers.json");
        super.readFile(userFile, userMap);
    }

    /**
     * This function will be called to add or update any user entity to the map and saving the updated information
     * back into the users.json file
     * @param username the username of the current modified or added user
     * @param userInfo the summarized user information of the current modified or added user
     */
    @Override
    public void addorUpdateUser(String username, String userInfo) throws IOException {
        super.addorUpdateEntity(userFile, userMap, username, userInfo);
    }

    /**
     * This function will be called to check if a user entity already has been entered in users.json file
     * @param username the username of the current modified or added user
     * @return true if the username was found from the users.json file and false otherwise
     */
    @Override
    public boolean userIdExists(String username) {
        return userMap.containsKey(username);
    }

    /**
     * This function will be called to retrieve the stored information from users.json of a user based on its username
     * @param username the username of the desired user
     * @return user details corresponding to username in string form
     */
    @Override
    public String userAsString(String username) {
        return userMap.get(username);
    }
}
