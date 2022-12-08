package UseCaseLayer.DataAccessInterface;

import org.json.simple.parser.ParseException;

import java.io.IOException;

public interface UserDataInterface {
    /*
     * This represents a UserDataInterface defines methods needed to
     * facilitate data reading and writing from users.json
     */

    /**
     * This function will be called to add or update any user entity to the map and saving the updated information
     * back into the users.json file
     * @param username the username of the current modified or added user
     * @param userInfo the summarized user information of the current modified or added user
     */
    void addorUpdateUser(String username, String userInfo) throws IOException, ParseException;

    /**
     * This function will be called to check if a user entity already has been entered in users.json file
     * @param username the username of the current modified or added user
     * @return true if the username was found from the users.json file and false otherwise
     */
    boolean userIdExists(String username) throws IOException, ParseException;

    /**
     * This function will be called to retrieve the stored information from users.json of a user based on its username
     * @param username the username of the desired user
     * @return user details corresponding to username in string form
     */
    String userAsString(String username) throws IOException, ParseException;
}
