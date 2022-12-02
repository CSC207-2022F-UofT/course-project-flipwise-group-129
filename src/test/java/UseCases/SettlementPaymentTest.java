package UseCases;

import DataAccess.GroupDataAccess;
import DataAccessInterface.GroupDataInterface;
import DataStructures.PaymentDetails;
import DataStructures.UpdatedDebts;
import Entities.Group;
import InputBoundary.SettlementBoundaryIn;
import Presenters.SettlementPresenter;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Objects;

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

class SettlementPaymentTest {
    
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

    @Test
    void executeDebtSettlement() throws IOException {

        setUp();

        GroupDataInterface groupData;
        {
            try {
                groupData = new GroupDataAccess("test");
            } catch (IOException | ParseException e) {
                throw new RuntimeException(e);
            }
        }

        SettlementPresenter presenter = new SettlementPresenter();
        SettlementBoundaryIn usecase = new SettlementPayment(presenter, groupData);

        // 2) Input data we can make this up for the test. Normally it would be created by the Controller.
        PaymentDetails inputData = new PaymentDetails("mishaalk", "randomC", "grpOne11");

        // 3) Run the use case
        UpdatedDebts outputData = usecase.executeDebtSettlement(inputData);

        // Add an assert statement or multiple to check if the output data is correct
        assert (Objects.equals(outputData.getOutcomeMessage(), "Success") && outputData.getUpdatedBalances() != null);
        
        tearDown();
    }

    @Test
    void debtSettlementFailure() throws IOException {
        
        setUp();

        GroupDataInterface groupData;
        {
            try {
                groupData = new GroupDataAccess("test");
            } catch (IOException | ParseException e) {
                throw new RuntimeException(e);
            }
        }

        SettlementPresenter presenter = new SettlementPresenter();
        SettlementBoundaryIn usecase = new SettlementPayment(presenter, groupData);

        // 2) Input data we can make this up for the test. Normally it would be created by the Controller.
        PaymentDetails inputData = new PaymentDetails("mishaalk", "userDne", "grpOne11");

        // 3) Run the use case
        UpdatedDebts outputData = usecase.executeDebtSettlement(inputData);

        // Add an assert statement or multiple to check if the output data is correct
        assert (Objects.equals(outputData.getOutcomeMessage(), "debt between selected users does not exist") && outputData.getUpdatedBalances() == null);
        
        tearDown();
    }

    @Test
    void createDebtDbCheck() throws IOException, org.json.simple.parser.ParseException {
        
        setUp();
        GroupDataInterface groupData;
        {
            try {
                groupData = new GroupDataAccess("test");
            } catch (IOException | ParseException e) {
                throw new RuntimeException(e);
            }
        }

        SettlementPresenter presenter = new SettlementPresenter();
        SettlementBoundaryIn usecase = new SettlementPayment(presenter, groupData);

        // 2) Input data we can make this up for the test. Normally it would be created by the Controller.
        PaymentDetails inputData = new PaymentDetails("mishaalk", "sopleee", "grpOne11");
        // 3) Run the use case
        usecase.executeDebtSettlement(inputData);

        // 4) Check against the resulting database for the updated debt information.
        try {
            Group groupAfter = getGroupInfo();
            assert !(groupAfter == null);
            assert (groupAfter.getGroupId().equals("grpOne11"));
            assert groupAfter.getPurchaseBalance().getDebtPair("sopleee", "mishaal").getDebtValue() == 0.0;
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
        
        tearDown();
    }

    Group getGroupInfo() throws IOException, ParseException {
        GroupDataInterface groupDsInterface = new GroupDataAccess("test");
        try {
            return Group.fromString(groupDsInterface.groupAsString("grpOne11"));
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
