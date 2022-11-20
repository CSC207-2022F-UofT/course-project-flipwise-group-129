package DataStructures;

import java.util.List;

public class JoinedGroupInfo {

    /*
    Represents the output to be presented about the information of a group
    into which a user was just added
     */

    private final List<String> usersInGroup; // all the users in the group
    private final List<String> groupIds; // the ids of all the groups the current user is a part of
    private final List<String> groupNames; // the names of all the groups that the current user is a part of

    private final String error; //any error if arises will contain a description here

    public JoinedGroupInfo(List<String> usersInGroup, List<String> groupIds, List<String> groupNames){
        this.groupIds = groupIds;
        this.groupNames = groupNames;
        this.usersInGroup = usersInGroup;
        this.error = null;
    }

    public JoinedGroupInfo(String error){
        this.error = error;
        this.groupIds = null;
        this.usersInGroup = null;
        this.groupNames = null;
    }

    public List<String> getUsersInGroup(){ return this.usersInGroup; }
    public List<String> getGroupIds() { return this.groupIds; }
    public List<String> getGroupNames() { return this.groupNames; }

    public String getError() { return this.error; }

}
