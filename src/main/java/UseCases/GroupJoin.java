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

public class GroupJoin implements GroupJoinBoundaryIn{

    /*
    Use Case Interactor to join a new group for the user
     */

    final GroupDataInterface groupDsInterface; //the database interface that enables us to add and obtain group info
    final UserDataInterface userDsInterface; // the database interface that enables us to add and obtain user infos
    final GroupJoinBoundaryOut groupJoinPresenter; // presenter that will update view after use case executes
    final GroupFactory groupFactory; //to delegate the creation of groups to

    public GroupJoin(GroupJoinBoundaryOut presenter, GroupFactory groupFactory, GroupDataInterface groupDsInterface, UserDataInterface userDsInterface){
        this.groupJoinPresenter = presenter;
        this.groupFactory = groupFactory;
        this.userDsInterface = userDsInterface;
        this.groupDsInterface = groupDsInterface;
    }

    @Override
    public JoinedGroupInfo joinGroup(JoinGroupRequest reqGroupInfo){
        //obtain the user from the database
        String userString = this.userDsInterface.userAsString(reqGroupInfo.getUserId());

        //create a user object
        User user = null;
        try {
            user = User.fromString(userString);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        //obtain the group info form the database
        String groupString = this.groupDsInterface.groupAsString(reqGroupInfo.getGroupId());
        Group group = null;
        try {
            group = Group.fromString(groupString);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        //check if user already in group?
        if (group.getUsers().contains(user)){
            //brr fail
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

        //pass new info to db
        try {
            this.groupDsInterface.addorUpdateGroup(group.getGroupId(), group.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.userDsInterface.addorUpdateUser(user.getUsername(), user.toString());

        //to controller
        List<String> usersInGroup = new ArrayList<>();
        List<String> groupIds = new ArrayList<>();
        List<String> groupNames = new ArrayList<>();

        group.getUsers().forEach(user1 -> usersInGroup.add(user1.getUsername()));
        user.getGroups().forEach(group1 -> groupIds.add(group1.getGroupId()));
        user.getGroups().forEach(group1 -> groupIds.add(group1.getGroupName()));

        JoinedGroupInfo joinedGroupInfo = new JoinedGroupInfo(usersInGroup, groupIds, groupNames);

        return this.groupJoinPresenter.prepareSuccessView(joinedGroupInfo);

    }

}
