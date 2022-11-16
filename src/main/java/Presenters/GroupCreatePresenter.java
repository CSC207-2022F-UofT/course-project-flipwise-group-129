package Presenters;
import OutputBoundary.GroupCreateBoundaryOut;
import DataStructures.CreatedGroupInfo;

public class GroupCreatePresenter implements GroupCreateBoundaryOut{
    @Override
    public CreatedGroupInfo prepareSuccessView(CreatedGroupInfo createdGroupInfo){
        return createdGroupInfo;
    }
}
