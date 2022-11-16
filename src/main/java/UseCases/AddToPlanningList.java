package UseCases;
import DataStructures.PlannedItemInfo;
import Entities.*;
import InputBoundary.AddToPlanningBoundaryIn;
import DataStructures.UpdatedLists;
import OutputBoundary.AddToPlanningBoundaryOut;
public class AddToPlanningList implements AddToPlanningBoundaryIn{
    AddToPlanningBoundaryOut outputBoundary;

    public AddToPlanningList(AddToPlanningBoundaryOut outputBoundary){
        this.outputBoundary = outputBoundary;
    }
    @Override
    public UpdatedLists addPlanning(PlannedItemInfo item) {
        // retrieve group from groupID using item.getGroupId()
        Item newItem = new Item(item.getName(), null , item.getPrice());
        // code to append newItem to the planningList of the group
        UpdatedLists updatedLists = new UpdatedLists(new PlanningList(), new PurchaseList());
        return outputBoundary.displayLists(updatedLists);
    }
}
