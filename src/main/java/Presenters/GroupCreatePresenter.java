package Presenters;
import OutputBoundary.GroupCreateBoundaryOut;
import DataStructures.CreatedGroupInfo;

public class GroupCreatePresenter implements GroupCreateBoundaryOut{
    /*
    Represents the presenter that updates the view on the group creation request
     */
    /**
     * prepares a successful group creation screen with all the required information to be displayed
     * @param createdGroupInfo contains all the information to be displayed
     * @return all the information to be displayed
     */
    @Override
    public CreatedGroupInfo prepareSuccessView(CreatedGroupInfo createdGroupInfo){
        //returns the group that has been created that is provided from the interface
        return createdGroupInfo;
    }

    /**
     * prepares a failure view when group creation fails and presents the corresponding error message
     * @param createdGroupInfo contains the error message
     * @return the error message wrapped in an appropriate datastrcuture
     */
    @Override
    public CreatedGroupInfo prepareFailView(CreatedGroupInfo createdGroupInfo){
        return createdGroupInfo;
    }
}
