package Presenters;

import DataStructures.CreatedGroupInfo;
import DataStructures.JoinedGroupInfo;
import OutputBoundary.GroupJoinBoundaryOut;

public class GroupJoinPresenter implements GroupJoinBoundaryOut {

    /*
    Represents the presenter that updates the view on the group join request
     */
    @Override
    public JoinedGroupInfo prepareSuccessView(JoinedGroupInfo joinedGroupInfo){
        //returns the group that has been joined that is provided from the interface
        return joinedGroupInfo;
    }

    @Override
    public JoinedGroupInfo prepareFailView(JoinedGroupInfo joinedGroupInfo){
        return joinedGroupInfo;
    }
}
