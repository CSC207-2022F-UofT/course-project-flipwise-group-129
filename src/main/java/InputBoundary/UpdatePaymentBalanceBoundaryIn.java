package InputBoundary;

import DataStructures.PaymentDetails;
import DataStructures.UpdatedDebts;

public interface UpdatePaymentBalanceBoundaryIn {
    UpdatedDebts updatePaymentBalance(PaymentDetails paymentDetails);
}
