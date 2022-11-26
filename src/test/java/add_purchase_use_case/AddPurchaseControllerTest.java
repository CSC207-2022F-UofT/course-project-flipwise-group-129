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
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import Presenters.AddPurchasePresenter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

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

        List<List<String>> returnedPlanning = outputData.getNewPlanningList();
        List<List<String>> returnedPurchased = outputData.getNewPurchasedList();

        PlanningList dbPlanning = groupInfoAfter.getPlanningList();
        PurchaseList dbPurchased = groupInfoAfter.getPurchaseList();

        assert (dbPlanning.getItems().size() == returnedPlanning.size());
        assert (dbPurchased.getItems().size() == returnedPurchased.size());

        for (int i = 0; i < dbPlanning.getItems().size(); i++) {
            assert (Objects.equals(dbPlanning.getItems().get(i).getItemId(), returnedPlanning.get(i).get(0)));
            assert (Objects.equals(dbPlanning.getItems().get(i).getItemName(), returnedPlanning.get(i).get(1)));
        }

        for (int i = 0; i < dbPurchased.getItems().size(); i++) {
            assert (Objects.equals(dbPurchased.getItems().get(i).getItemId(), returnedPurchased.get(i).get(0)));
            assert (Objects.equals(dbPurchased.getItems().get(i).getItemName(), returnedPurchased.get(i).get(1)));
            assert (Objects.equals(String.valueOf(dbPurchased.getItems().get(i).getPrice()), returnedPurchased.get(i).get(2)));
            assert (Objects.equals(dbPurchased.getItems().get(i).getBuyer().getUsername(), returnedPurchased.get(i).get(3)));
        }
    }

    Group getGroupInfo() throws IOException, ParseException {
        GroupDataInterface groupDsInterface = new GroupDataAccess();
        List<String> stringUsers = new ArrayList<>();
        List<String> stringItems = new ArrayList<>();
        List<String> stringPlanned = new ArrayList<>();
        // get the user from the database
        //check if the user exists
        if (!groupDsInterface.groupIdExists("group1")){
            throw new RuntimeException("Group Id does not exist");
        }
        String groupString = groupDsInterface.groupAsString("group1");

        Group group = Group.fromString(groupString);
        return group;
    }
}