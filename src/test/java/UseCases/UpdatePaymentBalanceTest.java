package UseCases;

import DataAccess.GroupDataAccess;
import DataAccess.ItemDataAccess;
import DataAccess.UserDataAccess;
import DataAccessInterface.GroupDataInterface;
import DataAccessInterface.ItemDataInterface;

import DataAccessInterface.UserDataInterface;
import DataStructures.PaymentInformation;
import DataStructures.UpdatedDebts;
import Entities.Debt;
import Entities.Group;
import Entities.User;
import InputBoundary.UpdatePaymentBalanceBoundaryIn;
import Presenters.UpdatePaymentBalancePresenter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.simple.parser.ParseException;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

class UpdatePaymentBalanceTest {

    /*
    In order to test the use case, we will need to do the following:

    1. Create a UpdatePaymentBalanceTest and prerequisite objects (arguments for the UpdatePaymentBalanceController
       constructor parameters).
    2. Create the Input Data in the form of
    3. Call the use case UpdatePaymentBalanceBoundaryIn method to run the use case.
    4. Check that the Output Data passed to UpdatePaymentBalancePresenter is correct.
    5. Check that the expected changed to the data layer are there.
     */

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
            List<String> stringGroups = new ArrayList<>(User.fromString(userString).getGroups());
            return stringGroups;
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Unable to process user from database");
        }
    }

    Map<String, List<List<Object>>> getGroupInfo() throws IOException, ParseException {
        GroupDataInterface groupDsInterface = new GroupDataAccess("test");
        Map<String, List<List<Object>>> debtsList = new HashMap<>();
        // get the user from the database
        // check if the user exists
        if (!groupDsInterface.groupIdExists("0001")){
            throw new RuntimeException("Group Id does not exist");
        }
        String groupString = groupDsInterface.groupAsString("0001");

        try {
            Group group = Group.fromString(groupString);
            for (Debt allDebt : group.getPurchaseBalance().getAllDebts()) {
                List<List<Object>> lst = new ArrayList<>();
                List<Object> userOwingAndDebtValue = new ArrayList<>();
                userOwingAndDebtValue.add(allDebt.getUserOwing());
                userOwingAndDebtValue.add(allDebt.getDebtValue());
                lst.add(userOwingAndDebtValue);
                debtsList.put(allDebt.getUserOwed().getUsername(), lst);
            }
            return debtsList;
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Unable to process user from database");
        }
    }

    @Test
    void UpdatePaymentBalanceSuccess() throws IOException, ParseException {

        setUp();

        // 1. Instantiate
        UpdatePaymentBalancePresenter presenter = new UpdatePaymentBalancePresenter() {
            @Override
            public UpdatedDebts prepareSuccessView(UpdatedDebts updatedDebts) throws IOException, ParseException {
                Map<String, List<List<Object>>> groupInfoBefore = getGroupInfo();
                List<String> users = Arrays.asList("rcordi", "randomC", "sopleee");
                boolean containsUser = false;
                for(List<Object> debt : updatedDebts.getUpdatedBalances()){
                    if (debt.get(0).equals("mishaalk")) {
                        containsUser = true;
                        break;
                    }
                }
                assert containsUser;
                boolean allUpdated = false;
                for(List<Object> debt : updatedDebts.getUpdatedBalances()){
                    if (debt.get(0).equals("mishaalk")) {
                        int indexOfUserOwing = groupInfoBefore.get("mishaalk").indexOf((String) debt.get(1));
                        double previousDebt = (double) groupInfoBefore.get("mishaalk").get(indexOfUserOwing).get(1);
                        if(users.contains((String) debt.get(1))) {
                            assert (double) debt.get(3) == ((double) previousDebt) + 19.99/3;
                        }
                    }
                }
//                for (List<Object> userOwed : updatedDebts.getUpdatedBalances().get("mishaalk")) {
//                    int indexOfUserOwing = groupInfoBefore.get("mishaalk").indexOf(userOwed);
//                    Object previousDebt = groupInfoBefore.get("mishaalk").get(indexOfUserOwing).get(1);
//                    if(users.contains((String) userOwed.get(0))) {
//                        assert (double) userOwed.get(1) == ((double) previousDebt) + 19.99/3;
//                    }
//                }
                assert updatedDebts.getOutcomeMessage() == null;
                return null;
            }

            @Override
            public UpdatedDebts prepareFailView(UpdatedDebts updatedDebts) {
                fail("Use case failure is unexpected.");
                return null;
            }
        };

        GroupDataInterface groupData = new GroupDataAccess("test");
        ItemDataInterface itemData = new ItemDataAccess("test");

        // 2. Input Data - we can make this up for the test, but normally it would be created from the controller.
        PaymentInformation inputData = new PaymentInformation("grpOne11", "mishaalk", (float) 19.99,
                "itemApple", Arrays.asList("rcordi", "randomC", "sopleee"));
        UpdatePaymentBalanceBoundaryIn useCase = new UpdatePaymentBalance(groupData, itemData, presenter, inputData);

        // 3. Run the use case.
        useCase.updatePaymentBalance(inputData);

        tearDown();
    }

    @Test
    void UpdatePaymentBalanceDNE() throws IOException, ParseException {

        setUp();

        // 1. Instantiate
        UpdatePaymentBalancePresenter presenter = new UpdatePaymentBalancePresenter() {

            @Override
            public UpdatedDebts prepareSuccessView(UpdatedDebts updatedDebts) {
                fail("Use case success is unexpected.");
                return null;
            }

            @Override
            public UpdatedDebts prepareFailView(UpdatedDebts updatedDebts) {
                assert updatedDebts.getUpdatedBalances() == null;
                assert updatedDebts.getOutcomeMessage() != null;
                return null;
            }
        };

        GroupDataInterface groupData = new GroupDataAccess("test");
        ItemDataInterface itemData = new ItemDataAccess("test");

        // 2. Input Data - we can make this up for the test, but normally it would be created from the controller.
        PaymentInformation inputData = new PaymentInformation("grpOne11", "mishaalk", (float) 19.99,
                "itemApple", Arrays.asList("rcordi", "rcordi", "sopleee"));
        UpdatePaymentBalanceBoundaryIn useCase = new UpdatePaymentBalance(groupData, itemData, presenter, inputData);

        // 3. Run the use case.
        useCase.updatePaymentBalance(inputData);

        tearDown();
    }

    @Test
    void UpdatePaymentBalanceDb() throws IOException, ParseException {

        setUp();

        // 1. Instantiate
        UpdatePaymentBalancePresenter presenter = new UpdatePaymentBalancePresenter() {

            @Override
            public UpdatedDebts prepareSuccessView(UpdatedDebts updatedDebts) {
                assert updatedDebts.getOutcomeMessage() != null;
                return null;
            }

            @Override
            public UpdatedDebts prepareFailView(UpdatedDebts updatedDebts) {
                fail("Use case fail is unexpected.");
                return null;
            }
        };

        GroupDataInterface groupData = new GroupDataAccess("test");
        ItemDataInterface itemData = new ItemDataAccess("test");

        // 2. Input Data - we can make this up for the test, but normally it would be created from the controller.
        PaymentInformation inputData = new PaymentInformation("grpOne11", "mishaalk", (float) 19.99,
                "itemApple", Arrays.asList("rcordi", "randomC", "sopleee"));
        UpdatePaymentBalanceBoundaryIn useCase = new UpdatePaymentBalance(groupData, itemData, presenter, inputData);

        // We now set the data from the database as a constant to check against our use case.
        List<String> userInfoBefore = getUserInfo();
        Map<String, List<List<Object>>> groupInfoBefore = getGroupInfo();

        // 3. Run the use case.
        useCase.updatePaymentBalance(inputData);

        try {
            List<String> userInfoAfter = getUserInfo();
            assert userInfoAfter.contains("mishaalk");
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
        try {
            Map<String, List<List<Object>>> groupInfoAfter = getGroupInfo();
            for(int x = 0; x < groupInfoAfter.size(); ++x) {
                List<List<Object>> currentAfterSublist = groupInfoAfter.get("mishaalk");
                List<List<Object>> currentBeforeSublist = groupInfoBefore.get("mishaalk");

                assert currentAfterSublist.get(x).get(0).equals(currentBeforeSublist.get(x).get(0));
                if(inputData.getUsersInvolvedInPurchase().contains(currentBeforeSublist.get(x).get(0))) {
                    assert (double) currentAfterSublist.get(x).get(1) == (double) currentBeforeSublist.get(x).get(1) + 19.99/3;
                }
                else {
                    assert (double) currentAfterSublist.get(x).get(1) == (double) currentBeforeSublist.get(x).get(1);
                }
            }
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }

        tearDown();
    }

}