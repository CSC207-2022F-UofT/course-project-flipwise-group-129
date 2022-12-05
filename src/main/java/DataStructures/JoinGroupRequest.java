package DataStructures;

public class JoinGroupRequest {

    /*
     Represents all the information passed in by the user to join a group
     */

    private final String groupId; // the id of the group that you need to join

    private final String username; // the username of the user that wants to join a group

    /**
     * Create an object representing all the information to join this group
     * @param groupId the id of the group to be joined
     * @param username the name of the user who wants to join
     */
    public JoinGroupRequest(String groupId, String username){
        this.groupId = groupId;
        this.username = username;
    }

    /**
     * get the id of the group to join
     * @return the id of the group to join
     */
    public String getGroupId() {
        // get the id of the group to join
        return this.groupId; }

    /**
     * get the name of the user to add to group
     * @return the name of the user to add to group
     */
    public String getUsername() {
        // get the username of the user
        return this.username; }
}
