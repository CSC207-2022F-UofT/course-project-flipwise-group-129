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
        PlannedItemInfo newItem = new PlannedItemInfo("Maggi", "group1");

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
        PlannedItemInfo newItem = new PlannedItemInfo("Maggi", "noGroup");

        // 3) Run the use case
        UpdatedLists outputData = usecase.addPlanning(newItem);

        if(outputData.getNewPlanningList() == null && outputData.getResultMessage().equals("Group cannot be found")){
            flagExists = true;
        }

        assert (flagExists);
    }

    @Test
    void itemAddedSuccessDatabaseUpdateCheck(){
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
        PlannedItemInfo newItem = new PlannedItemInfo("Maggi", "noGroup");

        // 3) Run the use case
        UpdatedLists outputData = usecase.addPlanning(newItem);

        if(outputData.getNewPlanningList() == null && outputData.getResultMessage().equals("Group cannot be found")){
            flagExists = true;
        }

        assert (flagExists);
    }

    @Test
    void createUserDbCheck() throws IOException, org.json.simple.parser.ParseException {
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
        PlannedItemInfo newItem = new PlannedItemInfo("Maggi", "noGroup");
        String userInfoBefore = getItemInfo();
        // 3) Run the use case
        UpdatedLists outputData = usecase.addPlanning(newItem);
        // chekc if the users list of groups has been updated
        try {
            String userInfoAfter = getItemInfo();
            assert userInfoBefore.equals("itemId does not exist");
            assert userInfoAfter.equals("maggi");
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }

        // confirm now that the group is in the db
        assert groupData.groupIdExists("0001");

    }

    String getItemInfo() throws IOException, ParseException {
        ItemDataInterface itemDsInterface = new ItemDataAccess();
        Map<String, String> itemDsMap = itemDsInterface.getItemMap();
        String currentItemId = null;
        for(Map.Entry<String, String> curItem: itemDsMap.entrySet()){
            try {
                String curItemName = Item.fromString(curItem.getValue()).getItemName();
                if(curItemName.equals("maggi")){
                    return curItem.getKey();
                }
            } catch (JsonProcessingException e) {
                return "Unable to process item from the database";
            }
        }
        return "item does not exist";
    }
}