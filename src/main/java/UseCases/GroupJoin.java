package UseCases;

import DataStructures.JoinGroupRequest;
import DataStructures.JoinedGroupInfo;
import Entities.Group;
import Entities.GroupFactory;
import Entities.User;
import OutputBoundary.GroupCreateBoundaryOut;
import OutputBoundary.GroupJoinBoundaryOut;

import javax.swing.*;
import java.util.List;
import java.util.ArrayList;

public class GroupJoin {

    final UserObtainDsGateway userDsGateway;
    final GroupJoinBoundaryOut groupJoinPresenter;
    final GroupFactory groupFactory;

    public GroupJoin(GroupJoinBoundaryOut presenter, GroupFactory groupFactory){
        this.groupJoinPresenter = presenter;
        this.groupFactory = groupFactory;
    }

    @Override
    public JoinedGroupInfo joinGroup(JoinGroupRequest reqGroupInfo){
        //obtain the user from the database
        User user = new User();
        //obtain the group info form the database
        Group group = new Group();

        //check if user already in group?
        if (group.getUsers().contains(user)){
            //brr fail
        }
        group.addUser(user);
        user.addGroup(group);

        //pass new info to db

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
