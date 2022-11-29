package UseCases;

import DataAccess.GroupDataAccess;
import DataAccess.ItemDataAccess;
import DataAccess.UserDataAccess;
import DataAccessInterface.GroupDataInterface;
import DataAccessInterface.ItemDataInterface;

import DataAccessInterface.UserDataInterface;
import DataStructures.PaymentInformation;
import DataStructures.UpdatedDebts;
import Entities.Group;
import Entities.User;
import InputBoundary.UpdatePaymentBalanceBoundaryIn;
import Presenters.UpdatePaymentBalancePresenter;

import java.io.IOException;
import java.util.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.simple.parser.ParseException;
import static org.junit.jupiter.api.Assertions.*;
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

    @Test
    void UpdatePaymentBalanceSuccess() throws IOException, ParseException {
        // 1. Instantiate
        UpdatePaymentBalancePresenter presenter = new UpdatePaymentBalancePresenter() {
            @Override
            public UpdatedDebts prepareSuccessView(UpdatedDebts updatedDebts) {
                assert updatedDebts.getUpdatedBalances().containsKey("mishaalk");
                assert !updatedDebts.getUpdatedBalances().isEmpty();
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
    }

    @Test
    void UpdatePaymentBalanceDNE() throws IOException, ParseException {
        // 1. Instantiate
        UpdatePaymentBalancePresenter presenter = new UpdatePaymentBalancePresenter() {

            @Override
            public UpdatedDebts prepareSuccessView(UpdatedDebts updatedDebts) {
                fail("Use case success is unexpected.");
                return null;
            }

            @Override
            public UpdatedDebts prepareFailView(UpdatedDebts updatedDebts) {
                assert updatedDebts.getUpdatedBalances().isEmpty();
                assert !updatedDebts.getUpdatedBalances().containsKey("mishaalk");
                assert Objects.equals(updatedDebts.getOutcomeMessage(), "Invalid HashMap provided.");
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
    }

    @Test
    void UpdatePaymentBalanceAlreadyUpdated() throws IOException, ParseException {
        // 1. Instantiate
        UpdatePaymentBalancePresenter presenter = new UpdatePaymentBalancePresenter() {

            @Override
            public UpdatedDebts prepareSuccessView(UpdatedDebts updatedDebts) {
                fail("Use case success is unexpected.");
                return null;
            }

            @Override
            public UpdatedDebts prepareFailView(UpdatedDebts updatedDebts) {
                assert updatedDebts.getUpdatedBalances().isEmpty();
                assert !updatedDebts.getUpdatedBalances().containsKey("mishaalk");
                assert Objects.equals(updatedDebts.getOutcomeMessage(), "Debts have already been updated.");
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
    }

    List<String> getUserInfo() throws IOException, ParseException {
        UserDataInterface userDsInterface = new UserDataAccess("test");
        List<String> stringGroups = new ArrayList<>();
        // get the user from the database
        //check if the user exists
        if (!userDsInterface.userIdExists("mishaalk")){
            throw new RuntimeException("User Id does not exist");
        }
        String userString = userDsInterface.userAsString("mishaalk");

        try {
            User.fromString(userString).getGroups().forEach(group -> stringGroups.add(group));
            return stringGroups;
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Unable to process user from database");
        }
    }

    List<List<String>> getGroupInfo() throws IOException, ParseException {
        GroupDataInterface groupDsInterface = new GroupDataAccess("test");
        List<String> stringUsers = new ArrayList<>();
        List<String> stringItems = new ArrayList<>();
        List<String> stringPlanned = new ArrayList<>();
        List<String> debtsList = new ArrayList<>();
        // get the user from the database
        // check if the user exists
        if (!groupDsInterface.groupIdExists("0001")){
            throw new RuntimeException("Group Id does not exist");
        }
        String groupString = groupDsInterface.groupAsString("0001");

        try {
            Group group = Group.fromString(groupString);
            group.getPurchaseList().getItems().forEach(item -> stringItems.add(item.getItemId()));
            group.getPlanningList().getItems().forEach(item -> stringPlanned.add(item.getItemId()));
            group.getUsers().forEach(user -> stringUsers.add(user));
            group.getPurchaseBalance().getAllDebts().forEach(Debt -> debtsList.add(Debt.getDebtValue().toString()));
            List<List<String>> overall = new ArrayList<>();
            overall.add(stringItems);
            overall.add(stringPlanned);
            overall.add(stringUsers);
            overall.add(debtsList);
            return overall;
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Unable to process user from database");
        }
    }

    @Test
    void UpdatePaymentBalanceDb() throws IOException, ParseException {
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
        List<List<String>> groupInfoBefore = getGroupInfo();

        // 3. Run the use case.
        useCase.updatePaymentBalance(inputData);

        try {
            List<String> userInfoAfter = getUserInfo();
            for (String s : userInfoBefore) {
                assert userInfoAfter.contains(s);
            }
            assert userInfoAfter.contains("mishaalk");
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
        try {
            List<List<String>> groupInfoAfter = getGroupInfo();
            assert groupInfoAfter.get(0) == groupInfoBefore.get(0);
            assert groupInfoAfter.get(1).size() >= groupInfoBefore.get(1).size();
            assert groupInfoAfter.get(2) == groupInfoBefore.get(2);

            for (int x = 0; x < groupInfoAfter.size(); ++x) {
                double debtValueBefore = Double.parseDouble(groupInfoBefore.get(3).get(x));
                double debtValueAfter = Double.parseDouble(groupInfoAfter.get(3).get(x));
                assert debtValueAfter <= debtValueBefore;
            }
            assert !groupInfoAfter.get(3).equals(groupInfoBefore.get(3));
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }

    }

}