package UseCases;

import DataAccess.GroupDataAccess;
import DataAccessInterface.GroupDataInterface;
import DataStructures.PaymentDetails;
import DataStructures.UpdatedDebts;
import InputBoundary.SettlementBoundaryIn;
import Presenters.SettlementPresenter;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class SettlementPaymentTest {

    @Test
    void executeDebtSettlement() {
        GroupDataInterface groupData;
        {
            try {
                groupData = new GroupDataAccess();
            } catch (IOException | ParseException e) {
                throw new RuntimeException(e);
            }
        }

        SettlementPresenter presenter = new SettlementPresenter();
        SettlementBoundaryIn usecase = new SettlementPayment(presenter, groupData);



        // 2) Input data â€” we can make this up for the test. Normally it would
        // be created by the Controller.
        PaymentDetails inputData = new PaymentDetails("Codi", "Avi", "group1");

        // 3) Run the use case
        UpdatedDebts outputData = usecase.executeDebtSettlement(inputData);

        // Add an assert statement or multiple to check if the output data is correct
        assert (Objects.equals(outputData.getOutcomeMessage(), "Success") && outputData.getUpdatedBalances() != null);
    }
}