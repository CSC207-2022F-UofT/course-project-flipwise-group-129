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
    final GroupFactory groupFactory; //to delegate the creation of groups to

    public GroupCreate(GroupCreateBoundaryOut presenter, GroupFactory groupFactory, GroupDataInterface groupDsInterface, UserDataInterface userDsInterface){
        this.groupCreatePresenter = presenter;
        this.groupFactory = groupFactory;
        this.groupDsInterface = groupDsInterface;
        this.userDsInterface = userDsInterface;
    }

    @Override
    public CreatedGroupInfo createNewGroup(ProposedGroupInfo newGroupInfo){
        // get the user from the database and create a User interface
        //check if the user exists
        if (!this.userDsInterface.userIdExists(newGroupInfo.getUsername())){
            CreatedGroupInfo createdGroupInfo = new CreatedGroupInfo("User Id does not exist");
            return this.groupCreatePresenter.prepareFailView(createdGroupInfo);
        }
        String userString = this.userDsInterface.userAsString(newGroupInfo.getUsername());

        User createdUser = null;
        try {
            createdUser = User.fromString(userString);
        } catch (JsonProcessingException e) {
            CreatedGroupInfo createdGroupInfo = new CreatedGroupInfo("unable to process database (user)");
            return this.groupCreatePresenter.prepareFailView(createdGroupInfo);
        }

        //intialize a set of users in the group
        Set<User> users = new TreeSet<>();
        users.add(createdUser); //add the created user into the group
        Group group = groupFactory.create(newGroupInfo.getUsername(), users); // create the required group
        //saving it into the database
        createdUser.addGroup(group); // add the new group the list of groups the user is a part of

        //pass new info to db
        try {
            this.groupDsInterface.addorUpdateGroup(group.getGroupId(), group.toString());
        } catch (IOException e) {
            CreatedGroupInfo createdGroupInfo = new CreatedGroupInfo("unable to update group into database");
            return this.groupCreatePresenter.prepareFailView(createdGroupInfo);
        }
        try {
            this.userDsInterface.addorUpdateUser(createdUser.getUsername(), createdUser.toString());
        } catch (IOException e) {
            CreatedGroupInfo createdGroupInfo = new CreatedGroupInfo("unable to update user data into database");
            return this.groupCreatePresenter.prepareFailView(createdGroupInfo);
        }

        // make a list of all the groups the user is in
        List<Group> allGroups = new ArrayList<>(createdUser.getGroups());
        List<String> allGroupIds = new ArrayList<>();
        List<String> allGroupNames = new ArrayList<>();
        for (Group group1: allGroups){
            allGroupNames.add(group1.getGroupName());
            allGroupIds.add(group1.getGroupId());
        }
        //create the output ds
        CreatedGroupInfo createdGroupInfo = new CreatedGroupInfo(group.getGroupId(), group.getGroupName(), createdUser.getUsername(), allGroupIds, allGroupNames);

        return this.groupCreatePresenter.prepareSuccessView(createdGroupInfo); //present it to the presenter
    }




}
