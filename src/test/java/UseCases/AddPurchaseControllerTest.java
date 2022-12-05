package UseCases;

import Controllers.AddPurchaseController;
import DataAccess.GroupDataAccess;
import DataAccess.ItemDataAccess;
import DataAccess.UserDataAccess;
import DataAccessInterface.GroupDataInterface;
import DataAccessInterface.ItemDataInterface;
import DataAccessInterface.UserDataInterface;
import DataStructures.PurchaseInfo;
import DataStructures.UpdatedLists;
import Entities.Group;
import Entities.Item;
import Entities.PlanningList;
import Entities.PurchaseList;
import InputBoundary.AddPurchaseBoundaryIn;
import UseCases.AddPurchase;
import static org.junit.jupiter.api.Assertions.*;

import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import Presenters.AddPurchasePresenter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

class AddPurchaseControllerTest {
    
    @Before
    public void setUp() throws IOException {
        //copy and create duplicate test stuff
        Path copiedGroups = Paths.get("src/test/resources/testgroupsCopy.json");
        Path originalPathGroups = Paths.get("src/test/resources/testgroups.json");
        Files.copy(originalPathGroups, copiedGroups, StandardCopyOption.REPLACE_EXISTING);

        Path copiedUsers = Paths.get("src/test/resources/testusersCopy.json");
        Path originalPathUsers = Paths.get("src/test/resources/testusers.json");
        Files.copy(originalPathUsers, copiedUsers, StandardCopyOption.REPLACE_EXISTING);

        Path copiedItems = Paths.get("src/test/resources/testitemsCopy.json");
        Path originalPathItems = Paths.get("src/test/resources/testitems.json");
        Files.copy(originalPathItems, copiedItems, StandardCopyOption.REPLACE_EXISTING);
    }

    @After
    public void tearDown(){
        File groupFile = new File("src/test/resources/testgroupsCopy.json");
        groupFile.delete();

        File userFile = new File("src/test/resources/testusersCopy.json");
        userFile.delete();

        File itemFile = new File("src/test/resources/testitemsCopy.json");
        itemFile.delete();
    }

    @Test
    void testPurchaseSuccess() throws IOException, ParseException {
        // To test the use case:
        // 1) Create a AddPurchaseController and prerequisite objects
        //    (arguments for the AddPurchaseController constructor parameters)
        // 2) create the Input Data in the form of the group, lists, and item
        // 3) Call the use case AddPurchase input boundary method to run the use case
        // 4) Check that the Output Data passed to the Presenter is correct
        // 5) Check that the expected changes to the data layer are there.

        setUp();
        // 1) Instantiate
        AddPurchasePresenter presenter = new AddPurchasePresenter();
        AddPurchaseBoundaryIn useCase = new AddPurchase();

        GroupDataInterface groupData = new GroupDataAccess("test");
        UserDataInterface userData = new UserDataAccess("test");
        ItemDataInterface itemData = new ItemDataAccess("test");
        AddPurchaseController controller = new AddPurchaseController(presenter, useCase, groupData, itemData, userData);


        // 2) Input data — we can make this up for the test. Normally it would
        // be created by the Controller.
        List<String> participatingUsers = new ArrayList<>();
        participatingUsers.add("sopleee");
        participatingUsers.add("mishaalk");


        // 3) Run the use case
        UpdatedLists outputData = controller.controlAddPurchaseUseCase("itemApple", participatingUsers, "sopleee", 10.0f, "grpOne11");

        for (List<String> temp: outputData.getNewPlanningList()) {
            assert (!Objects.equals(temp.get(0), "itemApple"));
        }
        boolean flagExists = false;
        for (List<String> temp: outputData.getNewPurchasedList()) {
            if (Objects.equals(temp.get(0), "itemApple")) {
                flagExists = true;
                break;
            }
        }
        assert(flagExists);

        assert(Objects.equals(outputData.getResultMessage(), "Success"));

        Item finalItem = Item.fromString(itemData.itemAsString("itemApple"));
        assert(finalItem.getPrice() == 10.0f && Objects.equals(finalItem.getBuyer(), "sopleee"));
        
        tearDown();

    }

    @Test
    void updateDbTest() throws IOException, ParseException {
        setUp();
        
        // 1) Instantiate
        AddPurchasePresenter presenter = new AddPurchasePresenter();
        AddPurchaseBoundaryIn useCase = new AddPurchase();

        GroupDataInterface groupData = new GroupDataAccess("test");
        UserDataInterface userData = new UserDataAccess("test");
        ItemDataInterface itemData = new ItemDataAccess("test");
        AddPurchaseController controller = new AddPurchaseController(presenter, useCase, groupData, itemData, userData);


        // 2) Input data — we can make this up for the test. Normally it would
        // be created by the Controller.
        List<String> participatingUsers = new ArrayList<>();
        participatingUsers.add("sopleee");
        participatingUsers.add("mishaalk");
        PurchaseInfo inputData = new PurchaseInfo("itemApple", participatingUsers,
                "sopleee", 10.0f, "grpOne11", presenter, groupData, itemData, userData);

        // 3) Run the use case
        UpdatedLists outputData = controller.controlAddPurchaseUseCase("itemApple", participatingUsers, "sopleee", 10.0f, "grpOne11");

        Group groupInfoAfter = getGroupInfo();
        Item itemInfoAfter = getItemInfo();

        assert (Objects.equals(itemInfoAfter.getBuyer(), "sopleee"));
        assert (itemInfoAfter.getPrice() == 10.0f);

        List<List<String>> returnedPlanning = outputData.getNewPlanningList();
        List<List<String>> returnedPurchased = outputData.getNewPurchasedList();

        PlanningList dbPlanning = groupInfoAfter.getPlanningList();
        PurchaseList dbPurchased = groupInfoAfter.getPurchaseList();

        assert (dbPlanning.getItems().size() == returnedPlanning.size());
        assert (dbPurchased.getItems().size() == returnedPurchased.size());

        int i = 0;
        for (Item item : dbPlanning) {
            assert (Objects.equals(item.getItemId(), returnedPlanning.get(i).get(0)));
            assert (Objects.equals(item.getItemName(), returnedPlanning.get(i).get(1)));
            i++;
        }

        i = 0;
        for (Item item : dbPurchased) {
            assert (Objects.equals(item.getItemId(), returnedPurchased.get(i).get(0)));
            assert (Objects.equals(item.getItemName(), returnedPurchased.get(i).get(1)));
            assert (Objects.equals(String.valueOf(item.getPrice()), returnedPurchased.get(i).get(2)));
            assert (Objects.equals(item.getBuyer(), returnedPurchased.get(i).get(3)));
            i++;
        }
        tearDown();
    }

    Group getGroupInfo() throws IOException, ParseException {
        GroupDataInterface groupDsInterface = new GroupDataAccess("test");
        // get the user from the database
        //check if the user exists
        if (!groupDsInterface.groupIdExists("grpOne11")){
            throw new RuntimeException("Group Id does not exist");
        }
        String groupString = groupDsInterface.groupAsString("grpOne11");

        return Group.fromString(groupString);
    }

    Item getItemInfo() throws IOException, ParseException {
        ItemDataInterface itemDsInterface = new ItemDataAccess("test");

        if (!itemDsInterface.itemIdExists("itemApple")){
            throw new RuntimeException(("ItemId does not exist"));
        }
        String itemString = itemDsInterface.itemAsString("itemApple");

        return Item.fromString(itemString);
    }
}
