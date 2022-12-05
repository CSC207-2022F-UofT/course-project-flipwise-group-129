package UseCases;

import Controllers.UpdatePaymentBalanceController;
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
        Path copiedGroups = Paths.get("././src/test/resources/testgroupsCopy.json");
        Path originalPathGroups = Paths.get("././src/test/resources/testgroups.json");
        Files.copy(originalPathGroups, copiedGroups, StandardCopyOption.REPLACE_EXISTING);

        Path copiedUsers = Paths.get("././src/test/resources/testusersCopy.json");
        Path originalPathUsers = Paths.get("././src/test/resources/testusers.json");
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

    List<Debt> getGroupInfo(List<String> usersInvolvedInPurchase) throws IOException, ParseException {
        GroupDataInterface groupDsInterface = new GroupDataAccess("test");
        List<Debt> debtsList = new ArrayList<>();
        // get the user from the database
        // check if the user exists
        if (!groupDsInterface.groupIdExists("grpOne11")){
            throw new RuntimeException("Group Id does not exist");
        }
        String groupString = groupDsInterface.groupAsString("grpOne11");

        try {
            Group group = Group.fromString(groupString);
            for(String usersOwing : usersInvolvedInPurchase) {
                System.out.println(usersOwing + group.getPurchaseBalance().getDebtPair("mishaalk", usersOwing));
                if (group.getPurchaseBalance().getDebtPair("mishaalk", usersOwing) != null) {
                    debtsList.add(group.getPurchaseBalance().getDebtPair("mishaalk", usersOwing));
                }
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
            public UpdatedDebts prepareSuccessView(UpdatedDebts updatedDebts) {
                List<String> users = Arrays.asList("randomC", "sopleee");
                boolean containsOwed = false;
                for(List<Object> curDebt : updatedDebts.getUpdatedBalances()){
                    if(curDebt.get(0).equals("mishaalk")){
                        containsOwed = true;
                        break;
                    }
                }
                assert containsOwed;
//                        updatedDebts.getUpdatedBalances().containsKey("mishaalk");
//                for (List<Object> userOwed : updatedDebts.getUpdatedBalances().get("mishaalk")) {
//                    int indexOfUserOwing = groupInfoBefore.get("mishaalk").indexOf(userOwed);
//                    Object previousDebt = groupInfoBefore.get("mishaalk").get(indexOfUserOwing).get(1);
//                    if(users.contains((String) userOwed.get(0))) {
//                        assert (double) userOwed.get(1) == ((double) previousDebt) + 19.99/3;
//                    }
//                }
                assert Objects.equals(updatedDebts.getOutcomeMessage(), "Success");
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
        UpdatePaymentBalanceBoundaryIn useCase = new UpdatePaymentBalance(groupData, itemData, presenter);
        UpdatePaymentBalanceController controller = new UpdatePaymentBalanceController(useCase);

        // 3. Run the use case.

        controller.create("grpOne11", "mishaalk", 20, "itemApple", Arrays.asList("randomC", "sopleee"));

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


        UpdatePaymentBalanceBoundaryIn useCase = new UpdatePaymentBalance(groupData, itemData, presenter);
        UpdatePaymentBalanceController controller = new UpdatePaymentBalanceController(useCase);

        // 3. Run the use case.
        controller.create("grpOne11", "mishaalk", 20, "itemApple", Arrays.asList("rcordi", "rcordi", "sopleee"));

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
        PaymentInformation inputData = new PaymentInformation("grpOne11", "mishaalk", (float) 20,
                "itemApple", Arrays.asList("randomC", "sopleee"));
        UpdatePaymentBalanceBoundaryIn useCase = new UpdatePaymentBalance(groupData, itemData, presenter);
        UpdatePaymentBalanceController controller = new UpdatePaymentBalanceController(useCase);

        // We now set the data from the database as a constant to check against our use case.
        List<String> userInfoBefore = getUserInfo();
        List<Debt> groupInfoBefore = getGroupInfo(Arrays.asList("randomC", "sopleee"));

        // 3. Run the use case.
        HashMap<String, Double> prevDebts = new HashMap<String, Double>();
        for (Debt allDebt : Group.fromString(groupData.groupAsString(inputData.getGroupID())).getPurchaseBalance().getAllDebts()) {
            if (allDebt.getUserOwed().getUsername().equals("mishaalk") && inputData.getUsersInvolvedInPurchase().contains(allDebt.getUserOwing().getUsername())){
                prevDebts.put(allDebt.getUserOwing().getUsername(), allDebt.getDebtValue());
            }
        }

        controller.create("grpOne11", "mishaalk", (float) 20, "itemApple", Arrays.asList("randomC", "sopleee"));

        try {
            List<String> userInfoAfter = getUserInfo();
            for(String group : userInfoAfter) {
                Group current = Group.fromString(groupData.groupAsString(group));
                assert current.getUsers().contains("mishaalk");
            }
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
        List<Debt> debts = getGroupInfo(inputData.getUsersInvolvedInPurchase());
        for (Debt debt : debts) {
            assert !prevDebts.containsKey(debt.getUserOwing().getUsername()) || prevDebts.get(debt.getUserOwing().getUsername()) + 10.0 == debt.getDebtValue();
        }

        tearDown();
    }

}