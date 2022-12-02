package Controllers;

import InputBoundary.GroupCreateBoundaryIn;
import DataStructures.CreatedGroupInfo;
import DataStructures.ProposedGroupInfo;
public class GroupCreateController {
    /**
    The controller that provides the inputs the user provided to create a new group
     */
    final GroupCreateBoundaryIn userInput; // the interface that enables us to call the usecase interactor

    /**
     * takes the input boundary that enables the controller to execute the use case
     * @param input the input boundary for the use case
     */
    public GroupCreateController(GroupCreateBoundaryIn input){
        this.userInput = input;
    }

    /**
     * method to create a group to be executed by the use case interactor by calling the method in the input boundary
     * parses input data into a ProposedGroupInfo object which is passed to the use case interactor
     * @param groupName the name of the group to be created
     * @param userId the username of the user requesting group creation
     * @return the output data structure with all the information to display to the view
     */
    public CreatedGroupInfo create(String groupName, String userId) {
        ProposedGroupInfo proposedInfo = new ProposedGroupInfo(userId, groupName); //create the input ds package

        return userInput.createNewGroup(proposedInfo); // create the new group and return the output
    }
}
