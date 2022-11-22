package group_create_use_case;

import DataAccess.GroupDataAccess;
import DataAccess.ItemDataAccess;
import DataAccess.UserDataAccess;
import DataAccessInterface.GroupDataInterface;
import DataAccessInterface.ItemDataInterface;
import DataAccessInterface.UserDataInterface;
import DataStructures.PurchaseInfo;
import DataStructures.UpdatedLists;
import Entities.Item;
import InputBoundary.AddPurchaseBoundaryIn;
import UseCases.AddPurchase;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import Presenters.

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.HashMap;

class GroupCreateInteractorTest {

    @Test
    void create() throws IOException, ParseException {
        // To test the use case:
        // 1) Create a GroupCreateInteractorTest and prerequisite objects
        //    (arguments for the GroupCreateController constructor parameters)
        // 2) create the Input Data in the form of the group and users
        // 3) Call the use case GroupCreate Input Boundary method to run the use case
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
}