/**
 * Data structure for storing output after a user login
 */
package DataStructures;

import java.util.List;

public class LoggedInInfo {

    /**
     * Constructor called when the login was successful. User data is passed through this data structure.
     *
     * @param username      username
     * @param allGroups the groups a user is in and the groups associated details
     */
    public LoggedInInfo(String username, List<List<Object>> allGroups) {
    }

    /**
     * Constructor called when the login fails
     * @param failed to report the login as failed
     */
    public LoggedInInfo(boolean failed) {
    }

}
