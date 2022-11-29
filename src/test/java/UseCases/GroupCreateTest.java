package UseCases;

import DataAccess.GroupDataAccess;
import DataAccess.UserDataAccess;
import DataAccessInterface.GroupDataInterface;
import DataAccessInterface.UserDataInterface;
import DataStructures.CreatedGroupInfo;
import DataStructures.ProposedGroupInfo;
import Entities.Group;
import Entities.User;
import InputBoundary.GroupCreateBoundaryIn;
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

import Presenters.GroupCreatePresenter;

import static org.junit.jupiter.api.Assertions.*;

class GroupCreateTest {

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

    List<String> getUserInfo() throws IOException, ParseException {
        UserDataInterface userDsInterface = new UserDataAccess("test");
        // get the user from the database
        //check if the user exists
        if (!userDsInterface.userIdExists("mishaalk")){
            throw new RuntimeException("User Id does not exist");
        }
        String userString = userDsInterface.userAsString("mishaalk");

        try {
            return new ArrayList<>(User.fromString(userString).getGroups());
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

        setUp();

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


        GroupDataInterface groupData = new GroupDataAccess("test");
        UserDataInterface userData = new UserDataAccess("test");
        GroupCreateBoundaryIn useCase = new GroupCreate(presenter, groupData, userData);

        // 2) Input data — we can make this up for the test. Normally it would
        // be created by the Controller.
        ProposedGroupInfo inputData = new ProposedGroupInfo("mishaalk", "groupDarcy");

        // 3) Run the use case
        useCase.createNewGroup(inputData);

        tearDown();
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

        setUp();

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


        GroupDataInterface groupData = new GroupDataAccess("test");
        UserDataInterface userData = new UserDataAccess("test");
        GroupCreateBoundaryIn useCase = new GroupCreate(presenter, groupData, userData);

        // 2) Input data — we can make this up for the test. Normally it would
        // be created by the Controller.
        ProposedGroupInfo inputData = new ProposedGroupInfo("mishaalki", "groupDarcy");

        // 3) Run the use case
        useCase.createNewGroup(inputData);

        tearDown();
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

        setUp();

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


        GroupDataInterface groupData = new GroupDataAccess("test");
        UserDataInterface userData = new UserDataAccess("test");
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
        boolean found = false;
        Map<String, String> groupMap = groupData.getGroupMap();
        for (Map.Entry<String, String> idEntry : groupMap.entrySet()) {
            String groupId = idEntry.getKey();
            Group group = Group.fromString(groupData.groupAsString(groupId));
            if (group.getGroupName().equals("groupDarcy")){
                found = true;
            }
        }
        assert found;

        tearDown();

    }
}