package Controllers;
import DataAccessInterface.GroupDataInterface;
import DataStructures.PlannedItemInfo;
import DataStructures.UpdatedLists;
import InputBoundary.AddToPlanningBoundaryIn;

import java.io.IOException;

public class AddToPlanningController {
    public AddToPlanningBoundaryIn inputBoundary;
    public AddToPlanningController(AddToPlanningBoundaryIn inputBoundary) {
        this.inputBoundary = inputBoundary;
    }
    public void performPlanningAdd(String name, Float price, String groupId) throws IOException {
        PlannedItemInfo itemInfo = new PlannedItemInfo(name, price, groupId);
        inputBoundary.addPlanning(itemInfo);
    }
}
