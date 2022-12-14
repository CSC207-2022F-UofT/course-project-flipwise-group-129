package UseCases;

import DataStructures.JoinGroupRequest;
import DataStructures.JoinedGroupInfo;
import Entities.*;
import InputBoundary.GroupJoinBoundaryIn;
import OutputBoundary.GroupJoinBoundaryOut;
import DataAccessInterface.*;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.simple.parser.ParseException;

public class GroupJoin implements GroupJoinBoundaryIn{
    /**
     * Use case  that allows for the joining of a user into a specified group
     */
    final GroupDataInterface groupDsInterface; //the database interface that enables us to add and obtain group info
    final UserDataInterface userDsInterface; // the database interface that enables us to add and obtain user infos
    final GroupJoinBoundaryOut groupJoinPresenter; // presenter that will update view after use case executes

    /**
     * Constructor to initiate the use case
     * @param presenter an interface that is implemented by the presenter to access the output from use case
     * @param groupDsInterface interface to access group related database queries
     * @param userDsInterface interface to access user related database queries
     */
    public GroupJoin(GroupJoinBoundaryOut presenter, GroupDataInterface groupDsInterface, UserDataInterface userDsInterface){
        this.groupJoinPresenter = presenter;
        this.userDsInterface = userDsInterface;
        this.groupDsInterface = groupDsInterface;
    }

    /**
     * Allows the user to join a group using its group id
     * Does not allow if the user is already in the group
     * Otherwise, adds the user in and creates new Debt pairs to accommodate the new user
     * @param reqGroupInfo the data structure containing all the information required to add a user to the specified
     * group
     */
    @Override
    public JoinedGroupInfo joinGroup(JoinGroupRequest reqGroupInfo){
        //obtain the user from the database
        User user;
        try{
            user = this.getUserFromDb(reqGroupInfo.getUsername());
        }catch (RuntimeException | IOException | ParseException e) {
            JoinedGroupInfo joinedGroupInfo = new JoinedGroupInfo(e.toString());
            return this.groupJoinPresenter.prepareFailView(joinedGroupInfo);
        }

        //obtain the group from the database:
        Group group;
        try{
            group = this.getGroupFromDb(reqGroupInfo.getGroupId());
        }catch (RuntimeException | IOException | ParseException e){
            JoinedGroupInfo joinedGroupInfo = new JoinedGroupInfo(e.toString());
            return this.groupJoinPresenter.prepareFailView(joinedGroupInfo);
        }

        try{
            this.addUserToGroup(user, group);
        }catch (RuntimeException | IOException | ParseException e){
            JoinedGroupInfo joinedGroupInfo = new JoinedGroupInfo("User already in group");
            return this.groupJoinPresenter.prepareFailView(joinedGroupInfo);
        }

        //save info to db
        try{
            this.saveData(user, group);
        }catch (RuntimeException e){
            JoinedGroupInfo joinedGroupInfo = new JoinedGroupInfo("");
            return this.groupJoinPresenter.prepareFailView(joinedGroupInfo);
        }

        System.out.println(user.getGroups());

        //to controller
        JoinedGroupInfo joinedGroupInfo = this.createOutputData(user, group);
        return this.groupJoinPresenter.prepareSuccessView(joinedGroupInfo);

    }

    /**
     * gets the specified user by username from the database if exists
     * otherwise throws a Runtime exception
     * @param username contains the unique username that can be used to identify user data from database
     * @return returns the user object obtained from db
     */

    private User getUserFromDb(String username) throws IOException, ParseException {
        // get the user from the database and create a User interface
        //check if the user exists
        if (!this.userDsInterface.userIdExists(username)){
            throw new RuntimeException("User Id does not exist");
        }
        String userString = this.userDsInterface.userAsString(username);

        try {
            return User.fromString(userString);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Unable to process user from database");
        }
    }

    /**
     * gets the specified group from groupId provided from the database if exists
     * otherwise throws a runtime exception
     * @param groupId the unique identification id for a group that can be used to identify group data from database
     * @return returns the group object obtained form db
     */

    private Group getGroupFromDb(String groupId) throws IOException, ParseException {
        //obtain the group info form the database
        //check if the group exists
        if (this.groupDsInterface.groupIdExists(groupId)){
            throw new RuntimeException("Invalid GroupID provided");
        }
        String groupString = this.groupDsInterface.groupAsString(groupId);
        Group group;
        try {
            group = Group.fromString(groupString);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Unable to obtain group info from database");
        }
        return group;
    }

    /**
     * adds the user to group if not already in the group
     * else throws a runtime exception
     * @param user the user that requested group join
     * @param group the group that was requested to join
     */

    private void addUserToGroup(User user, Group group) throws IOException, ParseException {
        //check if user already in group?
        if (group.getUsers().contains(user.getUsername())){
            throw new RuntimeException("User already in group");
        }

        //adding all needed new debt pairs:
        PurchaseBalance grpPurchaseBalance = group.getPurchaseBalance();
        for (String groupUserName : group.getUsers()) {
            grpPurchaseBalance.addDebtPair(new Debt(getUserFromDb(groupUserName), user, group.getGroupId()));
            grpPurchaseBalance.addDebtPair(new Debt(user, getUserFromDb(groupUserName), group.getGroupId()));
        }
        //mutating method, so no need to reassign

        group.addUser(user.getUsername()); // add the user into the list of users in the group
        user.addGroup(group.getGroupId()); // add the group into the list of groups the user is a part of
    }

    /**
     * Saves the modified user and group data into the database
     * @param user the user object after modifications
     * @param group the group object with all the required new information about the list of users and debts
     */

    private void saveData(User user, Group group){
        //pass new info to db
        try {
            this.groupDsInterface.addorUpdateGroup(group.getGroupId(), group.toString());
        } catch (IOException | ParseException e) {
            throw new RuntimeException("Unable to modify group info to database");
        }
        try {
            this.userDsInterface.addorUpdateUser(user.getUsername(), user.toString());
        } catch (IOException | ParseException e) {
            throw new RuntimeException("Unable to save user info into database");
        }
    }

    /**
     * Creates the data to be handled by presenter that will have the username, groupId, and groupNames;
     * @param user the user object that has been modified
     * @param group the group object that has been modified
     * @return the data structure that will be handled by the presenter
     */

    private JoinedGroupInfo createOutputData(User user, Group group){
        List<String> groupNames = new ArrayList<>();
        List<List<String>> planningList;
        List<List<String>> purchasedList;

        List<String> usersInGroup = new ArrayList<>(group.getUsers());
        List<String> groupIds = new ArrayList<>(user.getGroups());
        user.getGroups().forEach(group1 -> {
            try {
                groupNames.add(getGroupFromDb(group1).getGroupName());
            } catch (IOException | ParseException e) {
                throw new RuntimeException(e);
            }
        });

        //make a sublist  of the purchased and planning list
        planningList = this.getListItemList(group.getPlanningList());
        purchasedList = this.getListItemList(group.getPurchaseList());

        return new JoinedGroupInfo(usersInGroup, groupIds, groupNames, planningList, purchasedList);
    }

    private List<List<String>> getListItemList(ItemList itemList){
        List<List<String>> newList = new ArrayList<>();

        for (Item item : itemList.getItems()) {
            List<String> subList = new ArrayList<>();
            subList.add(item.getItemId());
            subList.add(item.getItemName());
            if (itemList instanceof PurchaseList){
                subList.add(String.valueOf(item.getPrice()));
                subList.add(item.getBuyer());
            }
            newList.add(subList);
        }

        return newList;
    }

}
