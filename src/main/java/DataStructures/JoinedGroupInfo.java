package DataStructures;

import java.util.List;

public class
JoinedGroupInfo {

    /*
    Represents the output to be presented about the information of a group
    into which a user was just added
     */

    private final List<String> usersInGroup; // all the users in the group
    private final List<String> groupIds; // the ids of all the groups the current user is a part of
    private final List<String> groupNames; // the names of all the groups that the current user is a part of

    private final List<List<String>> planningList; //all the [itemIds, itemNames] in the planning list of this group

    private final List<List<String>> purchasedList; // all the [itemIds, itemNames, item price, buyer username] in the purchase list of this group

    private final String error; //any error if arises will contain a description here

    /**
     * Creates an instance of all the information to be displayed onto the view, in case of no error
     * @param usersInGroup all the users in the group
     * @param groupIds all the ids of all the groups the newly joined user is in
     * @param groupNames all the names of all the groups the newly joined user is in
     */
    public JoinedGroupInfo(List<String> usersInGroup, List<String> groupIds, List<String> groupNames, List<List<String>> planningList, List<List<String>> purchasedList){
        this.groupIds = groupIds;
        this.groupNames = groupNames;
        this.usersInGroup = usersInGroup;
        this.planningList = planningList;
        this.purchasedList = purchasedList;
        this.error = null;
    }

    /**
     * Group join failed
     * @param error reason on why it failed
     */
    public JoinedGroupInfo(String error){
        this.error = error;
        this.groupIds = null;
        this.usersInGroup = null;
        this.groupNames = null;
        this.purchasedList = null;
        this.planningList = null;
    }

    /**
     * get all the users in the group
     * @return all the users in the group
     */
    public List<String> getUsersInGroup(){ return this.usersInGroup; }

    /**
     * get all the ids of all the groups the newly joined user is in
     * @return the ids of all the groups the newly joined user is in
     */
    public List<String> getGroupIds() { return this.groupIds; }

    /**
     * get all the names of all the groups the newly joined user is in
     * @return the names of all the groups the newly joined user is in
     */
    public List<String> getGroupNames() { return this.groupNames; }

    /**
     * get the list of all items planned to be purchased in this group
     * @return mapping of item ids to item names of items planned in this group
     */
    public List<List<String>> getPlanningList() { return this.planningList;}

    /**
     * get the list of all items purchased in this group
     * @return a mapping of item ids to item names of items purchased in this group
     */
    public List<List<String>> getPurchasedList() { return this.purchasedList;}

    /**
     * get the error on why group creation failed
     * @return why group creation failed
     */
    public String getError() { return this.error; }

}
