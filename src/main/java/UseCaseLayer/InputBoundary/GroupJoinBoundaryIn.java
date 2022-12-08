package UseCaseLayer.InputBoundary;

import UseCaseLayer.DataStructures.JoinGroupRequest;
import UseCaseLayer.DataStructures.JoinedGroupInfo;

public interface GroupJoinBoundaryIn {
    /*
     * Defining the functions that will be implemented by the GroupJoin use case through the boundary to
     * maintain clean architecture
     * to be implemented by the respective controller
     */

    /**
     * joins the group requested by the user
     * adds the user into the group
     * adds the group into the user list of groups
     * adds more debt objects into respective PurchaseBalance object
     * saves the user and group data into the database
     * returns the output data object to be handled by the presenter
     * @param requestGroupInfo contains all the information required to join the group
     * @return the output data to be handled by the present
     */

    JoinedGroupInfo joinGroup(JoinGroupRequest requestGroupInfo);
}
