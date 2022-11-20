package UseCases;
import DataStructures.ProposedGroupInfo;
import DataStructures.CreatedGroupInfo;
import Entities.*;
import InputBoundary.GroupCreateBoundaryIn;
import OutputBoundary.GroupCreateBoundaryOut;

import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.List;

import DataAccessInterface.*;
import com.fasterxml.jackson.core.JsonProcessingException;

public class GroupCreate implements GroupCreateBoundaryIn{

    /*
    Handles the creation of a new group
     */
    final GroupDataInterface groupDsInterface; //the database interface that enables us to add and obtain group info
    final UserDataInterface userDsInterface; // the database interface that enables us to add and obtain user infos
    final GroupCreateBoundaryOut groupCreatePresenter; // the presentor to provide the created group to

    public GroupCreate(GroupCreateBoundaryOut presenter, GroupDataInterface groupDsInterface, UserDataInterface userDsInterface){
        this.groupCreatePresenter = presenter;
        this.groupDsInterface = groupDsInterface;
        this.userDsInterface = userDsInterface;
    }

    @Override
    public CreatedGroupInfo createNewGroup(ProposedGroupInfo newGroupInfo){
        //obtain the user from the database
        User createdUser = null;
        try{
            createdUser = this.getUserFromDb(newGroupInfo.getUsername());
        }catch (RuntimeException e) {
            CreatedGroupInfo createdGroupInfo = new CreatedGroupInfo(e.toString());
            return this.groupCreatePresenter.prepareFailView(createdGroupInfo);
        }

        //creating a new group
        Group group = this.createGroup(createdUser, newGroupInfo.getGroupName());

        //saving the data into the database
        try{
            this.saveData(createdUser, group);
        } catch (RuntimeException e){
            CreatedGroupInfo createdGroupInfo = new CreatedGroupInfo(e.toString());
            return this.groupCreatePresenter.prepareFailView(createdGroupInfo);
        }

        CreatedGroupInfo createdGroupInfo = this.createOutputData(createdUser, group);

        return this.groupCreatePresenter.prepareSuccessView(createdGroupInfo); //present it to the presenter
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

    private Group createGroup(User createdUser, String groupName){
        //intialize a set of users in the group
        Set<User> users = new TreeSet<>();
        users.add(createdUser); //add the created user into the group
        Group group = new Group(groupName, users);// create the required group
        createdUser.addGroup(group); // add the new group the list of groups the user is a part of

        return group;
    }

    private void saveData(User user, Group group){
        //pass new info to db
        try {
            this.groupDsInterface.addorUpdateGroup(group.getGroupId(), group.toString());
        } catch (IOException e) {
            throw new RuntimeException("unable to update group into database");
        }
        try {
            this.userDsInterface.addorUpdateUser(user.getUsername(), user.toString());
        } catch (IOException e) {
            throw new RuntimeException("unable to update user data into database");
        }
    }

    private CreatedGroupInfo createOutputData(User createdUser, Group group){
        List<Group> allGroups = new ArrayList<>(createdUser.getGroups());
        List<String> allGroupIds = new ArrayList<>();
        List<String> allGroupNames = new ArrayList<>();
        for (Group group1: allGroups){
            allGroupNames.add(group1.getGroupName());
            allGroupIds.add(group1.getGroupId());
        }
        //return the output ds
        return new CreatedGroupInfo(
                group.getGroupId(),
                group.getGroupName(),
                createdUser.getUsername(),
                allGroupIds,
                allGroupNames
        );
    }

}
