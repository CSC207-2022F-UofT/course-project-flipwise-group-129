package UseCases;
import DataStructures.ProposedGroupInfo;
import DataStructures.CreatedGroupInfo;
import Entities.*;
import InputBoundary.GroupCreateBoundaryIn;
import OutputBoundary.GroupCreateBoundaryOut;
import com.sun.source.tree.Tree;

import java.sql.Array;
import java.util.Set;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.List;
public class GroupCreate implements GroupCreateBoundaryIn{
    final UserObtainDsGateway userDsGateway;
    final GroupCreateBoundaryOut groupCreatePresenter;
    final GroupFactory groupFactory;

    public GroupCreate(GroupCreateBoundaryOut presenter, GroupFactory groupFactory){
        this.groupCreatePresenter = presenter;
        this.groupFactory = groupFactory;
    }

    @Override
    public CreatedGroupInfo createNewGroup(ProposedGroupInfo newGroupInfo){
//        if (!userDsGateway.exists(newGroupInfo.getUser())){
//            return groupCreatePresenter.prepareFailView("Invalid Userid Given")
//        }
        User createdUser = this.userDsGateway.obtain(newGroupInfo.getUser()); // method to obtain user from id from dataset, yet to be implemented
        Set<User> users = new TreeSet<>();
        users.add(createdUser);
        Group group = groupFactory.create(newGroupInfo.getName(), users);
        //need some code to save the created group into the database;
        createdUser.addGroup(group);
        List<Group> allGroups = new ArrayList<>(createdUser.getGroups());
        List<String> allGroupIds = new ArrayList<>();
        List<String> allGroupNames = new ArrayList<>();
        for (Group group: allGroups){
            allGroupNames.add(group.getGroupName());
            allGroupIds.add(group.getGroupId());
        }
        //getting all the groups the user is in now.
        CreatedGroupInfo createdGroupInfo = new CreatedGroupInfo(group.getGroupId(), user.getUserId(), allGroupIds, allGroupNames);

        return this.groupCreatePresenter.prepareSuccessView(createdGroupInfo);
    }




}
