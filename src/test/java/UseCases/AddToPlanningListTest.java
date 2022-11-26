package UseCases;

import static org.junit.jupiter.api.Assertions.*;

import DataAccess.GroupDataAccess;
import DataAccess.ItemDataAccess;
import DataAccess.UserDataAccess;
import DataAccessInterface.GroupDataInterface;
import DataAccessInterface.ItemDataInterface;
import DataAccessInterface.UserDataInterface;
import DataStructures.PlannedItemInfo;
import DataStructures.UpdatedLists;
import Entities.*;
import InputBoundary.AddToPlanningBoundaryIn;
import Presenters.AddToPlanningPresenter;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

class AddToPlanningListTest {
    @Test
    void itemAddedWithAllRequiredInfoTest(){
        boolean flagExists = false;
        GroupDataInterface groupData = null;
        ItemDataInterface itemData = null;
        {
            try {
                groupData = new GroupDataAccess();
                itemData = new ItemDataAccess();
            } catch (IOException | ParseException e) {
                assert(false);
            }
        }

        AddToPlanningPresenter presenter = new AddToPlanningPresenter();
        AddToPlanningBoundaryIn usecase = new AddToPlanningList(presenter, groupData, itemData);

        // 2) Input data — we can make this up for the test. Normally it would
        // be created by the Controller.
        PlannedItemInfo newItem = new PlannedItemInfo("maggi", "group1");

        // 3) Run the use case
        UpdatedLists outputData = usecase.addPlanning(newItem);

        if(outputData.getNewPlanningList() != null){
            for (List<String> temp: outputData.getNewPlanningList()) {
                if (Objects.equals(temp.get(1), "Maggi")) {
                    flagExists = true;
                    break;
                }
            }
        }
        else {
            assert(false);
        }

        assert (flagExists);
    }

    @Test
    void itemNotAddedIfGroupIdDNETest(){
        boolean flagExists = false;
        GroupDataInterface groupData = null;
        ItemDataInterface itemData = null;
        {
            try {
                groupData = new GroupDataAccess();
                itemData = new ItemDataAccess();
            } catch (IOException | ParseException e) {
                assert(false);
            }
        }

        AddToPlanningPresenter presenter = new AddToPlanningPresenter();
        AddToPlanningBoundaryIn usecase = new AddToPlanningList(presenter, groupData, itemData);

        // 2) Input data — we can make this up for the test. Normally it would
        // be created by the Controller.
        PlannedItemInfo newItem = new PlannedItemInfo("maggi", "noGroup");

        // 3) Run the use case
        UpdatedLists outputData = usecase.addPlanning(newItem);

        if(outputData.getNewPlanningList() == null && outputData.getResultMessage().equals("Group cannot be found")){
            flagExists = true;
        }

        assert (flagExists);
    }

    @Test
    void createItemDbCheck() throws IOException, org.json.simple.parser.ParseException {
        GroupDataInterface groupData = null;
        ItemDataInterface itemData = null;
        {
            try {
                groupData = new GroupDataAccess();
                itemData = new ItemDataAccess();
            } catch (IOException | ParseException e) {
                assert(false);
            }
        }

        AddToPlanningPresenter presenter = new AddToPlanningPresenter();
        AddToPlanningBoundaryIn usecase = new AddToPlanningList(presenter, groupData, itemData);

        // 2) Input data — we can make this up for the test. Normally it would
        // be created by the Controller.
        PlannedItemInfo newItem = new PlannedItemInfo("maggi", "noGroup");
        List<String> itemInfoBefore = getItemInfo();
        Group groupBefore = getGroupInfo();
        // 3) Run the use case
        usecase.addPlanning(newItem);
        try {
            List<String> itemInfoAfter = getItemInfo();
            Group groupAfter = getGroupInfo();
            assert !(groupAfter == null | groupBefore == null);
            assert (groupBefore.getPlanningList().getItems().size() + 1) == groupAfter.getPlanningList().getItems().size();
            assert itemInfoBefore.get(0).equals("itemId does not exist");
            assert itemInfoAfter.get(1).equals("maggi");
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    List<String> getItemInfo() throws IOException, ParseException {
        ItemDataInterface itemDsInterface = new ItemDataAccess();
        List<String> itemInfo = new ArrayList<>();
        Map<String, String> itemDsMap = itemDsInterface.getItemMap();
        for(Map.Entry<String, String> curItem: itemDsMap.entrySet()){
            try {
                String curItemName = Item.fromString(curItem.getValue()).getItemName();
                if(curItemName.equals("maggi")){
                    itemInfo.add(curItem.getKey());
                    itemInfo.add(curItemName);
                    return itemInfo;
                }
            } catch (JsonProcessingException e) {
                itemInfo.add("Unable to process item from the database");
                return itemInfo;
            }
        }
        itemInfo.add("item does not exist");
        return itemInfo;
    }
    Group getGroupInfo() throws IOException, ParseException {
        GroupDataInterface groupDsInterface = new GroupDataAccess();
        Map<String, String> groupDsMap = groupDsInterface.getGroupMap();
        for(Map.Entry<String, String> curGroup: groupDsMap.entrySet()){
            try {
                Group curGroupEntity = Group.fromString(curGroup.getValue());
                if(curGroupEntity.getGroupName().equals("group1")){
                    return curGroupEntity;
                }
            } catch (JsonProcessingException e) {
                return null;
            }
        }
        return null;
    }
}