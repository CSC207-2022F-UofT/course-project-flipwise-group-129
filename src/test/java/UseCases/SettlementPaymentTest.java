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

class SettlementPaymentTest {

    @Test
    void executeDebtSettlement() {

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
    }

    @Test
    void debtSettlementFailure() {

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
    }

    @Test
    void createDebtDbCheck() throws IOException, org.json.simple.parser.ParseException {
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