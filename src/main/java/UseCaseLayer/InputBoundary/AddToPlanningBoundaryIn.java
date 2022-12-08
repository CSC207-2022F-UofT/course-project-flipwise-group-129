package UseCaseLayer.InputBoundary;
import UseCaseLayer.DataStructures.PlannedItemInfo;
import UseCaseLayer.DataStructures.UpdatedLists;

public interface AddToPlanningBoundaryIn {
    /**
     * This function will be called from the controller to execute the use case AddtoPlanningList.
     * This function retrieves the current group object and creates a new item instance and adds
     * to the group's planning list. It then saves the item and group back into the database.
     * @param item a package of all relevant information to create the item and access database
     * @return This returns an instance of UpdatedLists data structure that contains lists of planning list and
     * purchased list information, or if there is an error in the use case, a string that describes the error.
     */
    UpdatedLists addPlanning(PlannedItemInfo item);

}
