package InterfaceAdapters.Controllers;
import UseCaseLayer.DataStructures.PlannedItemInfo;
import UseCaseLayer.InputBoundary.AddToPlanningBoundaryIn;
import UseCaseLayer.DataStructures.UpdatedLists;
/**
 * This function will be called from the main/view to execute the use case AddtoPlanningList, which occurs
 * when a user wants to add a new item to the planning list. This function packages required information on the item
 * and current group received from the view into a PlannedItemInfo data structure. This information is passed to the
 * input boundary to perform the use case.
 */
public class AddToPlanningController {
    public final AddToPlanningBoundaryIn inputBoundary;
    /**
     * Creates a new AddToPlanningController to execute the AddtoPlanning use case
     * @param inputBoundary an instance of the input boundary passed in from the main/view to execute the use case
     */
    public AddToPlanningController(AddToPlanningBoundaryIn inputBoundary) {
        this.inputBoundary = inputBoundary;
    }
    /**
     * This function will be called from the main/view to execute the use case AddtoPlanningList, which occurs
     * when a user wants to add a new item to the planning list. This function packages required information on the item
     * and current group received from the view into a PlannedItemInfo data structure. This information is passed to the
     * input boundary to perform the use case.
     * @param name name of the item being created
     * @param groupId the groupId of the current group the item is being added to
     * @return This returns an instance of UpdatedLists data structure that contains lists of planning list and
     * purchased list information, or if there is an error in the use case, a string that describes the error.
     */
    public UpdatedLists performPlanningAdd(String name, String groupId) {
        PlannedItemInfo itemInfo = new PlannedItemInfo(name, groupId);
        return inputBoundary.addPlanning(itemInfo);
    }
}
