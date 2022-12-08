package UseCaseLayer.InputBoundary;

import UseCaseLayer.DataStructures.PaymentDetails;
import UseCaseLayer.DataStructures.UpdatedDebts;

public interface SettlementBoundaryIn {
    /**
     * updates the debts after conducting a settlement
     * @param settlement contains informaton on the two users to update debts between
     * @return the output data to be handled by the presenter
     */
    UpdatedDebts executeDebtSettlement(PaymentDetails settlement);
}
