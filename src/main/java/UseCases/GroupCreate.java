package UseCases;
import DataAccessInterface.GroupDataInterface;
import DataAccessInterface.UserDataInterface;
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
    final GroupDataInterface groupDsInterface;
    final UserDataInterface userDsInterface;
    final GroupCreateBoundaryOut groupCreatePresenter;
    final GroupFactory groupFactory;

    public GroupCreate(GroupCreateBoundaryOut presenter, GroupFactory groupFactory, GroupDataInterface groupDsInterface, UserDataInterface userDsInterface){
        this.groupCreatePresenter = presenter;
        this.groupFactory = groupFactory;
        this.groupDsInterface = groupDsInterface;
        this.userDsInterface = userDsInterface;
    }

    @Override
    public CreatedGroupInfo createNewGroup(ProposedGroupInfo newGroupInfo){
//        if (!userDsInterface.exists(newGroupInfo.getUsername())){
//            return groupCreatePresenter.prepareFailView("Invalid Userid Given")
//        }
        String userString = this.userDsInterface.userAsString(reqGroupInfo.getUserId());

        User createdUser = null;
        try {
            createdUser = User.fromString(userString);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        Set<User> users = new TreeSet<>();
        users.add(createdUser);
        Group group = groupFactory.create(newGroupInfo.getUsername(), users);
        //saving it into the database
        createdUser.addGroup(group);

        //
        //pass new info to db
        try {
            this.groupDsInterface.addorUpdateGroup(group.getGroupId(), group.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.userDsInterface.addorUpdateUser(createdUser.getUsername(), createdUser.toString());

        List<Group> allGroups = new ArrayList<>(createdUser.getGroups());
        List<String> allGroupIds = new ArrayList<>();
        List<String> allGroupNames = new ArrayList<>();
        for (Group group1: allGroups){
            allGroupNames.add(group1.getGroupName());
            allGroupIds.add(group1.getGroupId());
        }
        //getting all the groups the user is in now.
        CreatedGroupInfo createdGroupInfo = new CreatedGroupInfo(group.getGroupId(), createdUser.getUsername(), allGroupIds, allGroupNames);

        return this.groupCreatePresenter.prepareSuccessView(createdGroupInfo);
    }




}
