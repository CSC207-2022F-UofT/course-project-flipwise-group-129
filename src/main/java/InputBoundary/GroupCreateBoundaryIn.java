package InputBoundary;

import DataStructures.CreatedGroupInfo;
import DataStructures.ProposedGroupInfo;

public interface GroupCreateBoundaryIn {
    /*
     * Defining the functions that will be implemented by the GroupCreate use case through the boundary to
     * maintain clean architecture
     * to be implemented by the respective controller
     */

    /**
     * creates the new group requsted by the user
     * adds the user into the group
     * saves the new user and group data into the database
     * returns the output data object to be handled by the presenter
     * @param newGroupInfo contains all the information required to create a new group
     * @return the output data to be handled by the presenter
     */
    CreatedGroupInfo createNewGroup(ProposedGroupInfo newGroupInfo);
}
