package DataStructures;

import java.util.List;
public class CreatedGroupInfo {

    /*
    Represents the output to be presented onto the view
     */

    private final String groupId; //the id of the group that was created
    private final String groupName; // the name of the group that was created

    private final String user; // return the singular user thats in this group

    private final List<String> groupIds; // return the ids of all the groups the user is a part of

    private final List<String> groupNames; // return the names of the group the user is a part of

    public CreatedGroupInfo(String groupId, String groupName, String user, List<String> groupIds, List<String> groupNames){
        this.groupId = groupId;
        this.groupName = groupName;
        this.user = user;
        this.groupIds = groupIds;
        this.groupNames = groupNames;
    }

    public String getId(){
        //return the id of the group that was created
        return this.groupId;
    }

    public String getGroupName(){
        // return the name of the group  that was created
        return this.groupName;
    }

    public String getUsers(){
        //return all the users in this newly created group
        return this.user;
    }

    public List<String> getAllGroupIds(){
        // return all the ids of all the groups the user is a part of
        return this.groupIds;
    }

    public List<String> getAllGroupNames(){
        // return the names of all the groups the user is a part of
        return this.groupNames;
    }



}
