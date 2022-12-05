package Presenters;

import DataStructures.CreatedGroupInfo;
import DataStructures.JoinedGroupInfo;
import OutputBoundary.GroupJoinBoundaryOut;

public class GroupJoinPresenter implements GroupJoinBoundaryOut {

    /*
    Represents the presenter that updates the view on the group join request
     */

    /**
     * prepares a successful group join screen with all the required information to be displayed
     * @param joinedGroupInfo contains all the information to be displayed
     * @return all the information to be displayed
     */
    @Override
    public JoinedGroupInfo prepareSuccessView(JoinedGroupInfo joinedGroupInfo){
        //returns the group that has been joined that is provided from the interface
        return joinedGroupInfo;
    }

    /**
     * prepares a failure view when group joining fails and presents the corresponding error message
     * @param joinedGroupInfo contains the error message
     * @return the error message wrapped in an appropriate datastrcuture
     */
    @Override
    public JoinedGroupInfo prepareFailView(JoinedGroupInfo joinedGroupInfo){
        return joinedGroupInfo;
    }
}
