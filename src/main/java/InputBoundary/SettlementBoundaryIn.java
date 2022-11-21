package InputBoundary;

import DataStructures.PaymentDetails;
import DataStructures.UpdatedDebts;

public interface SettlementBoundaryIn {
    /**
     * updates the debts after conducting a settlement
     * @param settlement contains informaton on the two users to update debts between
     * @return the output data to be handled by the presenter
     */
    public UpdatedDebts executeDebtSettlement(PaymentDetails settlement);
}
