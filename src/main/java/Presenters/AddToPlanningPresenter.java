package Presenters;

import DataStructures.UpdatedLists;
import OutputBoundary.AddToPlanningBoundaryOut;

public class AddToPlanningPresenter implements AddToPlanningBoundaryOut {
    @Override
    public UpdatedLists displayLists(UpdatedLists lists) {
        return lists;
    }
}
