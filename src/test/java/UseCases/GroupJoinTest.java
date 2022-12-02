package UseCases;

import DataAccess.GroupDataAccess;
import DataAccess.UserDataAccess;
import DataAccessInterface.GroupDataInterface;
import DataAccessInterface.UserDataInterface;
import DataStructures.CreatedGroupInfo;
import DataStructures.JoinGroupRequest;
import DataStructures.JoinedGroupInfo;
import DataStructures.ProposedGroupInfo;
import Entities.Group;
import Entities.User;
import InputBoundary.GroupCreateBoundaryIn;
import InputBoundary.GroupJoinBoundaryIn;
import Presenters.GroupCreatePresenter;
import Presenters.GroupJoinPresenter;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import java.nio.file.*;

class GroupJoinTest {

    // To test the use case:
    // 1) Create a GroupJoinTest and prerequisite objects
    //    (arguments for the GroupJoinController constructor parameters)
    // 2) create the Input Data in the form of the group and users
    // 3) Call the use case GroupJoin Input Boundary method to run the use case
    // 4) Check that the Output Data passed to the Presenter is correct
    // 5) Check that the expected changes to the data layer are there.


    @Before
    public void setUp() throws IOException {
        //copy and create duplicate test stuff
        Path copiedGroups = Paths.get("././src/test/resources/testgroupsCopy.json");
        Path originalPathGroups = Paths.get("././src/test/resources/testgroups.json");
        Files.copy(originalPathGroups, copiedGroups, StandardCopyOption.REPLACE_EXISTING);

        Path copiedUsers = Paths.get("././src/test/resources/testusersCopy.json");
        Path originalPathUsers = Paths.get("././src/test/resources/testusers.json");
        Files.copy(originalPathUsers, copiedUsers, StandardCopyOption.REPLACE_EXISTING);
    }

    @After
    public void tearDown(){
        File groupFile = new File("././src/test/resources/testgroupsCopy.json");
        groupFile.delete();

        File userFile = new File("././src/test/resources/testusersCopy.json");
        userFile.delete();
    }
    @Test
    void joinGroupSuccess() throws IOException, ParseException {

        setUp();

        // 1) Instantiate
        GroupJoinPresenter presenter = new GroupJoinPresenter() {
            @Override
            public JoinedGroupInfo prepareSuccessView(JoinedGroupInfo outputData){
                assert outputData.getGroupNames().contains("group1");
                assert outputData.getUsersInGroup().contains("rcordi");
                assert outputData.getError() == null;
                return null;
            }

            @Override
            public JoinedGroupInfo prepareFailView(JoinedGroupInfo joinedGroupInfo){
                fail("Use case failure is unexpected");
                return null;
            }
        };

        GroupDataInterface groupData = new GroupDataAccess("test");
        UserDataInterface userData = new UserDataAccess("test");
        GroupJoinBoundaryIn useCase = new GroupJoin(presenter, groupData, userData);

        // 2) Input data — we can make this up for the test. Normally it would
        // be created by the Controller.
        JoinGroupRequest inputData = new JoinGroupRequest("group1", "rcordi");

        // 3) Run the use case
        useCase.joinGroup(inputData);

        tearDown();

    }

    @Test
    void joinGroupDNE() throws IOException, ParseException {

        setUp();

        // 1) Instantiate
        GroupJoinPresenter presenter = new GroupJoinPresenter() {
            @Override
            public JoinedGroupInfo prepareSuccessView(JoinedGroupInfo outputData){
                fail("Use case success is unexpected");
                return null;
            }

            @Override
            public JoinedGroupInfo prepareFailView(JoinedGroupInfo joinedGroupInfo){
                assert joinedGroupInfo.getUsersInGroup()== null;
                assert joinedGroupInfo.getGroupIds() == null;
                assert joinedGroupInfo.getGroupNames() == null;
                assert joinedGroupInfo.getPlanningList() == null;
                assert joinedGroupInfo.getPurchasedList() == null;
                assert Objects.equals(joinedGroupInfo.getError(), "Invalid GroupID provided");
                return null;
            }
        };

        GroupDataInterface groupData = new GroupDataAccess("test");
        UserDataInterface userData = new UserDataAccess("test");
        GroupJoinBoundaryIn useCase = new GroupJoin(presenter, groupData, userData);

        // 2) Input data — we can make this up for the test. Normally it would
        // be created by the Controller.
        JoinGroupRequest inputData = new JoinGroupRequest("mishaalk", "groupDarcy1");

        // 3) Run the use case
        useCase.joinGroup(inputData);

        tearDown();

    }

    @Test
    void joinGroupAlreadyJoined() throws IOException, ParseException {

        setUp();

        // 1) Instantiate
        GroupJoinPresenter presenter = new GroupJoinPresenter() {
            @Override
            public JoinedGroupInfo prepareSuccessView(JoinedGroupInfo outputData){
                fail("Use case success is unexpected");
                return null;
            }

            @Override
            public JoinedGroupInfo prepareFailView(JoinedGroupInfo joinedGroupInfo){
                assert joinedGroupInfo.getUsersInGroup()== null;
                assert joinedGroupInfo.getGroupIds() == null;
                assert joinedGroupInfo.getGroupNames() == null;
                assert joinedGroupInfo.getPlanningList() == null;
                assert joinedGroupInfo.getPurchasedList() == null;
                assert Objects.equals(joinedGroupInfo.getError(), "User already in group");
                return null;
            }
        };

        GroupDataInterface groupData = new GroupDataAccess("test");
        UserDataInterface userData = new UserDataAccess("test");
        GroupJoinBoundaryIn useCase = new GroupJoin(presenter, groupData, userData);

        // 2) Input data — we can make this up for the test. Normally it would
        // be created by the Controller.
        JoinGroupRequest inputData = new JoinGroupRequest("rcordi", "group1");

        // 3) Run the use case
        useCase.joinGroup(inputData);

        tearDown();

    }


    List<String> getUserInfo() throws IOException, ParseException {
        UserDataInterface userDsInterface = new UserDataAccess("test");
        // get the user from the database
        //check if the user exists
        if (!userDsInterface.userIdExists("rcordi")){
            throw new RuntimeException("User Id does not exist");
        }
        String userString = userDsInterface.userAsString("rcordi");

        try {
            return new ArrayList<>(User.fromString(userString).getGroups());
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Unable to process user from database");
        }
    }


    List<List<String>> getGroupInfo() throws IOException, ParseException {
        GroupDataInterface groupDsInterface = new GroupDataAccess("test");
        List<String> stringItems = new ArrayList<>();
        List<String> stringPlanned = new ArrayList<>();
        // get the user from the database
        //check if the user exists
        if (!groupDsInterface.groupIdExists("groupOne11")){
            throw new RuntimeException("Group Id does not exist");
        }
        String groupString = groupDsInterface.groupAsString("groupOne11");

        try {
            Group group = Group.fromString(groupString);
            group.getPurchaseList().getItems().forEach(item -> stringItems.add(item.getItemId()));
            group.getPlanningList().getItems().forEach(item -> stringPlanned.add(item.getItemId()));
            List<String> stringUsers = new ArrayList<>(group.getUsers());
            List<List<String>> overall = new ArrayList<>();
            overall.add(stringItems);
            overall.add(stringPlanned);
            overall.add(stringUsers);
            return overall;
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Unable to process user from database");
        }
    }

    @Test
    void joinGroupDb() throws IOException, ParseException {

        setUp();
        // 1) Instantiate
        GroupJoinPresenter presenter = new GroupJoinPresenter() {
            @Override
            public JoinedGroupInfo prepareSuccessView(JoinedGroupInfo outputData){

                assert outputData.getError() != null;
                return null;
            }

            @Override
            public JoinedGroupInfo prepareFailView(JoinedGroupInfo joinedGroupInfo){
                fail("Use case fail is unexpected");
                return null;
            }
        };

        GroupDataInterface groupData = new GroupDataAccess("test");
        UserDataInterface userData = new UserDataAccess("test");
        GroupJoinBoundaryIn useCase = new GroupJoin(presenter, groupData, userData);

        // 2) Input data — we can make this up for the test. Normally it would
        // be created by the Controller.
        JoinGroupRequest inputData = new JoinGroupRequest("groupOne11", "rcordi");

        //setting the data from the database as a constant to check later
        List<String> userInfoBefore = getUserInfo();
        List<List<String>> groupInfoBefore = getGroupInfo();

        // 3) Run the use case
        useCase.joinGroup(inputData);

        try {
            List<String> userInfoAfter = getUserInfo();
            for (String s : userInfoBefore) {
                assert userInfoAfter.contains(s);
            }
            assert userInfoAfter.contains("groupOne11");
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
        try {
            List<List<String>> groupInfoAfter = getGroupInfo();
            assert groupInfoAfter.get(0) == groupInfoBefore.get(0);
            assert groupInfoAfter.get(1) == groupInfoBefore.get(1);

            for (String s : groupInfoBefore.get(2)) {
                assert groupInfoAfter.get(2).contains(s);
            }
            assert groupInfoAfter.get(2).contains("rcordi");
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }

        tearDown();

    }

}