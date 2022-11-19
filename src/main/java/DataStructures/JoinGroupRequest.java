package DataStructures;

public class JoinGroupRequest {

    /*
     Represents all the information passed in by the user to join a group
     */

    private String groupId; // the id of the group that you need to join

    private String username; // the username of the user that wants to join a group

    public JoinGroupRequest(String groupId, String username){
        this.groupId = groupId;
        this.username = username;
    }

    public String getGroupId() {
        // get the id of the group to join
        return this.groupId; }
    public String getUsername() {
        // get the username of the user
        return this.username; }
}
