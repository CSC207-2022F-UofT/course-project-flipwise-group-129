package group_create_use_case;

import DataAccess.GroupDataAccess;
import DataAccess.UserDataAccess;
import DataAccessInterface.GroupDataInterface;
import DataAccessInterface.UserDataInterface;
import DataStructures.ProposedGroupInfo;
import DataStructures.CreatedGroupInfo;
import Entities.*;
import InputBoundary.GroupCreateBoundaryIn;
import UseCases.GroupCreate;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import Presenters.GroupCreatePresenter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.HashMap;

class GroupCreateInteractorTest {

    @Test
    void createUserExist() throws IOException, ParseException {
        // To test the use case:
        // 1) Create a GroupCreateInteractorTest and prerequisite objects
        //    (arguments for the GroupCreateController constructor parameters)
        // 2) create the Input Data in the form of the group and users
        // 3) Call the use case GroupCreate Input Boundary method to run the use case
        // 4) Check that the Output Data passed to the Presenter is correct
        // 5) Check that the expected changes to the data layer are there.

        // 1) Instantiate
        GroupCreatePresentor presenter = new GroupCreatePresenter();
        GroupCreate usecase = new GroupCreate();
        GroupDataInterface groupData = new GroupDataAccess();
        UserDataInterface userData = new UserDataAccess();


        // 2) Input data — we can make this up for the test. Normally it would
        // be created by the Controller.
        ProposedGroupInfo inputData = new ProposedGroupInfo("mishaalk", "groupDarcy");

        // 3) Run the use case
        CreatedGroupInfo outputData = usecase.create(inputData);

        assert outputData.getGroupNames().contains("groupDarcy");
        assert outputData.getGroupName().equals("groupDarcy");
        assert outputData.error == null;

    }

    @Test
    void createUserNotExist() throws IOException, ParseException {
        // To test the use case:
        // 1) Create a GroupCreateInteractorTest and prerequisite objects
        //    (arguments for the GroupCreateController constructor parameters)
        // 2) create the Input Data in the form of the group and users
        // 3) Call the use case GroupCreate Input Boundary method to run the use case
        // 4) Check that the Output Data passed to the Presenter is correct
        // 5) Check that the expected changes to the data layer are there.

        // 1) Instantiate
        GroupCreatePresentor presenter = new GroupCreatePresenter();
        GroupCreate usecase = new GroupCreate();
        GroupDataInterface groupData = new GroupDataAccess();
        UserDataInterface userData = new UserDataAccess();


        // 2) Input data — we can make this up for the test. Normally it would
        // be created by the Controller.
        ProposedGroupInfo inputData = new ProposedGroupInfo("mishaalki", "groupDarcy");

        // 3) Run the use case
        CreatedGroupInfo outputData = usecase.create(inputData);

        assert outputData.getGroupNames() == null;
        assert outputData.getGroupName() == null;
        assert outputData.getGroupId() == null;
        assert outputData.getGroupIds() == null;
        assert outputData.error != null;
        
    }
}