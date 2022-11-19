package UseCases;
import DataAccess.GroupDataAccess;
import DataStructures.PlannedItemInfo;
import Entities.*;
import InputBoundary.AddToPlanningBoundaryIn;
import DataStructures.UpdatedLists;
import OutputBoundary.AddToPlanningBoundaryOut;
import DataAccessInterface.*;
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
        UpdatedLists updatedLists = new UpdatedLists(currGroup.getPlanningList(), currGroup.getPurchaseList());
        // how detailed do you want retrieved from updated lists <-- all the info for items, I guess?
        return outputBoundary.displayLists(updatedLists);
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
