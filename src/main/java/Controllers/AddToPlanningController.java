package Controllers;
import UseCases.AddToPlanningList;
import DataStructures.PlannedItemInfo;
public class AddToPlanningController {
    public PlannedItemInfo itemInfo;

    public PlannedItemInfo getItemInfo() {
        return itemInfo;
    }

    public void setItemInfo(PlannedItemInfo itemInfo) {
        this.itemInfo = itemInfo;
    }

    public void performPlanningAdd() {
//        AddToPlanningList.___(itemInfo);
    }
}
