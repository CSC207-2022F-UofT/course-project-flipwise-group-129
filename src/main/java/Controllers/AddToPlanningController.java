package Controllers;
import DataStructures.PlannedItemInfo;
import InputBoundary.AddToPlanningBoundaryIn;
import DataStructures.UpdatedLists;

public class AddToPlanningController {
    public AddToPlanningBoundaryIn inputBoundary;
    public AddToPlanningController(AddToPlanningBoundaryIn inputBoundary) {
        this.inputBoundary = inputBoundary;
    }
    public UpdatedLists performPlanningAdd(String name, Float price, String groupId) {
        PlannedItemInfo itemInfo = new PlannedItemInfo(name, price, groupId);
        return inputBoundary.addPlanning(itemInfo);
    }
}
