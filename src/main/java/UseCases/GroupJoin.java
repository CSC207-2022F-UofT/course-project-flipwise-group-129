package UseCases;

import DataStructures.JoinGroupRequest;
import DataStructures.JoinedGroupInfo;
import Entities.Group;
import Entities.GroupFactory;
import Entities.User;
import InputBoundary.GroupJoinBoundaryIn;
import OutputBoundary.GroupJoinBoundaryOut;
import DataAccessInterface.*;


import java.util.List;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GroupJoin implements GroupJoinBoundaryIn{

    final GroupDataInterface groupDsInterface;
    final UserDataInterface userDsInterface;
    final GroupJoinBoundaryOut groupJoinPresenter;
    final GroupFactory groupFactory;

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

        //repeated code that should ideally be packed into a method use case interface
        User user = User.fromString(userString);

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
        group.addUser(user);
        user.addGroup(group);

        //pass new info to db
        this.groupDsInterface.addorUpdateGroup(group.getGroupId(), group.toString());
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
