package UseCaseLayer.DataStructures;

import java.util.List;
public class CreatedGroupInfo {

    /*
    Represents the output to be presented onto the view
     */

    private final String groupId; //the id of the group that was created
    private final String groupName; // the name of the group that was created

    private final String user; // return the singular user that's in this group

    private final List<String> groupIds; // return the ids of all the groups the user is a part of

    private final List<String> groupNames; // return the names of the group the user is a part of

    private final String error; //any error if arises will contain a description here

    /**
     * Creates an instance of all the information to be displayed onto the view, in case of no error
     * @param groupId the id of the group created
     * @param groupName the name of the group created
     * @param user the user who created the group
     * @param groupIds the ids of all the groups the user is in
     * @param groupNames the names of all the groups the user is in
     */
    public CreatedGroupInfo(String groupId, String groupName, String user, List<String> groupIds, List<String> groupNames){
        this.groupId = groupId;
        this.groupName = groupName;
        this.user = user;
        this.groupIds = groupIds;
        this.groupNames = groupNames;
        this.error = null;
    }

    /**
     * Group creation failed
     * @param error reason on why it failed
     */

    public CreatedGroupInfo(String error){
        this.error = error;
        this.user = null;
        this.groupNames = null;
        this.groupIds = null;
        this.groupId = null;
        this.groupName = null;
    }

    /**
     * get the id of the created group
     * @return the id of the created group
     */
    public String getId(){
        //return the id of the group that was created
        return this.groupId;
    }

    /**
     * get the name of the created group
     * @return the name of the created group
     */
    public String getGroupName(){
        // return the name of the group  that was created
        return this.groupName;
    }

    /**
     * get the users in the group
     * @return the sole user in the group
     */
    public String getUsers(){
        //return all the users in this newly created group
        return this.user;
    }

    /**
     * get all the ids of all the groups the sole user is in
     * @return the ids of all the groups the sole user is in
     */
    public List<String> getAllGroupIds(){
        // return all the ids of all the groups the user is a part of
        return this.groupIds;
    }

    /**
     * get all the names of all the groups the sole user is in
     * @return the names of all the groups the sole user is in
     */
    public List<String> getAllGroupNames(){
        // return the names of all the groups the user is a part of
        return this.groupNames;
    }

    /**
     * get the error on why group creation failed
     * @return why group creation failed
     */
    public String getError() { return this.error; }

}
