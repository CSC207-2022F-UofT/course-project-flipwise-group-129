package Presenters;
import OutputBoundary.GroupCreateBoundaryOut;
import DataStructures.CreatedGroupInfo;

public class GroupCreatePresenter implements GroupCreateBoundaryOut{
    /*
    Represents the presenter that updates the view on the group creation request
     */
    @Override
    public CreatedGroupInfo prepareSuccessView(CreatedGroupInfo createdGroupInfo){
        //returns the group that has been created that is provided from the interface
        return createdGroupInfo;
    }
}
