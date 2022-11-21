package UseCases;
import DataAccessInterface.*;
import DataStructures.PlannedItemInfo;
import Entities.*;
import InputBoundary.AddToPlanningBoundaryIn;
import DataStructures.UpdatedLists;
import OutputBoundary.AddToPlanningBoundaryOut;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddToPlanningList implements AddToPlanningBoundaryIn{
    AddToPlanningBoundaryOut outputBoundary;
    GroupDataInterface groupAccess;
    ItemDataInterface itemAccess;

    /**
     * Creates a new AddtoPlanningList use case instance to implement the use case
     * @param outputBoundary the output boundary to communicate updated display information to the view
     * @param groupAccess the data access interface to retrieve and update group relevant data
     * @param itemAccess the data access interface to update item relevant data
     */
    public AddToPlanningList(
            AddToPlanningBoundaryOut outputBoundary, GroupDataInterface groupAccess, ItemDataInterface itemAccess){
        this.outputBoundary = outputBoundary;
        this.groupAccess = groupAccess;
        this.itemAccess = itemAccess;
    }

    /**
     * This function will be called from the controller to execute the use case AddtoPlanningList
     * This function retrieves the current group object and creates a new item instance and adds
     * to the group's planning list. It then saves the item and group back into the database
     * @param item a package of all relevant information to create the item and access database
     * @return This returns an instance of UpdatedLists data structure that contains lists of planning list and
     * purchased list information, or if there is an error in the use case, a string that describes the error
     */
    @Override
    public UpdatedLists addPlanning(PlannedItemInfo item) {
        String groupId = item.getGroupId();
        // Get the Group and Item entities to manipulate
        Group currGroup = retreiveGroup(groupId);
        if (currGroup == null){
            return outputBoundary.failErrorMessage(new UpdatedLists("Group cannot be found"));
        }
        Item newItem = createItem(item);
        if (newItem == null){
            return outputBoundary.failErrorMessage(new UpdatedLists("Item could not be created"));
        }
        // Add the new item to the current group's planning list and save it back into the database.
        currGroup.getPlanningList().addItems(newItem);
        if (!saveGroup(groupId, currGroup.toString())){
            return outputBoundary.failErrorMessage(new UpdatedLists("PlanningList could not be updated"));
        }
        UpdatedLists updatedLists = new UpdatedLists(
                getUpdatedPlanning(currGroup.getPlanningList()), getUpdatedPurchase(currGroup.getPurchaseList()));
        return outputBoundary.displayLists(updatedLists);
    }

    /**
     * This function converts the current group's planning list object into a list that is readable in the view
     * @param planningList the current group's planningList object
     * @return This returns a list of itemName and itemId from the items in the current group's planning list
     */
    private List<List<String>> getUpdatedPlanning(PlanningList planningList){
        List<List<String>> stringPlanningList = new ArrayList<>();
        for(Item curItem: planningList.getItems()){
            List<String> currentItem = new ArrayList<>();
            currentItem.add(curItem.getItemId());
            currentItem.add(curItem.getItemName());
            stringPlanningList.add(currentItem);
        }
        return stringPlanningList;
    }
    /**
     * This function creates an item and saves the new item into the database
     * @param item packaged information from the controller containing relevant data on the new item
     * @return This returns the new item object created from the information or null if the item could not be saved
     */
    private Item createItem(PlannedItemInfo item) {
        Item newItem = new Item(item.getName());
        try {
            itemAccess.addorUpdateItem(newItem.getItemId(), newItem.toString());
        } catch (IOException e) {
            return null;
        }
        return newItem;
    }
    /**
     * This function returns a group object from the matching groupId in the database
     * @param groupId the current group's groupId
     * @return This returns a group object if found in the database, otherwise, null
     */
    private Group retreiveGroup(String groupId) {
        String groupInfo = groupAccess.groupAsString(groupId);
        try {
            return Group.fromString(groupInfo);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
    /**
     * This function saves the updated group information back into the database
     * @param groupId the current groupId
     * @param groupData the current group's information
     * @return This returns a success or failure of this attempt
     */
    private boolean saveGroup(String groupId, String groupData) {
        try {
            groupAccess.addorUpdateGroup(groupId, groupData);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
