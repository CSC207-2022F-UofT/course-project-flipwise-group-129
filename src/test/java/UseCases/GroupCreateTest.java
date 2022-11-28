package UseCases;

import DataAccess.GroupDataAccess;
import DataAccess.UserDataAccess;
import DataAccessInterface.GroupDataInterface;
import DataAccessInterface.UserDataInterface;
import DataStructures.CreatedGroupInfo;
import DataStructures.ProposedGroupInfo;
import Entities.User;
import InputBoundary.GroupCreateBoundaryIn;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import Presenters.GroupCreatePresenter;

import static org.junit.jupiter.api.Assertions.*;

class GroupCreateTest {

    List<String> getUserInfo() throws IOException, ParseException {
        UserDataInterface userDsInterface = new UserDataAccess();
        List<String> stringGroups = new ArrayList<>();
        // get the user from the database
        //check if the user exists
        if (!userDsInterface.userIdExists("mishaalk")){
            throw new RuntimeException("User Id does not exist");
        }
        String userString = userDsInterface.userAsString("mishaalk");

        try {
            User.fromString(userString).getGroups().forEach(group -> stringGroups.add(group.getGroupId()));
            return stringGroups;
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Unable to process user from database");
        }
    }

    @Test
    void createNewGroup() throws IOException, org.json.simple.parser.ParseException {
        // To test the use case:
        // 1) Create a GroupCreateTest and prerequisite objects
        //    (arguments for the GroupCreateController constructor parameters)
        // 2) create the Input Data in the form of the group and users
        // 3) Call the use case GroupCreate Input Boundary method to run the use case
        // 4) Check that the Output Data passed to the Presenter is correct
        // 5) Check that the expected changes to the data layer are there.

        // 1) Instantiate
        GroupCreatePresenter presenter = new GroupCreatePresenter() {
            @Override
            public CreatedGroupInfo prepareSuccessView(CreatedGroupInfo outputData){
                assert outputData.getAllGroupNames().contains("groupDarcy");
                assert outputData.getGroupName().equals("groupDarcy");
                assert outputData.getError() == null;
                assert outputData.getError() == null;
                return null;
            }

            @Override
            public CreatedGroupInfo prepareFailView(CreatedGroupInfo createdGroupInfo){
                fail("Use case failure is unexpected");
                return null;
            }
        };


        GroupDataInterface groupData = new GroupDataAccess();
        UserDataInterface userData = new UserDataAccess();
        GroupCreateBoundaryIn useCase = new GroupCreate(presenter, groupData, userData);

        // 2) Input data — we can make this up for the test. Normally it would
        // be created by the Controller.
        ProposedGroupInfo inputData = new ProposedGroupInfo("mishaalk", "groupDarcy");

        // 3) Run the use case
        useCase.createNewGroup(inputData);
    }

    @Test
    void createUserNotExist() throws IOException, org.json.simple.parser.ParseException {
        // To test the use case:
        // 1) Create a GroupCreateInteractorTest and prerequisite objects
        //    (arguments for the GroupCreateController constructor parameters)
        // 2) create the Input Data in the form of the group and users
        // 3) Call the use case GroupCreate Input Boundary method to run the use case
        // 4) Check that the Output Data passed to the Presenter is correct
        // 5) Check that the expected changes to the data layer are there.

        // 1) Instantiate
        GroupCreatePresenter presenter = new GroupCreatePresenter() {
            @Override
            public CreatedGroupInfo prepareSuccessView(CreatedGroupInfo outputData){

                fail("Use case success is unexpected");
                return null;
            }

            @Override
            public CreatedGroupInfo prepareFailView(CreatedGroupInfo outputData){
                assert outputData.getAllGroupNames() == null;
                assert outputData.getGroupName() == null;
                assert outputData.getId() == null;
                assert outputData.getAllGroupIds() == null;
                assert Objects.equals(outputData.getError(), "User Id does not exist");
                return null;
            }
        };


        GroupDataInterface groupData = new GroupDataAccess();
        UserDataInterface userData = new UserDataAccess();
        GroupCreateBoundaryIn useCase = new GroupCreate(presenter, groupData, userData);

        // 2) Input data — we can make this up for the test. Normally it would
        // be created by the Controller.
        ProposedGroupInfo inputData = new ProposedGroupInfo("mishaalki", "groupDarcy");

        // 3) Run the use case
        useCase.createNewGroup(inputData);
    }

    @Test
    void createUserDbCheck() throws IOException, org.json.simple.parser.ParseException {
        // To test the use case:
        // 1) Create a GroupCreateTest and prerequisite objects
        //    (arguments for the GroupCreateController constructor parameters)
        // 2) create the Input Data in the form of the group and users
        // 3) Call the use case GroupCreate Input Boundary method to run the use case
        // 4) Check that the Output Data passed to the Presenter is correct
        // 5) Check that the expected changes to the data layer are there.

        // 1) Instantiate
        GroupCreatePresenter presenter = new GroupCreatePresenter() {
            @Override
            public CreatedGroupInfo prepareSuccessView(CreatedGroupInfo outputData){
                assert outputData.getError() != null;
                return null;
            }

            @Override
            public CreatedGroupInfo prepareFailView(CreatedGroupInfo outputData){
                fail("Use case failure is unexpected");
                return null;
            }
        };


        GroupDataInterface groupData = new GroupDataAccess();
        UserDataInterface userData = new UserDataAccess();
        GroupCreateBoundaryIn useCase = new GroupCreate(presenter, groupData, userData);

        //setting the data from the database as a constant to check later
        List<String> userInfoBefore = getUserInfo();

        // 2) Input data — we can make this up for the test. Normally it would
        // be created by the Controller.
        ProposedGroupInfo inputData = new ProposedGroupInfo("mishaalk", "groupDarcy");

        // 3) Run the use case
        useCase.createNewGroup(inputData);

        // chekc if the users list of groups has been updated
        try {
            List<String> userInfoAfter = getUserInfo();
            for (String s : userInfoBefore) {
                assert userInfoAfter.contains(s);
            }
            assert userInfoAfter.contains("groupDarcy");
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }

        // confirm now that the group is in the db
        assert groupData.groupIdExists("0001");

    }
}