package Controllers;

import DataStructures.JoinedGroupInfo;
import DataStructures.JoinGroupRequest;
import InputBoundary.GroupJoinBoundaryIn;

public class GroupJoinController {
    /*
    The controller that provides the inputs the user provided to join a group
     */
    final GroupJoinBoundaryIn userInput; // the interface that enables us to call the usecase interactor

    public GroupJoinController(GroupJoinBoundaryIn input){
        this.userInput = input;
    }

    JoinedGroupInfo create(String groupName, String userId) {
        JoinGroupRequest proposedInfo = new JoinGroupRequest(groupName, userId); //create the input ds package

        return userInput.joinGroup(proposedInfo); // create the new group and return the output
    }
}
