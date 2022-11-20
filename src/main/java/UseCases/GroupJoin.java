package UseCases;

import DataStructures.CreatedGroupInfo;
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

public class GroupJoin implements GroupJoinBoundaryIn{

    /*
    Use Case Interactor to join a new group for the user
     */

    final GroupDataInterface groupDsInterface; //the database interface that enables us to add and obtain group info
    final UserDataInterface userDsInterface; // the database interface that enables us to add and obtain user infos
    final GroupJoinBoundaryOut groupJoinPresenter; // presenter that will update view after use case executes

    public GroupJoin(GroupJoinBoundaryOut presenter, GroupDataInterface groupDsInterface, UserDataInterface userDsInterface){
        this.groupJoinPresenter = presenter;
        this.userDsInterface = userDsInterface;
        this.groupDsInterface = groupDsInterface;
    }

    @Override
    public JoinedGroupInfo joinGroup(JoinGroupRequest reqGroupInfo){
        //obtain the user from the database
        User user = null;
        try{
            user = this.getUserFromDb(reqGroupInfo.getUsername());
        }catch (RuntimeException e) {
            JoinedGroupInfo joinedGroupInfo = new JoinedGroupInfo(e.toString());
            return this.groupJoinPresenter.prepareFailView(joinedGroupInfo);
        }

        //obtain the group from the databse:
        Group group = null;
        try{
            group = this.getGroupFromDb(reqGroupInfo.getGroupId());
        }catch (RuntimeException e){
            JoinedGroupInfo joinedGroupInfo = new JoinedGroupInfo(e.toString());
            return this.groupJoinPresenter.prepareFailView(joinedGroupInfo);
        }

        try{
            this.addUserToGroup(user, group);
        }catch (RuntimeException e){
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

        //to controller
        JoinedGroupInfo joinedGroupInfo = this.createOutputData(user, group);
        return this.groupJoinPresenter.prepareSuccessView(joinedGroupInfo);

    }

    private User getUserFromDb(String username){
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

    private Group getGroupFromDb(String groupId){
        //obtain the group info form the database
        //check if the group exists
        if (!this.groupDsInterface.groupIdExists(groupId)){
            throw new RuntimeException("Invalid GroupID provided");
        }
        String groupString = this.groupDsInterface.groupAsString(groupId);
        Group group = null;
        try {
            group = Group.fromString(groupString);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Unable to obtain group info from database");
        }
        return group;
    }

    private void addUserToGroup(User user, Group group){
        //check if user already in group?
        if (group.getUsers().contains(user)){
            throw new RuntimeException("User already in group");
        }

        //adding all needed new debt pairs:
        PurchaseBalance grpPurchaseBalance = group.getPurchaseBalance();
        for (User groupUser : group.getUsers()) {
            grpPurchaseBalance.addDebtPair(new Debt(groupUser, user, group.getGroupId()));
            grpPurchaseBalance.addDebtPair(new Debt(user, groupUser, group.getGroupId()));
        }
        //mutating method, so no need to reassign

        group.addUser(user); // add the user into the list of users in the group
        user.addGroup(group); // add the group into the list of groups the user is a part of
    }

    private void saveData(User user, Group group){
        //pass new info to db
        try {
            this.groupDsInterface.addorUpdateGroup(group.getGroupId(), group.toString());
        } catch (IOException e) {
            throw new RuntimeException("Unable to modify group info to database");
        }
        try {
            this.userDsInterface.addorUpdateUser(user.getUsername(), user.toString());
        } catch (IOException e) {
            throw new RuntimeException("Unable to save user info into database");
        }
    }

    private JoinedGroupInfo createOutputData(User user, Group group){
        List<String> usersInGroup = new ArrayList<>();
        List<String> groupIds = new ArrayList<>();
        List<String> groupNames = new ArrayList<>();

        group.getUsers().forEach(user1 -> usersInGroup.add(user1.getUsername()));
        user.getGroups().forEach(group1 -> groupIds.add(group1.getGroupId()));
        user.getGroups().forEach(group1 -> groupIds.add(group1.getGroupName()));

        return new JoinedGroupInfo(usersInGroup, groupIds, groupNames);
    }

}
