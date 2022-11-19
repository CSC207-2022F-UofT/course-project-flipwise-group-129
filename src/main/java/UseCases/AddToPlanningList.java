package UseCases;
import DataAccess.GroupDataAccess;
import DataStructures.PlannedItemInfo;
import Entities.*;
import InputBoundary.AddToPlanningBoundaryIn;
import DataStructures.UpdatedLists;
import OutputBoundary.AddToPlanningBoundaryOut;
import DataAccessInterface.*;

import java.util.ArrayList;
import java.util.List;

public class AddToPlanningList implements AddToPlanningBoundaryIn{
    AddToPlanningBoundaryOut outputBoundary;

    public AddToPlanningList(AddToPlanningBoundaryOut outputBoundary){
        this.outputBoundary = outputBoundary;
    }
    @Override
    public UpdatedLists addPlanning(PlannedItemInfo item) {
        String groupId = item.getGroupId();
        GroupDataInterface groupAccess = new GroupDataAccess(groupJasonPath);
        Group currGroup = retreiveGroupInfo(groupId, groupAccess);
        Item newItem = createItem(item);
        currGroup.getPlanningList().addItems(newItem);
        saveGroup(groupId, groupAccess);
        UpdatedLists updatedLists = new UpdatedLists(
                getUpdatedPlanning(currGroup.getPlanningList()), getUpdatedPurchase(currGroup.getPurchaseList()));
        // how detailed do you want retrieved from updated lists <-- all the info for items, I guess?
        return outputBoundary.displayLists(updatedLists);
    }
    private List<List<String>> getUpdatedPurchase(PlanningList planningList){
        List<List<String>> stringPlanningList = new ArrayList<>();
        for(Item curItem: planningList.getItems()){
            List<String> currentItem = new ArrayList<>();
            currentItem.add(curItem.getItemId());
            currentItem.add(curItem.getItemName());
            stringPlanningList.add(currentItem);
        }
        return stringPlanningList;
    }

    private List<List<String>> getUpdatedPlanning(PurchaseList purchaseList){
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
    private Item createItem(PlannedItemInfo item){
        String itemId = ""; // UTC timestamp
        Item newItem = new Item(itemId, item.getName(), null , item.getPrice());
        String itemStringify = newItem.toString();
        ItemDataInterface itemAccess = new ItemDataAccess(itemJasonPath);
        itemAccess.addorUpdateItem(itemStringify);
        return newItem;
    }
    private Group retreiveGroupInfo(String groupId, GroupDataInterface groupAccess){
        String groupInfo = groupAccess.groupAsString(groupID);
        return new Group(groupInfo); //the fromString is a constructor, right?
    }

    private void saveGroup(String groupId, GroupDataInterface groupAccess){
        groupAccess.addorUpdateGroup(groupId);
    }
}
