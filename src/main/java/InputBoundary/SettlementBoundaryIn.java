package InputBoundary;

import DataStructures.PaymentDetails;
import DataStructures.UpdatedDebts;

public interface SettlementBoundaryIn {
    public UpdatedDebts executeDebtSettlement(PaymentDetails settlement);
}
