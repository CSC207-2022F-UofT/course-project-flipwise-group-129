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
import org.json.simple.parser.ParseException;

public class GroupCreate implements GroupCreateBoundaryIn{

    /**
     * Usecase interactor that handles the creation of a group
     */
    final GroupDataInterface groupDsInterface; //the database interface that enables us to add and obtain group info
    final UserDataInterface userDsInterface; // the database interface that enables us to add and obtain user infos
    final GroupCreateBoundaryOut groupCreatePresenter; // the presenter to provide the created group to

    /**
     * Constructor to initiate the usecase interactor
     * @param presenter an interface that is implemented by the presentor to access the output from use case interactor
     * @param groupDsInterface interface to access group related database queries
     * @param userDsInterface interface to access user related dataase queries
     */
    public GroupCreate(GroupCreateBoundaryOut presenter, GroupDataInterface groupDsInterface, UserDataInterface userDsInterface){
        this.groupCreatePresenter = presenter;
        this.groupDsInterface = groupDsInterface;
        this.userDsInterface = userDsInterface;
    }

    /**
     * Allows the user to create a group
     * adds the user in as the sole member of group
     * @param newGroupInfo the data structure containing all the information required to create a new group
     */
    @Override
    public CreatedGroupInfo createNewGroup(ProposedGroupInfo newGroupInfo){
        //obtain the user from the database
        User createdUser;
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

    /**
     * gets the specified user by username from the database if exists
     * otherwise throws a Runtime exception
     * @param username contains the unique username that can be used to identify user data from database
     * @return returns the user object obtained from db
     */
    private User getUserFromDb(String username){
        // get the user from the database and create a User interface
        //check if the user exists

        try {
            if (!this.userDsInterface.userIdExists(username)){
                throw new RuntimeException("User Id does not exist");
            }
            String userString = "";
            userString = this.userDsInterface.userAsString(username);
            return User.fromString(userString);
        } catch (IOException | ParseException e) {
            throw new RuntimeException("Unable to process user from database");
        }
    }

    private Group getGroupFromDb(String groupId){
        //obtain the group info form the database
        //check if the group exists

        try {
            if (!this.groupDsInterface.groupIdExists(groupId)){
                throw new RuntimeException("Invalid GroupID provided");
            }
            String groupString = "";
            Group group = null;
            groupString = this.groupDsInterface.groupAsString(groupId);
            group = Group.fromString(groupString);
            return group;
        } catch (IOException | ParseException e) {
            throw new RuntimeException("Unable to obtain group info from database");
        }
    }

    /**
     * creates the group
     * adds the user to the group
     * adds the group to the list of groups the user is a part of
     * @param createdUser the user that requested group creation
     * @param groupName the requested group's name
     */
    private Group createGroup(User createdUser, String groupName){
        //intialize a set of users in the group
        Set<String> users = new TreeSet<>();
        users.add(createdUser.getUsername()); //add the created user into the group
        Group group = new Group(groupName, users);// create the required group
        createdUser.addGroup(group.getGroupId()); // add the new group the list of groups the user is a part of

        return group;
    }

    /**
     * Saves the modified user and new group data into the database
     * @param user the user object after modifications
     * @param group the new group object with all the required new information about the list of users
     */
    private void saveData(User user, Group group){
        //pass new info to db
        try {
            this.groupDsInterface.addorUpdateGroup(group.getGroupId(), group.toString());
        } catch (IOException | ParseException e) {
            throw new RuntimeException("unable to update group into database");
        }
        try {
            this.userDsInterface.addorUpdateUser(user.getUsername(), user.toString());
        } catch (IOException | ParseException e) {
            throw new RuntimeException("unable to update user data into database");
        }
    }

    /**
     * Creates the data to be handled by presenter that will have the group names the person is part of
     * its ids
     * @param createdUser the user object that has been modified
     * @param group the group object that has been created
     * @return the datastructure that will be handled by the presenter
     */
    private CreatedGroupInfo createOutputData(User createdUser, Group group){
        List<String> allGroupIds = new ArrayList<>(createdUser.getGroups());
        List<String> allGroupNames = new ArrayList<>();
        for (String groupid: allGroupIds){
            allGroupNames.add(getGroupFromDb(groupid).getGroupName());
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
