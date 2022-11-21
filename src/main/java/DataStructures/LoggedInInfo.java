/**
 * Data structure for storing output after a user login
 */
package DataStructures;

import java.util.List;

public class LoggedInInfo {
    private String statusMessage = "true";
    private String username;

    private List<List<List<String>>> userAllGroups;
    private List<String> groups;
    private String groupName;
    private String groupId;
    private List<String> planningList;
    private List<String> purchaseList;
    private List<String> usersInGroup;

    /**
     * Constructor called when the login was successful. User data is passed through this data structure.
     * @param username username
     * @param groups groups that is user is in
     * @param groupName group names that the user is in
     * @param groupId group IDs that the user is in
     * @param planningList the planning list for each group the user is in
     * @param purchaseList the purchase list for each group the user is in
     * @param usersInGroup the list of users that are in each group the user is in
     */
    public LoggedInInfo(String username, List<String> groups, String groupName, String groupId, List<String> planningList, List<String> purchaseList, List<String> usersInGroup) {
        this.statusMessage
        this.username = username;
        this.groups = groups;
        this.groupName = groupName;
        this.groupId = groupId;
        this.planningList = planningList;
        this.purchaseList = purchaseList;
        this.usersInGroup = usersInGroup;
    }

    /**
     * Constructor called when the login fails
     */
    public LoggedInInfo(String successMessage) {
        this.statusMessage = successMessage;
    }

    public String getUsername() {
        return this.username;
    }

    public List<List<List<String>>> getUserAllGroups() {

    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setGroups() {

    }

    public void setGroupName() {

    }

    public void setGroupId() {

    }

    public void setPlanningList() {

    }

    public void setPurchaseList() {

    }

    public void setUsersInGroup() {

    }
}
