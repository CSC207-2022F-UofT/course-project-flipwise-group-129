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
import UseCases.AddToPlanningList;
import Presenters.AddToPlanningPresenter;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

class AddToPlanningListTest {
    // To test the use case:
    // 1) Create a GroupCreateInteractorTest and prerequisite objects
    //    (arguments for the GroupCreateController constructor parameters)
    // 2) create the Input Data in the form of the group and users
    // 3) Call the use case GroupCreate Input Boundary method to run the use case
    // 4) Check that the Output Data passed to the Presenter is correct
    // 5) Check that the expected changes to the data layer are there.

    @Test
    void createUserNotExist() throws IOException, ParseException {
        GroupDataInterface groupData;
        ItemDataInterface itemData;
        {
            try {
                groupData = new GroupDataAccess();
                itemData = new ItemDataAccess();
            } catch (IOException | ParseException e) {
                throw new RuntimeException(e);
            }
        }

        AddToPlanningPresenter presenter = new AddToPlanningPresenter();
        AddToPlanningBoundaryIn usecase = new AddToPlanningList(presenter, groupData, itemData);



        // 2) Input data — we can make this up for the test. Normally it would
        // be created by the Controller.
        PlannedItemInfo newItem = new PlannedItemInfo("Maggi", "group1");

        // 3) Run the use case
        UpdatedLists outputData = usecase.addPlanning(newItem);

        boolean flagExists = false;
        for (List<String> temp: outputData.getNewPlanningList()) {
            if (Objects.equals(temp.get(0), "Maggi")) {
                flagExists = true;
                break;
            }
        }

        assert (flagExists);
}}