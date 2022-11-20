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

    public AddToPlanningList(
            AddToPlanningBoundaryOut outputBoundary, GroupDataInterface groupAccess, ItemDataInterface itemAccess){
        this.outputBoundary = outputBoundary;
        this.groupAccess = groupAccess;
        this.itemAccess = itemAccess;
    }
    @Override
    public UpdatedLists addPlanning(PlannedItemInfo item) {
        String groupId = item.getGroupId();
        // Get the Group and Item entities to manipulate
        Group currGroup = retreiveGroup(groupId);
        Item newItem = createItem(item);
        // Add the new item to the current group's planning list and save it back into the database.
        currGroup.getPlanningList().addItems(newItem);
        saveGroup(groupId, currGroup.toString());
        UpdatedLists updatedLists = new UpdatedLists(
                getUpdatedPlanning(currGroup.getPlanningList()), getUpdatedPurchase(currGroup.getPurchaseList()));
        return outputBoundary.displayLists(updatedLists);
    }
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

    private List<List<String>> getUpdatedPurchase(PurchaseList purchaseList){
        List<List<String>> stringPurchasedList = new ArrayList<>();
        for(Item curItem: purchaseList.getItems()){
            List<String> currentItem = new ArrayList<>();
            currentItem.add(curItem.getItemId());
            currentItem.add(curItem.getItemName());
            currentItem.add(curItem.getBuyer().getUsername());
            currentItem.add(curItem.getPrice().toString());
            stringPurchasedList.add(currentItem);
        }
        return stringPurchasedList;
    }
    private Item createItem(PlannedItemInfo item) {
        Item newItem = new Item(item.getName(), null , item.getPrice(), new ArrayList<>());
        try {
            itemAccess.addorUpdateItem(newItem.getItemId(), newItem.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return newItem;
    }
    private Group retreiveGroup(String groupId) {
        String groupInfo = groupAccess.groupAsString(groupId);
        try {
            return Group.fromString(groupInfo);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveGroup(String groupId, String groupData) {
        try {
            groupAccess.addorUpdateGroup(groupId, groupData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
