package Presenters;

import DataStructures.UpdatedLists;
import OutputBoundary.AddToPlanningBoundaryOut;

import java.util.List;

public class AddToPlanningPresenter implements AddToPlanningBoundaryOut {
    @Override
    public List<List<String>> displayLists(UpdatedLists lists) {
        return lists.getNewPlanningList();
    }
}
