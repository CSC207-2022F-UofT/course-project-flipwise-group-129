/**
 * Data structure for storing output after a user login
 */
package DataStructures;

import java.util.List;

public class LoggedInInfo {
    private boolean statusBool = true;
    private String username;

    private List<List<List<String>>> userAllGroups;

    /**
     * Constructor called when the login was successful. User data is passed through this data structure.
     *
     * @param username      username
     * @param allGroups the groups a user is in and the groups associated details
     */
    public LoggedInInfo(String username, List<List<List<String>>> allGroups) {
        this.username = username;
        this.userAllGroups = allGroups;
    }

    /**
     * Constructor called when the login fails
     * @param failed to report the login as failed
     */
    public LoggedInInfo(boolean failed) {
        this.statusBool = failed;
    }

    public String getUsername() {
        return this.username;
    }

    public List<List<List<String>>> getUserAllGroups() {
        return this.userAllGroups;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
