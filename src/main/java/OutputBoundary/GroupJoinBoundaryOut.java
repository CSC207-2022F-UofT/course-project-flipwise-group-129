package OutputBoundary;

import DataStructures.JoinedGroupInfo;

public interface GroupJoinBoundaryOut {
    /*
     * Defining the functions that will be implemented by the GroupJoin Presenter through the boundary to
     * maintain clean architecture
     * to be implemented by the respective present
     */

    /**
     * prepares all the information to be displayed in the view
     * the name of the group
     * the users in it
     * the debts for the user
     * all the groups the user is a part of
     * @param joinedGroupInfo contains all the information to be displayed
     * @return all the information to be displayed
     */
    JoinedGroupInfo prepareSuccessView(JoinedGroupInfo joinedGroupInfo);

    /**
     * Alerts the view in case of a failure
     * @param joinedGroupInfo contains the error message
     * @return the error messaged wrapped in an output data structure
     */
    JoinedGroupInfo prepareFailView(JoinedGroupInfo joinedGroupInfo);
}
