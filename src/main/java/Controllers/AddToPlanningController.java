package Controllers;
import UseCases.AddToPlanningList;
import DataStructures.PlannedItemInfo;
public class AddToPlanningController {
    public PlannedItemInfo itemInfo;
    public PlannedItemInfo getItemInfo() {
        return itemInfo;
    }

    public void setItemInfo(String name, Float price) {
        this.itemInfo = new PlannedItemInfo(name, price);
    }

    public void performPlanningAdd() {
        AddToPlanningList.addPlanning(itemInfo);
    }
}
