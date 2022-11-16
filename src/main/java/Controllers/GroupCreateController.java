package Controllers;

import InputBoundary.GroupCreateBoundaryIn;
import DataStructures.CreatedGroupInfo;
import DataStructures.ProposedGroupInfo;
public class GroupCreateController {

    final GroupCreateBoundaryIn userInput;

    public GroupCreateController(GroupCreateBoundaryIn input){
        this.userInput = input;
    }

    CreatedGroupInfo create(String groupName, String userId) {
        ProposedGroupInfo proposedInfo = new ProposedGroupInfo(groupName, userId);

        return userInput.createNewGroup(proposedInfo);
    }
}
