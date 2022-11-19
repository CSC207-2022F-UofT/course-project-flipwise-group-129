package Controllers;

import InputBoundary.GroupCreateBoundaryIn;
import DataStructures.CreatedGroupInfo;
import DataStructures.ProposedGroupInfo;
public class GroupCreateController {
    /*
    The controller that provides the inputs the user provided to create a new group
     */
    final GroupCreateBoundaryIn userInput; // the interface that enables us to call the usecase interactor

    public GroupCreateController(GroupCreateBoundaryIn input){
        this.userInput = input;
    }

    CreatedGroupInfo create(String groupName, String userId) {
        ProposedGroupInfo proposedInfo = new ProposedGroupInfo(groupName, userId); //create the input ds package

        return userInput.createNewGroup(proposedInfo); // create the new group and return the output
    }
}
