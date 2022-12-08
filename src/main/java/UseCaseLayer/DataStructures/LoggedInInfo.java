/**
 * Data structure for storing output after a user login
 */
package UseCaseLayer.DataStructures;
import java.util.List;

public class LoggedInInfo {
    private boolean statusBool = true;
    private String username;

    private List<List<Object>> userAllGroups;

    /**
     * Constructor called when the login was successful. User data is passed through this data structure.
     *
     * @param username      username
     * @param allGroups the groups a user is in and the groups associated details
     */
    public LoggedInInfo(String username, List<List<Object>> allGroups) {
        this.username = username;
        this.userAllGroups = allGroups;
    }

    /**
     * Constructor called when the login fails
     * @param success to report the login as failed
     */
    public LoggedInInfo(boolean success) {
        this.statusBool = success;
    }

    /**
     * @return if the login was successful
     */
    public boolean statusBool() {
        return statusBool;
    }

    /**
     *
     * @return the username of the user who logged in
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return the details of groups that a user is a part of
     */
    public List<List<Object>> getUserAllGroups() {
        return userAllGroups;
    }
}