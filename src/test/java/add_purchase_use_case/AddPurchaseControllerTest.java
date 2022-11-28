package add_purchase_use_case;

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
import org.junit.jupiter.api.Test;
import Presenters.AddPurchasePresenter;

import java.io.IOException;
import java.util.*;

class AddPurchaseControllerTest {

    @Test
    void testPurchaseSuccess() throws IOException, ParseException {
        // To test the use case:
        // 1) Create a AddPurchaseController and prerequisite objects
        //    (arguments for the AddPurchaseController constructor parameters)
        // 2) create the Input Data in the form of the group, lists, and item
        // 3) Call the use case AddPurchase input boundary method to run the use case
        // 4) Check that the Output Data passed to the Presenter is correct
        // 5) Check that the expected changes to the data layer are there.

        // 1) Instantiate
        AddPurchasePresenter presenter = new AddPurchasePresenter();
        AddPurchaseBoundaryIn usecase = new AddPurchase();
        GroupDataInterface groupData = new GroupDataAccess();
        UserDataInterface userData = new UserDataAccess();
        ItemDataInterface itemData = new ItemDataAccess();


        // 2) Input data — we can make this up for the test. Normally it would
        // be created by the Controller.
        PurchaseInfo inputData = new PurchaseInfo("1", new ArrayList<>(Collections.singleton("Avi")),
                "Avi", 10.0f, "group1", presenter, groupData, itemData, userData);

        // 3) Run the use case
        UpdatedLists outputData = usecase.executeUseCase(inputData);

        for (List<String> temp: outputData.getNewPlanningList()) {
            assert (!Objects.equals(temp.get(0), "1"));
        }
        boolean flagExists = false;
        for (List<String> temp: outputData.getNewPurchasedList()) {
            if (Objects.equals(temp.get(0), "1")) {
                flagExists = true;
                break;
            }
        }
        assert(flagExists);

        assert(Objects.equals(outputData.getResultMessage(), "Success"));

        Item finalItem = Item.fromString(itemData.itemAsString("1"));
        assert(finalItem.getPrice() != 0.0 && finalItem.getBuyer() != null);

    }

    @Test
    void testPurchaseFail() throws IOException, ParseException {
        // To test the use case:
        // 1) Create a AddPurchaseController and prerequisite objects
        //    (arguments for the AddPurchaseController constructor parameters)
        // 2) create the Input Data in the form of the group, lists, and item
        // 3) Call the use case AddPurchase input boundary method to run the use case
        // 4) Check that the Output Data passed to the Presenter is correct
        // 5) Check that the expected changes to the data layer are there.

        // 1) Instantiate
        AddPurchasePresenter presenter = new AddPurchasePresenter() {
            /**
             * Prepares and returns the information changed by the use case to the view
             *
             * @param updatedLists the data structure with the new planning and purchased lists
             * @return a UpdatedLists object containing the updated information of the lists
             */
            @Override
            public UpdatedLists prepareSuccessViewInformation(UpdatedLists updatedLists) {
                fail("Use case success is unprecedented");
                return null;
            }

            /**
             * Returns the information from an error in the form of an error message to the view
             *
             * @param errorInformation contains the error message raised
             * @return the data structure containing the error information
             */
            @Override
            public UpdatedLists prepareFailViewInformation(UpdatedLists errorInformation) {
                assert errorInformation.getNewPurchasedList() == null;
                assert errorInformation.getNewPlanningList() == null;
                assert !Objects.equals(errorInformation.getResultMessage(), "Success");
                return null;
            }
        };
        AddPurchaseBoundaryIn usecase = new AddPurchase();
        GroupDataInterface groupData = new GroupDataAccess();
        UserDataInterface userData = new UserDataAccess();
        ItemDataInterface itemData = new ItemDataAccess();


        // 2) Input data — we can make this up for the test. Normally it would
        // be created by the Controller.
        PurchaseInfo inputData = new PurchaseInfo("1", new ArrayList<>(Collections.singleton("Avi")),
                "Avi", 10.0f, "group1", presenter, groupData, itemData, userData);

        // 3) Run the use case
        UpdatedLists outputData = usecase.executeUseCase(inputData);


    }

    @Test
    void updateDbTest() throws IOException, ParseException {
        // 1) Instantiate
        AddPurchasePresenter presenter = new AddPurchasePresenter();
        AddPurchaseBoundaryIn usecase = new AddPurchase();
        GroupDataInterface groupData = new GroupDataAccess();
        UserDataInterface userData = new UserDataAccess();
        ItemDataInterface itemData = new ItemDataAccess();


        // 2) Input data — we can make this up for the test. Normally it would
        // be created by the Controller.
        PurchaseInfo inputData = new PurchaseInfo("1", new ArrayList<>(Collections.singleton("Avi")),
                "Avi", 10.0f, "group1", presenter, groupData, itemData, userData);

        // 3) Run the use case
        UpdatedLists outputData = usecase.executeUseCase(inputData);

        Group groupInfoAfter = getGroupInfo();
        Item itemInfoAfter = getItemInfo();

        assert (Objects.equals(itemInfoAfter.getBuyer().getUsername(), "Avi"));
        assert (itemInfoAfter.getPrice() == 10.0f);

        List<List<String>> returnedPlanning = outputData.getNewPlanningList();
        List<List<String>> returnedPurchased = outputData.getNewPurchasedList();

        PlanningList dbPlanning = groupInfoAfter.getPlanningList();
        PurchaseList dbPurchased = groupInfoAfter.getPurchaseList();

        assert (dbPlanning.getItems().size() == returnedPlanning.size());
        assert (dbPurchased.getItems().size() == returnedPurchased.size());

        int i = 0;
        Iterator<Item> iterPlan = dbPlanning.iterator();
        while (iterPlan.hasNext()) {
            Item item = iterPlan.next();
            assert (Objects.equals(item.getItemId(), returnedPlanning.get(i).get(0)));
            assert (Objects.equals(item.getItemName(), returnedPlanning.get(i).get(1)));
            i++;
        }

        i = 0;
        Iterator<Item> iterPur = dbPurchased.iterator();
        while (iterPur.hasNext()) {
            Item item = iterPur.next();
            assert (Objects.equals(item.getItemId(), returnedPurchased.get(i).get(0)));
            assert (Objects.equals(item.getItemName(), returnedPurchased.get(i).get(1)));
            assert (Objects.equals(String.valueOf(item.getPrice()), returnedPurchased.get(i).get(2)));
            assert (Objects.equals(item.getBuyer().getUsername(), returnedPurchased.get(i).get(3)));
            i++;
        }
    }

    Group getGroupInfo() throws IOException, ParseException {
        GroupDataInterface groupDsInterface = new GroupDataAccess();
        // get the user from the database
        //check if the user exists
        if (!groupDsInterface.groupIdExists("group1")){
            throw new RuntimeException("Group Id does not exist");
        }
        String groupString = groupDsInterface.groupAsString("group1");

        return Group.fromString(groupString);
    }

    Item getItemInfo() throws IOException, ParseException {
        ItemDataInterface itemDsInterface = new ItemDataAccess();

        if (!itemDsInterface.itemIdExists("1")){
            throw new RuntimeException(("ItemId does not exist"));
        }
        String itemString = itemDsInterface.itemAsString("1");

        return Item.fromString(itemString);
    }
}