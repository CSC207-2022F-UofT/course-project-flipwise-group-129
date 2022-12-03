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
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

class AddToPlanningListTest {
    
    @Before
    public void setUp() throws IOException {
        //copy and create duplicate test stuff
        Path copiedGroups = Paths.get("src/test/resources/testgroupsCopy.json");
        Path originalPathGroups = Paths.get("src/test/resources/testgroups.json");
        Files.copy(originalPathGroups, copiedGroups, StandardCopyOption.REPLACE_EXISTING);

        Path copiedUsers = Paths.get("src/test/resources/testusersCopy.json");
        Path originalPathUsers = Paths.get("src/test/resources/testusers.json");
        Files.copy(originalPathUsers, copiedUsers, StandardCopyOption.REPLACE_EXISTING);
    }

    @After
    public void tearDown(){
        File groupFile = new File("src/test/resources/testgroupsCopy.json");
        groupFile.delete();

        File userFile = new File("src/test/resources/testusersCopy.json");
        userFile.delete();
    }
    
    @Test
    void itemAddedWithAllRequiredInfoTest(){

        try {
            setUp();
        } catch (IOException e) {
            fail("unexpected database error");
        }
        boolean flagExists = false;
        GroupDataInterface groupData = null;
        ItemDataInterface itemData = null;
        {
            try {
                groupData = new GroupDataAccess("test");
                itemData = new ItemDataAccess("test");
            } catch (IOException | ParseException e) {
                assert(false);
            }
        }

        AddToPlanningPresenter presenter = new AddToPlanningPresenter();
        AddToPlanningBoundaryIn usecase = new AddToPlanningList(presenter, groupData, itemData);

        // 2) Input data — we can make this up for the test. Normally it would
        // be created by the Controller.
        PlannedItemInfo newItem = new PlannedItemInfo("maggi", "grpOne11");

        // 3) Run the use case
        UpdatedLists outputData = usecase.addPlanning(newItem);

        if(outputData.getNewPlanningList() != null){
            for (List<String> temp: outputData.getNewPlanningList()) {
                if (Objects.equals(temp.get(1), "maggi")) {
                    flagExists = true;
                    break;
                }
            }
        }
        else {
            assert(false);
        }

        assert (flagExists);
        
        tearDown();
    }

    @Test
    void itemNotAddedIfGroupIdDNETest(){
        try {
            setUp();
        } catch (IOException e) {
            fail("unexpected database error");
        }

        boolean flagExists = false;
        GroupDataInterface groupData = null;
        ItemDataInterface itemData = null;
        {
            try {
                groupData = new GroupDataAccess("test");
                itemData = new ItemDataAccess("test");
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
        
        tearDown();
    }

    @Test
    void createItemDbCheck() throws IOException, org.json.simple.parser.ParseException {
        
        setUp();
        GroupDataInterface groupData = null;
        ItemDataInterface itemData = null;
        {
            try {
                groupData = new GroupDataAccess("test");
                itemData = new ItemDataAccess("test");
            } catch (IOException | ParseException e) {
                assert(false);
            }
        }

        AddToPlanningPresenter presenter = new AddToPlanningPresenter();
        AddToPlanningBoundaryIn usecase = new AddToPlanningList(presenter, groupData, itemData);

        // 2) Input data — we can make this up for the test. Normally it would
        // be created by the Controller.
        PlannedItemInfo newItem = new PlannedItemInfo("paneer", "grpOne11");
        List<String> itemInfoBefore = getItemInfo();
        Group groupBefore = getGroupInfo();
        // 3) Run the use case
        usecase.addPlanning(newItem);
        try {
            List<String> itemInfoAfter = getItemInfo();
            Group groupAfter = getGroupInfo();
            assert !(groupAfter == null | groupBefore == null);
            assert (groupBefore.getPlanningList().getItems().size() + 1) == groupAfter.getPlanningList().getItems().size();
            assert itemInfoBefore.get(0).equals("item does not exist");
            assert itemInfoAfter.get(1).equals("paneer");
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
        
        tearDown();
    }

    List<String> getItemInfo() throws IOException, ParseException {
        ItemDataInterface itemDsInterface = new ItemDataAccess("test");
        List<String> itemInfo = new ArrayList<>();
        Map<String, String> itemDsMap = itemDsInterface.getItemMap();
        for(Map.Entry<String, String> curItem: itemDsMap.entrySet()){
            try {
                String curItemName = Item.fromString(curItem.getValue()).getItemName();
                if(curItemName.equals("paneer")){
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
        GroupDataInterface groupDsInterface = new GroupDataAccess("test");
        try {
            return Group.fromString(groupDsInterface.groupAsString("grpOne11"));
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
