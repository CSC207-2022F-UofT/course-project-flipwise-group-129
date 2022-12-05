package Controllers;

import DataStructures.JoinedGroupInfo;
import DataStructures.JoinGroupRequest;
import InputBoundary.GroupJoinBoundaryIn;

public class GroupJoinController {
    /*
    The controller that provides the inputs the user provided to join a group
     */
    final GroupJoinBoundaryIn userInput; // the interface that enables us to call the UseCase

    /**
     * takes the input boundary that enables the controller to execute the use case
     * @param input the input boundary for the use case
     */
    public GroupJoinController(GroupJoinBoundaryIn input){
        this.userInput = input;
    }

    /**
     * method to join a group to be executed by the use case by calling the method in the input boundary
     * parses input data into a JoinGroupRequest object which is passed to the use case
     * @param groupId the id of the group to join
     * @param userId the username of the user requesting group creation
     * @return the output data structure with all the information to display to the view
     */
    public JoinedGroupInfo create(String groupId, String userId) {
        JoinGroupRequest proposedInfo = new JoinGroupRequest(groupId, userId); //create the input ds package

        return userInput.joinGroup(proposedInfo); // create the new group and return the output
    }
}
